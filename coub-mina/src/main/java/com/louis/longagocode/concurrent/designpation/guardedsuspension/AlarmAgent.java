package com.louis.longagocode.concurrent.designpation.guardedsuspension;



import java.util.Timer;

/**
 * @author John·Louis
 * @date created on 2020/2/15
 * description:
 * 负责谅解告警服务器，并发送告警信息至告警服务器
 */
public class AlarmAgent {

    //用于记录AlarmAgent是否连接上告警服务器
    private volatile boolean connectedToServer = false;

    //    模式角色：GuardedSuspension.predicate
    private final Predicate agentConnected = new Predicate() {
        @Override
        public boolean evaluate() {
            return connectedToServer;
        }
    };

//    心跳定时器
    private final Timer heartbeatTimer = new Timer(true);

    //    模式角色：
//    GuardedSuspension.Blocker
    private final Blocker blocker = new ConditionVarBlocker();





}
