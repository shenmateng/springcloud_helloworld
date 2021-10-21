package com.mt.user.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author ：libin
 * @version ：
 * @description ：
 * @program ：dnst
 * @date ：Created in 2021/4/1 8:39
 * @since ：
 */
@Slf4j
public class MoreThread {

    private  volatile String Viname;
  

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        String str = "======================================================================================================================" +
                "============================================================================";

        CountDownLatch countDownLatch = new CountDownLatch(3);

        MyCallAble m1 = new MyCallAble("m1", 1000L, countDownLatch);
        MyCallAble m2 = new MyCallAble("m2", 2000L, countDownLatch);
        MyCallAble m3 = new MyCallAble("m3", 3000L, countDownLatch);

        Future<String> s1 = ThreadPoolManager.submit(m1);
        Future<String> s2 = ThreadPoolManager.submit(m2);
        Future<String> s3 = ThreadPoolManager.submit(m3);
        //通过futureTask对象的get方法来接收futureTask的值
        FutureTask futureTask = new FutureTask(m1);
        log.info("任务提交完成,主线程开始等待");
        countDownLatch.await();
        log.info("等待完成,主线程继续");
        String res1 = s1.get();
        String res2 = s2.get();
        String res3 = s3.get();

        log.info(res1 + res2 + res3);
        ExecutorService executorService = Executors.newWorkStealingPool();


    }
}
