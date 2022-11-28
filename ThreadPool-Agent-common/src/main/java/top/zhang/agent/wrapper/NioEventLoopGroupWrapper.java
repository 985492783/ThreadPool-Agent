package top.zhang.agent.wrapper;

import io.netty.channel.nio.NioEventLoopGroup;
import top.zhang.ThreadPoolMonitorData;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 98549
 * @date 2022/10/7 11:38
 */
public class NioEventLoopGroupWrapper extends ThreadPoolWrapper {

    private transient NioEventLoopGroup nioEventLoopGroup;

    private Class<?> className;
    private int hashCode;
    private boolean running;
    private AtomicInteger rejectCount;

    private NioEventLoopGroupWrapper(){
    }

    private NioEventLoopGroupWrapper(NioEventLoopGroup nioEventLoopGroup){
        this.nioEventLoopGroup = nioEventLoopGroup;
        this.running = !nioEventLoopGroup.isShutdown();
        this.hashCode = nioEventLoopGroup.hashCode();
        this.rejectCount = ThreadPoolMonitorData.getReject(nioEventLoopGroup);
        this.className = nioEventLoopGroup.getClass();
    }

    public static ThreadPoolWrapper getInstance(NioEventLoopGroup nioEventLoopGroup) {
        NioEventLoopGroupWrapper nioEventLoopGroupWrapper = (NioEventLoopGroupWrapper) map.get(nioEventLoopGroup.hashCode());
        if(nioEventLoopGroupWrapper==null){
            nioEventLoopGroupWrapper = new NioEventLoopGroupWrapper(nioEventLoopGroup);
            map.put(nioEventLoopGroup.hashCode(),nioEventLoopGroupWrapper);
        }
        nioEventLoopGroupWrapper.running = true;
        return nioEventLoopGroupWrapper;
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
    public int getHashCode() {
        return this.hashCode;
    }

    public Class<?> getClassName() {
        return className;
    }

    public AtomicInteger getRejectCount() {
        return rejectCount;
    }
}
