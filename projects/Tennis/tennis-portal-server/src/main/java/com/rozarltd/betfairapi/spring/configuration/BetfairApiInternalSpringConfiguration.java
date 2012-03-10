package com.rozarltd.betfairapi.spring.configuration;


import com.betfair.publicapi.global.BFGlobalService;
import com.betfair.publicapi.global.types.LoginErrorEnum;
import com.betfair.publicapi.global.types.LoginReq;
import com.betfair.publicapi.global.types.LoginResp;
import com.rozarltd.betfairapi.BetfairApiRuntimeException;
import com.rozarltd.betfairapi.internal.BetfairSessionToken;
import com.rozarltd.betfairapi.internal.factory.GlobalAPIRequestFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class BetfairApiInternalSpringConfiguration {
    private static final String username = "undefined";
    private static final String password = "undefined";

    @Bean
    @Lazy
    public BetfairSessionToken betfairApiSessionToken(BFGlobalService betfairGlobalApiService,
                                               GlobalAPIRequestFactory betfairGlobalApiRequestFactory) {

        LoginReq request = betfairGlobalApiRequestFactory.createLoginRequest(username, password);
        LoginResp response = betfairGlobalApiService.login(request);

        if(LoginErrorEnum.OK == response.getErrorCode()) {
            return new BetfairSessionToken(response.getHeader().getSessionToken());
        }

        throw new BetfairApiRuntimeException(String.format("Login to Betfair API failed with error '%s'.",
                response.getErrorCode()));
    }
}
