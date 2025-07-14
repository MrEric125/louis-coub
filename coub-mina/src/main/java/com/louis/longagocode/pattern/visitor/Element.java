package com.louis.longagocode.pattern.visitor;

import com.pattern.visitor.Visitor;

/**
 * @author John·Louis
 * @date created on 2020/2/15
 * description:
 */
public interface Element {

    void accept(Visitor visitor);



}
