package letcode.queue;

import java.util.Stack;

public class MyQueue232 {
    private Stack<Integer> inStack;
    private Stack<Integer> outStack;
    public MyQueue232() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    public void push(int x) {

        inStack.push(x);

    }

    /**
     * 弹出头元素
     * @return
     */
    public int pop() {
        if (outStack.empty()) {
            in2out();
        }

        return outStack.pop();
    }

    /**
     * 获取头元素，不弹出
     * @return
     */
    public int peek() {
        if (outStack.empty()) {
            in2out();
        }
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.empty() && outStack.empty();

    }
    private void in2out() {
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
    }


}
