package com.rozarltd.eventing.event;

import com.rozarltd.common.DateTimeFormats;
import com.rozarltd.entity.monitoring.DurableLogEvent;

import java.text.MessageFormat;
import java.util.Date;

public class ReplicationCompletedEvent extends LogEvent {
    private long fromDate;
    private long toDate;
    private long newItemCount;
    private long duration;

    public ReplicationCompletedEvent(Object source) {
        super(source);
    }

    public ReplicationCompletedEvent withFromDate(Date date) {
        this.fromDate = date.getTime();
        return this;
    }

    public ReplicationCompletedEvent withToDate(Date date) {
        this.toDate = date.getTime();
        return this;
    }

    public ReplicationCompletedEvent withDuration(long duration) {
        this.duration = duration;
        return this;
    }

    public ReplicationCompletedEvent withNewItemCount(long count) {
        this.newItemCount = count;
        return this;
    }

    @Override
    public DurableLogEvent toDurableEventLog() {
        String logMessage = MessageFormat.format("Copied {0} betfair statement records in {1}ms for period between {2} and {3}.",
                newItemCount, duration, DateTimeFormats.NO_MILLIS.print(fromDate), DateTimeFormats.NO_MILLIS.print(toDate));
        return new DurableLogEvent().withType("BF_STATEMENT_COPY").withText(logMessage).withCreateNow();
    }
}
