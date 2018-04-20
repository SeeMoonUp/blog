<html>
<body>
<h2>Hello World! </h2>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://cdn.bootcss.com/showdown/1.3.0/showdown.min.js"></script>
<div>
    <textarea id="content"></textarea>
    <textarea id="result"></textarea>
</div>
<div id="viewHtml">

</div>
<script>
    function compile(){
        var text = document.getElementById("content").value;
        var converter = new showdown.Converter();
        var html = converter.makeHtml(text);
        document.getElementById("result").innerHTML = html;
        document.getElementById("viewHtml").innerHTML = html;
    }
</script>
</body>
</html>
