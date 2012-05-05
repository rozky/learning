<div id="bet-slip" class="betSlipWrapper module">
    <h1 class="header">
        Bet Slip
    </h1>

    <div class="content">
        <form method="POST" action="/betfair/bet" enctype="UTF-8">
            <h2>Selection Name</h2>
            <h3>Market Name</h3>

            <!--<div class="header">-->
                <!--<div class="price"><label for="betSlipPrice">Price</label></div>-->
                <!--<div class="stake"><label for="betSlipPrice">Stake</label></div>-->
                <!--<div class="profit">To Win</div>-->
            <!--</div>-->

            <div class="selection">
                <div class="price"><label for="betSlipPrice">Price</label><input id="betSlipPrice" type="text" name="price" value="" maxlength="4"/></div>
                <div class="stake"><label for="betSlipStake">Stake</label><input id="betSlipStake" type="text" name="stake" value="" maxlength="7"/></div>
                <div class="profit"><label for="betSlipProfit">To Win</label><input id="betSlipProfit" type="text" name="profit" value="34567.65" disabled/></div>
            </div>

            <div style="background-color: #fff; padding: 5px 0px 5px 0px;">
                <button class="yellow">Place Bet</button>
                <button class="yellow">Cancel</button>
            </div>

            <input type="hidden" name="marketId" value="">
            <input type="hidden" name="selectionId" value="">
        </form>

        <div class="overlay">
            <table>
                <tr>
                    <td>
                        <div>
                            <em></em>
                            <button>close</button>
                        </div>
                    </td>
                </tr>
            </table>
        </div>

        <div class="emptyOverlay">
            <table>
                <tr>
                    <td>
                        <div class="big">Nothing Selected</div>
                        <div class="small">Make your selection on the left by clicking on the runner you would like to place bet on</div>
                    </td>
                </tr>
            </table>
        </div>
    </div>


    <!--<h1 class="header">Bet Slip</h1>-->
    <!--<div class="content">-->
        <!--<form method="POST" action="/betfair/bet" enctype="UTF-8">-->
            <!--<input type="hidden" name="marketId" value="">-->
            <!--<input type="hidden" name="selectionId" value="">-->

            <!--<h2>Selection Name</h2>-->
            <!--<h3>Market Name</h3>-->

            <!--<div class="header">-->
                <!--<div class="price"><label for="betSlipPrice">Price</label></div>-->
                <!--<div class="stake"><label for="betSlipPrice">Stake</label></div>-->
                <!--<div class="profit">Profit</div>-->
            <!--</div>-->

            <!--<div>-->
                <!--<div class="price"><input id="betSlipPrice" type="text" name="price" value="Price"/></div>-->
                <!--<div class="stake"><input type="text" name="stake" value="Stake"/></div>-->
                <!--<div class="profit">Selection Name</div>-->
            <!--</div>-->

            <!--<div style="background-color: #fff; padding: 5px 0px 5px 0px;">-->
                <!--<button class="yellow">Place Bet</button>-->
            <!--</div>-->
        <!--</form>-->

        <!--<div class="overlay">-->
            <!--<table>-->
                <!--<tr>-->
                    <!--<td>-->
                        <!--<div>-->
                            <!--<em></em>-->
                            <!--<button>close</button>-->
                        <!--</div>-->
                    <!--</td>-->
                <!--</tr>-->
            <!--</table>-->
        <!--</div>-->

        <!--<div class="emptyOverlay">-->
            <!--<table>-->
                <!--<tr>-->
                    <!--<td>-->
                        <!--<div class="big">Nothing Selected</div>-->
                        <!--<div class="small">Make your selection on the left by clicking on the runner you would like to place bet on</div>-->
                    <!--</td>-->
                <!--</tr>-->
            <!--</table>-->
        <!--</div>-->

        <!--<form method="POST" action="/betfair/bet" enctype="UTF-8">-->
            <!--<input type="hidden" name="marketId" value="">-->
            <!--<input type="hidden" name="selectionId" value="">-->

            <!--<ol>-->
               <!---->
                <!--<li style="background-color: #fff; padding: 5px 0px 5px 0px;">-->
                    <!--<button class="yellow">Place Bet</button>-->
                <!--</li>-->
            <!--</ol>-->

            <!--<div class="profit">-->
                <!--&pound;&nbsp;<em>1314.67</em>-->
            <!--</div>-->
        <!--</form>-->
        <!--<div class="overlay">-->
            <!--<table>-->
                <!--<tr>-->
                    <!--<td>-->
                        <!--<div>-->
                            <!--<em></em>-->
                            <!--<button>close</button>-->
                        <!--</div>-->
                    <!--</td>-->
                <!--</tr>-->
            <!--</table>-->
        <!--</div>-->

        <!--<div class="emptyOverlay">-->
            <!--<table>-->
                <!--<tr>-->
                    <!--<td>-->
                        <!--<div class="big">Nothing Selected</div>-->
                        <!--<div class="small">Make your selection on the left by clicking on the runner you would like to place bet on</div>-->
                    <!--</td>-->
                <!--</tr>-->
            <!--</table>-->
        <!--</div>-->
    <!--</div>-->

</div>