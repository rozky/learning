package com.rozarltd.repository;

import com.rozarltd.entity.monitoring.DurableLogEvent;
import org.springframework.data.repository.CrudRepository;

public interface DurableLogEventRepository extends CrudRepository<DurableLogEvent, Integer> {
}
