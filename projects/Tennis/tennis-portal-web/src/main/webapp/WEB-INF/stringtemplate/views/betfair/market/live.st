<html>
<head>
    <title>Home</title>

    <link rel="stylesheet" href="/static/css/login-form.css" type="text/css" media="screen" />

    <!--<script src="/static/js/lib/jquery/jquery-1.7.1.min.js"></script>-->
</head>

<body>
    <div id="container">
        <h1>Markets ($marketsCount$)</h1>
        <ol>
            $markets:{
                <li>
                    <div>$it.menuPath$</div>
                    <div style="">$it.marketName$</div>
                    <div style="width: 25%; float: left;">$it.marketType$</div>
                    <div style="width: 25%; float: left;">$it.betDelay$</div>
                </li>
            }$
        </ol>
    </div>
</body>
</html>