PostProcessor 是后置处理器，主要作用就是给某些 特殊的Bean 进行加强处理的，
增强有两者，一种是实例化，一种是初始化 before and after

Aware 接口 功能

1. 意识到
   当我们获取到某些bean的时候，能够通过aware接口获取相关属性
   比方说BeanFactoryAware 接口
   当我们通过ApplicationContext.getBean(A.class) 得到了A这个对象的实例的时候，我们是没法知道这个实例的工厂方法是哪一个
   但是当我们A类实现了BeanFactoryAware接口之后，我们就能够通过getBeanFactory(),这个方法获取到BeanFactory

自动装配是在BeanFactoryPostProcessor中处理的

BeanDefinitionReader 用于解析

Environment

在spring中如果某些特殊时期需要做一些特殊的事情，需要怎么做，==>监听器（观察者模式）