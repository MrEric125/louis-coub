package provider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jun.liu
 * @date created on 2020/7/19
 * description:
 */
public class LocalRegister {


    private static Map<String, Class> map = new ConcurrentHashMap<>();

    public static void regist(String interfaceName, Class implClass) {
        map.put(interfaceName, implClass);
    }

    public static Class get(String interfaceName) {
        return map.get(interfaceName);
    }
}
