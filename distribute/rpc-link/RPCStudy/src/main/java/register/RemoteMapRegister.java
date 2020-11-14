package register;

import framework.RemoteURL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jun.liu
 * @date created on 2020/7/19
 * description:
 */
public class RemoteMapRegister {

    private static Map<String, List<RemoteURL>> REGISTER = new ConcurrentHashMap<>();


    public static void regist(String interfaceName, RemoteURL url) {

        List<RemoteURL> remoteURLS = Optional.ofNullable(REGISTER.get(interfaceName))
                .filter(item -> item.stream()
                                .allMatch(u -> u.getHostname().equals(url.getHostname())))
                .orElse(new ArrayList<>());



        remoteURLS.add(url);

        REGISTER.put(interfaceName, remoteURLS);

    }
}
