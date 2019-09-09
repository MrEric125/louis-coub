package com.louis.rmi.server;

import java.rmi.Remote;

/**
 * @author louis
 * <p>
 * Date: 2019/7/30
 * Description:
 */
public interface HelloRegistryFacade extends Remote {

    String helloWorld(String name);
}
