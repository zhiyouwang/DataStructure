public class QuickSort {

    /**
     * 快速排序 O（nlogn）
     * 不稳定
     * 思想：1、在待排元素里任取一个元素作为基准（通常选取第一个元素，但最好的选择是从待排元素中随机选取一个作为基准），称
     * 为基准元素。
     * 2、将待排的元素进行区分，比基准元素大的元素放在它的右边，比其小的元素放在它的左边。
     * 3、对左右两个分区重复以上步骤直到所有的元素都是有序的
     * @param nums
     */
    public static void quickSort(int[] nums, int _left, int _right) {
        // 定义基准元素
        int axis = 0;
        int left = _left;
        int right = _right;
        // 待排序的元素至少有两个的情况
        if (left < right) {
            // 将待排的第一个元素作为基准元素
            axis = nums[left];
            // 从左右两边交替扫描，直到left = right
            while (left != right) {
                while (right > left && nums[right] >= axis) {
                    // 从右往左，直到找到第一个比基准元素小的元素
                    right--;
                }
                // 找到后与arr[left]交换
                swap(nums, left, right);

                while (left < right && nums[left] <= axis) {
                    left++;
                }
                swap(nums, left ,right);
            }
            // 将基准元素归位
            nums[right] = axis;
            // 对基准元素左边的元素进行递归排序
            quickSort(nums, _left, right - 1);
            // 对基准元素右边的进行递归排序
            quickSort(nums,left + 1, _right);
        }
    }

    /**
     *
     * 随机快速排序，在大样本情况下，会保证时间复杂度在O（nlogn）
     * 传统的快速排序每次只是排序一个数据，，并且没有考虑等于基准的值的情况
     * 这里将最右边的元素作为基准元素
     * @param arr
     * @param left
     * @param right
     */
    public static void randomQuickSort(int[] arr, int left, int right) {
        if (arr == null || arr.length < 2) return;
        if (left < right) {
            // 这里的产生随机索引有点问题
            //swap(arr, (int)(Math.random() * (right - left + 1)), right);
            int[] part = partiton(arr, left, right);
            randomQuickSort(arr, left, part[0] - 1);
            randomQuickSort(arr, part[1] + 1, right);
        }
    }

    /**
     * 排序数组核心
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static int[] partiton(int[] arr, int left, int right) {
        int less = left - 1;
        int more = right;
        while (left < more) {
            if (arr[left] < arr[right]) {
                swap(arr, ++less, left++);
            } else if (arr[left] > arr[right]) {
                swap(arr, --more, left);
            } else {
                left++;
            }
        }
        // 在这需要归位
        swap(arr, more, right);
        return new int[]{less + 1, more};
    }

    /**
     * 交换数组元素
     * @param nums
     * @param i
     * @param j
     */
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,6,2,8,7,9,2,0};
        //quickSort(nums,0,nums.length - 1);
        randomQuickSort(nums, 0, nums.length - 1);
        for (int i: nums
             ) {
            System.out.print(i + " ");
        }

    }
}
