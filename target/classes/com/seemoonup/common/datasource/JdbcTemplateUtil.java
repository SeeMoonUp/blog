package com.seemoonup.common.datasource;

import com.google.common.collect.Maps;
import com.seemoonup.common.spring.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Iterator;
import java.util.Map;

/**
 * @authou aupdatebylemon
 * @date 2016/6/26
 * @desc
 */
public class JdbcTemplateUtil {
    private static final Logger GCW_LOG = LoggerFactory.getLogger(JdbcTemplateUtil.class.getName());
    private static Map<String, JdbcTemplate> jdbcMap = Maps.newConcurrentMap();
    private static Map<String, Long> expireTimeMap = Maps.newConcurrentMap();
    private final static Long DEFAULT_EXPIRE_TIME = 1000L;
    private final static int DEFAULT_MIX_SIZE = 1000;

    public static JdbcTemplate getTemplate(String key) {
        JdbcTemplate jdbcTemplate = jdbcMap.get(key);
        if (jdbcTemplate == null) {
            jdbcTemplate = SpringContextHolder.getBean("jdbcTemplate");
            putTemplate(key, jdbcTemplate);
        }
        if (jdbcMap.size() > DEFAULT_MIX_SIZE) {
            GCW_LOG.info("达到最大size：{}，开始清除过期数据",DEFAULT_MIX_SIZE);
            clearMap();
        }
        return jdbcTemplate;
    }

    public static void putTemplate(String key, JdbcTemplate jdbcTemplate) {
        putTemplate(key, jdbcTemplate, DEFAULT_EXPIRE_TIME);
    }

    public static void putTemplate(String key, JdbcTemplate jdbcTemplate, Long expire) {
        jdbcMap.put(key, jdbcTemplate);
        addExpireTimeMap(key, expire);
    }

    private static void addExpireTimeMap(String key, Long expire) {
        expireTimeMap.put(key, expire * 1000 + System.currentTimeMillis());
    }

    private static boolean isExpired(String key) {
        Long expireTime = expireTimeMap.get(key);
        if (null == expireTime) {
            GCW_LOG.debug("不存在于缓存过期时间map中 key=" + key);
            return true;
        }

        long now = System.currentTimeMillis();
        if (now >= expireTime) {
            GCW_LOG.info("缓存已过期 key=" + key);
            return true;
        }

        return false;
    }

    public static void clearMap() {
        try {
            for (Iterator<String> itr = expireTimeMap.keySet().iterator(); itr.hasNext();) {
                String key = itr.next();
                if (isExpired(key)) {
                    jdbcMap.remove(key);
                    itr.remove(); // 删除expire
                }
            }
            GCW_LOG.info("清除map结束");
        } catch (Exception e) {
            GCW_LOG.error("清除map错误", e);
        }
    }

}
