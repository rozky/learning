package com.rozarltd.betting.domain.market;

import javax.net.ssl.SSLEngineResult;
import java.util.Date;
import java.util.List;

public class SportEvent {
    private long id;
    private long parentId;
    private String name;
    private SSLEngineResult.Status status;
    private Date startDate;
    private Date endDate;
    private List<SportEvent> childEvents;
}
