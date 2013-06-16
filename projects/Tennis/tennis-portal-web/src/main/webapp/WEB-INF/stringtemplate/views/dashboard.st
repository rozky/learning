<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap, from Twitter</title>

    <style>
        .image {
            position: relative;
            /*width: 100%; *//* for IE 6 */
        }

        h2 {
            position: absolute;
            top: 70px;
            left: 4px;
            width: 100%;
        }

        h2 span {
            color: white;
            font: bold 0.4em/1 Helvetica, Sans-Serif;
            letter-spacing: -1px;
            background: rgb(0, 0, 0); /* fallback color */
            background: rgba(0, 0, 0, 0.7);
            padding: 10px;
        }
    </style>
</head>

<body>
<div class="row-fluid">
    <div class="span2 well well-small bgblue">
        <div class="text-center"><strong>£300</strong></div>
        <div class="text-center">
            <small>Recommended Bet Size</small>
        </div>
    </div>
</div>

<h4>Today / Last Day</h4>
<div class="row-fluid">
    <div class="span2 well well-small bgblue">
        <div class="text-center"><strong>$statsByDate.latestDayStats.betCount$ / $statsByDate.latestDayStats.wonBetCount$ / $statsByDate.latestDayStats.lostBetCount$</strong></div>
        <div class="text-center">
            <small>Total / Win / Lost Bet Count</small>
        </div>
    </div>
    <div class="span2 well well-small bgblue">
        <div class="text-center"><strong>$statsByDate.totalDays$ / $statsByDate.profitableDays$ / $statsByDate.daysInLost$</strong></div>
        <div class="text-center">
            <small>Total / Win / Lost / Days</small>
        </div>
    </div>
    <div class="span2 well well-small bgblue">
        <div class="text-center"><strong>$latestDayMarketStats.marketCount$ / $latestDayMarketStats.winningMarketCount$ / $latestDayMarketStats.losingMarketCount$</strong></div>
        <div class="text-center">
            <small>Total / Win / Lost Market</small>
        </div>
    </div>
    <div class="span2 well well-small bgblue">
        <div class="text-center"><strong>$statsByDate.latestDayStats.stake;format="doubleScale2"$</strong></div>
        <div class="text-center">
            <small>Stake</small>
        </div>
    </div>
    <div class="span2 well well-small bgblue">
        <div class="text-center"><strong>$statsByDate.latestDayStats.profit;format="doubleScale2"$</strong></div>
        <div class="text-center">
            <small>Profit</small>
        </div>
    </div>
    <div class="span2 well well-small bgblue">
        <div class="text-center"><strong>$statsByDate.latestDayStats.returnOfInvestment;format="percent"$</strong></div>
        <div class="text-center">
            <small>ROI</small>
        </div>
    </div>
</div>


<h4>Last 7 days</h4>
<div class="row-fluid">
    <div class="span2 well well-small bgblue">
        <div class="text-center"><strong>$statsByDate.betCount$ / $statsByDate.wonBetCount$ / $statsByDate.lostBetCount$</strong></div>
        <div class="text-center">
            <small>Total / Win / Lost Bet Count</small>
        </div>
    </div>
    <div class="span2 well well-small bgblue">
        <div class="text-center"><strong>$statsByDate.totalDays$ / $statsByDate.profitableDays$ / $statsByDate.daysInLost$</strong></div>
        <div class="text-center">
            <small>Total / Win / Lost Days</small>
        </div>
    </div>
    <div class="span2 well well-small bgblue">
        <div class="text-center"><strong>$statsByMarket.marketCount$ / $statsByMarket.winningMarketCount$ / $statsByMarket.losingMarketCount$</strong></div>
        <div class="text-center">
            <small>Total / Win / Lost Market</small>
        </div>
    </div>
    <div class="span2 well well-small bgblue">
        <div class="text-center"><strong>$statsByDate.stakeAmountTotal;format="doubleScale2"$</strong></div>
        <div class="text-center">
            <small>Stake</small>
        </div>
    </div>
    <div class="span2 well well-small bgblue">
        <div class="text-center"><strong>$statsByDate.profitTotal;format="doubleScale2"$</strong></div>
        <div class="text-center">
            <small>Profit</small>
        </div>
    </div>
    <div class="span2 well well-small bgblue">
        <div class="text-center"><strong>$statsByDate.returnOfInvestment;format="percent"$</strong></div>
        <div class="text-center">
            <small>ROI</small>
        </div>
    </div>
