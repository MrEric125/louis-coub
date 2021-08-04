mvc 启动过程
###. web.xml
    1. 加载配置的Listener   ContextLoaderListener
            这个context的作用很简单就是当Tomcat 启动后创建应用程序的上下文，和servletsContext,这个时候的上下文里面其实什么都没有，就是一个空壳子（这一步非必须），
            在下一步创建上下文Context的时候就会把在listener中创建的context作为servlet init()中创建的context的父context.如下图 
            //todo 为什么要这么做?

![img_6.png](img_6.png)


    2. 加载配置的Servlet    DispatcherServlet
        找刚才创建的上下文，如果找到了，会将刚才的那个上下文作为当前上下文的父上下文，(合并 envirment,目前看到的是这些)
        如果没有找到，那么就创建一个对应的上下文，然后往上下文中塞数据，但是需要塞进去的数据时从哪儿来的，找到这个confiLocation,如下图，默认是 SpringMvc-servlet.xml ，
![img.png](img.png)

        当然咯，这个配置的路径我们是可以在web.xml中修改的
        
![img_1.png](img_1.png)

        已经有了上下文，是不是可以将IOC 和AOP的那些bean给初始化一下，执行下面代码
        this.configureAndRefreshWebApplicationContext(wac);
        自动注入的代码也注入了，接下来，spring mvc 的一些处理器是不是可以初始化一下了
         FrameworkServlet#onRefresh()    
        刚才说的往上下文中方的数据又哪些，其实我们需要考虑web的几个场景

        1. 上传文件
        2. 页面跳转ModelView
        3. 直接restful 形式的数据
        4. cookie 
        5. 国际化等
        这些功能怎么支持的呢？都是通过处理器来支持的，DispatcherServlet#initStrategies(ApplicationContext context)
        
        在加载的过程中还有一些特殊的逻辑，比方说是客户加的处理器，适配器等，如果客户没有加呢？还会有兜底的处理器，和适配器
   
###. 基于java代码
    servlet 3.0之后，可以以编程方式注册Servlet,如下图注解说明
![img_2.png](img_2.png)

    如果我们需要以编程方式而不是通过web.xml方式启动一个基于servlet的项目，那么可以实现这个接口，我们可以根据我们自己的需求，自由的创建或者使用DispartcherServlet 
![img_3.png](img_3.png)

    其中图中的McvConfig类就相当于configLocation中需要加载的spring 配置，我们可以将自己的处理器，视图解析器等放在这里加载
    接下来初始化web容器的上下文逻辑基本上是一致，但是这个时候我们其实已经创建了一个ApplicationContext, 所以执行的逻辑稍稍有一点区别，如下图
![img_5.png](img_5.png)
    
    不同点在图中已经标记出来，其他逻辑基本保持一致。
###. spring-boot 内置tomcat启动过程
    基于spring-boot启动的 mvc项目的顺序会和原生的mvc项目有些不一样，但是整体的逻辑还是一样的。

    1. 初始化上下文
    2. 启动内置tomcat 服务器
    3. 创建DispartcherServlet
    4. 访问的时候调用GenericServlet#init()


### 访问页面
    