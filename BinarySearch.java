public class BinarySearch {

    /**
     * 二分法查找，时间复杂度（O（logn））-必须要求数组有序
     * @param nums
     * @param target
     * @return
     */
    public static boolean binarySearch(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (target == nums[mid]) {
                return true;
            } else if (target > nums[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,7,9};
        int target = 4;
        System.out.println(binarySearch(nums, target));
    }
}
