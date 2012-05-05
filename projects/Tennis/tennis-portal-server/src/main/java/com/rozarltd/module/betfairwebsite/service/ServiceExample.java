package com.rozarltd.module.betfairwebsite.service;


import com.rozarltd.module.betfairwebsite.exception.WebScrapingException;
import com.rozarltd.module.betfairwebsite.page.coupon.domain.TennisInPlayCoupon;

// todo - can remove this class
public class ServiceExample {

    public static void main(String[] args) throws WebScrapingException {
        int marketId = 105251079;

        TennisInPlayCoupon todayInPlayTennisCoupon = new BetfairWebsiteClient().getTodayInPlayTennisCoupon();

        System.out.println("done");
    }
}
