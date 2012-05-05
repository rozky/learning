<div id="current-bets" class="greyTable currentBets module">
    <h1 class="header">
        Your Current Bets
        <button class="refresh">Refresh</button>
    </h1>

    <div class="content">
         $if(hasCurrentBets)$
            <div class="header">
                <div class="betType">Type</div>
                <div class="time">Time</div>
                <div class="selection">Selection Name</div>
                <div class="size">Stake [ &pound; ]</div>
                <div class="price">Price</div>
                <div class="profit">Profit [ &pound; ]</div>
            </div>
            $currentBets:{bet|
                <div class="bet dataRow" data-bet-id="$bet.betId$" data-bet-status="$bet.betStatus$">
                    <div class="betType p1" >$bet.betStatus$</div>
                    <div class="time p1">$bet.placedDate;format="time:short"$</div>
                    <div class="selection p1">$runnerNames.(bet.selectionId)$</div>
                    <!--<div class="selection p1">$bet.selectionId$</div>-->
                    <div class="size p1">&pound;$bet.size;format="doubleScale2"$</div>
                    <div class="price p1">$bet.price;format="doubleScale2"$</div>
                    <div class="profit p1">&pound;$bet.profitAndLost;format="doubleScale2"$</div>
                    <div class="overlay"><em>a</em>Canceling your bet ...</div>
                </div>
            }$
        $else$
            <div class="notification">You haven't placed any bets</div>
        $endif$


        <div id="refreshOverlay" class="overlay">
            <table class="wh100">
                <tr>
                    <td>
                        <span class="spinner">Loading your current bets</span>
                    </td>
                </tr>
            </table>
        </div>
    </div>

    <div id="betOverlayTmp">
        <em>spinner</em>Canceling your bet ...
    </div>

</div>