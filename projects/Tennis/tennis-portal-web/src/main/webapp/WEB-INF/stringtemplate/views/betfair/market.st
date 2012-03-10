<html>
<head>
    <title>Home</title>

    <link rel="stylesheet" href="/static/css/login-form.css" type="text/css" media="screen" />

    <!--<script src="/static/js/lib/jquery/jquery-1.7.1.min.js"></script>-->
</head>

<body>
    <div id="container">
        <h1>Markets</h1>
        <ol>
            $markets.keys:{key|
                <li>
                    <div style="padding: 5px; border: 1px solid black; margin: 5px 0px">
                        <h1>$key$</h1>
                        <ol>
                            $markets.(key):{
                                <li style="padding: 5px;">
                                    $it.marketName$
                                </li>
                            }$
                        </ol>

                    </div>
                </li>
            }$
        </ol>
    </div>
</body>
</html>