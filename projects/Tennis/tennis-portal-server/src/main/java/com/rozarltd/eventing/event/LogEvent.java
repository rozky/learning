package com.rozarltd.eventing.event;

import com.rozarltd.entity.monitoring.DurableLogEvent;
import org.springframework.context.ApplicationEvent;

public abstract class LogEvent extends ApplicationEvent {

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the component that published the event (never {@code null})
     */
    public LogEvent(Object source) {
        super(source);
    }

    public abstract DurableLogEvent toDurableEventLog();
}
