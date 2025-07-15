### SPRING MVC

#### @RequestMapping 功能实现

当一个类或者一个方法加了`@RequestMaping`之后，其实相当于将这个方法映射成一条 url,当我们访问url的时候，知道具体执行的是哪个方法
嗯，其实实现思路和IOC的实现思路是一样的，就是将@RequestMapping中的url与方法的映射保存起来，当后期请求url的时候就从这个映射中找，
找到了，那么就直接通过反射的方式调用这个方法，执行具体业务逻辑就可以，

以上是 spring中的设计思路 很简单，接下来看代码实现

1. 在程序启动的时候，将URL 与方法的映射缓存起来，在后期访问url的时候，直接使用

    1. 在加载完`AbstractHandlerMethodMapping`之后会执行`initHandlerMethods()` 这个方法也是缓存url与带有`@RequestMapping`
       之间关系的入口，

    2. 一些校验功做吧，首先通过Type 获取所有待校验的一些BeanName,当然这些beanName有的带了`@Controller`和`@RequestMapping`
       ,但是有的没有带，所以需要校验，校验完之后，进入`detectHandlerMethods(Object handler)`方法，

    3. 这个方法`detectHandlerMethods()`主要做两件事

        1. 满足条件方法执行`getMappingForMethod(Method,Class<?>)`,构造一个执行方法与url映射的对象

           如下图,将图中1写在下面，其实不太对，因为先执行的是
           `MethodIntrospector.selectMethods(Class<?> targetType, final MetadataLookup<T> metadataLookup) `
           .只是在这个方法中会满足一定条件（其实我们暂时可以不用看这个条件，类似于方法是不是public,有没有响应值，是不是桥接方法，这个对象是不是代理对象吧）的时候会调用1出写的代码，类似于js中的回调，这也是java
           8 的新特性的写法吧。

        2. 将满足要求的对象放在一个Map中，其中泛型T 其实就是 RequestMappingInfo

        3. 遍历刚才的Map,将其中的信息注册起来，其实就是将信息细化，放到各自的map中，这个注册的过程又会将RequestMappingInfo
           装换成HandlerMethod,后期在DispartcherServlet中处理的都是HandlerMethod

       ![image-20201115225151897](C:\Users\Eric\AppData\Roaming\Typora\typora-user-images\image-20201115225151897.png)

    4. 执行`getMappingForMethod(Method,Class)`

    5. 注册

       ![image-20201115231124328](C:\Users\Eric\AppData\Roaming\Typora\typora-user-images\image-20201115231124328.png)

2. 访问url的时候，通过DispartcherServlet 找到url对应的方法，通过反射的方式执行这个方法

## spring boot 解析@RequestParam @RequestMapping @RequestBody过程 @ResponseBody

### 初始化



