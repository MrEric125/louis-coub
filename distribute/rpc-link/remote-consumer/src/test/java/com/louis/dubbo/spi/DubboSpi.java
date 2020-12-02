package com.louis.dubbo.spi;

import com.louis.remote.api.Robots;
import org.apache.dubbo.common.extension.ExtensionLoader;


/**
 * @author jun.liu
 * @since 2020/12/2 19:51
 */
public class DubboSpi {

    public static void main(String[] args) {
        ExtensionLoader<Robots> extensionLoader = ExtensionLoader.getExtensionLoader(Robots.class);
        Robots bumblebee = extensionLoader.getExtension("bumblebee");
        bumblebee.sayHello();

    }
}
