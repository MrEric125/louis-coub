package com.louis.rmi;

import com.louis.rmi.server.HelloRegistryFacade;
import lombok.extern.slf4j.Slf4j;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author louis
 * <p>
 * Date: 2019/7/30
 * Description:
 */
@Slf4j
public class RegistryClient {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(1099);
            HelloRegistryFacade helloRegistry = (HelloRegistryFacade) registry.lookup("helloRegistry");
            String response = helloRegistry.helloWorld("zhangsan");
            log.info("response:{}", response);

        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }


}
