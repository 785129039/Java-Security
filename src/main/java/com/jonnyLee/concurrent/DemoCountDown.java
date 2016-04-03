package com.jonnyLee.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * copyright    <a href="http://www.ypxgt.com/">一品效果图</a>
 * <pre>
 *     author      jonnyLee
 *     date        3/28/16
 *     email       2355952129qq.com
 *     desc
 * </pre>
 */
public class DemoCountDown {
    public static void main(String[] args) throws InterruptedException {
        final int count = 20;

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i=0; i<10; i++) {
            final int finalI = i;
            executorService.execute(new Runnable() {
                public void run() {
                    System.out.println(finalI);
                }
            });
        }


        final CountDownLatch l = new CountDownLatch(count);
        for(int i = 0; i < count; ++i)
        {
            final int index = i;
            new Thread(new Runnable() {

                public void run() {

                    try {
                        Thread.currentThread().sleep(1 * 1000);
                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }

                    System.out.println("thread " + index + " has finished...");

                    l.countDown();

                }
            }).start();
        }

        try {
            l.await();
        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        System.out.println("now all threads have finished");

    }
}
