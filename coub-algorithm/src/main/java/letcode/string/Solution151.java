package letcode.string;

class Solution151 {
    public String reverseWords(String s) {
        String[] split = s.split(" ");
        int len = split.length;
        if (len == 1) {
            return split[0];
        }
        int left = 0, right = len - 1;

        String head = "";
        String mid = "";
        String tail = "";

        while (left <= right) {
            // 如果左边为空格
            if (split[left].isEmpty()) {
                left++;
                continue;
            }
            // 如果右边为空格
            if (split[right].isEmpty()) {
                right--;
                continue;
            }
            // 左右碰头
            if (left == right && !split[right].isEmpty()) {
                mid = split[right];
                break;
            }
            // 交换位置
            swap(split, left, right);
            head = head + split[left];
            tail = split[right] + tail;
            if (left + 1 < right) {
                head = head + " ";
                tail = " " + tail;
            }
            left++;
            right--;
        }
        if (mid.isEmpty()) {
            return head.trim() + " " + tail.trim();
        }
        return head.trim() + " " + mid + " " + tail.trim();
    }


    public void swap(String[] split, int i, int j) {
        String tmp = split[i];
        split[i] = split[j];
        split[j] = tmp;
    }

    public static void main(String[] args) {
        System.out.println(new Solution151().reverseWords("a good   example"));
    }
}