package com.pattern.chain;

/**
 * @author John·Louis
 * @date created on 2020/3/12
 * description:
 */
public abstract class Leader {
    private LeaderChain next;

    public void setNext(LeaderChain next) {
        this.next = next;
    }

    public LeaderChain getNext() {
        return next;
    }

    public abstract void handleRequest(int LeaveDays);
}
