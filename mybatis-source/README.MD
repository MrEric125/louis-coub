baseMapper  封装思路
baseMapper 提供的基本功能， add、delete、update、selectById、selectByOne
baseMapper中可以通过注解`@SelectProvider` 来指定一个默认的实现类 BaseProvider
**关键**
BaseProver中如何获取到BaseMapper中指定的实际运行时泛型类型

找到泛型类型之后，就可以很方便找到字段，然后根据需求生成SQL
