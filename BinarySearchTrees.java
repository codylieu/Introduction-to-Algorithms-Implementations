import java.util.Stack;

// B-trees
// Nodes have upper and lower bounds on keys they can contain, expressed in terms of t (minimum degree)
// Every node other than root has >= t - 1 keys
// Every internal node other than the root has >= t children
// Every node may contain at most 2t - 1 keys -> at most 2t children
// Runtime for insert and delete is O(t log_t n)
public class BinarySearchTrees {
  
  public static void main(String[] args) {
    Node root = new Node(6);
    Node n1 = new Node(5);
    Node n2 = new Node(9);
    Node n3 = new Node(2);
    Node n4 = new Node(5);
    Node n5 = new Node(7);
    Node n6 = new Node(10);
    Node n7 = new Node(8);
    root.left = n1; root.right = n2;
    n1.left = n3; n1.right = n4;
    n2.left = n5; n2.right = n6;
    n5.right = n7;
    preOrderTraversalIterative(root);
    postOrderTraversalIterative(root);
  }

  // 1) Create empty stack
  // 2) Initialize current node as root
  // 3) Push current node to stack and set current = current.left until current is null
  // 4) If current is null and stack isn't empty
  // a) Pop item from stack
  // b) Print popped item, set current = current.right
  // c) Go to step 3
  // 5) If current is null and stack is empty, done
  private static void inOrderTraversalIterative(Node root) {
    Stack<Node> stack = new Stack<>();
    Node cur = root;
    while(cur != null || !stack.empty()) {
      if(cur != null) {
        stack.push(cur);
        cur = cur.left;
      }
      else {
        cur = stack.pop();
        System.out.print(cur.val + " ");
        cur = cur.right;
      }
    }
    System.out.println();
  }

  // 1) Create an empty stack
  // 2) Do following while root is not null
  // a) Push root's right child and then root to stack
  // b) Set root as root's left child
  // 3) Pop item from stack and set it as root
  // a) If popped item has a right child and right child is at the top of the stack,
  // remove right child from stack and push the root back and set root as root's right child
  // b) Else print root's data and set root as null
  // 4) Repeat 2 and 3 while stack isn't empty
  private static void postOrderTraversalIterative(Node root) {
    Stack<Node> stack = new Stack<>();
    if(root.right != null) {
      stack.push(root.right);
    }
    stack.push(root);
    root = root.left;
    while(!stack.empty()) {
      while(root != null) {
        if(root.right != null) {
          stack.push(root.right);
        }
        stack.push(root);
        root = root.left;
      }
      root = stack.pop();
      if(root.right != null && !stack.empty() && root.right.equals(stack.peek())) {
        Node temp = stack.pop();
        stack.push(root);
        root = temp;
      }
      else {
        System.out.print(root.val + " ");
        root = null;
      }
    }
    System.out.println();
  }

  // 1) Create an empty stack and push root to stack
  // 2) Do following while stack isn't empty
  // a) Pop item from stack and print it
  // b) Push right child of popped item to stack
  // c) Push left child of popped item to stack
  private static void preOrderTraversalIterative(Node root) {
    Stack<Node> stack = new Stack<>();
    stack.push(root);
    while(!stack.empty()) {
      Node temp = stack.pop();
      System.out.print(temp.val + " ");
      if(temp.right != null) {
        stack.push(temp.right);
      }
      if(temp.left != null) {
        stack.push(temp.left);
      }
    }
    System.out.println();
  }

  private static Node treeDelete(Node root, Node z) {
    if(z.left == null) {
      transplant(root, z, z.right);
    }
    else if(z.right == null) {
      transplant(root, z, z.left);
    }
    else {
      Node y = treeMinimum(z.right);
      if(findParent(root, y).val != z.val) {
        transplant(root, y, y.right);
        y.right = z.right;
      }
      transplant(root, z, y);
      y.left = z.left;
      z.val = y.val;
    }
    return root;
  }

  private static Node findParent(Node root, Node n) {
    Node parent = null;
    while(root.val != n.val) {
      parent = root;
      if(root.val < n.val) {
        root = root.right;
      }
      else {
        root = root.left;
      }
    }
    return parent;
  }

  private static void transplant(Node root, Node u, Node v) {
    Node parent = findParent(root, u);
    if(parent == null) {
      root = v;
    }
    else if(parent.left.val == u.val) {
      parent.left = v;
    }
    else {
      parent.right = v;
    }
  }

  private static void treeInsert(Node root, Node n) {
    Node parent = null;
    while(root != null) {
      parent = root;
      if(n.val < root.val) {
        root = root.left;
      }
      else {
        root = root.right;
      }
    }
    if(parent == null) {
      root = n;
    }
    else if(n.val < parent.val) {
      parent.left = n;
    }
    else {
      parent.right = n;
    }
  }

  private static Node treeSearch(Node root, int x) {
    if(root == null || root.val == x) {
      return root;
    }
    if(x < root.val) {
      return treeSearch(root.left, x);
    }
    return treeSearch(root.right, x);
  }

  private static Node treeMinimum(Node root) {
    while(root.left != null) {
      root = root.left;
    }
    return root;
  }

  private static Node treeMaximum(Node root) {
    while(root.right != null) {
      root = root.right;
    }
    return root;
  }

  private static void inOrderTraversalRecursive(Node root) {
    if(root != null) {
      inOrderTraversalRecursive(root.left);
      System.out.print(root.val + " ");
      inOrderTraversalRecursive(root.right);
    }
  }

  private static void preOrderTraversalRecursive(Node root) {
    if(root != null) {
      System.out.print(root.val + " ");
      preOrderTraversalRecursive(root.left);
      preOrderTraversalRecursive(root.right);
    }
  }

  private static void postOrderTraversalRecursive(Node root) {
    if(root != null) {
      postOrderTraversalRecursive(root.left);
      postOrderTraversalRecursive(root.right);
      System.out.print(root.val + " ");
    }
  }

  private static class Node {
    public int val;
    public Node left;
    public Node right;

    public Node(int val) {
        this.val = val;
        left = null;
        right = null;
    }
  }
}