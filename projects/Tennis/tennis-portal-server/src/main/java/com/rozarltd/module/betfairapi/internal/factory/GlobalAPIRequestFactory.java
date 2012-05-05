package com.rozarltd.module.betfairapi.internal.factory;


import com.betfair.publicapi.global.types.LoginReq;
import com.rozarltd.module.betfairapi.domain.BetfairProductIdEnum;
import org.springframework.stereotype.Component;

@Component
public class GlobalAPIRequestFactory {
    public LoginReq createLoginRequest(String username, String password) {
        LoginReq request = new LoginReq();
        request.setUsername(username);
        request.setPassword(password);
        request.setProductId(BetfairProductIdEnum.FREE_API.getId());
        return request;
    }
}
