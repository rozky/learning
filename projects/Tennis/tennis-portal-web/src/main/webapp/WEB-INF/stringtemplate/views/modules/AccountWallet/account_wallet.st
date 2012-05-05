<div id="account-wallets" class="greyTable module">
    <h1 class="header">
        Account Wallets
        <button class="refresh">Refresh</button>
    </h1>

    <div class="content">
        $if(accountWallets)$
            <div class="wallet header">
                <div class="name">Name</div>
                <div class="balance">Wallet Balance</div>
                <div class="availableBalance">Available Balance</div>
                <div class="exposure">Current Exposure</div>
            </div>
            $accountWallets:{wallet|
                <div class="wallet dataRow">
                    <div class="name">$wallet.name$</div>
                    <div class="balance">$wallet.balance;format="doubleScale2"$</div>
                    <div class="availableBalance">$wallet.availableBalance;format="doubleScale2"$</div>
                    <div class="exposure">$wallet.exposure;format="doubleScale2"$</div>
                </div>
            }$
        $else$
            <div class="notification">Not Loaded: Click on Refresh button to load</div>
        $endif$

        <div class="overlay">
            <table class="wh100">
                <tr>
                    <td>
                        <span class="blueSpinner">Loading Wallets Details</span>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>