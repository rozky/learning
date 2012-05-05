package com.rozarltd.module.betfairrestapi.domain.market;

import java.util.List;

public class BetfairRestApiMarketRunners {
    private List<BetfairRestApiMarketRunner> runner;

    public BetfairRestApiMarketRunners() {
    }

    // When "runners" property in json response is not object but empty string "" (when market is closed)
    // then this constructor will be used.
    // Without this constructor json deserialization  will fail
    public BetfairRestApiMarketRunners(String string) {
    }

    public List<BetfairRestApiMarketRunner> getRunner(){
		return this.runner;
	}
	public void setRunner(List<BetfairRestApiMarketRunner> runner){
		this.runner = runner;
	}
}
