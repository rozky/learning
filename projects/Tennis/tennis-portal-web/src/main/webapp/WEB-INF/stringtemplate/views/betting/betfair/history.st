<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Bootstrap, from Twitter</title>

    <!-- Bootstrap -->
    <link href="/static/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="/static/css/bootstrap.docs.css" rel="stylesheet" media="screen">
</head>

<body>
<div class="navbar">
	<div class="navbar-inner">
		<div class="container-fluid">
			<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</a>
			<a class="brand" href="#" name="top">Brand Name</a>
			<div class="nav-collapse collapse">
				<ul class="nav">
					<li><a href="#"><i class="icon-home"></i> Home</a></li>
					<li class="divider-vertical"></li>
					<li class="active"><a href="#"><i class="icon-file"></i> Pages</a></li>
					<li class="divider-vertical"></li>
					<li><a href="#"><i class="icon-envelope"></i> Messages</a></li>
					<li class="divider-vertical"></li>
                  	<li><a href="#"><i class="icon-signal"></i> Stats</a></li>
					<li class="divider-vertical"></li>
					<li><a href="#"><i class="icon-lock"></i> Permissions</a></li>
					<li class="divider-vertical"></li>
				</ul>
				<div class="btn-group pull-right">
					<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
						<i class="icon-user"></i> admin	<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="#"><i class="icon-wrench"></i> Settings</a></li>
						<li class="divider"></li>
						<li><a href="#"><i class="icon-share"></i> Logout</a></li>
					</ul>
				</div>
			</div>
			<!--/.nav-collapse -->
		</div>
		<!--/.container-fluid -->
	</div>
	<!--/.navbar-inner -->
</div>
<!--/.navbar -->

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span3 bs-docs-sidebar " >
            <!--<label>Textbox with calendar icon</label>-->
            <div class="input-append"><input type="text" id="" name=""><span class="add-on"><i class="icon-calendar"></i></span></div>
            <div class="input-append"><input type="text" id="" name=""><span class="add-on"><i class="icon-calendar"></i></span></div>

            <ul class="nav nav-list bs-docs-sidenav" data-spy="affix" data-offset-top="10">
                $betHistory.keys:{
                    <li><a href="#$it;format="snakecase"$"><i class="icon-chevron-right"></i>$it$</a></li>
                }$
            </ul>
        </div>
        <div class="span9">
            $betHistory.keys:{
                <section id="$it;format="snakecase"$">
                <div class="page-header">
                    <h1>$it$</h1>
                </div>
                $betHistory.(it):{item|
                    <div class="row show-grid">
                        <!--<div class="span5">$item.fullMarketName$</div>-->
                        <div class="span4">$item.settledAt$</div>
                        <div class="span2">$item.stake$</div>
                        <div class="span2">$item.winLost$</div>
                        <div class="span2">$item.accountBalance$</div>
                    </div>}$
                </section>
            }$
        </div>
    </div>

</div>



<!-- Le javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="http://code.jquery.com/jquery.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<!--<script src="../assets/js/bootstrap-transition.js"></script>-->
<!--<script src="../assets/js/bootstrap-alert.js"></script>-->
<!--<script src="../assets/js/bootstrap-modal.js"></script>-->
<!--<script src="../assets/js/bootstrap-dropdown.js"></script>-->
<!--<script src="../assets/js/bootstrap-scrollspy.js"></script>-->
<!--<script src="../assets/js/bootstrap-tab.js"></script>-->
<!--<script src="../assets/js/bootstrap-tooltip.js"></script>-->
<!--<script src="../assets/js/bootstrap-popover.js"></script>-->
<!--<script src="../assets/js/bootstrap-button.js"></script>-->
<!--<script src="../assets/js/bootstrap-collapse.js"></script>-->
<!--<script src="../assets/js/bootstrap-carousel.js"></script>-->
<!--<script src="../assets/js/bootstrap-typeahead.js"></script>-->

<!--<script type="text/javascript" src="https://www.google.com/jsapi"></script>-->
<!--<script type="text/javascript">-->
  <!--google.load('visualization', '1', {packages: ['corechart']});-->
<!--</script>-->
<!--<script type="text/javascript">-->
  <!--function drawVisualization() {-->
    <!--var data = google.visualization.arrayToDataTable([-->
    <!--$candles:{candle|-->
            <!--['$candle.name$', $candle.start$, $candle.start$, $candle.end$, $candle.end$],-->
        <!--}$-->
    <!--], true);-->

    <!--var options = {-->
      <!--legend:'none'-->
    <!--};-->

    <!--// var chart = new google.visualization.CandlestickChart(document.getElementById('candelstick_charts'));-->
    <!--var chart = new google.visualization.CandlestickChart(document.getElementById('chart_div'));-->
    <!--console.log("fff");-->
    <!--chart.draw(data, options);-->
  <!--}-->

  <!--google.setOnLoadCallback(drawVisualization);-->
<!--</script>-->

</body>
</html>
