package com.rozarltd.module.betfairapi.domain.account.statement;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import static org.fest.assertions.Assertions.assertThat;

public class AccountStatementRecordPlacedDateComparatorTest {
    private AccountStatementRecord.PlacedDateComparator comparator = new AccountStatementRecord.PlacedDateComparator();

    @Test
    public void shouldTwoRecordsWithSamePlacedDate() {
        // given
        Date now = new Date();
        AccountStatementRecord left = new AccountStatementRecord();
        left.setPlacedAt(DateUtils.addMinutes(now, -10).getTime());

        // when
        int result = comparator.compare(left, left);

        // then
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void shouldTwoRecordsWithDifferentPlacedDate() {
        // given
        Date now = new Date();
        AccountStatementRecord left = new AccountStatementRecord();
        left.setPlacedAt(DateUtils.addMinutes(now, -10).getTime());
        AccountStatementRecord right = new AccountStatementRecord();
        right.setPlacedAt(DateUtils.addMinutes(now, -9).getTime());

        // when & then
        assertThat(comparator.compare(left, right)).isEqualTo(-1);
        assertThat(comparator.compare(right, left)).isEqualTo(1);
    }

    @Test
    public void shouldSortCollectionOdRecordsFromOldestToYoungest() {
        Date now = new Date();

        // when
        Set<AccountStatementRecord> toSort = new TreeSet<AccountStatementRecord>(comparator);
        toSort.add(betPlacedAd("1", DateUtils.addMinutes(now, -10)));
        toSort.add(betPlacedAd("2", null));
        toSort.add(betPlacedAd("3", DateUtils.addMinutes(now, -11)));

        // then
        Iterator<AccountStatementRecord> iterator = toSort.iterator();
        assertThat(iterator.next().getId()).isEqualTo("3");
        assertThat(iterator.next().getId()).isEqualTo("1");
        assertThat(iterator.next().getId()).isEqualTo("2");
    }

    private AccountStatementRecord betPlacedAd(String id, Date date) {
        AccountStatementRecord bet = new AccountStatementRecord();
        bet.setId(id);
        if(date != null) {
            bet.setPlacedAt(date.getTime());
        }

        return bet;
    }
}
