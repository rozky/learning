package com.rozarltd.service;

import com.rozarltd.domain.CopyBettingActivityResult;

import java.util.Date;

/**
 * Interface for administrating betting activity
 *
 * @param <U> the user type
 */
public interface BettingActivityAdminService<U> {

    /**
     * Copy betting activity from the external source into the internal(local) data storage
     *
     * @param user the user whose betting activity to copy
     * @param from the start date
     * @param to the end date
     *
     * @return the result of the copy operation
     */
    CopyBettingActivityResult copyBettingActivity(U user, Date from, Date to);

    /**
     * Copy betting activity from the external source into the local data store starting from a date of the latest already copied activity
     * and ending at the current date/time. If no data have been copied than it should try to copy data for the last 3 months.
     *
     * @param user the user whose betting activity to copy
     * @return the result of the copy operation
     */
    CopyBettingActivityResult copyBettingActivity(U user);
}
