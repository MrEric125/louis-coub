mvc 启动过程
###. web.xml
    1. 加载配置的Listener   ContextLoaderListener
            这个context的作用很简单就是当Tomcat 启动后创建应用程序的上下文，和servletsContext,这个时候的上下文里面其实什么都没有，及时一个空壳子（这一步非必须）

    2. 加载配置的Servlet    DispatcherServlet
        找刚才创建的上下文，如果找到了，会往这个上下文中赛对应的数据，
        如果没有找到，那么久创建一个对应的上下文，然后往上下文中塞数据，
        刚才说的往上下文中方的数据又哪些，其实我们需要考虑web的几个场景
        1. 上传文件
        2. 页面跳转ModelView
        3. 直接restful 形式的数据
        4. cookie 
        5. 国际化等
        这些功能怎么支持的呢？都是童工处理器来支持的，DispatcherServlet#initStrategies(ApplicationContext context) 

   
###. 基于java代码
###. spring-boot 内置tomcat启动过程
    基于spring-boot启动的 mvc项目的顺序会和原生的mvc项目有些不一样，但是整体的逻辑还是一样的。