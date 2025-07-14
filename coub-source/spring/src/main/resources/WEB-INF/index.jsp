<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<body>
<h2>Hello World!</h2>
<button ><a href="/returnSuccess" target="_blank">returnSuccess</a></button>
<button ><a href="/returnString" target="_blank">returnString</a></button>
<button id="clickId">PostRequest</button>
<button ><a href="/clickGet/2/Jef">GetRequest</a></button>
</body>
<script>
    $( "#clickId" ).click(function () {
        var a = 1;
        $.post("/clickPost", {id: a}, function (data) {

        }, "json");
    });
</script>
</html>