</div>

<h4>Open Markets</h4>
<div class="row-fluid">
    <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered">
        <thead>
        <tr>
            <th></th>
            <th>Date</th>
            <th>Market</th>
            <th>Final Selection</th>
            <th>Bet Count</th>
            <th>Stake</th>
            <th>Profit</th>
            <th>ROI</th>
            <th>Comment</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        $latestDayMarketStats.items:{
            <tr>
                <td>1</td>
                <td>$it.date;format="date:short"$</td>
                <td>$it.marketName$</td>
                <td>$it.finalSelectionName$</td>
                <td>$it.betCount$</td>
                <td>$it.totalStake;format="doubleScale2"$</td>
                <td>$it.profit;format="doubleScale2"$</td>
                <td>$it.returnOfInvestment;format="percent"$</td>
                <td>Good match with no surprises</td>
                <td>
                    <a href="user.html"><i class="icon-pencil"></i></a>
                    <a href="#myModal" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
                </td>
            </tr>
        }$
        </tbody>
    </table>
</div>

<h4>Settled Markets</h4>
<div class="row-fluid">
    <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered">
        <thead>
        <tr>
            <th></th>
            <th>Date</th>
            <th>Market</th>
            <th>Final Selection</th>
            <th>Bet Count</th>
            <th>Stake</th>
            <th>Profit</th>
            <th>ROI</th>
            <th>Comment</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        $statsByMarket.items:{
            <tr>
                <td>$i$</td>
                <td>$it.date;format="date:short"$</td>
                <td>$it.marketName$</td>
                <td>final selection</td>
                <td>$it.betCount$</td>
                <td>$it.totalStake;format="doubleScale2"$</td>
                <td>$it.profit;format="doubleScale2"$</td>
                <td>$it.returnOfInvestment;format="percent"$</td>
                <td>Good match with no surprises</td>
                <td>
                    <a href="user.html"><i class="icon-pencil"></i></a>
                    <a href="#myModal" role="button" data-toggle="modal"><i class="icon-remove"></i></a>
                </td>
            </tr>
        }$
        </tbody>
    </table>
</div>

<div class="row-fluid">
    <!-- Bottom switcher of slider -->
    <ul class="thumbnails">
        <li class="span2">
            <a class="thumbnail image">
                <img src="http://placehold.it/190/234653/eeeeee&text=+"/>
                <h2><span>Profit By Day</span></h2>
            </a>
        </li>
        <li class="span2">
            <a class="thumbnail image">
                <img src="http://placehold.it/190/234653/eeeeee&text=+"/>
                <h2><span>Profit By Day</span></h2>
            </a>
        </li>
        <li class="span2">
            <a class="thumbnail ">
                <img src="http://placehold.it/190/234653/eeeeee&text=+"/>
                <!--<h2><span>Profit By Day</span></h2>-->
            </a>
        </li>
        <li class="span2">
            <a class="thumbnail ">
                <img src="http://placehold.it/190/234653/eeeeee&text=+"/>
                <!--<h2><span>Profit By Day</span></h2>-->
            </a>
        </li>
        <li class="span2">
            <a class="thumbnail ">
                <img src="http://placehold.it/190/234653/eeeeee&text=+"/>
                <!--<h2><span>Profit By Day</span></h2>-->
            </a>
        </li>
        <li class="span2">
            <a class="thumbnail ">
                <img src="http://placehold.it/190/234653/eeeeee&text=+"/>
                <!--<h2><span>Profit By Day</span></h2>-->
            </a>
        </li>
    </ul>
</div>
</body>
</html>
