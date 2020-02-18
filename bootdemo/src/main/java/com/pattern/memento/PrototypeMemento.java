package com.pattern.memento;

/**
 * @author John·Louis
 * @date created on 2020/2/15
 * description:
 *
 * 其实就是将当前类的某个状态持久化到其它类中存储，
 * 当这个类要回滚的时候直接从其他类中获取就可以了
 */
public class PrototypeMemento {

    public static void main(String[] args) {
        OriginatorPrototype originatorPrototype = new OriginatorPrototype();
        PrototypeCaretaker caretaker = new PrototypeCaretaker();

        originatorPrototype.setState("S0");

        System.out.printf("%s%s\n", "初始化状态：", originatorPrototype.getState());

        caretaker.setMemento(originatorPrototype.createMemento());
        originatorPrototype.setState("S1");
        System.out.printf("%s%s\n", "新状态：", originatorPrototype.getState());

        originatorPrototype.restoreMemento(caretaker.getMemento());
        System.out.printf("%s%s\n", "恢复状态：", originatorPrototype.getState());



    }
}
