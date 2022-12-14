package top.zhang.agent;

import io.netty.channel.nio.NioEventLoopGroup;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;
import top.zhang.agent.advice.jdk.ThreadPoolExecutorConstructorAdvice;
import top.zhang.agent.advice.jdk.ThreadPoolExecutorDestroyAdvice;
import top.zhang.agent.advice.jdk.ThreadPoolExecutorExecuteAdvice;
import top.zhang.agent.advice.jdk.ThreadPoolExecutorRejectAdvice;
import top.zhang.agent.advice.netty.NioEventLoopGroupConstructorAdvice;
import top.zhang.agent.server.MyHttpServer;

import java.lang.instrument.Instrumentation;
import java.util.Arrays;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 98549
 * @date 2022/10/6 1:17
 */
public class ByteBuddyAgent {

    private static int port=9854;
    private static String token=null;
    private static MyHttpServer myHttpServer=new MyHttpServer();
    public static void agentmain(String args, Instrumentation inst) {
        premain(args, inst);
    }

    public static void premain(String args, Instrumentation instrumentation) {
        init(args);
        myHttpServer.bind(port);
        myHttpServer.print();
        try {
            threadPoolExecutor(instrumentation);
            nioEventLoopGroup(instrumentation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void init(String args) {
        String[] split = args.split("&");
        Arrays.stream(split).forEach((s)->{
            String[] arg = s.split("=");
            if(arg.length==2){
                initConfig(arg);
            }
        });
    }

    private static void initConfig(String[] arg) {
        if(arg[0].equals("port")&&port==9854){
            port = Integer.parseInt(arg[1]);
        }
        if(arg[0].equals("token") && token==null){
            token = arg[1];
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
                //???????????????bootstrap???????????????????????????instrumentation???????????????type??????????????????
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
                                .visit(Advice.to(ThreadPoolExecutorRejectAdvice.class)
                                        .on(ElementMatchers.named("reject")))
                )
                .installOn(instrumentation);
    }

    public static String getToken(){
        return token;
    }

}
