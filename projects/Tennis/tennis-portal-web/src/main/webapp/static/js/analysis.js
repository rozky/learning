function drawVisualization() {
  var data = google.visualization.arrayToDataTable(
          [ ["Date", "Won Amount", "Lost Amount"],
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
          textPosition: "out"
      },
      vAxis: {
          textStyle: {color: "#666"},
          textPosition: "in"
      },
      isStacked: true,
      legend: {position: "top"},
      lineWidth: 1,
      width: "100%",
      pointSize: 3
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