import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 用数组自定义实现二叉树
 */
public class BinaryTreeImpl {

    /**
     * 内部节点类
     */
    class Node {
        int data;
        Node left;
        Node right;
        /**
         * 有参构造函数
         */
        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    /**
     * 创建二叉树
     * @param datas 实现二叉树各节点值的数组
     * @param nodeList 二叉树List
     */
    private void createBinaryTree(int[] datas, List<Node> nodeList) {
        // 将数组变成node节点
        for (int nodeIndex = 0; nodeIndex < datas.length; nodeIndex++) {
            Node node = new Node(datas[nodeIndex]);
            nodeList.add(node);
        }
        // 给所有父节点设定子节点
        for (int index = 0; index < nodeList.size() / 2 - 1; index++) {
            // 编号为n的节点它的左子节点编号为2*n，右子节点为2*n+1，但是因为list从0开始，所有要额外加1
            // 最后一个父节点有可能没有右子节点，需要单独处理
            nodeList.get(index).setLeft(nodeList.get(index * 2 + 1));
            nodeList.get(index).setRight(nodeList.get(index * 2 + 2));
        }
        // 单独处理最后一个父节点
        int index = nodeList.size() / 2 - 1;
        // 先设置左子节点
        nodeList.get(index).setLeft(nodeList.get(index * 2 + 1));
        // 如果有奇数个节点，最后一个父节点才有右子节点
        if (nodeList.size() % 2 == 1) {
            nodeList.get(index).setRight(nodeList.get(index * 2 + 2));
        }
    }

    /**
     * 遍历当前节点的值
     * @param node 当前节点
     */
    public void checkCurrentNode(Node node) {
        System.out.print(node.getData() + " ");
    }

    /**
     * 先序遍历二叉树
     * @param node 二叉树根节点
     */
    public void preOrderTraversal(Node node) {
        // 很重要，必须加上，当遇到叶子节点时用来停止向下遍历
        if (node == null) {
            return;
        }
        checkCurrentNode(node);
        preOrderTraversal(node.getLeft());
        preOrderTraversal(node.getRight());
    }

    /**
     * 中序遍历二叉树
     * @param node 根节点
     */
    public void inOrderTraversal(Node node) {
        if (node ==null) {
            return;
        }
        inOrderTraversal(node.getLeft());
        checkCurrentNode(node);
        inOrderTraversal(node.getRight());
    }

    /**
     * 后序遍历二叉树
     * @param node 根节点
     */
    public void postOrderTraversal(Node node) {
        if (node == null) {
            return;
        }
        postOrderTraversal(node.getLeft());
        postOrderTraversal(node.getRight());
        checkCurrentNode(node);
    }

    /**
     * 非递归先序遍历
     * @param node
     */
    public void preOrderTraversalByLoop(Node node) {
        Stack<Node> stack = new Stack<>();
        Node p = node;
        while (p != null || !stack.isEmpty()) {
            // 当p不为空时，就读取p的值，并不断更新p为其左子节点
            while (p != null) {
                checkCurrentNode(p);
                // 入栈是为了遍历右子节点
                stack.push(p);
                p = p.getLeft();
            }
            if (!stack.isEmpty()) {
                p = stack.pop();
                p = p.getRight();
            }
        }
    }

    /**
     * 非递归中序遍历
     * @param node
     */
    public void inOrderTraversalByLoop(Node node) {
        Stack<Node> stack = new Stack<>();
        Node p =node;
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                stack.push(p);
                p = p.getLeft();
            }
            if (!stack.isEmpty()) {
                p = stack.pop();
                checkCurrentNode(p);
                p = p.getRight();
            }
        }
    }

    /**
     * 非递归后序遍历
     * @param node
     */
    public void postOrderTraversalByLoop(Node node) {
        Stack<Node> stack = new Stack<>();
        Node p = node, prev = node;
        while (p != null || !stack.isEmpty()) {
            while (p != null) {
                stack.push(p);
                p = p.getLeft();
            }
            if (!stack.isEmpty()) {
                Node temp = stack.peek().getRight();
                if (temp == null || temp == prev) {
                    p = stack.pop();
                    checkCurrentNode(p);
                    prev = p;
                    p = null;
                } else {
                    p = temp;
                }
            }
        }
    }

    /**
     * 广度优先遍历（从上到下）
     * @param node
     */
    public void bfs(Node root) {
        if (root == null) {
            return;
        }
        LinkedList<Node> queue = new LinkedList<>();
        // 根节点入队
        queue.offer(root);
        // 当队列里有值时，每次取出队首的node打印，打印之后判断node是否有子节点，若有，将子节点加入队列
        while (queue.size() > 0) {
            Node node = queue.peek();
            // 取出队首元素并打印
            queue.poll();
            System.out.print(node.data + " ");
            if (node.right != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**
     * 深度优先遍历
     *
     */
    public void dfs(Node node, List<List<Integer>> rst, List<Integer> list) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            list.add(node.data);
            // 这里将list存入rst中时，不能直接将list存入，而是通过新建一个list来实现，因为如果直接用list的话，后面remove
            // 的时候也会将其最后一个存的节点删掉
            rst.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
        }
        list.add(node.data);
        dfs(node.left, rst, list);
        dfs(node.right, rst, list);
        list.remove(list.size() - 1);
    }


    public static void main(String[] args) {
        BinaryTreeImpl tree = new BinaryTreeImpl();
        int[] datas = new int[]{1,2,3,4,5,6,7,8,9};
        List<Node> nodeList = new LinkedList<>();
        tree.createBinaryTree(datas, nodeList);
        Node root = nodeList.get(0);
        System.out.println("先序遍历");
        tree.preOrderTraversal(root);
        System.out.println("\n广度优先：");
        tree.bfs(root);
        System.out.println("\n深度优先：");
        List<List<Integer>> rst = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        tree.dfs(root, rst, list);
        System.out.println(rst);
    }
}
