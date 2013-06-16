<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Simple | Stats</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="/static/css/bootstrap.css" rel="stylesheet">
    <link href="/static/css/mobile.css" rel="stylesheet">
    <style>
        body {
        padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
        }
    </style>
    <link href="/static/css/bootstrap-responsive.css" rel="stylesheet">

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="../assets/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="../assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="../assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="../assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="../assets/ico/apple-touch-icon-57-precomposed.png">
</head>

<body>

<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="#">Simple</a>

            <div class="nav-collapse">
                <ul class="nav">
                    <li class="active"><a href="#">Stats</a></li>
                    <li><a href="#about">Replicate</a></li>
                </ul>
            </div>
            <!--/.nav-collapse -->
        </div>
    </div>
</div>

<!--<div id="chart_div" style="width: 900px; height: 500px;"></div>-->
<div id="chart_div" class="container">
</div>

<div class="container">
    <table class="table table-striped">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Value</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Profit</td>
                    <td>$summary.profit$</td>
                </tr>
                <tr>
                    <td>Deposits</td>
                    <td>$summary.deposits$</td>
                </tr>

                <tr>
                    <td>Withdrawals</td>
                    <td>$summary.withdrawals$</td>
                </tr>

                <tr>
                    <td>Winning Days Count</td>
                    <td>$summary.winningDaysCount$</td>
                </tr>

                <tr>
                    <td>Losing Days Count</td>
                    <td>$summary.losingDaysCount$</td>
                </tr>

            </tbody>
        </table>

    <table class="table table-striped">
        <thead>
            <tr>
                <th></th>
                <th>Day</th>
                <th>Profit</th>
                <th>Start</th>
                <th>End</th>
                <th>In</th>
                <th>Out</th>
                <th>Count</th>
                <th>Balance</th>
            </tr>
        </thead>
        <tbody>
            $statements:{statement|
            <tr>
                <td>$i$</td>
                <td>$statement.value.day;format="dd/MM/yyyy"$</td>
                <td>$statement.value.profit$</td>
                <td>$statement.value.startBalance$</td>
                <td>$statement.value.endBalance$</td>
                <td>$statement.value.deposit$</td>
                <td>$statement.value.withdrawal$</td>
                <td>$statement.value.itemsCount$</td>
                <td>$statement.value.balance$</td>
            </tr>
            }$
        </tbody>
    </table>
</div>
<!-- /container -->

<!-- Le javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="/static/js/lib/jquery/jquery-1.7.1.min.js"></script>
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

<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
  google.load('visualization', '1', {packages: ['corechart']});
</script>
<script type="text/javascript">
  function drawVisualization() {
    var data = google.visualization.arrayToDataTable([
    $candles:{candle|
            ['$candle.name$', $candle.start$, $candle.start$, $candle.end$, $candle.end$],
        }$
    ], true);

    var options = {
      legend:'none'
    };

    // var chart = new google.visualization.CandlestickChart(document.getElementById('candelstick_charts'));
    var chart = new google.visualization.CandlestickChart(document.getElementById('chart_div'));
    console.log("fff");
    chart.draw(data, options);
  }

  google.setOnLoadCallback(drawVisualization);
</script>

</body>
</html>
