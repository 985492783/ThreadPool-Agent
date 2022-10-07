package top.zhang.wrapper;

import java.util.concurrent.Executor;

/**
 * @author 98549
 * @date 2022/10/7 10:58
 */
public abstract class ThreadPoolWrapper {

    public abstract Executor getExecutor();

    public abstract int getMaximumPoolSize();

    public abstract boolean isRunning();

    public abstract void down();

    public abstract void reject();

    public abstract int getHashCode();
}
