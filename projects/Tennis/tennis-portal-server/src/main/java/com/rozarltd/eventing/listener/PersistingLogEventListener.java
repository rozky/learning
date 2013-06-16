package com.rozarltd.eventing.listener;

import com.rozarltd.eventing.event.LogEvent;
import com.rozarltd.repository.DurableLogEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

// todo - register with application context
public class PersistingLogEventListener implements ApplicationListener<LogEvent> {

    @Autowired
    private DurableLogEventRepository eventRepository;

    @Override
    public void onApplicationEvent(LogEvent event) {
        eventRepository.save(event.toDurableEventLog());
    }
}
