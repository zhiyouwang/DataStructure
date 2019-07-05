public class MergerSort {

    /**
     * 归并排序 O(nlogn)
     * 分治法的一个典型应用，思想：将待排序数据分为两部分，对每部分队规的应用，在两部分都排序好后进行合并
     * Java.util.Arrays类中的sort方法就是使用归并排序的变体实现的。
     * 稳定
     * @param nums
     */
    public static void mergerSort(int[] nums) {
        if (nums.length > 1) {
            // 对数组的左半部分进行处理
            int length1 = nums.length / 2;
            int[] arr_left = new int[length1];
            System.arraycopy(nums, 0, arr_left, 0, length1);
            mergerSort(arr_left);

            // 对数组的右半部分进行处理
            int length2 = nums.length - length1;
            int[] arr_right = new int[length2];
            System.arraycopy(nums, length1, arr_right, 0, length2);
            mergerSort(arr_right);

            // 合并两个数组
            int[] data = merger(arr_left, arr_right);
            System.arraycopy(data, 0, nums, 0, nums.length);
        }

    }

    private static int[] merger(int[] arr_left, int[] arr_right) {
        int[] arr = new int[arr_left.length + arr_right.length];
        int count_left = 0;
        int count_right = 0;
        int count_arr = 0;
        // 进行合并
        while (count_left < arr_left.length && count_right < arr_right.length) {
            if (arr_left[count_left] < arr_right[count_right]) {
                arr[count_arr++] = arr_left[count_left++];
            } else {
                arr[count_arr++] = arr_right[count_right++];
            }
        }
        while (count_left < arr_left.length) {
            arr[count_arr++] = arr_left[count_left++];
        }
        while (count_right < arr_right.length) {
            arr[count_arr++] = arr_right[count_right++];
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,6,3,7,4,5,9,0};
        mergerSort(nums);
        for (int i: nums
             ) {
            System.out.print(i + " ");
        }
    }
}
