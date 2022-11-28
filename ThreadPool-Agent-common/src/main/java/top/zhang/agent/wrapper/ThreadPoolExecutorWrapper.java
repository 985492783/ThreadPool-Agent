package top.zhang.agent.wrapper;


import top.zhang.ThreadPoolMonitorData;

import java.io.Serializable;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 98549
 * @date 2022/10/7 10:59
 */
public class ThreadPoolExecutorWrapper extends ThreadPoolWrapper {

    private transient ThreadPoolExecutor threadPoolExecutor;

    private Class<?> className;
    private int hashCode;
    private AtomicInteger rejectCount;
    private boolean running;
    private long completedTaskCount;

    private ThreadPoolExecutorWrapper(){
    }
    public static ThreadPoolExecutorWrapper getInstance(ThreadPoolExecutor threadPoolExecutor){
        ThreadPoolExecutorWrapper threadPoolExecutorWrapper = (ThreadPoolExecutorWrapper) map.get(threadPoolExecutor.hashCode());
        if(threadPoolExecutorWrapper==null){
            threadPoolExecutorWrapper = new ThreadPoolExecutorWrapper(threadPoolExecutor);
            map.put(threadPoolExecutor.hashCode(),threadPoolExecutorWrapper);
        }
        threadPoolExecutorWrapper.running = true;
        return threadPoolExecutorWrapper;
    }

    private ThreadPoolExecutorWrapper(ThreadPoolExecutor threadPoolExecutor){
        this.threadPoolExecutor = threadPoolExecutor;
        this.hashCode = threadPoolExecutor.hashCode();
        this.running = !threadPoolExecutor.isShutdown();
        this.rejectCount = ThreadPoolMonitorData.getReject(threadPoolExecutor);
        this.className = threadPoolExecutor.getClass();
        this.completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
    }


    @Override
    public Executor getExecutor() {
        return this.threadPoolExecutor;
    }

    @Override
    public int getMaximumPoolSize() {
        return this.threadPoolExecutor.getMaximumPoolSize();
    }

    @Override
    public boolean isRunning() {
        return this.running;
    }

    public AtomicInteger getRejectCount() {
        return rejectCount;
    }

    @Override
    public void down() {
        this.running = false;
    }


    @Override
    public int getHashCode() {
        return this.hashCode;
    }

    public Class<?> getClassName() {
        return className;
    }

    public long getCompletedTaskCount() {
        this.completedTaskCount = this.threadPoolExecutor.getCompletedTaskCount();
        return this.completedTaskCount;
    }
}
