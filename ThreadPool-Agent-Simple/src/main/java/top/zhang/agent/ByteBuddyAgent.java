package top.zhang.agent;

import io.netty.channel.nio.NioEventLoopGroup;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;
import top.zhang.agent.advice.jdk.ThreadPoolExecutorConstructorAdvice;
import top.zhang.agent.advice.jdk.ThreadPoolExecutorDestroyAdvice;
import top.zhang.agent.advice.jdk.ThreadPoolExecutorExecuteAdvice;
import top.zhang.agent.advice.netty.NioEventLoopGroupConstructorAdvice;

import java.lang.instrument.Instrumentation;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 98549
 * @date 2022/10/6 1:17
 */
public class ByteBuddyAgent {

    public static void agentmain(String args, Instrumentation inst) {
        premain(args, inst);
    }

    public static void premain(String args, Instrumentation instrumentation) {
        try {
            threadPoolExecutor(instrumentation);
            nioEventLoopGroup(instrumentation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void nioEventLoopGroup(Instrumentation instrumentation) {
        new AgentBuilder.Default()
                .disableClassFormatChanges()
                .ignore(ElementMatchers.noneOf(NioEventLoopGroup.class))
                .with(AgentBuilder.InitializationStrategy.NoOp.INSTANCE)
                .with(AgentBuilder.RedefinitionStrategy.REDEFINITION)
                .with(AgentBuilder.TypeStrategy.Default.REDEFINE)
                .with(AgentBuilder.InjectionStrategy.UsingUnsafe.INSTANCE)
                .type(ElementMatchers.is(NioEventLoopGroup.class))
                .transform((builder, typeDescription, classLoader, javaModule) ->
                        builder.visit(Advice.to(NioEventLoopGroupConstructorAdvice.class)
                                        .on(ElementMatchers.isConstructor()))
                )
                .installOn(instrumentation);
    }


    private static void threadPoolExecutor(Instrumentation instrumentation) {
        new AgentBuilder.Default()
                .disableClassFormatChanges()
                //默认是不对bootstrap类加载器加载的对象instrumentation，忽略某个type后，就可以了
                .ignore(ElementMatchers.not(ElementMatchers.hasSuperType(ElementMatchers.is(ThreadPoolExecutor.class))))
                .with(AgentBuilder.InitializationStrategy.NoOp.INSTANCE)
                .with(AgentBuilder.RedefinitionStrategy.REDEFINITION)
                .with(AgentBuilder.TypeStrategy.Default.REDEFINE)
                .with(AgentBuilder.InjectionStrategy.UsingUnsafe.INSTANCE)
                .type(ElementMatchers.is(ThreadPoolExecutor.class))
                .transform((builder, typeDescription, classLoader, javaModule) ->
                        builder.visit(Advice.to(ThreadPoolExecutorDestroyAdvice.class)
                                        .on(ElementMatchers.named("finalize")
                                                .or(ElementMatchers.named("shutdown")
                                                        .or(ElementMatchers.named("shutdownNow")))))
                                .visit(Advice.to(ThreadPoolExecutorExecuteAdvice.class)
                                        .on(ElementMatchers.named("execute")))
                                .visit(Advice.to(ThreadPoolExecutorConstructorAdvice.class)
                                        .on(ElementMatchers.isConstructor()))
                )
                .installOn(instrumentation);
    }


}
