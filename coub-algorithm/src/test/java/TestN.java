import java.text.DecimalFormat;

public class TestN {


    /**
     * 已知公式：F=P×(1+i)^n 求N;
     *
     * @param args
     */
    public static void main(String[] args) {


    }

    public static void getN() {
        double F = 4, P = 2, i = 0.15;
        double N = Math.log(F / P) / Math.log(1 + i);
        System.out.println(N);
    }

    public static void getI() {
        double F = 1000.01455479; // 替换为实际的 F 值
        double P = 1000; // 替换为实际的 P 值
        double n = 1; // 替换为实际的 n 值

        double i = Math.exp(Math.log(F/P)/n) - 1;

        DecimalFormat df = new DecimalFormat("#.000000000");
        String formattedValue = df.format(i);

        System.out.println("i = " + formattedValue);
    }
}
