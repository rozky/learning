package com.rozarltd.module.betfairwebsite.service;


import com.rozarltd.module.betfairwebsite.exception.WebScrapingException;
import com.rozarltd.module.betfairwebsite.page.coupon.domain.TennisInPlayCoupon;
import com.rozarltd.util.http.HttpTemplate;

// todo - can remove this class
public class ServiceExample {

    public static void main(String[] args) throws WebScrapingException {
        int marketId = 105251079;

        TennisInPlayCoupon todayInPlayTennisCoupon =
                new BetfairWebsiteClient(new HttpTemplate()).getTodayInPlayTennisCoupon();

        System.out.println("done");
    }
}
