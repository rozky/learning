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