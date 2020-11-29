spring boot 容器启动过程
1. springApplication.run()
  在SpringApplication(ResourceLoader,primarySources)这个构造方法执行的时候，主要做的两件事

  1. 指定启动ApplicationType是什么，一般是servlet

  2. setInitializers(Collection)

    1. 解析`META-INF/spring.factories`下的对应类名，将其缓存起来，后期实例化这些factoryName,

      ![image-20201128132136722](C:\Users\Eric\AppData\Roaming\Typora\typora-user-images\image-20201128132136722.png)

    2. 在`createSpringFactoriesInstances(type, parameterTypes, classLoader, args, names);`将其实例化成相关对象，然后，放在Initializers这个容器中，

       一般情况下，加载的屙屎这几个类

       1. org.springframework.boot.context.ConfigurationWarningsApplicationContextInitializer
       2. org.springframework.boot.context.ContextIdApplicationContextInitializer
       3. org.springframework.boot.context.config.DelegatingApplicationContextInitializer
       4. org.springframework.boot.web.context.ServerPortInfoApplicationContextInitializer
       5. org.springframework.boot.autoconfigure.SharedMetadataReaderFactoryContextInitializer
       6. org.springframework.boot.autoconfigure.logging.ConditionEvaluationReportLoggingListener

  3. setListeners(Collection)

    着重看看`getSpringFactoriesInstances()`这个方法，我看好多地方都在重用这个方法，但是功能还不完全相同

    

2. 执行run

   1. 启动时间监控

      `StopWatch`来操作

   2. 启动listeners

   3. 设置context

   4. freshContext()

   5. afterRefresh()

   6. listeners.started(context)

3. 执行freshContext()
   