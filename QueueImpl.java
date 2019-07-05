import java.util.Arrays;

/**
 * 利用数组自定义实现队列相关操作
 *
 * 如果用顺序数组来实现队列，每次出队后都得将后面的元素向前移动一个单位，会增加时间复杂度
 * 为了避免数组的整体移动，可以引入两个变量f和r
 * f：始终等于Queue的首元素在数组中的下标，即指向下次出队元素的位置
 * r:始终等于Queue的末元素的下标加1，即指向下次入队元素的位置
 *
 * 为了解决可能出现数组下标溢出的情况，每一次对f和r进行操作时，都要以数组的长度做取模运算
 */
public class QueueImpl {

    // 定义容量常量
    private static final int CAPACITY = 1024;
    // 定义队列容量
    private static int capacity;
    // 队首元素
    private static int front;
    // 队尾元素
    private static int rear;
    // 队列数组
    private static Object[] array;

    /**
     * 无参构造函数
     */
    public QueueImpl() {
        this.capacity = CAPACITY;
        array = new Object[capacity];
        front = rear = 0;
    }

    /**
     * 获取队列的大小
     */
    public static int getSize() {
        if (isEmpty()) {
            return 0;
        } else {
            return (capacity + rear - front) % capacity;
        }
    }

    /**
     * 判断队列是否为空
     * @return 布尔类型
     */
    private static boolean isEmpty() {
        return front == rear;
    }

    /**
     * 将元素加入到队列中（队尾）
     */
    public static void enQueue(Object element) throws Exception {
        if (getSize() == capacity - 1) {
            throw new Exception();
        }
        array[rear] = element;
        rear = (rear + 1) % capacity;
    }

    /**
     * 从队列中取元素
     */
    public static Object deQueue() throws Exception {
        if (isEmpty()) {
            throw new Exception();
        }
        Object element = array[front];
        front = (front + 1) % capacity;
        return element;
    }

    /**
     * 获取队首元素（不拿出来）
     */
    public static Object frontElement() throws Exception {
        if (isEmpty()) {
            throw new Exception();
        }
        return array[front];
    }

    /**
     * 获取队列所有元素
     */
    public static void getAllQueueElements() {
        Object[] arrayList = new Object[getSize()];
        for (int i = front, j = 0; j < getSize(); j++) {
            arrayList[j] = array[i];
            i = (i + 1) % capacity;
        }
        System.out.println(Arrays.toString(arrayList));
    }

    public static void main(String[] args) {
        QueueImpl queue = new QueueImpl();
        System.out.println(queue.getSize());
        System.out.println(queue.isEmpty());
        try {
            queue.enQueue(8);
            queue.enQueue(7);
            queue.enQueue(5);
            queue.enQueue(6);
            queue.enQueue(3);

            queue.getAllQueueElements();
            System.out.println(queue.getSize());
            System.out.println(queue.frontElement());
            System.out.println(queue.deQueue());
            System.out.println(queue.deQueue());
            System.out.println(queue.deQueue());
            System.out.println(queue.deQueue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
