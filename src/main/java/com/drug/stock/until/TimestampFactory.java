package com.drug.stock.until;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author lenovo
 */
public class TimestampFactory {
    public static Timestamp getTimestamp() {
        Date date = new Date();
        return new Timestamp(date.getTime());
    }

}
