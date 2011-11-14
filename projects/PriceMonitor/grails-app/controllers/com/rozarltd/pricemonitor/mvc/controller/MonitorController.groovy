package com.rozarltd.pricemonitor.mvc.controller

import com.rozarltd.pricemonitor.domain.Monitor
import com.rozarltd.pricemonitor.service.MonitorExecutor

class MonitorController {

  def index = { redirect(action: list, params: params) }

  def list = { [monitorList: Monitor.list()] }

  def active = {
    def monitor = Monitor.get("761ec14d86e0d39c0c67f34b65000f45");

    def executor = new MonitorExecutor();

    executor.execute(monitor);

    [monitor: monitor]
  }
}
