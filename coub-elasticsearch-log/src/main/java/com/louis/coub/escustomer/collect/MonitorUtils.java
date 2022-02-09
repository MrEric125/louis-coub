package com.louis.coub.escustomer.collect;

import com.sun.jmx.mbeanserver.JmxMBeanServer;
import lombok.extern.slf4j.Slf4j;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;

@Slf4j
public class MonitorUtils {

    /**
     * 获取本机IP地址
     *
     * @return
     */
    public static String getLocalIP() {
        return HOST_NAME;
    }

    /**
     * 返回本机IP
     */
    private static final String HOST_NAME = getInet4Address();

    private static final String network_name_bond0 = "bond0";

    private static final String network_name_eth0 = "eth0";

    private static final String network_name_eth1 = "eth1";

    private static final String network_name_eth2 = "eth2";

    private static final String LOCALHOST_IP = "127.0.0.1";
    private static final String EMPTY_IP = "0.0.0.0";
    /**
     * 虚拟网卡
     */
    private static final String virtualNetworkName = "lo:";
    /**
     * 需要过滤的IP
     */
    private static final List<String> FILTER_IP;

    /**
     * 返回本机实例编号
     */
    private static String INSTANCE_CODE = null;

    static {
        FILTER_IP = new ArrayList<>();
        FILTER_IP.add("192.168.122.1");
        FILTER_IP.add("169.254.95.120");
        FILTER_IP.add("169.254.95.118");
        FILTER_IP.add("192.168.90.15");
        //FILTER_IP.add("192.168.90.247");
        FILTER_IP.add("172.17.0.1");
        FILTER_IP.add(LOCALHOST_IP);
        FILTER_IP.add(EMPTY_IP);


    }


    public static String getInet4Address() {
        String hostName = null;
        try {
            InetAddress localAddress = InetAddress.getLocalHost();
            hostName = localAddress.getHostAddress();
        } catch (Exception e) {
            log.warn("get localhost ip on error:{}", e.getMessage());
        }
        try {

            Map<String, String> hostNames = getHostNames();


            if (hostNames.size() > 0) {
                //物理机有可能有bond0
                if (hostNames.containsKey(network_name_bond0)) {
                    return hostNames.get(network_name_bond0);
                }
                //当前物理机eth1一般是业务IP
                if (hostNames.containsKey(network_name_eth1)) {
                    return hostNames.get(network_name_eth1);
                }
                //虚拟机，容器eth0一般是业务IP
                if (hostNames.containsKey(network_name_eth0)){
                    return hostNames.get(network_name_eth0);
                }

                final String p = "172.";
                for(Map.Entry<String,String> host:hostNames.entrySet()){
                    String ip = host.getValue();
                    //网卡名称
                    String networkName = host.getKey();
                    if (!ip.startsWith(p)
                            && !FILTER_IP.contains(ip)
                            && !virtualNetworkName.startsWith(networkName)){
                        return ip;
                    }
                }


            } else {
                return hostName;
            }

        } catch (Exception e1) {
            log.warn("Error getting IP address:{}", e1.getMessage());
        }
        return hostName;
    }

