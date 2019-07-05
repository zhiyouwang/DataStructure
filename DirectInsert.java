public class DirectInsert {

    /**
     * 直接插入排序 O（n**2）
     * 思想：将一个记录插入到已排序好的有序表中，从而得到一个新的记录数增1的有序表
     * 稳定
     * @param nums
     */
    public static void directInsert(int[] nums) {
        int j;
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            j = i - 1;
            while (j > -1 && temp < nums[j]) {
                nums[j + 1] = nums[j];
                j--;
            }
            // 正确位置，temp归位
            nums[j + 1] = temp;
        }
    }

    /**
     * 插入排序，比上述方法要简单些
     * @param arr
     */
    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    private static void swap(int[] arr, int j, int i) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,6,3,4,8,7,9};
        directInsert(nums);
        for (int i : nums
             ) {
            System.out.print(i + " ");
        }
    }
}
