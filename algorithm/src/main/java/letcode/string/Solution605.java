package letcode.string;

public class Solution605 {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;

        if (len <= 1) {
            if (flowerbed[0] == 1 ){
                return n < 1;
            }
            if (flowerbed[0] == 0) {
                return n <= 1;
            }
        }

        for (int i = 0; i < len; i++) {
            if (i == 0) {
                if (compareAfter(i,flowerbed) ){
                    flowerbed[i] = 1;
                    n--;
                }
            } else if (i == len - 1) {
                if (comparePre(i, flowerbed)) {
                    n--;
                    flowerbed[i] = 1;
                }
            }else {
                if (compareAfter(i, flowerbed) && comparePre(i, flowerbed)) {
                    n--;
                    flowerbed[i] = 1;
                }
            }
            if (n <= 0) {
                break;
            }
        }
        return n <= 0;
    }

    public boolean comparePre(int i,int[] flowerbed) {
        return flowerbed[i] == flowerbed[i - 1] && flowerbed[i] == 0;
    }

    public boolean compareAfter(int i,int[] flowerbed) {
        return flowerbed[i] == flowerbed[i + 1] && flowerbed[i] == 0;
    }



    public static void main(String[] args) {
        System.out.println(new Solution605().canPlaceFlowers(new int[]{1, 0,0, 0, 1}, 1));
    }
}
