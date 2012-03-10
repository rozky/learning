package com.rozarltd.betfairapi.internal.spring;


import com.betfair.publicapi.global.BFGlobalService;
import com.betfair.publicapi.global.types.LoginErrorEnum;
import com.betfair.publicapi.global.types.LoginReq;
import com.betfair.publicapi.global.types.LoginResp;
import com.rozarltd.betfairapi.internal.BetfairSessionToken;
import com.rozarltd.betfairapi.internal.factory.GlobalAPIRequestFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

//@Component
public class SessionTokenFactoryBean implements FactoryBean<BetfairSessionToken> {
    @Autowired private BFGlobalService betfairGlobalService;
    @Autowired private GlobalAPIRequestFactory requestFactory;
    @Value("${betfair.api.username}") private String username;
    @Value("${betfair.api.password}") private String password;

    private BetfairSessionToken sessionToken;

    @Override
    public BetfairSessionToken getObject() throws Exception {
        if(sessionToken == null) {
            sessionToken = getSessionToken();
        }
        return sessionToken;
    }

    @Override
    public Class<?> getObjectType() {
        return BetfairSessionToken.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    private BetfairSessionToken getSessionToken() {
        LoginReq request = requestFactory.createLoginRequest(username, password);
        LoginResp response = betfairGlobalService.login(request);

        if(LoginErrorEnum.OK == response.getErrorCode()) {
            return new BetfairSessionToken(response.getHeader().getSessionToken());
        }
        return null;
    }


}
