package com.louis.longagocode.pattern.adapter.clazz;

/**
 * 优点：
 * 提高了子类的复用程度，增加了子类的透明度，灵活性好
 * 缺点：
 *  过多的使用适配器，会让系统很凌乱，
 * 注意事项：
 * 适配器不是在详细设计的时候添加的，而是在解决正在服役的系统的问题的时候添加的。
 * @author John·Louis
 * @date created on 2020/3/12
 * description:
 */
public class ClassAdapter extends Adapter implements Target {
    @Override
    public void request() {
        specificRequest();

    }
}
