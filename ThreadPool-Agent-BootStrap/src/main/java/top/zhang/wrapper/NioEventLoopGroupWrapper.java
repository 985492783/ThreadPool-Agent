package top.zhang.wrapper;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import io.netty.channel.nio.NioEventLoopGroup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 98549
 * @date 2022/10/7 11:38
 */
public class NioEventLoopGroupWrapper extends ThreadPoolWrapper {
    @JsonSerialize(using = NullSerializer.class)
    private static Map<Integer,NioEventLoopGroup> map = new ConcurrentHashMap<>();
    @JsonSerialize(using = NullSerializer.class)
    private final NioEventLoopGroup nioEventLoopGroup;

    private final int hashCode;
    private boolean running;
    private AtomicInteger rejectCount;

    private NioEventLoopGroupWrapper(NioEventLoopGroup nioEventLoopGroup){
        this.nioEventLoopGroup = nioEventLoopGroup;
        this.running = !nioEventLoopGroup.isShutdown();
        this.hashCode = nioEventLoopGroup.hashCode();
    }

    public static ThreadPoolWrapper getInstance(NioEventLoopGroup nioEventLoopGroup) {
        return new NioEventLoopGroupWrapper(nioEventLoopGroup);
    }


    @Override
    public Executor getExecutor() {
        return this.nioEventLoopGroup;
    }

    @Override
    public int getMaximumPoolSize() {
        return this.nioEventLoopGroup.executorCount();
    }

    @Override
    public boolean isRunning() {
        return running;
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
