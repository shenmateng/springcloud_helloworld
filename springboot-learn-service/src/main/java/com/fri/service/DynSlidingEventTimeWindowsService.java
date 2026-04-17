package com.fri.service;

import com.fri.domain.scanevent.ScanEventLogDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.api.common.typeutils.TypeSerializer;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.WindowAssigner;

import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.triggers.EventTimeTrigger;
import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.eclipse.jetty.util.ajax.JSON;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author: mateng
 * @program: spring-cloud
 * @Date: 2023/5/18 15:38
 * @Version: 2.1.5.2
 * @Description: 动态开窗实现类
 * ░░░░░░░░░░░░░░░░░░░░░░░░▄░░
 * ░░░░░░░░░▐█░░░░░░░░░░░▄▀▒▌░
 * ░░░░░░░░▐▀▒█░░░░░░░░▄▀▒▒▒▐
 * ░░░░░░░▐▄▀▒▒▀▀▀▀▄▄▄▀▒▒▒▒▒▐
 * ░░░░░▄▄▀▒░▒▒▒▒▒▒▒▒▒█▒▒▄█▒▐
 * ░░░▄▀▒▒▒░░░▒▒▒░░░▒▒▒▀██▀▒▌
 * ░░▐▒▒▒▄▄▒▒▒▒░░░▒▒▒▒▒▒▒▀▄▒▒
 * ░░▌░░▌█▀▒▒▒▒▒▄▀█▄▒▒▒▒▒▒▒█▒▐
 * ░▐░░░▒▒▒▒▒▒▒▒▌██▀▒▒░░░▒▒▒▀▄
 * ░▌░▒▄██▄▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒
 * ▀▒▀▐▄█▄█▌▄░▀▒▒░░░░░░░░░░▒▒▒
 * You are not expected to understand this
 */

@Slf4j
public class DynSlidingEventTimeWindowsService extends WindowAssigner<Object, TimeWindow> {

    private static final long serialVersionUID = 1L;

    //窗口大小
    private final long size;

    //滑动步长
    private final long slide;

    private final long offset;

    private long realSize = 0L;

    private long realSlide = 0L;



    protected DynSlidingEventTimeWindowsService(long size, long slide, long offset) {
        if (Math.abs(offset) >= slide || size <= 0) {
            throw new IllegalArgumentException(
                    "SlidingEventTimeWindows parameters must satisfy "
                            + "abs(offset) < slide and size > 0");
        }

        this.size = size;
        this.slide = slide;
        this.offset = offset;

    }



    @Override
    public Collection<TimeWindow> assignWindows(Object element, long timestamp, WindowAssignerContext context) {
        log.info("开窗收到的kafka数据对象：{}", JSON.toString(element));

        try {
            ScanEventLogDO scanEventLogDO = (ScanEventLogDO)element;
            //kafka中的开窗时间必须是毫秒级，如果传入过小，会造成频繁开窗
            Long scanTime = scanEventLogDO.getScanTime();
            if(ObjectUtils.isNotEmpty(scanTime)){
                if(this.realSize!=scanTime){
                    this.realSize = scanTime;
                    this.realSlide= scanTime;
                }
            }
        }catch (Exception e){
            log.info("element对象强转失败：【" + e + "】");
        }
        log.info("从kafka数据中获取到的动态-----窗口大小：【" + realSize + "】");
        log.info("从kafka数据中获取到的动态-----滑动步长：【" + realSlide + "】");
        log.info("初始化-----窗口大小：【" + size + "】");
        log.info("初始化-----滑动步长：【" + slide + "】");
        if (timestamp > Long.MIN_VALUE) {
            List<TimeWindow> windows = new ArrayList<>((int) ((realSize == 0? size : realSize) / (realSlide == 0? slide:realSlide)));
            long lastStart = TimeWindow.getWindowStartWithOffset(timestamp, offset, (realSlide == 0? slide:realSlide));
            for (long start = lastStart; start > timestamp - (realSize == 0? size : realSize); start -= (realSlide == 0? slide:realSlide)) {
                log.info("开窗开始时间：【" + start + "】");
                log.info("开窗结束时间：【" + start + (realSize == 0? size : realSize) + "】");
                windows.add(new TimeWindow(start, start + (realSize == 0? size : realSize)));
            }
            log.info("开窗的集合对象：【" + windows + "】");
            return windows;
        } else {
            throw new RuntimeException(
                    "Record has Long.MIN_VALUE timestamp (= no timestamp marker). "
                            + "Is the time characteristic set to 'ProcessingTime', or did you forget to call "
                            + "'DataStream.assignTimestampsAndWatermarks(...)'?");
        }
    }

    public long getSize() {
        return size;
    }

    public long getSlide() {
        return slide;
    }

    @Override
    public Trigger<Object, TimeWindow> getDefaultTrigger(StreamExecutionEnvironment env) {
        return EventTimeTrigger.create();
    }

    @Override
    public String toString() {
        return "SlidingEventTimeWindows(" + size + ", " + slide + ")";
    }

    /**
     * Creates a new {@code SlidingEventTimeWindows} {@link WindowAssigner} that assigns elements to
     * sliding time windows based on the element timestamp.
     *
     * @param size The size of the generated windows.
     * @param slide The slide interval of the generated windows.
     * @return The time policy.
     */
    public static DynSlidingEventTimeWindowsService of(Time size, Time slide) {
        return new DynSlidingEventTimeWindowsService(size.toMilliseconds(), slide.toMilliseconds(), 0);
    }

    /**
     * Creates a new {@code SlidingEventTimeWindows} {@link WindowAssigner} that assigns elements to
     * time windows based on the element timestamp and offset.
     *
     * <p>For example, if you want window a stream by hour,but window begins at the 15th minutes of
     * each hour, you can use {@code of(Time.hours(1),Time.minutes(15))},then you will get time
     * windows start at 0:15:00,1:15:00,2:15:00,etc.
     *
     * <p>Rather than that,if you are living in somewhere which is not using UTC±00:00 time, such as
     * China which is using UTC+08:00,and you want a time window with size of one day, and window
     * begins at every 00:00:00 of local time,you may use {@code of(Time.days(1),Time.hours(-8))}.
     * The parameter of offset is {@code Time.hours(-8))} since UTC+08:00 is 8 hours earlier than
     * UTC time.
     *
     * @param size The size of the generated windows.
     * @param slide The slide interval of the generated windows.
     * @param offset The offset which window start would be shifted by.
     * @return The time policy.
     */
    public static DynSlidingEventTimeWindowsService of(Time size, Time slide, Time offset) {
        return new DynSlidingEventTimeWindowsService(
                size.toMilliseconds(), slide.toMilliseconds(), offset.toMilliseconds());
    }



    @Override
    public TypeSerializer<TimeWindow> getWindowSerializer(ExecutionConfig executionConfig) {
        return new TimeWindow.Serializer();
    }

    @Override
    public boolean isEventTime() {
        return true;
    }
}
