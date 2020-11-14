package com.louis.rmi.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author louis
 * <p>
 * Date: 2019/7/30
 * Description:
 */
public class RegistryService {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            HelloRegistryFacade facade = new HelloRegistryFacadeImpl();
            registry.rebind("helloRegistry",facade);
            System.out.println("启动 RMI 服务成功");

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
