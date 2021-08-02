package com.zbs.other;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * description: ThreadPoolTest
 * date: 2021/7/29 10:07
 * author: zhangbs
 * version: 1.0
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        //        ThreadPoolExecutor
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        for (int i = 1; i < 6; i++) {
            int threadNum = i;
            /**
             * execute和submit都属于线程池的方法，execute只能提交Runnable类型的任务，而submit既能提交Runnable类型任务也能提交Callable类型任务。
             * execute会直接抛出任务执行时的异常，submit会吃掉异常，可通过Future的get方法将任务执行时的异常重新抛出。             *
             * execute所属顶层接口是Executor,submit所属顶层接口是ExecutorService，实现类ThreadPoolExecutor重写了execute方法,抽象类AbstractExecutorService重写了submit方法。
             */
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("正在执行线程：第" + threadNum + "个线程；" + Thread.currentThread().getName());
                }
            });
        }
        threadPool.shutdown();

    }

    /**
     * 自建线程池
     */
    @Test
    public void testPool() {
        // 方便设计线程名字demo-pool-%d
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(6, 10,
                120, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy()
        );
        for (int i = 1; i < 6; i++) {
            int threadNum = i;
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "执行线程" + threadNum);
                }
            });
        }
        threadPoolExecutor.shutdown();
    }
}
