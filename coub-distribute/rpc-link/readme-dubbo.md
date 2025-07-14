# spring boot 整合dubbo, 

网上有很多关于dubbo的搭建方式，但是有很多都不是官方的，而且配置方式都是五花八门的，使用的dubbo 也不是apache的。还不全，
反正我是搭建不起来的。

最后之后求助官方的文档，所以以下写的内容都是依赖于apache dubbo和apache dubbo-spring-boot-project的
具体的依赖jar包都是在文末

具体代码其实很简单，但是这个过程中会有一些坑，在此记录一下

刚才说了代码很简单无非就是给三个模块consumer、producer、api，api模块专门写接口，
 producer模块实现api模块的接口，使用@Service注册这个接口，需要注意的是@Service是dubbo中的@Service而不是spring中的
consumer模块依赖于api中的接口，也就是调用api中的接口，需要使用@Reference指定注册的接口

具体代码补贴出来了，地址在文末，有兴趣的可以看看

# provider
在application.properties中有如下配置，注明dubbo可以使用两种RPC 协议

>dubbo.protocols.dubbo.name = dubbo
>dubbo.protocols.dubbo.port = 20880
>dubbo.protocols.rest.name = rest
>dubbo.protocols.rest.port = 8091

producer模块中如果使用`rest`协议，那么就需要引入相关的jar包，包括两方面，一方面rest接口的，一方面是rest服务器，
rest接口依赖
```xml
        <dependency>
            <groupId>org.jboss.resteasy</groupId>
            <artifactId>resteasy-jaxrs</artifactId>
            <version>${resteasy_version}</version>
        </dependency>
```
**dubbo.protocol.server**  如果不配置使用的是jetty服务器，需要引入相关jar包
```xml
    <dependency>
            <groupId>org.eclipse.jetty</groupId>
            <artifactId>jetty-servlet</artifactId>
            <version>${jetty_version}</version>
        </dependency>
```
否则会报`java.lang.NoClassDefFoundError: org/eclipse/jetty/util/log/StdErrLog` 这个异常

**dubbo.protocol.server=netty** 则需要引入netty的jar包
```xml
    <dependency>
            <groupId>org.jboss.resteasy</groupId>
            <artifactId>resteasy-netty4</artifactId>
            <version>${resteasy_version}</version>
        </dependency>
```
否则会报`java.lang.NoClassDefFoundError: org/jboss/resteasy/plugins/server/netty/NettyJaxrsServer`这个异常

**dubbo.protocol.server=tomcat** 则需要引入tomcat相关依赖，好吧，其实rest使用tomcat这种方式我是没有试成功，
如果Spring boot 也是用的是tomcat服务器，除非Spring boot使用的其他服务器才能成功启动
```xml
<properties>
    <tomcat_embed_version>9.0.22</tomcat_embed_version>
</properties>
<dependencies>
    <dependency>
        <groupId>org.apache.tomcat.embed</groupId>
        <artifactId>tomcat-embed-core</artifactId>
        <version>${tomcat_embed_version}</version>
    </dependency>
</dependencies>

```
但是很奇怪的的，如果我单独指定dubbo中的tomcat版本，则会抛出`java.lang.NoClassDefFoundError: javax/servlet/GenericFilter`,
直接使用Spring boot中继承的tomcat版本则会抛出以下异常

```log
An attempt was made to call a method that does not exist. The attempt was made from the following location:
    org.apache.dubbo.remoting.http.tomcat.TomcatHttpServer.<init>(TomcatHttpServer.java:66)
The following method did not exist:
    org.apache.catalina.connector.Connector.setProtocol(Ljava/lang/String;)V
The method's class, org.apache.catalina.connector.Connector, is available from the following locations:
    jar:file:/D:/programmer/maven/repository/org/apache/tomcat/embed/tomcat-embed-core/9.0.21/tomcat-embed-core-9.0.21.jar!/org/apache/catalina/connector/Connector.class
It was loaded from the following location:
    file:/D:/programmer/maven/repository/org/apache/tomcat/embed/tomcat-embed-core/9.0.21/tomcat-embed-core-9.0.21.jar
Action:
Correct the classpath of your application so that it contains a single, compatible version of org.apache.catalina.connector.Connector

```
只有单独指定spring boot 中的tomcat版本才能够正常启动
当然我们解决上面的问题也可以将Spring Boot中使用的服务器换成其他的服务，也是ok的

# consumer
**dubbo.protocol.name=dubbo**
这里是指定项目中的RPC默认协议是dubbo,但其实也可以在具体的，Reference中指定协议类型如下
@Reference(version = "1.0.0",protocol = "dubbo")

注意：consumer中指定的协议需要在provider中的协议中有指定，
否则如果不匹配那么就会` No provider available for the service` 异常

一般的不论是在provider中或者是在consumer中，都是在配置文件中指定协议类型的，不单独在需要引用的
 时候指定协议类型


在consumer中如果使用了`rest`协议，那么就需要引入引入一下两个包
```xml
        <dependency>
            <groupId>org.jboss.resteasy</groupId>
            <artifactId>resteasy-jaxrs</artifactId>
            <version>${resteasy_version}</version>
        </dependency>

        <dependency>
            <groupId>org.jboss.resteasy</groupId>
            <artifactId>resteasy-client</artifactId>
            <version>${resteasy_version}</version>
        </dependency>
```


一下为dubbo通用接口依赖，也就是说是consumer和producer中都需要使用的依赖
```xml
     <properties>
        <dubbo.version>2.7.3</dubbo.version>
    </properties>
    <dependencies>
    <!--        dubbo dependencies-->
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo</artifactId>
            <version>${dubbo.version}</version>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework</groupId>
                    <artifactId>spring</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>javax.servlet</groupId>
                    <artifactId>servlet-api</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>log4j</groupId>
                    <artifactId>log4j</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo-spring-boot-starter</artifactId>
            <version>${dubbo.version}</version>
        </dependency>
        <!-- Zookeeper dependencies -->
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo-dependencies-zookeeper</artifactId>
            <version>${dubbo.version}</version>
            <type>pom</type>
            <exclusions>
                <exclusion>
                    <groupId>org.slf4j</groupId>
                    <artifactId>slf4j-log4j12</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>

    <dependencyManagement>
        <!-- Apache Dubbo -->
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo-dependencies-bom</artifactId>
            <version>${dubbo.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo-registry-nacos</artifactId>
            <version>${dubbo.version}</version>
        </dependency>
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo-spring-boot-actuator</artifactId>
            <version>${dubbo.version}</version>
        </dependency>
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo</artifactId>
            <version>${dubbo.version}</version>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework</groupId>
                    <artifactId>spring</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>javax.servlet</groupId>
                    <artifactId>servlet-api</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>log4j</groupId>
                    <artifactId>log4j</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <dependency>
            <groupId>org.apache.dubbo</groupId>
            <artifactId>dubbo-serialization-kryo</artifactId>
            <version>${dubbo.version}</version>
            <exclusions>
                <exclusion>
                    <groupId>log4j</groupId>
                    <artifactId>log4j</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>org.apache.dubbo</groupId>
                    <artifactId>dubbo-common</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
     </dependencyManagement>

```

相关代码在`https://gitee.com/Eric125/boot-mybatis`的rpc-link中，起的名字比较随意莫见怪
