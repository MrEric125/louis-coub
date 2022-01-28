B+tree dgree 为3的时候，总共能放多少数据

1.假设索引为Bigint 8byte

一个页有16k   大概能放1170个索引

总共数据能放的数据为1170 * 1170 * 16=2kw,

为什么每个页的数据不要太大，因为每次会把当前page load 到内存中，如果太大，占用内存太多，就很划不来了

聚集索引可以理解为索引与实际数据设计在一块


mac wifi 异常日志

Tue Oct 26 20:45:50.259 <kernel> installGTK: GTK installed
Tue Oct 26 20:45:54.289 <airport[170]> __enableTemporarilyDisabledNetworks: FAILED to complete operation within 4.0s, continuing
Tue Oct 26 20:45:54.296 <airport[170]> ERROR: rapportd (425) is not entitled for com.apple.wifi.join_history, will not allow request
Tue Oct 26 20:45:54.296 <airport[170]> ERROR: sharingd (492) is not entitled for com.apple.wifi.join_history, will not allow request
Tue Oct 26 20:49:34.410 <airport[170]> psCallback: set powersave(7) failed, 5 (Input/output error)
Tue Oct 26 20:50:23.646 <kernel> Unexpected payload found for message 4, dataLen 0

如何读懂mac 异常日志

1. spring 对事务支持原理，以及数据库事务原理，to 文档

2. zk 短时间down了，consumer 也能够调用消费者，为什么，原理，在什么地方。

3. mysql 优化分为那几个层面

----

调用interrupt()方法仅仅是在当前线程中打了一个停止的标记，并不是真正的停止线程

interrupted()测试当前线程是否已经是中断状态，执行后具有清除中断状态flag的功能

isInterrupted()测试线程Thread对象是否已经是中断状态，但不清除中断状态flag

运行命令：
java.exe -agentlib:jdwp=transport=dt_socket,address=localhost:51973,suspend=y,server=n 
-XX:tieredStopAtLevel=1
-javaAgent:C:\Users....
-Dcom.sun.management.jmxremote 
-Dspring.jmx.enabled=true
-Dfile.encoding=UTF-8
-classpath "D:\..."
