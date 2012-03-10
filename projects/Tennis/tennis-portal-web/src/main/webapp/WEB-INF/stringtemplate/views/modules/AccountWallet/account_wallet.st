<div id="account-wallets" class="module">
    <h1 class="header">
        Account Wallets
        <button class="refresh">Refresh</button>
    </h1>

    <div class="content">
        $if(accountWallets)$
            <div class="wallet header">
                <div class="name">Name</div>
                <div class="balance">Balance</div>
                <div class="availableBalance">Available Balance</div>
                <div class="exposure">Exposure</div>
            </div>
            $accountWallets:{wallet|
                <div class="wallet">
                    <div class="name">$wallet.name$</div>
                    <div class="balance">$wallet.balance$</div>
                    <div class="availableBalance">$wallet.availableBalance$</div>
                    <div class="exposure">$wallet.exposure$</div>
                </div>
            }$

            <div class="overlay">
                <table class="wh100">
                    <tr>
                        <td>
                            <span class="spinner">Loading wallets details</span>
                        </td>
                    </tr>
                </table>
            </div>
        $else$
            <div class="notification">Not Loaded</div>
        $endif$
    </div>
</div>