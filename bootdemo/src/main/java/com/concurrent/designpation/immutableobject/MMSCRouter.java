package com.concurrent.designpation.immutableobject;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author John·Louis
 * @date created on 2020/2/2
 * description:
 *
 * {@link ImmutableObject}
 */
public class MMSCRouter {

    /**
     * volatile保证在多线程情况下的可见性
     */
    private static volatile MMSCRouter instance = new MMSCRouter();

    private final Map<String, MMSCInfo> routeMap;

    public MMSCRouter() {
        this.routeMap = MMSCRouter.retrieveRouteMapFromDB();
    }

    /**
     * 从数据库获取的数据
     * @return
     */
    private static Map<String, MMSCInfo> retrieveRouteMapFromDB() {
        Map<String, MMSCInfo> map = Maps.newHashMap();
        map.put("test", new MMSCInfo("testdevice", "testURL", 10));
        return map;
    }

    /**
     * 根据手机号码前缀获取响应的彩信中心信息
     * @param msisdnPrefix 手机号码前缀
     * @return 彩信中心信息
     */
    public MMSCInfo getMMSC(String msisdnPrefix) {
        return routeMap.get(msisdnPrefix);
    }

    /**
     * 将当前MMSCRourter的实例更新为指定的新实例
     * @param newInstance
     */
    public static void setInstance(MMSCRouter newInstance) {
        instance = newInstance;
    }

    private static Map<String, MMSCInfo> deepCop(Map<String, MMSCInfo> routeMap) {
        return routeMap.keySet().stream().collect(Collectors.toMap(x -> x, y -> new MMSCInfo(routeMap.get(y))));
    }

    public Map<String,MMSCInfo> getRouteMap() {
//        做防御性复制
        return Collections.unmodifiableMap(deepCop(routeMap));
    }




}
