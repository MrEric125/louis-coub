package letcode.queue;


import java.util.LinkedList;
import java.util.Queue;

class MyStack225 {

    Queue<Integer> queue;

    public MyStack225() {
        queue = new LinkedList<>();

    }

    public void push(int x) {
        int size = queue.size();
        queue.offer(x);
        for (int i = 0; i < size; i++) {
            Integer head = queue.poll();
            queue.offer(head);
        }

    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();

    }
}