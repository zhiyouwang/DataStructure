public class BubbleSort {

    /**
     * 冒泡排序 O（n**2），要理解flag的作用
     * 稳定
     * @param nums
     */
    public static void bubbleSort(int[] nums) {
        boolean flag = true;
        for (int i = 0; i < nums.length - 1 && flag; i++) {
            flag = false;
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                    flag = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,5,9,8,3,7,2,6};
        bubbleSort(nums);
        for (int i : nums) {
            System.out.print(i + " ");
        }

    }
}
