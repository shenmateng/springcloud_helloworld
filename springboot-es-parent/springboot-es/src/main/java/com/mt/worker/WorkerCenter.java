package com.mt.worker;

import com.mt.config.AssetConfig;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class WorkerCenter {
    private ThreadPoolExecutor threadPoolExecutor;
    private static WorkerCenter workerCenter = new WorkerCenter();


    public static WorkerCenter singleton(){
        return workerCenter;
    }

    public void init(){
        threadPoolExecutor = new ThreadPoolExecutor(
                AssetConfig.TASK_THREAD_CORE_SIZE,
                AssetConfig.TASK_THREAD_MAX_SIZE,
                1000L,
                TimeUnit.MILLISECONDS,
                new LimitLinkedBlockingQueue<Runnable>(AssetConfig.TASK_THREAD_QUEUE_SIZE),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }


    public void put(Runnable runnable) {
        threadPoolExecutor.execute(runnable);
    }

    public void setCorePoolSize(int corePoolSize){
        threadPoolExecutor.setCorePoolSize(corePoolSize);
    }

    public void setMaximumPoolSize(int maximumPoolSize){
        threadPoolExecutor.setMaximumPoolSize(maximumPoolSize);
    }

    public int getBlockQueueSize(){
        return threadPoolExecutor.getQueue().size();
    }

    public Future<?> submit(Runnable task){
        return threadPoolExecutor.submit(task);
    }


    public <T> Future<T> submit(Callable<T> task){
        return threadPoolExecutor.submit(task);
    }
}
