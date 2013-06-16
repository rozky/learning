<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap, from Twitter</title>
</head>

<body>

<div class="row-fluid">
    <div class="span3 bs-docs-sidebar " >
        <ul class="nav nav-list bs-docs-sidenav" data-spy="affix" data-offset-top="40">
            <li><a href="#won_lost_graph"><i class="icon-chevron-right"></i>Won vs Lost Chart</a></li>
            <li><a href="#profit_graph"><i class="icon-chevron-right"></i>Profit Per Day Chart</a></li>
            <li><a href="#profit_evolution_chart"><i class="icon-chevron-right"></i>Profit Evolution</a></li>
            <li><a href="#days_in_profit_evolution_chart"><i class="icon-chevron-right"></i>Won / Lost Rate</a></li>
            <li><a href="#roi_chart"><i class="icon-chevron-right"></i>Return Of Investment</a></li>
            <li><a href="#data_table"><i class="icon-chevron-right"></i>Data Table</a></li>
        </ul>
    </div>
    <div class="span9">
        <label>Date Range</label>
        <div class="input-append"><input type="text" id="datarange" name=""><span class="add-on"><i class="icon-calendar"></i></span></div>

        <p>Profit W/W: $marketBets.totalProfitForWinningInitialBet;format="doubleScale2"$</p>
        <p>Lost W/L: $marketBets.totalLostForWinningInitialBet;format="doubleScale2"$</p>
        <p>Profit L/W: $marketBets.totalProfitForLosingInitialBet;format="doubleScale2"$</p>
        <p>Lost L/L: $marketBets.totalLostForLosingInitialBet;format="doubleScale2"$</p>
        <p><strong>Bet Count For Winning 1st Bet: $marketBets.betCountForWinningFirstBet$</strong></p>
        <p>Bet Count For Losing 1st Bet: $marketBets.betCountForLosingFirstBet$</p>
        <p>Winning Bet Count: $marketBets.winningBetCount$</p>
        <p>Losing Bet Count: $marketBets.losingBetCount$</p>

        <section id="profit_evolution_chart">
        <h4>Profit By Bet Count</h4>
        <div id="profit_by_bet_count_chart_div"></div>

        <section id="count_by_bet_count_chart">
        <h4>Market Count By Bet Count</h4>
        <div id="count_by_bet_count_chart_div"></div>

            request: $servlet$

        <section id="data_table">
        <h4>Data Table</h4>
        <section id="$it;format="snakecase"$">
        <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th></th>
                    <th>Date</th>
                    <th>Market Name</th>
                    <th>Bet Count</th>
                    <th>Profit</th>
                </tr>
            </thead>
            <tbody>
                $marketBets.items:{
                    <tr>
                        <td>$it.eventId$</td>
                        <td>$it.date;format="date:short"$</td>
                        <td>$it.marketName$</td>
                        <td>$it.betCount$</td>
                        <td>$it.profit;format="doubleScale2"$</td>
                    </tr>
                }$
            </tbody>
        </table>
    </div>
</div>




<!-- Le javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="http://code.jquery.com/jquery.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="/static/js/moment.min.js"></script>
<script src="/static/js/daterangepicker.js"></script>
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

<script type="text/javascript">
    jQuery().ready(function() {
        jQuery("#datarange").daterangepicker()
    });
</script>



<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
  google.load('visualization', '1', {packages: ['corechart']});
</script>
<script type="text/javascript">
  function drawVisualization() {


          var options = {
              height: 300,
              areaOpacity: 1,
              chartArea: {width: "97%"},
              colors: ["#08C", "#DF013A" ,"#999"],
              hAxis: {
                  textStyle: {color: "#666"},
                  showTextEvery: 1,
                  textPosition: "out",
              },
              vAxis: {
                  textStyle: {color: "#666"},
                  textPosition: "in"
              },
              isStacked: true,
              legend: {position: "top"},
              lineWidth: 1,
              width: "100%",
              pointSize: 3,
          };

      var profitByBetCountData = google.visualization.arrayToDataTable([
                        ["Date", "Profit"],
                        $marketBets.profitByBetCount.keys:{['$it$', $marketBets.profitByBetCount.(it)$],}$
                ]);
          var profitByBetCountChart = new google.visualization.ColumnChart(document.getElementById('profit_by_bet_count_chart_div'));
      profitByBetCountChart.draw(profitByBetCountData, options);

      var countByBetCountData = google.visualization.arrayToDataTable([
                              ["Date", "Count"],
                              $marketBets.countByBetCount.keys:{['$it$', $marketBets.countByBetCount.(it)$],}$
                      ]);
                var countByBetCountChart = new google.visualization.ColumnChart(document.getElementById('count_by_bet_count_chart_div'));
            countByBetCountChart.draw(countByBetCountData, options);
  }

  google.setOnLoadCallback(drawVisualization);
</script>

</body>
</html>
