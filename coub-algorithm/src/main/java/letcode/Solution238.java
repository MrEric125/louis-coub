package letcode;

class Solution238 {
    public int[] productExceptSelf(int[] nums) {

        int len = nums.length;

        int[] answerLeft = new int[len];
        int[] answerRight = new int[len];
        int[] answer = new int[len];

        int leftStart = 1, rightStart = len - 2;
        answerLeft[0] = nums[0];
        answerRight[len - 1] = nums[len - 1];

        while (leftStart < len && rightStart >= 0) {

            answerLeft[leftStart] = nums[leftStart] * answerLeft[leftStart - 1];
            answerRight[rightStart] = nums[rightStart] * answerRight[rightStart + 1];
            leftStart++;
            rightStart--;
        }
        for (int i = 0; i < len; i++) {

            int leftValue = 1, rightValue = 1;
            if (i - 1 >= 0) {
                leftValue = answerLeft[i - 1];
            }
            if (i + 1 < len) {
                rightValue = answerRight[i + 1];
            }
            answer[i] = leftValue * rightValue;

        }
        return answer;
    }

    public static void main(String[] args) {
        new Solution238().productExceptSelf(new int[]{1, 2, 3, 4});
    }
}