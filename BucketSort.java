public class BucketSort {

    /**
     * 桶排序：外排序，与数据状况无关，属于非比较类排序
     * 本例就是计数排序的一个例子，此外，基数排序也属于桶排序
     * 主要要了解桶的思想-->容器。
     * @param arr
     */
    public static void bucketSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        // 定义桶
        int[] bucket = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }
        int i = 0;
        for (int j = 0; j < bucket.length; j++) {
            while (bucket[j]-- > 0) {
                arr[i++] = j;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,6,3,4,5,2,9};
        bucketSort(nums);
        for (int i: nums
             ) {
            System.out.print(i + " ");
        }
    }
}
