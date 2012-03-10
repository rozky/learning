<html>
<head>
    <title>Home</title>
    <link media="screen,projection" type="text/css" href="/static/css/lib" rel="Stylesheet">

    <!-- 1140px Grid styles for IE -->
	<!--[if lte IE 9]><link rel="stylesheet" href="/static/css/lib/1140grid/ie.css" type="text/css" media="screen" /><![endif]-->

	<!-- The 1140px Grid - http://cssgrid.net/ -->
	<link rel="stylesheet" href="/static/css/lib/1140grid/1140.css" type="text/css" media="screen" />

    <script src="/static/js/lib/jquery/jquery-1.7.1.min.js"></script>
</head>

<body id="home">
    <h1>$welcome$</h1>
    <div id="container">
        <div id="header"></div>

        <div id="content">
            <div id="navigation"></div>

            <div id="middle">
				<div class="sectionHeader"></div>
				<div class="sectionArticle"></div>
				<div class="sectionFooter"></div>
			</div>

 			<div id="aside">
                 $if(accountStatementItems)$
                    <ul>
                        $accountStatementItems:{<li>$it.marketName$</li>}$
                     </ul>
                 $else$
                    <h1>empty</h1>
                 $endif$
 			</div>
        </div>

        <div id="footer"></duv>
    </div>
</body>
</html>