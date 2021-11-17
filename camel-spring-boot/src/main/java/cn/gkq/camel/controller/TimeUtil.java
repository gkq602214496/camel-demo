package cn.gkq.camel.controller;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Objects;

/**
 * <p>时间工具类</p>
 *
 * @author GKQ
 * @date 2021/7/30
 */
public class TimeUtil {

    public static final DateTimeFormatter monthTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取当前毫秒时间戳
     *
     * @return
     */
    public static long toEpochMilli() {
        return LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 获取当前毫秒时间戳
     *
     * @return
     */
    public static long localDateTimeToTimestamp(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 获取当前毫秒时间戳
     *
     * @return
     */
    public static String timeToTimestampString(Long timestamp) {
        return dateTimeFormatter.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault()));
    }

    /**
     * 时间字符串转毫秒时间戳
     *
     * @return
     */
    public static Long timeToLong(String time) {
        LocalDateTime parse = timeToLocalDateTime(time);
        return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * UTC时间字符串转毫秒时间戳
     *
     * @return
     */
    public static Long utcTimeToTimestamp(String time) {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(time);
        LocalDateTime parse = zonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Shanghai")).toLocalDateTime();
        return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 时间字符串转LocalDateTime
     *
     * @return
     */
    public static LocalDateTime timeToLocalDateTime(String time) {
        LocalDateTime parse = LocalDateTime.parse(time, dateTimeFormatter);
        return parse;
    }

    /**
     * 字符串时间转换伟时间类型
     *
     * @param data 字符串时间，@example 2021-08-03T16:41:47.027Z
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime String2Date(String data) {
        String substring = data.substring(0, data.indexOf("Z"));
        return LocalDateTime.parse(substring, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * 月份时间格式化
     *
     * @return
     */
    public static String getMonthString(LocalDate localDate) {
        return monthTimeFormatter.format(localDate);
    }

    /**
     * 获取指定月差时间
     *
     * @return
     */
    public static LocalDate minusMonths(LocalDate localDate, long monthsToSubtract) {
        return localDate.minusMonths(monthsToSubtract);
    }

    /**
     * 获取指定天数差时间
     *
     * @return
     */
    public static LocalDate minusDays(LocalDate localDate, long daysToSubtract) {
        return localDate.minusDays(daysToSubtract);
    }

    /**
     * 获取指定天数差时间
     *
     * @return
     */
    public static LocalDateTime minusLocalDateTime(LocalDate localDate, long daysToSubtract) {
        return  minLocalDateTime(localDate.minusDays(daysToSubtract));
    }

    /**
     * 获取指定天数差时间
     *
     * @return
     */
    public static LocalDateTime minLocalDateTime(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.MIN);
    }

    /**
     * 获取最小时间
     *
     * @param localDate
     * @return
     */
    public static LocalDateTime minDateTime(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.MIN);
    }

    /**
     * 获取最大时间
     *
     * @param localDate
     * @return
     */
    public static LocalDateTime maxDateTime(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.MAX);
    }



    /**
     * 格式化时间
     *
     * @param text      数据
     * @param formatter 格式器
     * @return {@link LocalDate}
     */
    public static LocalDate dateTimeFormat(CharSequence text, DateTimeFormatter formatter) {
        // 验证
        Objects.requireNonNull(formatter, "formatter");
        // 格式化
        TemporalAccessor temporalAccessor = formatter.parse(text);
        int year = temporalAccessor.get(ChronoField.YEAR);
        int month;
        try {
            month = temporalAccessor.get(ChronoField.MONTH_OF_YEAR);
        } catch (UnsupportedTemporalTypeException e) {
            month = 1;
        }
        int day;
        try {
            day = temporalAccessor.get(ChronoField.DAY_OF_MONTH);
        } catch (UnsupportedTemporalTypeException e) {
            day = 1;
        }
        return LocalDate.of(year, month, day);
    }



}
