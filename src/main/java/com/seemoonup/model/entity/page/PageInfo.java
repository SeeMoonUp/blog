/**
 * 
 */
package com.seemoonup.model.entity.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fupdatebylemon(fermi@youleyu.com)
 * @date May 22, 2014
 * @desc
 */
public class PageInfo {
	
	private boolean ifShowPrevious = false;		//是否显示上一页
	private boolean ifShowNext = false;		//是否显示下一页
	private int showPageCount = 5;			//显示的页码数，得是奇数
	private long recordCount = 0;				//记录总数
	private long pageCount = 0;					//页码总数
	private List<Long> showPages = new ArrayList<Long>();		//显示的页码
	private boolean ifShowLastPage = false;						//是否显示尾页
	private boolean ifShowFirstPage = false;					//是否显示首页
	private int start = 0;
	private int page = 1;
	private int limit = 10;
	private Map<String, Object> params = new HashMap<String, Object>();

	public void putParam(String name, Object value) {
		params.put(name, value);
	}
	public Map<String, Object> getParams() {
		return this.params;
	}
	public void prepareInfo() {
		if (page <= 0) {
			page = 1;
		}
//		if (recordCount < limit) {
//			recordCount = limit;
//		}
		pageCount = (recordCount % limit) == 0 ? recordCount / limit : (recordCount / limit) + 1;

		if (pageCount == 0) {
			pageCount = 1;
		}

		if (page > pageCount) {
			page = (int)pageCount;
		}

		start = limit * (page - 1);
		if (page > (showPageCount / 2 + 1)) {
			ifShowPrevious = true;
		}
		if ((pageCount - page) > (showPageCount / 2)) {
			ifShowNext = true;
		}
		long beginPage = 1;
		if (pageCount <= showPageCount) {
			showPages.add(beginPage);
			for (int j = 1; j < pageCount; j++) {
				beginPage++;
				showPages.add(beginPage);
			}
		} else {
			if (page >= (showPageCount/2 + 1) && (pageCount - page) > (showPageCount / 2)) {
				beginPage = page - (showPageCount / 2);
			} else if ((pageCount - page) <= (showPageCount / 2)) {
				beginPage = pageCount - showPageCount + 1;
			}
			showPages.add(beginPage);
			for (int j = 1; j < showPageCount; j++) {
				beginPage++;
				showPages.add(beginPage);
			}
		}
		
		//ifShowFirstPage
		if (showPages.size() > 0 && showPages.get(0) > 1) {
			setIfShowFirstPage(true);
		}
		
		//ifShowLastPage
		if (showPages.size() > 0 && pageCount > showPageCount) {
			if (showPages.get(showPages.size() - 1) < pageCount) {
				setIfShowLastPage(true);
			}
		}
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		if (page <= 0) {
			this.page = 1;
		} else {
			this.page = page;
		}
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public boolean isIfShowPrevious() {
		return ifShowPrevious;
	}
	public void setIfShowPrevious(boolean ifShowPrevious) {
		this.ifShowPrevious = ifShowPrevious;
	}
	public boolean isIfShowNext() {
		return ifShowNext;
	}
	public void setIfShowNext(boolean ifShowNext) {
		this.ifShowNext = ifShowNext;
	}
	public int getShowPageCount() {
		return showPageCount;
	}
	public void setShowPageCount(int showPageCount) {
		this.showPageCount = showPageCount;
	}
	public List<Long> getShowPages() {
		return showPages;
	}
	public void setShowPages(List<Long> showPages) {
		this.showPages = showPages;
	}
	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	public long getPageCount() {
		return pageCount;
	}
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	public static void main(String[] args) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setRecordCount(4);
		pageInfo.setLimit(4);
		pageInfo.setPage(1);
		pageInfo.prepareInfo();
		System.out.println(pageInfo.getStart());
		System.out.println(pageInfo.getLimit());
		System.out.println(pageInfo.getPageCount());
		System.out.println(pageInfo.getShowPages());
		System.out.println(pageInfo.isIfShowPrevious());
		System.out.println(pageInfo.isIfShowNext());
	}
	public boolean isIfShowFirstPage() {
		return ifShowFirstPage;
	}
	public void setIfShowFirstPage(boolean ifShowFirstPage) {
		this.ifShowFirstPage = ifShowFirstPage;
	}
	public boolean isIfShowLastPage() {
		return ifShowLastPage;
	}
	public void setIfShowLastPage(boolean ifShowLastPage) {
		this.ifShowLastPage = ifShowLastPage;
	}
}
