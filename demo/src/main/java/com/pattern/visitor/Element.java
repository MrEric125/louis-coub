package com.pattern.visitor;

/**
 * @author John·Louis
 * @date created on 2020/2/15
 * description:
 */
public interface Element {

    void accept(Visitor visitor);



}
