public class SimpleSelectSort {

    /**
     * 简单选择排序，要领悟思想的精髓 O（n**2）
     * 思想：设排序序列的记录个数是n，进行n-1次选择，每次在n-i+1个记录中选择关键字最小的记录作为有效序列中的第i个记录
     * 不稳定
     * @param nums
     */
    public static void simpleSelecSort(int[] nums) {
        if (nums == null || nums.length < 2) return;
        for (int i = 0; i < nums.length - 1; i++) {
            int sign = i;
            // 查找最小数据的索引
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[sign]) {
                    sign = j;
                }
            }
            if (sign != i) {
                int temp = nums[sign];
                nums[sign] = nums[i];
                nums[i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,5,7,2,6,1,9,4};
        simpleSelecSort(nums);
        for (int i: nums
             ) {
            System.out.print(i + " ");
        }
    }
}
