package top.zhang.wrapper;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 98549
 * @date 2022/10/7 10:59
 */
public class ThreadPoolExecutorWrapper extends ThreadPoolWrapper{
    @JsonSerialize(using = NullSerializer.class)
    private static Map<Integer,ThreadPoolExecutorWrapper> map = new ConcurrentHashMap<>();
    @JsonSerialize(using = NullSerializer.class)
    private final ThreadPoolExecutor threadPoolExecutor;

    private final int hashCode;
    private AtomicInteger rejectCount;
    private boolean running;

    public static ThreadPoolExecutorWrapper getInstance(ThreadPoolExecutor threadPoolExecutor){
        ThreadPoolExecutorWrapper threadPoolExecutorWrapper = map.get(threadPoolExecutor.hashCode());
        if(threadPoolExecutorWrapper==null){
            threadPoolExecutorWrapper = new ThreadPoolExecutorWrapper(threadPoolExecutor);
            map.put(threadPoolExecutor.hashCode(),threadPoolExecutorWrapper);
        }
        return threadPoolExecutorWrapper;
    }

    private ThreadPoolExecutorWrapper(ThreadPoolExecutor threadPoolExecutor){
        this.threadPoolExecutor = threadPoolExecutor;
        this.hashCode = threadPoolExecutor.hashCode();
        this.running = !threadPoolExecutor.isShutdown();
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
    public void reject() {
        this.rejectCount.incrementAndGet();
    }


    @Override
    public int getHashCode() {
        return this.hashCode;
    }
}
