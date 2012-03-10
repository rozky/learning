package com.rozarltd.betfairapi.service;


import com.betfair.publicapi.exchange.BFExchangeService;
import com.betfair.publicapi.exchange.types.GetAccountFundsErrorEnum;
import com.betfair.publicapi.exchange.types.GetAccountFundsReq;
import com.betfair.publicapi.exchange.types.GetAccountFundsResp;
import com.betfair.publicapi.global.BFGlobalService;
import com.betfair.publicapi.global.types.LoginErrorEnum;
import com.betfair.publicapi.global.types.LoginReq;
import com.betfair.publicapi.global.types.LoginResp;
import com.rozarltd.betfairapi.domain.account.Wallet;
import com.rozarltd.betfairapi.internal.factory.BFExchangeAPIRequestFactory;
import com.rozarltd.betfairapi.internal.factory.GlobalAPIRequestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BetfairAccountService implements AccountService {
    private final BFGlobalService betfairGlobalService;
    private final GlobalAPIRequestFactory betfairGlobalApiRequestFactory;
    private final BFExchangeService betfairExchangeService;
    private final BFExchangeAPIRequestFactory betfairExchangeApiRequestFactory;

    @Autowired
    public BetfairAccountService(BFGlobalService betfairGlobalService,
                                 GlobalAPIRequestFactory betfairGlobalApiRequestFactory,
                                 BFExchangeService betfairExchangeService,
                                 BFExchangeAPIRequestFactory betfairExchangeApiRequestFactory) {
        this.betfairGlobalService = betfairGlobalService;
        this.betfairGlobalApiRequestFactory = betfairGlobalApiRequestFactory;
        this.betfairExchangeService = betfairExchangeService;
        this.betfairExchangeApiRequestFactory = betfairExchangeApiRequestFactory;
    }

    public String login(String username, String password) {
        LoginReq request = betfairGlobalApiRequestFactory.createLoginRequest(username, password);
        LoginResp response = betfairGlobalService.login(request);

        if(LoginErrorEnum.OK == response.getErrorCode()) {
            return response.getHeader().getSessionToken();
        }
        return null;
    }

    @Override
    public Wallet getAccountWallets(String sessionToken) {
        GetAccountFundsReq request = betfairExchangeApiRequestFactory.createGetAccountFundsRequest(sessionToken);
        GetAccountFundsResp response = betfairExchangeService.getAccountFunds(request);

        if(GetAccountFundsErrorEnum.OK.equals(response.getErrorCode())) {

            Wallet wallet = new Wallet(Wallet.UK_WALLET_NAME, true);
            wallet.setBalance(response.getBalance());
            wallet.setAvailableBalance(response.getAvailBalance());
            wallet.setExposure(response.getExposure());

            return wallet;
        }

        return new Wallet(Wallet.UK_WALLET_NAME, false);
    }
}
