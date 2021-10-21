package com.mt.user.thread;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * @author ：libin
 * @version ：
 * @description ：
 * @program ：dnst
 * @date ：Created in 2021/4/1 13:57
 * @since ：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class MyCallAble implements Callable<String> {

    private String name;
    private Long sleepTime;
    private CountDownLatch countDownLatch;

    @Override
    public String call() {
        try {
            log.info(name + "sleep" + sleepTime);
            Thread.sleep(sleepTime);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(name + "sleep_end");
        countDownLatch.countDown();
        return name;
    }
}
