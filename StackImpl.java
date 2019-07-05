import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 利用数组实现栈结构
 */
public class StackImpl {

    // 定义容量常量
    private static final int CAPACITY = 1024;
    // 定义容量
    private static int capacity;
    // 定义栈顶位置top，top = -1属于栈空
    private static int top = -1;
    // 基本的数组对象
    private static Object[] array;

    /**
     * 初始化栈的容量
     */
    public StackImpl() {
        this.capacity = CAPACITY;
        array = new Object[capacity];
    }

    /**
     * 获取栈的大小
     */
    public int getSize() {
        if (isEmpty()) {
            return 0;
        } else {
            return top + 1;
        }
    }

    /**
     * 判断栈是否为空
     */
    public boolean isEmpty() {
        return top < 0;
    }

    /**
     * 获取栈顶元素
     */
    public Object top() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return array[top];
    }

    /**
     * 将元素进栈
     * @param element
     */
    public void push(Object element) throws Exception {
        if (getSize() == CAPACITY) {
            throw new Exception();
        }
        array[++top] = element;
    }

    /**
     * 将栈顶元素取出来
     */
    public Object pop() throws Exception {
        if (isEmpty()) {
            throw new Exception();
        }
        return array[top--];
    }

    /**
     * 获取栈的所有元素
     */
    public String getAllElements() {
        Object[] arr = new Object[top + 1];
        if (!isEmpty()) {
            for (int i = 0; i < getSize(); i++) {
                arr[i] = array[i];
            }
        }
        return Arrays.toString(arr);
    }

    public static void main(String[] args) {
        StackImpl stack = new StackImpl();
        System.out.println(stack.getSize());
        System.out.println(stack.isEmpty());
        try {
            stack.push(8);
            stack.push(7);
            stack.push(5);
            stack.push(3);
            stack.push(6);
            stack.push(1);
            System.out.println(stack.getSize());
            System.out.println(stack.top());
            System.out.println(stack.getAllElements());

            System.out.println(stack.pop());
            System.out.println(stack.pop());
            System.out.println(stack.pop());
            System.out.println(stack.pop());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
