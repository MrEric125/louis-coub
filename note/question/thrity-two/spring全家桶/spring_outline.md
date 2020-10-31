### outline
 
#### spring framework
1. IOC/DI
2. AOP
    - 静态代理
    - 动态代理
3. 框架组件
    - web
    - beans
    - core
    - context
    - webflux
4. 实现方式
    - AOP
       - 静态代理
       - 动态代理
    - PlaceHolder动态替换
        - PropertyPlaceholderConfigurer
        - PropertySourcesPlaceholderConfigurer
    - 事务
        - 隔离类型
            - ISOLATION_DEFAULT
            - ISOLATION_READ-UNCOMMITED
            - ISOLATION_READ_COMMITED
            - ISOLATION_REPEATABLE_READ
        - 传播类型
            - PROPAGATION_REQUIRED
            - PROPAGATION_SUPPORTS
            - PROPAGATION_MANDATORY
            - PROPAGATION_REQUIRES_NEW
            - PROPAGATION_NOT_SUPPORTED
            - PROPAGATION_NEVER
            -PROPAGATION_NESTED
    - 核心接口
        - ApplicationContext
        - BeanFactory
        - BeanHttpResult
        - FactoryBean
    - 拓展接口
        - BeanFactoryPostProcessor 处理所有bean前，对BeanFactory进行预处理
        - BeanDefinitionRegistryPostProcessor 可以添加自定义Bean
        - BeanPostProcessor 支持在Bean初始化前、后对bean进行处理
        - ApplicationContextAware 可以获取ApplicationContext以及其中的Bean
        - InitializingBean 在Bean创建完成执行，所有的属性注入完成之后执行
        - DisposableBean： 在bean销毁之前执行
        - ApplicationListener:用来监听产生的引用事件

    - scope
        - Singleton
        - Prototype
        - Request
        - Session
        - Global-session
        - Application
        - Websocket
    - 事件机制   
        - ContextRefreshedEvent
        - ContextStatedEvent
        - ContextStoppedEvent
        - ContextClosedEvent
        - RequestHandledEvent   
    - springcontext 初始化过程
        1. prepareRefresh()
        2. ConfigurableListableBeanFactory beanFactory=obtainFreshBeanFactory()
        3. prepareBeanFacoty(beanFactory)
        4. postProcessBeanFactory(beanFactory)
        5. invokeBeanFactoryPostProcessors(beanFactory)
        6. registerBeanPostProcessors(beanFactory) 
        7. initMEssageSource()
        8. initApplicationEventMulticaster()
        9. onRefresh()
        10. registerListeners()
        11. finishBeanFactoryInitialization(beanFactory)
        12. finishRefresh()
        13. resetCommonCaches()
5. bean的生命周期
    1. 构造函数
    2. 依赖注入
    3. BeanNameAware
    4. BeanFactoryAware
    5. APplicationCOntextAware
    6. BeanPostProcessor
    7. InitializingBean
    8. 自定义init方法
    9. BeanPostProcessor后置方法
    10. 使用
    11 DisposableBean
    12. 自定义的destroy方法

#### Spring Boot
1. 启动流程
    - prepareEnvironment
    - createApplicationContext
    - postProcessApplicationCOntext
    - applyInitializers
    - listeners.contextPrepared
    - listeners.contextLoaded
    - refreshContext
2. 注解
    - @SpringBootApplication
    - @SPringBootConfiguration
    - @EnableAutoConfifuration
    - @Conditional
       - @ConditionalOnBean
       - @ConditionalOnClass
       - @ConditionalOnExpression
       - @ConditionalOnMissingBean
       - @ConditionalOnMissingClass
       - @ConditionalOnNotWebApplication
3. 配置文件
    - bootstrap
    - application


4. 模块
    - starter
    - actuator
    - devtools
    - cli
#### Spring Data
#### Spring Cloud
1. sleuth
2. netflix
3. config
4. Bus
5. Security
