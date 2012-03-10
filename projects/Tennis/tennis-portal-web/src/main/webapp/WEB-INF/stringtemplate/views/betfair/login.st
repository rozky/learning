<html>
<head>
    <title>Home</title>

    <link rel="stylesheet" href="/static/css/login-form.css" type="text/css" media="screen" />

    <!--<script src="/static/js/lib/jquery/jquery-1.7.1.min.js"></script>-->
</head>

<body>
    <div id="container" class="formWrap">
        <form action="login" method="post">
            <ol>
                <li>
                    <label for="username">Username:</label>
                    <input id="username" type="text" name="username" value="$command.username$"/>

                    $if(validationErrors.errors.("username"))$
                        <div class="error">$validationErrors.errors.("username")$</div>
                    $endif$
                </li>
                <li>
                    <label for="password">Password:</label>
                    <input id="password" type="password" name="password" value="$command.password$"/>

                    $if(validationErrors.errors.("password"))$
                        <div class="error">$validationErrors.errors.("password")$</div>
                    $endif$
                </li>
                <li>
                    <button>
                        <span>Login</span>
                    </button>
                </li>
            </ol>
        </form>
    </div>
</body>
</html>