package com.louis.coub.puborsub;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;

@Slf4j
public class Producer implements Runnable, Watcher{

    private ZooKeeper zk;



    public Producer(String address) {
        try {
            this.zk = new ZooKeeper(address, 3000, this);
        } catch (Exception e) {
            log.info("启动zk异常：", e);
        }
    }

    @Override
    public void run() {
        int i = 0;
        //每隔10s向队列中放入数据
        while (true){
            try {
                zk.create("/zookeeper/queue/queue-",(Thread.currentThread().getName()+"-"+i).getBytes(),
                        ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT_SEQUENTIAL);
                Thread.sleep(10000);
                i++;
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void process(WatchedEvent watchedEvent) {

    }
}
