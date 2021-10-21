/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */

package com.mt.user.thread;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/**
 * @author ：libin
 * @version ：B1.6.0
 * @description ：callable 抽象类 构造方法中传递参数  子类构造方法中不可删除super
 * @program ：bmp
 * @date ：Created in 2021/6/3 19:46
 * @since ：
 */
@Slf4j
public abstract class BaseCallable<T,R> implements Callable<R> {

    private CountDownLatch countDownLatch;
    private T t;

    public BaseCallable(CountDownLatch countDownLatch ,T t) {
        this.countDownLatch = countDownLatch;
        this.t = t;
    }

    @Override
    public R call() {
        try {
            return asyncMethod();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(ExceptionUtils.getStackTrace(e));
        } finally {
            this.countDownLatch.countDown();
            Thread.currentThread().interrupt();
        }
        return null;
    }

    protected abstract R asyncMethod();


    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    public T getParam() {
        return t;
    }

    public void setParam(T t) {
        this.t = t;
    }
}
