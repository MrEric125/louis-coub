
### SPRING MVC

#### @RequestMapping 功能实现

当一个类或者一个方法加了`@RequestMaping`之后，其实相当于将这个方法映射成一条 url,当我们访问url的时候，知道具体执行的是哪个方法
嗯，其实实现思路和IOC的实现思路是一样的，就是将@RequestMapping中的url与方法的映射保存起来，当后期请求url的时候就从这个映射中找，
找到了，那么就直接通过反射的方式调用这个方法，执行具体业务逻辑就可以，

以上是 spring中的设计思路 很简单，接下来看代码实现

1. 当`AbstractHandlerMethodMappiing`类加载完成之后，会执行`afterPropertiesSet()`中的`initHandlerMethods()`

initHandlerMethods()这个方法就是将映射关系存储起来的地方

