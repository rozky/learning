package com.rozarltd.pricemonitor.domain

import grails.plugins.couchdb.CouchEntity

@CouchEntity(type="monitor")
class Monitor {
  String code
  String name
  String url
  List<MonitorItem> items
  String schedulerExpression
  String status

  static constraints = {
  }
}
