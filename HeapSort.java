public class HeapSort {

    /**
     * 堆排序 O（nlogn）
     * 堆排序在很多场景都有很好的应用
     * 它是用数组存储完全二叉树，所有的操作都是在数组上进行的
     * 思想：先构建大顶堆，然后将堆顶的数组放到数组的最后，再对堆进行调整，这样一直到所有的排序完毕
     * 每次调整堆的复杂度都是logn
     * 不稳定排序
     * @param arr
     */
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int size = arr.length;
        swap(arr, 0, --size);
        while (size > 0) {
            heapify(arr, 0, size);
            swap(arr, 0, --size);
        }
    }

    /**
     * 调整堆的结构（核心）
     * @param arr
     * @param index
     * @param size
     */
    private static void heapify(int[] arr, int index, int size) {
        int left = index * 2 + 1;
        while (left < size) {
            int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private static void swap(int[] arr, int i, int i1) {
        arr[i] = arr[i] ^ arr[i1];
        arr[i1] = arr[i] ^ arr[i1];
        arr[i] = arr[i] ^ arr[i1];
        /*int temp = arr[i];
        arr[i] = arr[i1];
        arr[i1] = temp;*/
    }

    /**
     * 构建大顶堆
     * @param arr
     * @param index
     */
    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,6,2,5,7,9,6};
        heapSort(nums);
        for (int i : nums
             ) {
            System.out.print(i + " ");
        }
    }
}
