package com.mt.user.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：libin
 * @version ：
 * @program ：dnst
 * @date ：Created in 2021/1/5 10:41
 * @description ：jmm 内存模型   volatile 关键字
 */
public class JmmTest {


    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger(10);

        Thread aaa = new Thread(()->{
            int a = 0;
            while (a<10){
                i.getAndIncrement();
            }
        },"aaa");
        Thread bbb = new Thread(()->{
            int a = 0;
            while (a<10){
                i.getAndIncrement();
            }
        },"bbb");
        Thread ccc = new Thread(()->{
            int a = 0;
            while (a<10){
                i.getAndIncrement();
            }
        },"ccc");

        aaa.start();
        bbb.start();
        ccc.start();


    }
}
