package com.louis.longagocode.louis.rmi.server;

import com.louis.rmi.server.HelloRegistryFacade;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author louis
 * <p>
 * Date: 2019/7/30
 * Description:
 */
public class HelloRegistryFacadeImpl extends UnicastRemoteObject implements HelloRegistryFacade {


    public HelloRegistryFacadeImpl() throws RemoteException {

    }

    @Override
    public String helloWorld(String name) {
        return "[Registry] 你好! " + name;
    }
}
