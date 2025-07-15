package com.linked;

import java.io.Serializable;

public interface MyList<E extends Serializable> extends Serializable {

    void add(E e);

    void addFirst(E e);

    void addLast(E e);

    void addIndex(int index, E e);


    void deleteLast();

    void deleteFirst();

    void deleteIndex(int index);


    E find(int index);

}
