package exam;

import java.util.Scanner;

/**
 * @author John·Louis
 * @date created on 2020/2/14
 * description:
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line1 = sc.nextLine();
        String line2 = sc.nextLine();
        String[] keys = line1.split(" ");
        String[] answers = line2.split(" ");

        int numA = 0;
        int numB = 0;
        for (int i = 0; i < answers.length; i++) {
            if (answers[i].equals(keys[i])) {
                numA++;
            } else {
                for (int j = 0; j < keys.length; j++) {
                    if (answers[i].equals(keys[j])) {
                        numB++;
                    }
                }
            }
        }
        System.out.println(numA + "A" + numB + "B");
    }
}
