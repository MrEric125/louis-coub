package com.louis.dubbo.spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.ExtensionLoader;

import java.util.List;


/**
 * dubbo 中
 *
 * @see Adaptive
 * @see org.apache.dubbo.common.extension.Activate
 * @see org.apache.dubbo.common.extension.SPI
 * @see ExtensionLoader#getExtension(String)
 * @see ExtensionLoader#getActivateExtension(URL, String)
 * @see ExtensionLoader#getAdaptiveExtension()
 */
//@Adaptive
public class DubboSPIServiceImpl1 implements DubboSPIService {
    @Override
    public void printInfo() {
        System.out.println("dubbo spi");
    }

    public static void main(String[] args) {
        ExtensionLoader<DubboSPIService> extensionLoader = ExtensionLoader.getExtensionLoader(DubboSPIService.class);
        extensionLoader.getDefaultExtension().printInfo();
        String extensionName = "impl";
        List<DubboSPIService> activateExtension = extensionLoader.getActivateExtension(URL.valueOf(extensionName), extensionName);
        activateExtension.forEach(System.out::println);

    }
}
