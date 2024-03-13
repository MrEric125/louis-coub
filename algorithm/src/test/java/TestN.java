public class TestN {


    /**
     * 已知公式：F=P×(1+i)^n 求N;
     *
     * @param args
     */
    public static void main(String[] args) {

        double F = 4, P = 2, i = 0.04;
        double N = Math.log(F / P) / Math.log(1 + i);
        System.out.println(N);
    }
}