    private static Map<String, String> getHostNames() throws SocketException {
        Map<String, String> hostNames = new HashMap<>();
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface network = interfaces.nextElement();
            log.debug("networkInterface name is:{},displayName is:{},isVirtual:{} ", network.getName(),
                    network.getDisplayName(), network.isVirtual());
            Enumeration<InetAddress> addresses = network.getInetAddresses();
            if (addresses != null && !network.isVirtual()) {
                while (addresses.hasMoreElements()) {
                    try {
                        InetAddress address = addresses.nextElement();
                        String ip = address.getHostAddress();

                        if ("127.0.0.1".equals(ip) || "Inet6Address".equals(address.getClass().getSimpleName())) {
                            log.debug(
                                    "IP address is not valid,host address is:{},class name is:{},networkInterface name is:{}",
                                    address.getHostAddress(), address.getClass().getSimpleName(), network.getName());
                        } else {
                            // 只取Inet4Address的地址
                            log.info(
                                    "Get to the valid IP address,host address is:{},class name is:{},networkInterface name is:{}",
                                    address.getHostAddress(), address.getClass().getSimpleName(), network.getName());
                            if (ip != null && !"".equals(ip.trim())){
                                hostNames.put(network.getName(), ip);
                            }
                            // return address.getHostAddress();
                        }
                    } catch (Throwable e) {
                        log.warn("Failed to get network card ip address. cause:{}", e.getMessage());
                    }
                }
            }
        }
        return hostNames;
    }

    /**
     * 返回JVM实例编号
     *
     * @return
     */
    public static String getJvmInstanceCode() {
        if (INSTANCE_CODE == null) {
            INSTANCE_CODE = getInstanceCode();
            System.setProperty("appInstanceCode",INSTANCE_CODE);
        }
        return INSTANCE_CODE;

    }
    private static String getInstanceCode() {
        // 先去检测当前应用是否是tomcat，如果是返回端口号
        String os = System.getProperty("os.name");
        String separator = ":";
        if (os.startsWith("Windows")){
            separator = "@";
        }
        Integer port = getTomcatPortByMBean();
        if (port != null) {
            return getLocalIP() + separator + port.toString();
        }
        int instanceValue = (getStartPath() + getApplicationPath()).hashCode();
        String instanceCode;
        if (instanceValue < 0) {
            instanceValue = Math.abs(instanceValue);
        }
        instanceCode = String.valueOf(instanceValue);
        return getLocalIP() + separator + instanceCode;
    }

    /**
     * 启动路径
     */
    private static String getStartPath() {
        String startPath = System.getProperties().get("user.dir").toString();
        startPath = startPath.replaceAll("\\\\", "/");
        return startPath;
    }

    /**
     * 工程路径（web） classes路径 （worker）
     */
    private static String getApplicationPath() {
        String classPath = Thread.currentThread().getContextClassLoader().getResource(MonitorUtils.class.getName().replaceAll("\\.","/") + ".class").getPath();
        return classPath;
    }

    /**
     * 获取当前进程的PID
     */
    public static int getPid() {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        try {
            return Integer.parseInt(name.substring(0, name.indexOf('@')));
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 从MBean获取tomcat运行端口
     *
     * @return
     */
    public static Integer getTomcatPortByMBean() {
        // final String PROTOCOL = "HTTP/1.1";

        MBeanServer mBeanServer = null;
        try {
            ArrayList<MBeanServer> mBeanServers = MBeanServerFactory.findMBeanServer(null);
            if (mBeanServers.size() > 0) {
                for (MBeanServer _mBeanServer : mBeanServers) {
                    if (_mBeanServer instanceof JmxMBeanServer) {
                        mBeanServer = _mBeanServer;
                        break;
                    }
                }
            }
            if (mBeanServer == null) {
                return null;
            }
            //标准容器采用这种
            Integer port = getTomcatPort("Catalina:type=Connector,*",mBeanServer);
            if (port != null){
                return port;
            }else{
                //spring boot采用这种
                return  getTomcatPort("Tomcat:type=Connector,*",mBeanServer);
            }

        } catch (Throwable e) {
            log.warn("dmc get app web port fail:{}",e.getMessage());
        }
        return null;
    }
    private static Integer getTomcatPort(String name,MBeanServer mBeanServer)throws Exception{
        final String schema = "http";
        Set<ObjectName> objectNames = mBeanServer.queryNames(new ObjectName(name), null);
        if (objectNames != null && objectNames.size() > 0) {
            for (ObjectName objectName : objectNames) {
                String  protocol = (String) mBeanServer.getAttribute(objectName, "protocol");
                if (protocol != null){
                    protocol = protocol.toLowerCase();
                    if (protocol.startsWith(schema)) {
                        return (Integer) mBeanServer.getAttribute(objectName, "port");
                    }
                }

            }
        }
        return null;
    }

}
