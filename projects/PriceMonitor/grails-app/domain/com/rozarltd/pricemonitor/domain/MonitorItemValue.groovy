package com.rozarltd.pricemonitor.domain

import grails.plugins.couchdb.CouchEntity

@CouchEntity(type="value")
class MonitorItemValue {
//  String type
  String monitor
  String item
  String value
  long date
}
