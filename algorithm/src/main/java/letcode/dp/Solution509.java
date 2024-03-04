package letcode.dp;


public class Solution509 {

    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        return fib(n - 2) + fib(n - 1);
    }
    public static void main(String[] args) {
        Solution509 solution509 = new Solution509();
        long l = System.currentTimeMillis();
        System.out.println(solution509.fib(30));
        System.out.println(System.currentTimeMillis() - l);
    }
}
