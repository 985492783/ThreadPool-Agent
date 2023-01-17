# Thread-Pool-Agent (TPA)
#### 监控线程池，通过java agent监控线程池，动态改变线程池参数  
项目结构  
>|--bootStrap 使用bootStrap类加载器加载，达到在线程池类加载之前注入的功能  
>|--common 线程池类封装wrapper类，便于后期数据传输  
>|--console 控制台  
>|--core agent核心，对线程池的方法进行注入


### 使用方法
package或者下载拿到core包以及bootStrap包  
在启动类加上此参数  
> -javaagent:core.jar(core包路径)  
> -Xbootclasspath/a:bootStrap.jar(bootStrap包路径)
