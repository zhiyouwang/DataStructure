/**
 *  Java实现自定义链表
 */
public class LinkList<T> {

    /**
     *  定义一个内部类Node，Node就表示链表的节点
      */
    private class Node {
        // 表示链表的数据域
        private T data;
        // 表示链表的指针域
        private Node next;

        // 定义一个空的构造器
        public Node() {}

        // 定义有参构造器，初始化链表的各个属性
        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    // 保存链表的头结点
    private Node header;
    // 保存链表的尾节点
    private Node tail;
    // 保存链表中的节点的数目
    private int size;

    /**
     * 构造函数，创建一个空链表
     */
    public LinkList() {
        // 由于是空链表，头结点和尾节点都为空
        header = null;
        tail = null;
        size = 0;
    }

    /**
     * 有参构造函数，以指定的元素创建一个链表，这个链表就只有一个元素
     * @param element 元素值
     */
    public LinkList(T element) {
        header = new Node(element,null);
        // 只有一个节点，头尾相同
        tail = header;
        size++;
    }

    /**
     * 根据索引index来获取指定位置的节点，索引从0开始
     * @param index
     */
    public Node getNodeByIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("链表索引越界！");
        } else {
            // 单链表从header节点开始
            Node current = header;
            for (int i = 0; i < size && current != null; i++, current = current.next) {
                if (i == index) {
                    return current;
                }
            }
        }
        return null;
    }

    /**
     * 获取索引为index处的元素
     * @param index
     */
    public T getData(int index) {
        return getNodeByIndex(index).data;
    }

    /**
     * 查找指定元素的索引
     * @param element
     */
    public int locate(T element) {
        // 从头结点开始寻找
        Node current = header;
        for (int i = 0; i < size && current != null; i++, current = current.next) {
            if (current.data.equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 向链表指定位置插入一个元素
     * @param element 插入元素值
     * @param index 插入位置
     */
    public void insert(T element, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("链表索引越界！");
        }
        // 如果是空链表
        if (header == null) {
            addAtTail(element);
        } else {
            // 获取插入节点的前一个节点
            Node pre = getNodeByIndex(index - 1);
            // 让prev的next指向新增的节点，让新节点的next引用原来prev的下一个节点，就是当前要插入的节点
            pre.next = new Node(element, pre.next);
            size++;
        }
    }

    /**
     * 采用尾插法为链表添加新节点
     * @param element
     */
    public void addAtTail(T element) {
        // 如果该链表是空链表
        if (header == null) {
            header = new Node(element, null);
            // 只有一个节点
            tail = header;
        } else {
            // 创建新的节点
            Node newNode = new Node(element, null);
            // 让尾节点的next指向新的节点
            tail.next = newNode;
            // 让新增节点成为新的尾节点
            tail = newNode;
        }
        size++;
    }

    /**
     * 采用头插法为链表添加新的节点
     * @param element
     */
    public void addAtHeader(T element) {
        // 创建新的节点，让新的节点的next指向原来的header，并让新的节点成为新的header
        header = new Node(element, header);
        // 如果是空链表
        if (tail == null) {
            tail = header;
        }
        size++;
    }

    /**
     * 删除链表中指定索引处的元素
     * @param index
     */
    public T delete(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("线性表索引越界！");
        }
        // 要删除的元素对应的节点
        Node del = null;
        // 如果被删除的是header节点
        if (index == 0) {
            del = header;
            // 让头结点的下一个节点作为头结点
            header = header.next;
        } else {
            // 获取要删除节点的前一个节点
            Node prev = getNodeByIndex(index - 1);
            // 获取将要被删除的节点
            del = prev.next;
            // 让被删除节点前一个节点的next指向被删除节点的下一个节点
            prev.next = del.next;
            // 释放被删除节点的引用
            del.next = null;
        }
        size--;
        return del.data;
    }

    /**
     * 删除链表的最后一个元素
     */
    public T remove() {
        return delete(size - 1);
    }

    /**
     * 返回链表的尾节点的数据
     */
    public T elementLast() {
        if (empty()) {
            return  null;
        } else {
            return tail.data;
        }
    }

    /**
     * 返回链表的第一个节点的数据
     */
    public T elementFirst() {
        if (empty()) {
            return null;
        } else {
            return header.data;
        }
    }

    /**
     * 判断链表是否为空
     */
    public boolean empty() {
        return size == 0;
    }

    /**
     * 清空链表
     */
    public void clear() {
        header = null;
        tail = null;
        size = 0;
    }

    /**
     * 返回String类型链表数据
     */
    public String toString() {
        // 如果链表是空的
        if (empty()) {
            return "[]";
        } else {
            StringBuilder sBuilder = new StringBuilder("[");
            for (Node current = header; current != null; current = current.next) {
                sBuilder.append(current.data.toString() + ",");
            }
            int len = sBuilder.length();
            return sBuilder.delete(len - 1, len).append("]").toString();
        }
    }

    /**
     * 递归实现单链表反转
     * @param current
     * @return
     */
   public Node rev(Node current) {
        if (current == null || current.next == null) return current;
        Node nextNode = current.next;
        current.next = null;
        Node revResult = rev(nextNode);
        nextNode.next = current;
        return revResult;
   }

    /**
     * 非递归单链表反转
     * @param head
     * @return 反转后的头结点
     */
   public Node revByLoop(Node head) {
        if (head == null) return null;
        Node nodeResult = null;
        Node nodePre = null;
        Node current = head;
        while (current != null) {
            Node nodeNext = current.next;
            if (nodeNext == null) {
                nodeResult = current;
            }
            current.next = nodePre;
            nodePre = current;
            current = nodeNext;
        }
        return nodeResult;
   }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        LinkList<String> list = new LinkList<>();
        list.insert("aaa", 0);
        list.addAtTail("bbb");
        list.addAtTail("ccc");
        //list.delete(2);
        //list.remove();
        //list.clear();
        /*System.out.println(list.elementFirst());
        System.out.println(list.elementLast());
        System.out.println(list.empty());*/
        //System.out.println(list.rev(list.header).data);

        //System.out.println(list.revByLoop(list.header).data);
        System.out.println(list);
    }

}
