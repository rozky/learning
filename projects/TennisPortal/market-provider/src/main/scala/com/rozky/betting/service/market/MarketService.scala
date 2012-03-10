package com.rozky.betting.service.market

import com.rozky.betting.domain.market.Market


trait MarketService {
  def createMarket(market: Market) {

  }
  def loadMarket(id: Long)
}