<!DOCTYPE html>
<html lang="en">
<head>
    <title>Betfair Analytics</title>
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

        <section id="won_lost_graph">
        <h4>Win Amount vs Lost Amount</h4>
        <div id="win_lost_chart_div"></div>
        <div class="row-fluid">
            <div class="span2 well well-small well-blue">
                <div class="text-center"><strong>$analysis.wonAmountTotal;format="doubleScale2"$</strong></div>
                <div class="text-center"><small>Total Won Amount</small></div>
            </div>
            <div class="span2 well well-small">
                <div class="text-center"><strong>$analysis.lostAmountTotal;format="doubleScale2"$</strong></div>
                <div class="text-center"><small>Total Lost Amount</small></div>
            </div>
            <div class="span2 well well-small">
                <div class="text-center"><strong>$analysis.commissionAmountTotal;format="doubleScale2"$</strong></div>
                <div class="text-center"><small>Total Commission</small></div>
            </div>
            <div class="span2 well well-small">
                <div class="text-center"><strong>$analysis.stakeAmountTotal;format="doubleScale2"$</strong></div>
                <div class="text-center"><small>Total Stake Amount</small></div>
            </div>
            <div class="span2 well well-small">
                <div class="text-center"><strong>$analysis.wonAmountMax;format="doubleScale2"$</strong></div>
                <div class="text-center"><small>Max Won</small></div>
            </div>
            <div class="span2 well well-small">
                <div class="text-center"><strong>$analysis.lostAmountMax;format="doubleScale2"$</strong></div>
                <div class="text-center"><small>Max Lost</small></div>
            </div>
        </div>

        <section id="profit_graph">
        <h4>Profit Per Day</h4>
        <div id="profit_chart_div"></div>
        <div class="row-fluid">
            <div class="span2 well well-small">
                <div class="text-center"><strong>$analysis.profitTotal;format="doubleScale2"$</strong></div>
                <div class="text-center"><small>Profit Total</small></div>
            </div>
            <div class="span2 well well-small">
                <div class="text-center"><strong>$analysis.returnOfInvestment;format="doubleScale2"$</strong></div>
                <div class="text-center"><small>ROI</small></div>
            </div>
            <div class="span2 well well-small">
                <div class="text-center"><strong>$analysis.daysInProfit;format="doubleScale2"$</strong></div>
                <div class="text-center"><small>Days In Profit</small></div>
            </div>
            <div class="span2 well well-small">
                <div class="text-center"><strong>$analysis.daysInLost;format="doubleScale2"$</strong></div>
                <div class="text-center"><small>Days In Lost</small></div>
            </div>
            <div class="span2 well well-small">
                <div class="text-center"><strong>$analysis.profitMax;format="doubleScale2"$</strong></div>
                <div class="text-center"><small>Max Profit</small></div>
            </div>
            <div class="span2 well well-small">
                <div class="text-center"><strong>$analysis.lostMax;format="doubleScale2"$</strong></div>
                <div class="text-center"><small>Max Lost</small></div>
            </div>
        </div>


        <section id="profit_evolution_chart">
        <h4>Profit Evolution</h4>
        <div id="profit_evolution_chart_div"></div>

        <section id="days_in_profit_evolution_chart">
        <h4>Days In Profit Evolution</h4>
        <div id="days_in_profit_evolution_chart_div"></div>

        <section id="roi_chart">
        <h4>Return of Investment</h4>
        <div id="roi_chart_div"></div>

        <section id="data_table">
        <h4>Data Table</h4>
        <section id="$it;format="snakecase"$">
        <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th></th>
                    <th>Date</th>
                    <th>Won</th>
                    <th>Lost</th>
                    <th>Profit</th>
                    <th>Commission</th>
                    <th>Stake</th>
                    <th>Bet Count</th>
                </tr>
            </thead>
            <tbody>
                $analysis.items:{
                    <tr>
                        <td>$i$</td>
                        <td>$it.date;format="date:short"$</td>
                        <td>$it.won;format="doubleScale2"$</td>
                        <td>$it.lost;format="doubleScale2"$</td>
                        <td>$it.profit;format="doubleScale2"$</td>
                        <td>$it.commission;format="doubleScale2"$</td>
                        <td>$it.stake;format="doubleScale2"$</td>
                        <td>$it.betCount$</td>
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
    var data = google.visualization.arrayToDataTable([
            ["Date", "Won Amount", "Lost Amount"],
            $analysis.items:{['$it.date;format="date:short"$', $it.won$, $it.lost$],}$
    ]);

    var options = {
        height: 300,
        areaOpacity: 1,
        chartArea: {width: "97%"},
        colors: ["#08C", "#DF013A" ,"#999"],
        hAxis: {
            textStyle: {color: "#666"},
            showTextEvery: 10,
            textPosition: "none",
        },
        vAxis: {
            textStyle: {color: "#666"},
            textPosition: "in"
        },
        bar: {
            groupWidth: "90%"
        },
        isStacked: true,
        legend: {position: "top"},
        lineWidth: 1,
        width: "100%",
        pointSize: 3,
    };

    var wonLostChart = new google.visualization.ColumnChart(document.getElementById('win_lost_chart_div'));
    wonLostChart.draw(data, options);

      var profitChartData = google.visualization.arrayToDataTable([
                  ["Date", "Profit"],
                  $analysis.items:{['$it.date;format="date:short"$', $it.profit$],}$
          ]);
      var profitChart = new google.visualization.ColumnChart(document.getElementById('profit_chart_div'));
      profitChart.draw(profitChartData, options);

      var profitEvolutionChartData = google.visualization.arrayToDataTable([
                      ["Date", "Profit"],
                      $analysis.profitEvolution:{['$it.key;format="date:short"$', $it.value$],}$
              ]);
      var profitEvolutionChart = new google.visualization.AreaChart(document.getElementById('profit_evolution_chart_div'));
      profitEvolutionChart.draw(profitEvolutionChartData, jQuery.extend({}, options, {areaOpacity: 0.2}));

      var daysInProfitEvolutionChartData = google.visualization.arrayToDataTable([
                        ["Date", "Profit"],
                        $analysis.daysInProfitEvolution:{['$it.key;format="date:short"$', $it.value$],}$
                ]);
        var daysInProfitEvolutionChart = new google.visualization.AreaChart(document.getElementById('days_in_profit_evolution_chart_div'));
      daysInProfitEvolutionChart.draw(daysInProfitEvolutionChartData, jQuery.extend({}, options, {areaOpacity: 0.2}));

      var roiData = google.visualization.arrayToDataTable([
                      ["Date", "Return Of Investment"],
                      $analysis.items:{['$it.date;format="date:short"$', $it.returnOfInvestment;format="percent"$],}$
              ]);
      var roiChart = new google.visualization.ColumnChart(document.getElementById('roi_chart_div'));
      roiChart.draw(roiData, jQuery.extend({}, options, {areaOpacity: 0.2}));

    }

  google.setOnLoadCallback(drawVisualization);
</script>

</body>
</html>
