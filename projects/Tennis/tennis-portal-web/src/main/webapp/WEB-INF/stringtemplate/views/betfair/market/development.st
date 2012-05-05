<html>
<head>
    <title>Market Development</title>

    <link rel="stylesheet" href="/static/css/lib/960grid/960_24_col.css" type="text/css" media="screen"/>

    <link rel="stylesheet" href="/static/css/application.css" type="text/css" media="screen"/>

    <link rel="stylesheet" href="/static/js/modules/MarketList/css/marketListModule.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="/static/js/modules/BetSlip/css/betSlip.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="/static/js/modules/CurrentBets/css/currentBetsModule.css" type="text/css" media="screen" />
    <link rel="stylesheet" href="/static/js/modules/AccountWallet/css/accountWallet.css" type="text/css" media="screen" />

    <script src="/static/js/lib/jquery/jquery-1.7.1.min.js"></script>
    <script src="/static/js/lib/underscore-min-1.3.1.js"></script>
    <script src="/static/js/lib/backbone-min-0.9.1.js"></script>


</head>

<body>
<div id="container" class="container_24">
    <div class="grid_14">
        <div id="market-list" class="marketList module" >
            <h1 class="header">
                Your Current Bets
                <button class="refresh">Refresh</button>
            </h1>
            <div class="content">
                $markets:{market|
                    <div class="marketWrapper">
                        $if(market.inPlay)$
                            <div class="live">Live</div>
                        $elseif(market.todayMarket)$
                            <div class="time today">$market.startAtDate;format="time:short"$</div>
                        $else$
                            <div class="time">$market.startAtDate;format="time:short"$</div>
                        $endif$

                        <ol data-market-name="$market.marketNameShort$">
                            $market.runners:{runner|
                            <li class="runner position-$i$" data-market-id="$market.marketId$"
                                data-selection-id="$runner.runnerId$">
                                <span class="runnerName">$runner.runnerName$</span>
                                <em class="price">$runner.bestBackPrice.price;format="doubleScale2"$</em>
                            </li>
                            }$
                             <div class="overlay">
                                <table class="wh100">
                                    <tr>
                                        <td>
                                            <div>
                                                <em>Suspended</em>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                             </div>
                        </ol>
                    </div>
                }$
            </div>
        </div>
    </div>

    <div class="grid_10">
        <!--<h1 class="notification-error">Your bet has been placed successfully</h1>-->
        <!--<h1 class="notification-success">Your bet has been placed successfully</h1>-->
        <!--<h1 class="notification-warning">Processing your bet, please wait ...</h1>-->
        $modules/BetSlip/bet_slip()$
        $modules/CurrentBets/current_bets()$
        $modules/AccountWallet/account_wallet()$
    </div>
</div>

<!--<h1 class="page-alert">-->
    <!--You have been successfully logged in.-->
    <!--<button>close</button>-->
<!--</h1>-->

<script src="/static/js/modules/MarketList/js/marketListModule.js"></script>
<script src="/static/js/modules/BetSlip/js/betSlipModule.js"></script>
<script src="/static/js/modules/CurrentBets/js/currentBetsModule.js"></script>
<script src="/static/js/modules/AccountWallet/js/accountWalletModule.js"></script>
</body>
</html>