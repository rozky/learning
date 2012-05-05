package com.rozarltd.module.betfairwebsite.page.coupon.component;

import com.rozarltd.module.betfairwebsite.page.coupon.domain.TennisInPlayCoupon;
import com.rozarltd.domain.market.MarketRunner;
import com.rozarltd.spring.bean.ZipPropertyValues;
import org.springframework.stereotype.Component;
import org.springframework.validation.DataBinder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class TennisInPlayCouponExtractor {

    private static final Pattern runnerLinePattern = Pattern.compile("p.m_R\\((.*)\\);");

    public TennisInPlayCoupon extract(String source) {
        TennisInPlayCoupon coupon = new TennisInPlayCoupon();

        Matcher matcher = runnerLinePattern.matcher(source);
        while (matcher.find()) {
            String runnerLine = matcher.group(1);
            String[] runnerFieldValues = runnerLine.split(",");
            coupon.addRunner(MarketRunnerFactory.create(runnerFieldValues));
        }

        return coupon;
    }

    private static class MarketRunnerFactory {
        private static final String[] runnerFieldNames = {
                "runnerId",
                ZipPropertyValues.IGNORE_NAME,
                "marketId",
                "runnerName",
                ZipPropertyValues.IGNORE_NAME,
                ZipPropertyValues.IGNORE_NAME,
                ZipPropertyValues.IGNORE_NAME,
                ZipPropertyValues.IGNORE_NAME,
                ZipPropertyValues.IGNORE_NAME,
                ZipPropertyValues.IGNORE_NAME,
                "bestBackPrice.price",
                "bestBackPrice.amount",
                "bestLayPrice.price",
                "bestLayPrice.amount",
                ZipPropertyValues.IGNORE_NAME,
                ZipPropertyValues.IGNORE_NAME,
                ZipPropertyValues.IGNORE_NAME,
                ZipPropertyValues.IGNORE_NAME,
                ZipPropertyValues.IGNORE_NAME};

        public static MarketRunner create(String[] runnerFieldValues) {
            DataBinder binder = new DataBinder(new MarketRunner());
            binder.bind(new ZipPropertyValues(runnerFieldNames, runnerFieldValues));
            if (!binder.getBindingResult().hasErrors()) {
                return (MarketRunner) binder.getTarget();
            }
            else {
                // todo - print out errors
                throw new IllegalArgumentException("Invalid runner property values. Values: " + runnerFieldValues);
            }
        }
    }
}
