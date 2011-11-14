package com.rozarltd.pricemonitor.service

import com.rozarltd.pricemonitor.domain.Monitor
import com.rozarltd.pricemonitor.domain.MonitorItemValue
import org.ccil.cowan.tagsoup.Parser
import org.apache.commons.lang.time.DateUtils

class MonitorExecutor {
  def execute(Monitor monitor) {
    def pageHTML = new XmlSlurper(new Parser()).parseText(monitor.url.toURL().text)

    monitor.items.each {item ->
      if("A" == item.status) {
        def value = pageHTML.depthFirst().find {it.@class == item.el}

        if (value != null) {
          new MonitorItemValue(monitor: monitor.code, item: item.name, value: value.text().replaceAll("[^0-9.]", ""), date: DateUtils.truncate(new Date(), Calendar.DATE).getTime()).save();
        }
      }
    }
  }
}
