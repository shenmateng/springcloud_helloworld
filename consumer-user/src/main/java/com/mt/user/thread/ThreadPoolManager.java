/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */

package com.mt.user.thread;

import org.springframework.util.ObjectUtils;

import java.util.concurrent.*;

/**
 * @author ：libin
 * @version ：
 * @description ：execute会直接抛出任务执行时的异常，submit会吃掉异常，可通过Future的get方法将任务执行时的异常重新抛出
 * @program ：dnst
 * @date ：Created in 2021/4/1 13:49
 * @since ：
 */
public class ThreadPoolManager {
    private ThreadPoolManager() {
    }

    public static ThreadPool instance;

    /**
     * 获取单例的线程池对象
     *
     * @return 线程池
     */
    public static synchronized ThreadPool getInstance() {

        if (ObjectUtils.isEmpty(instance)) {
            // 获取处理器数量
            int cpuNum = Runtime.getRuntime().availableProcessors();
            // 根据cpu数量,计算出合理的线程并发数
//                    CPU密集型应用，则线程池大小设置为N+1
//                    IO密集型应用，则线程池大小设置为2N+1
            int threadNum = cpuNum + 1;
            //默认是双核的cpu 每个核心走一个线程 一个等待线程
            instance = new ThreadPool(threadNum, threadNum, Integer.MAX_VALUE);

        }
        return instance;
    }

    public static class ThreadPool {
        private ThreadPoolExecutor mExecutor;

        private ThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime) {

            mExecutor = new ThreadPoolExecutor(corePoolSize,// 核心线程数
                    maximumPoolSize, // 最大线程数
                    keepAliveTime, // 闲置线程存活时间
                    TimeUnit.MILLISECONDS,// 时间单位
                    new LinkedBlockingDeque<Runnable>(1000),// 线程队列
                    Executors.defaultThreadFactory(),// 线程工厂
                    new ThreadPoolExecutor.AbortPolicy() {// 队列已满,而且当前线程数已经超过最大线程数时的异常处理策略
                        @Override
                        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                            super.rejectedExecution(r, e);
                        }
                    }
            );
        }
    }


    /**
     * cancel
     *
     * @param runnable 移除
     */
    public static Boolean cancel(Runnable runnable) {
        return getInstance().mExecutor.getQueue().remove(runnable);
    }

    /**
     * sbumit
     *
     * @param runnable runable
     */
    public static void submit(Runnable runnable) {
        getInstance().mExecutor.submit(runnable);
    }

    /**
     * sbumit callback
     *
     * @param callable callable
     * @param <T>      返回值类型
     * @return future
     */
    public static <T> Future<T> submit(Callable<T> callable) {
        return getInstance().mExecutor.submit(callable);
    }

    /**
     * execute
     *
     * @param runnable runnable
     */
    public static void execute(Runnable runnable) {
        getInstance().mExecutor.execute(runnable);
    }

}
