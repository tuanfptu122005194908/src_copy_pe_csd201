import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Queue;

/**
 *
 * @author Tuan va Quan
 */
/*
 * ||__TRAVERSAL__//breadth-first traversal
 * || ||______//preorder
 * || ||______//postorder
 * || ||______//inorder
 * || ||______//use BFS change second node have age >=5 to age = 10
 * || ||______//preorder with condition: 3 <= price <= 5
 * ||
 * ||___INSERT____//insert by string
 * || ||____//insert by integer/double
 * || ||____//insert have age > 4 use BTF
 * ||
 * ||___SEARCH____//search
 * || ||____//search by string
 * || ||____//search by integer/double
 * || ||____//search by index k
 * || ||____//search node max depth in tree
 * || ||____// Tìm node có depth lớn nhất trong subtree có root là p
 * ||
 * ||___COUNT_____//count node in tree
 * || ||____//count node have 1 child
 * || ||____//count node have exactly 2 child
 * || ||____//count height of tree
 * ||
 * ||___DELETE____//delete by copy integer/double
 * || ||______//delete by copy string
 * || ||______//delete by node p
 * || ||______//delete by merging by node p
 * || ||______//delete node co gia tri depth max
 * ||
 * ||___BALANCE___//balance tree
 * || ||______//balance simple array list
 * ||
 * ||___ROTATE____//rotate left
 * || ||_______//rotate right
 * || ||_______//rotate any node
 * || ||_______//rotate right root
 * ||
 * ||___GET_______//get node father tham so la key
 * || ||____//get node by string
 * || ||____//get node by integer/double
 * || ||____//get node father by node child
 * || ||____//get node in position k
 * || ||____// get node at position k by breadth-first traversal
 * || ||____// get node at position k by pere-order traversal
 * || ||____// get node at position k by post-order traversal
 * || ||____// get node at position k by inorder traversal
 * || ||____// get node at position k have right child breadth-first traversal
 * || ||____// get node at position k have left child breadth-first traversal
 * || ||____// get node at position k have right child pre-order traversal
 * || ||____// get node at position k have left child pre-order traversal
 * || ||____// get node at position k have right child post-order traversal
 * || ||____// get node at position k have left child post-order traversal
 * || ||____// get node at position k have right child inorder traversal
 * || ||____// get node at position k have left child inorder traversal
 * || ||__// xu ly cac bai toan su dung getNodeAtPositionWithLeftChild_Postorder
 * || ||____// get node have depth max breadth-first traversal
 * || ||____// get node have depth max pre-order traversal
 * || ||____// get node have depth max post-order traversal
 * || ||____// get node have depth max inorder traversal
 * ||___OTHER_____//calculate level of node
 * || ||_____//calculate factor
 * || ||_____//copy all node to tree by inorder traversal
 * || ||_____//Calculate balance factor
 * || ||_____//Calculate level all node
 * || ||_____//balance a binary search tree
 * 
 */
// breadth-first traversal
public void bfs(Node p) { // input root
    if (p == null)
        return;
    MyQueue m = new MyQueue();
    m.enqueue(p);
    while (!m.isEmpty()) {
        Node q = (Node) m.dequeue();// get node
        if (q.left != null) { // if lever still hava node left
            m.enqueue(q.left);
        }
        if (q.right != null) { // if lever still hava node right
            m.enqueue(q.right);
        }
        visit(q);// traversal them
    }
}

// preorder
public void preorder(Node p) {
    if (p == null) {
        return;
    }
    visit(p);
    preorder(p.left);
    preorder(p.right);
}

// postorder
public void postorder(Node p) {
    if (p == null) {
        return;
    }
    postorder(p.left);
    postorder(p.right);
    visit(p);
}

// inorder
public void inorder(Node p) {
    if (p == null) {
        return;
    }
    inorder(p.left);
    visit(p);
    inorder(p.right);
}

// use BFS change second node have age >=5 to age = 10
public void changeNodeCondition(Node p) {
    if (p == null)
        return;
    MyQueue m = new MyQueue();
    m.enqueue(p);
    while (!m.isEmpty()) {
        Node q = (Node) m.dequeue();
        if (q.left != null) {
            m.enqueue(q.left);
        }
        if (q.right != null) {
            m.enqueue(q.right);
        }
        if (q.info.age >= 5) { // maybe or not
            c++;
            if (c == 2) {
                q.info.age = 10;
                break;
            }
        }
    }
}

// preorder with condition: 3 <= price <= 5
void preOrder2(Node p, RandomAccessFile f) throws Exception {
    if (p == null)
        return;
    if (p.info.price >= 3 && p.info.price <= 5) {
        fvisit(p, f);
    }
    preOrder2(p.left, f);
    preOrder2(p.right, f);
}

// insert by string
public void insert(Person x) {
    Node p = new Node(x);
    if (isEmpty()) {
        root = p;
        return;
    }
    Node f = null;
    Node q = root;
    while (q != null) {
        if (q.info.name.equals(x.name)) {
            System.out.println("Insertion failed, duplicated key");
            return;
        } else if (q.info.name.compareToIgnoreCase(x.name) > 0) {
            f = q;
            q = q.left;
        } else {
            f = q;
            q = q.right;
        }
    }
    if (f.info.name.compareToIgnoreCase(x.name) > 0)
        f.left = p;
    else
        f.right = p;
}

// insert by integer/double
public void insert(Car x) {
    Node q = new Node(x);
    if (isEmpty()) {
        root = q;
        return;
    }
    Node f, p;
    f = null;
    p = root;
    while (p != null) {
        if (p.info.price == x.price) {
            System.out.println("The key " + x.price + " already exists, no insertion");
            return;
        }
        f = p;
        if (x.price < p.info.price)
            p = p.left;
        else
            p = p.right;
    }
    if (x.price < f.info.price)
        f.left = q;
    else
        f.right = q;
}

// insert have age > 4 use BTF
public void breadthModifier() {
    if (root == null) {
        return; // change
        MyQueue m = new MyQueue();
        m.enqueue(root);
        while (!m.isEmpty()) {
            Node q = (Node) m.dequeue();
            if (q.left != null) {
                m.enqueue(q.left);
            }
            if (q.right != null) {
                m.enqueue(q.right);
            }
            if (q.info.age > 4) {
                h.insert(q.info);
            }
        }
    }
}

// search
public Node search(Person x) {
    return search(root, x);
}

// search by string
public Node search(Node p, Person x) {
    if (p == null) {
        return null;
    }
    if (p.info.name.equals.x.name) {
        return p;
    } else if (p.info.name.compareToIgnoreCase(x.name) > 0) {
        return search(p.left, x);
    } else {
        return search(p.right, x);
    }
}

// search by integer/double
public Node search(Node p, int key) {
    if (p == null)
        return null;
    if (p.info == key)
        return p;
    else if (p.info > key)
        return search(p.left, key);
    else
        return search(p.right, key);
}

// search by index k
public Node searchByIndex(Node p, int k) {
    if (p == null || k < 1) {
        return null;
    }
    Queue m = new Queue();
    m.enqueue(p);
    int count = 0;
    while (!m.isEmpty()) {
        Node q = (Node) m.dequeue();
        count++;
        if (count == k) {
            return q;
        }
        if (q.left != null) {
            m.enqueue(q.left);
        }
        if (q.right != null) {
            m.enqueue(q.right);
        }
    }
    return null; // If k is greater than the number of nodes
}

// search node max depth in tree
public Node getDeepestNode(Node p) {
    if (p == null) return null;
    Queue q = new Queue();
    q.enqueue(p);
    Node deepest = p;
    int maxDepth = p.info.depth;
    
    while (!q.isEmpty()) {
        Node r = (Node) q.dequeue();
        if (r.info.depth > maxDepth) {
            maxDepth = r.info.depth;
            deepest = r;
        }
        if (r.left != null) q.enqueue(r.left);
        if (r.right != null) q.enqueue(r.right);
    }
    return deepest;
}
// Tìm node có depth lớn nhất trong subtree có root là p
ode getDeepestNodeInSubtree(Node p) {
    if (p == null) return null;
    Queue q = new Queue();
    q.enqueue(p);
    Node deepest = p;
    int maxDepth = p.info.depth;
    
    while (!q.isEmpty()) {
        Node r = (Node) q.dequeue();
        if (r.info.depth > maxDepth) {
            maxDepth = r.info.depth;
            deepest = r;
        }
        if (r.left != null) q.enqueue(r.left);
        if (r.right != null) q.enqueue(r.right);
    }
    return deepest;
}
// count node in tree
public int count(Node p) {
    if (p == null)
        return (0);
    int k, h, r;
    k = count(p.left);
    h = count(p.right);
    r = k + h + 1;
    return (r);
}

// count node have 1 child
int countModifer(Node p) {
    int n = 0;
    MyQueue m = new MyQueue();
    m.enqueue(p);
    while (!m.isEmpty()) {
        Node q = (Node) m.dequeue();
        if (q.left != null)
            m.enqueue(q.left);
        if (q.right != null)
            m.enqueue(q.right);
        // If q has only one child, increment c by 1
        if (q.left == null && q.right != null)
            n++;
        if (q.right == null && q.left != null)
            n++;
    }
    return n;
}

// count node have exactly 2 child
int countNodeCo2con(Node p) {
    int n = 0;
    MyQueue m = new MyQueue();
    m.enqueue(p);
    while (!m.isEmpty()) {
        Node q = (Node) m.dequeue();
        if (q.left != null)
            m.enqueue(q.left);
        if (q.right != null)
            m.enqueue(q.right);
        if (q.left != null && q.right != null)
            n++;
    }
    return n;
}

// count height of tree
public int height(Node p) {
    if (p == null) {
        return 0;
    }
    int l = height(p.left) + 1;
    int r = height(p.right) + 1;
    return (l > r) ? l : r;
}

// delete by copy integer/double
public void deleByCopy(int xPrice) {
    if (root == null) {
        System.out.println(" The tree is empty, no deletion");
        return;
    }
    Node f, p; // f will be the father of p
    p = root;
    f = null;
    while (p != null) {
        if (p.info.price == xPrice) {
            break;// Found key x
        }
        if (xPrice < p.info.price) {
            f = p;
            p = p.left;
        } else {
            f = p;
            p = p.right;
        }
    }
    if (p == null) {
        System.out.println(" The key " + xPrice + " does not exist, no deletion");
        return;
    }
    if (p.left == null && p.right == null) // p is a leaf node
    {
        if (f == null) // The tree is one node
        {
            root = null;
        } else {
            if (f.left == p) {
                f.left = null;
            } else {
                f.right = null;
            }
        }
        return;
    }
    if (p.left != null && p.right == null) // p has only left child
    {
        if (f == null) // p is a root
        {
            root = p.left;
        } else {
            if (f.left == p) // p is a left child
            {
                f.left = p.left;
            } else {
                f.right = p.left;
            }
        }
        return;
    }

    if (p.left == null && p.right != null) // p has only right child
    {
        if (f == null) // p is a root
        {
            root = p.right;
        } else {
            if (f.left == p) // p is aleft child
            {
                f.left = p.right;
            } else {
                f.right = p.right;
            }
        }
        return;
    }
    if (p.left != null && p.right != null) // p has both left and right children
    {
        Node q, fr, rp; // p's key will be replaced by rp's one
        fr = null;
        q = p.left;
        rp = q;
        while (rp.right != null) {
            fr = rp;
            rp = rp.right; // Find the right most node on the left sub-tree
        }
        p.info = rp.info;
        if (fr == null) // rp is just a left son of p
        {
            p.left = rp.left;
        } else {
            fr.right = rp.left;
        }
    }
}

// delete by copy string
void deleByCopy(String xName) {
    Node f, p;
    f = null;
    p = root;
    while (p != null) {
        if (p.info.name.equals(xName))
            break;
        f = p;
        if (xName.compareTo(p.info.name) < 0)
            p = p.left;
        else
            p = p.right;
    }
    if (p == null)
        return; // not found

    // p is leaf node
    if (p.left == null && p.right == null) {
        if (f == null) // p=root
        {
            root = null;
        } else {
            if (p == f.left)
                f.left = null;
            f.right = null;
        }
        return;
    }

    // p has left child only
    if (p.left != null && p.right == null) {
        if (f == null) // p=root
        {
            root = p.left;
        } else {
            if (p == f.left)
                f.left = p.left;
            f.right = p.left;
        }
        return;
    }

    // p has right child only
    if (p.left == null && p.right != null) {
        if (f == null) // p=root
        {
            root = p.right;
        } else {
            if (p == f.left)
                f.left = p.right;
            f.right = p.right;
        }
        return;
    }

    // p has both 2 children
    if (p.left != null && p.right != null) {// find the right most node
        Node q = p.left;
        Node frp, rp;
        frp = null;
        rp = q;
        while (rp.right != null) {
            frp = rp;
            rp = rp.right;
        }
        // rp is the right most node on the left child
        p.info = rp.info;
        if (frp == null) // rp=q
        {
            p.left = q.left;
        } else {
            frp.right = rp.left;
        }
    }
}

// delete by node p
public void deleteByCopy(Node p) {
    if (isEmpty()) {
        return;
    }
    if (p == null) {
        System.out.println("Key does not exists, deletion failed");
        return;
    }
    // Find Node f where f is father of p
    Node f = null;
    Node q = root;
    while (q != p) {
        if (q.info.name.compareTo(p.info.name) > 0) { // Changed
            f = q;
            q = q.left;
        } else {
            f = q;
            q = q.right;
        }
    }
    // 1. p is a leaf (no right and left child)
    if (p.left == null && p.right == null) {
        // a BST has a Node only
        if (f == null) {
            root = null;
        } else if (f.left == p) {
            f.left = null;
        } else if (f.right == p) {
            f.right = null;
        }
    }
    // 2. p has a left child only
    else if (p.left != null && p.right == null) {
        if (f == null) {// remove root
            root = p.left;
        } else if (f.right == p) {
            f.right = p.left;
        } else if (f.left == p) {
            f.left = p.left;
        }
    }
    // 3. p has a right child only
    else if (p.right != null && p.left == null) {
        if (f == null) {// remove root
            root = p.right;
        } else if (f.right == p) {
            f.right = p.right;
        } else if (f.left == p) {
            f.left = p.right;
        }
    }
    // 4. Both of right and left child
    else if (p.left != null && p.right != null) {
        f = null;
        Node rp = p.left;
        while (rp.right != null) {
            f = rp;
            rp = rp.right;
        }
        p.info = rp.info;
        if (f == null) {// rp has no right child
            p.left = rp.left;
        } else {
            f.right = rp.left;
        }
    }
}

// delete by merging by node p
public void deleteByMerging(Node p) {
    if (isEmpty()) {
        return;
    }
    if (p == null) {
        System.out.println("Key does not exists, deletion failed");
        return;
    }
    // Find Node f where f is father of p
    Node f = null;
    Node q = root;
    while (q != p) {
        if (q.info.name.compareTo(p.info.name) > 0) { // Changed
            f = q;
            q = q.left;
        } else {
            f = q;
            q = q.right;
        }
    }
    // 1. p is a leaf (no right and left child)
    if (p.left == null && p.right == null) {
        // a BST has a Node only
        if (f == null) {
            root = null;
        } else if (f.left == p) {
            f.left = null;
        } else if (f.right == p) {
            f.right = null;
        }
    }
    // 2. p has a left child only
    else if (p.left != null && p.right == null) {
        if (f == null) {// remove root
            root = p.left;
        } else if (f.right == p) {
            f.right = p.left;
        } else if (f.left == p) {
            f.left = p.left;
        }
    }
    // 3. p has a right child only
    else if (p.right != null && p.left == null) {
        if (f == null) {// remove root
            root = p.right;
        } else if (f.right == p) {
            f.right = p.right;
        } else if (f.left == p) {
            f.left = p.right;
        }
    }
    // 4. Both of right and left child
    else if (p.left != null && p.right != null) {
        Node q1 = p.left;

        while (q1.right != null) {
            q1 = q1.right;
        }
        q1.right = p.right;
        if (f == null) {// remove root
            root = p.left;
        } else if (f.left == p) {
            f.left = p.left;
        } else if (f.right == p) {
            f.right = p.left;
        }
    }
}

// delete node co gia tri depth max
public void deleteNodeDepthMax() {
    if (root == null) {
        return;
    }
    Node deepestNode = getDeepestNode(root);
    if (deepestNode != null) {
        deleteByCopy(deepestNode);
    }
}




// balance tree
public void balance(ArrayList a, int first, int last) {
    if (first > last)
        return;
    int m = (first + last) / 2;
    Person x = ((Node) a.get(m)).info;
    insert(x);
    balance(a, first, m - 1);
    balance(a, m + 1, last);
}

// balance simple array list
public void balance(Node p) {
    ArrayList a = new ArrayList();
    buildArray(a, p);
    int first = 0;
    int last = a.size() - 1;
    BSTree b = new BSTree(); // create new tree
    b.balance(a, first, last);
    root = b.root; // referen root to root b
}

// rotate left
public Node rotateLeft(Node p) {// must be have node right
    if (p.right == null) {
        return p;
    }
    Node q = p.right;
    p.right = q.left;
    q.left = p;
    return q;
}

// rotate right
public Node rotateRight(Node p) {
    if (p.left == null) {
        return p;
    }
    Node q = p.left;
    p.left = q.right;
    q.right = p;
    return q;
}

// rotate any node
public void rotateModifier(Node node) {
    Node nodeRotate = rotateToRight(node);
    Node nodeFather = father(node.info.price);
    if (nodeFather == null)
        root = nodeRotate;
    else {
        if (nodeFather.left == node)
            nodeFather.left = nodeRotate;
        else
            nodeFather.right = node;
    }
}

// rotate right root
Node rotateToRight(Node p) {// root = rotateToRight(root)
    if (p == null || p.left == null)
        return (p);
    Node q = p.left;
    p.left = q.right;
    q.right = p;
    return (q);
}

// get node father
Node father(int xPrice) {
    Node f, p;
    f = null;
    p = root;
    while (p != null) {
        if (p.info.price == xPrice)
            break;
        f = p;
        if (xPrice < p.info.price)
            p = p.left;
        else
            p = p.right;
    }
    return (f);
}

// get node by string
public Node getNode(String xName) {
    Node p = root;
    while (p != null) {
        if (p.info.name.compareToIgnoreCase(xName) > 0)
            p = p.left;
        else if (p.info.name.compareToIgnoreCase(xName) < 0)
            p = p.right;
        return p;
    }
    return null;
}

// get node by integer/double
public Node getNode(int x) {
    Node p = root;
    while (p != null) {
        if (p.info > x)
            p = p.left;
        else if (p.info < x)
            p = p.right;
        return p;
    }
    return null;
}

// get node father by node child
public Node getFather(Node child) {
    if (child == null || child == root) {
        return null; // root has no father
    }
    Node father = null;
    Node current = root;
    int key = child.info.price; // assuming info has price attribute

    while (current != null) {
        if (current.info.price == key) {
            break;
        }
        father = current;
        if (key < current.info.price) {
            current = current.left;
        } else {
            current = current.right;
        }
    }
    return father;
}

// get node in position k
public Node getNodeAtPosition(int k) {
    if (k < 1 || root == null) {
        return null; // Invalid position or empty tree
    }
    MyQueue m = new MyQueue();
    m.enqueue(root);
    int count = 0;
    while (!m.isEmpty()) {
        Node q = (Node) m.dequeue();
        count++;
        if (count == k) {
            return q; // Found the node at position k
        }
        if (q.left != null) {
            m.enqueue(q.left);
        }
        if (q.right != null) {
            m.enqueue(q.right);
        }
    }
    return null; // If k is greater than the number of nodes
}

// get node at position k by breadth-first traversal
public Node getNodeAtPosition_BreadthFirst(int k) {
    if (k < 1 || root == null) {
        return null; // Invalid position or empty tree
    }
    MyQueue m = new MyQueue();
    m.enqueue(root);
    int count = 0;
    while (!m.isEmpty()) {
        Node q = (Node) m.dequeue();
        count++;
        if (count == k) {
            return q; // Found the node at position k
        }
        if (q.left != null) {
            m.enqueue(q.left);
        }
        if (q.right != null) {
            m.enqueue(q.right);
        }
    }
    return null; // If k is greater than the number of nodes
}
// get node at position k by pere-order traversal
public Node getNodeAtPosition_Preorder(int k) {
    count = 0; 
    return getNodePreorder(root, k);
}
private Node getNodeAtPosition_Preorder_Helper(Node p, int k) {
    if (p == null) return null;

    count++;
    if (count == k) return p;

    Node left = getNodeAtPosition_Preorder_Helper(p.left, k);
    if (left != null) return left;

    return getNodeAtPosition_Preorder_Helper(p.right, k);
}
// get node at position k by post-order traversal
public Node getNodeAtPosition_Postorder(int k) {
    count = 0; 
    return getNodePostorder(root, k);
}
private Node getNodeAtPosition_Postorder_Helper(Node p, int k) {
    if (p == null) return null;

    Node left = getNodeAtPosition_Postorder_Helper(p.left, k);
    if (left != null) return left;

    Node right = getNodeAtPosition_Postorder_Helper(p.right, k);
    if (right != null) return right;

    count++;
    if (count == k) return p;

    return null;
}
// get node at position k by inorder traversal
public Node getNodeAtPosition_Inorder(int k) {
    count = 0; 
    return getNodeInorder(root, k);
}
private Node getNodeAtPosition_Inorder_Helper(Node p, int k) {
    if (p == null) return null;

    Node left = getNodeAtPosition_Inorder_Helper(p.left, k);
    if (left != null) return left;

    count++;
    if (count == k) return p;

    return getNodeAtPosition_Inorder_Helper(p.right, k);
}


// get node at position k have right child breadth-first traversal
public Node getNodeAtPositionWithRightChild(int k) {
    if (k < 1 || root == null) {
        return null; // Invalid position or empty tree
    }
    MyQueue m = new MyQueue();
    m.enqueue(root);
    int count = 0;
    while (!m.isEmpty()) {
        Node q = (Node) m.dequeue();
        if (q.right != null) { // Check if the node has a right child
            count++;
            if (count == k) {
                return q; // Found the node at position k with right child
            }
        }
        if (q.left != null) {
            m.enqueue(q.left);
        }
        if (q.right != null) {
            m.enqueue(q.right);
        }
    }
    return null; // If k is greater than the number of nodes with right child
}

// get node at position k have left child breadth-first traversal
public Node getNodeAtPositionWithLeftChild(int k) {
    if (k < 1 || root == null) {
        return null; // Invalid position or empty tree
    }
    MyQueue m = new MyQueue();
    m.enqueue(root);
    int count = 0;
    while (!m.isEmpty()) {
        Node q = (Node) m.dequeue();
        if (q.left != null) { // Check if the node has a left child
            count++;
            if (count == k) {
                return q; // Found the node at position k with left child
            }
        }
        if (q.left != null) {
            m.enqueue(q.left);
        }
        if (q.right != null) {
            m.enqueue(q.right);
        }
    }
    return null; // If k is greater than the number of nodes with left child
}

// get node at position k have right child pre-order traversal
public Node getNodeAtPositionWithRightChild_Preorder(int k) {
    count = 0; 
    return getNodePreorder(root, k);
}

private int count = 0;

private Node getNodePreorder(Node p, int k) {
    if (p == null)
        return null;

    
    if (p.right != null) {
        count++;
        if (count == k) {
            return p;
        }
    }

   
    Node leftResult = getNodePreorder(p.left, k);
    if (leftResult != null)
        return leftResult;

    
    return getNodePreorder(p.right, k);
}

// get node at position k have left child pre-order traversal
public Node getNodeAtPositionWithLeftChild_Preorder(int k) {
    count = 0; 
    return getNodePreorder_Left(root, k);
}

private Node getNodePreorder_Left(Node p, int k) {
    if (p == null)
        return null;

   
    if (p.left != null) {
        count++;
        if (count == k) {
            return p;
        }
    }

  
    Node leftResult = getNodePreorder_Left(p.left, k);
    if (leftResult != null)
        return leftResult;

   
    return getNodePreorder_Left(p.right, k);
}

// get node at position k have right child post-order traversal
public Node getNodeAtPositionWithRightChild_Postorder(int k) {
    count = 0; 
    return getNodePostorder(root, k);
}

private Node getNodePostorder(Node p, int k) {
    if (p == null)
        return null;

    Node leftResult = getNodePostorder(p.left, k);
    if (leftResult != null)
        return leftResult;

    
    Node rightResult = getNodePostorder(p.right, k);
    if (rightResult != null)
        return rightResult;

   
    if (p.right != null) {
        count++;
        if (count == k) {
            return p;
        }
    }

    return null;
}

// get node at position k have left child post-order traversal
public Node getNodeAtPositionWithLeftChild_Postorder(int k) {
    count = 0; 
    return getNodePostorder_Left(root, k);
}

private Node getNodePostorder_Left(Node p, int k) {
    if (p == null)
        return null;
   
    Node leftResult = getNodePostorder_Left(p.left, k);
    if (leftResult != null)
        return leftResult;
   
    Node rightResult = getNodePostorder_Left(p.right, k);
    if (rightResult != null)
        return rightResult;
   
    if (p.left != null) {
        count++;
        if (count == k) {
            return p;
        }
    }
    return null;
}


public Node getNodeAtPositionWithRightChild_Inorder(int k) {
    count = 0;
    return getNodeInorder(root, k);
}

private Node getNodeInorder(Node p, int k) {
    if (p == null)
        return null;

    Node leftResult = getNodeInorder(p.left, k);
    if (leftResult != null)
        return leftResult;
 
    if (p.right != null) {
        count++;
        if (count == k) {
            return p;
        }
    }
  
    return getNodeInorder(p.right, k);
}

// get node at position k have left child inorder traversal
public Node getNodeAtPositionWithLeftChild_Inorder(int k) {
    count = 0; 
    return getNodeInorder_Left(root, k);
}

private Node getNodeInorder_Left(Node p, int k) {
    if (p == null)
        return null;
 
    Node leftResult = getNodeInorder_Left(p.left, k);
    if (leftResult != null)
        return leftResult;
  
    if (p.left != null) {
        count++;
        if (count == k) {
            return p;
        }
    }
 
    return getNodeInorder_Left(p.right, k);
}

// xu ly cac bai toan su dung getNodeAtPositionWithLeftChild_Postorder
{Node p = getNodeAtPositionWithLeftChild_Postorder(2);
        if (p != null && p.left != null) {
            Node father = getFather(p);
            Node q = rotateRight(p);
            if (father == null) {
                root = q;
            } else {
                if (p.info.step < father.info.step) {
                    father.left = q;
                } else {
                    father.right = q;
                }
            }
        }
    }

    //get node have depth max breadth-first traversal
    Node getDeepestNode(Node p) {
    if (p == null) return null;
    MyQueue q = new MyQueue();
    q.enqueue(p);
    Node res = p;
    while (!q.isEmpty()) {
        Node r = (Node) q.dequeue();
        res = r; // Node cuối cùng của BFS là node sâu nhất
        if (r.left != null) q.enqueue(r.left);
        if (r.right != null) q.enqueue(r.right);
    }
    return res;
}
// get node have depth max pre-order traversal
Node deepest = null;
int maxDepth = -1;

void findDeepestPreOrder(Node p, int level) {
    if (p == null) return;
    
   
    if (level > maxDepth) {
        maxDepth = level;
        deepest = p;
    }

    findDeepestPreOrder(p.left, level + 1);
    findDeepestPreOrder(p.right, level + 1);
}

// get node have depth max post-order traversal
Node deepest = null;
int maxDepth = -1;

void findDeepestPostOrder(Node p, int level) {
    if (p == null) return;
   
    findDeepestPostOrder(p.left, level + 1);
 
    findDeepestPostOrder(p.right, level + 1);
    
    if (level > maxDepth) {
        maxDepth = level;
        deepest = p;
    }
}

// get node have depth max inorder traversal
Node deepest = null;
int maxDepth = -1;
void findDeepestInOrder(Node p, int level) {
    if (p == null) return;
   
    findDeepestInOrder(p.left, level + 1);
  
    if (level > maxDepth) {
        maxDepth = level;
        deepest = p;
    }

    findDeepestInOrder(p.right, level + 1);
}
// calculate level of node
public void calLevel(Node p) {
    if (p == null) {
        return;
    }
    MyQueue m = new MyQueue();
    m.enqueue(p);
    p.level = 1;// first, leve = 1
    while (!m.isEmpty()) {
        Node q = (Node) m.dequeue();
        if (q.left != null) {
            q.left.level = q.level + 1;
            m.enqueue(q.left);
        }
        if (q.right != null) {
            q.right.level = q.level + 1;
            m.enqueue(q.right);
        }

    }
}

// calculate factor
public void calculateBalance(Node p) {
    if (p == null) {
        return;
    }
    MyQueue m = new MyQueue();
    m.enqueue(p);
    while (!m.isEmpty()) {
        Node q = (Node) m.dequeue();
        if (q.left != null) {
            m.enqueue(q.left);
        }
        if (q.right != null) {
            m.enqueue(q.right);
        }
        q.bal = height(q.right) - height(q.left);
        if (isAVL && q.bal < -1 || q.bal > 1) {// De cho thuc hien nhieu
            isAVL = false;
        }
    }
}

// copy all node to tree by inorder traversal
public void buildArray(ArrayList a, Node p) {
    if (p == null) {
        return;
    }
    buildArray(a, p.left);
    a.add(p);
    buildArray(a, p.right);
}

// Calculate balance factor
void calculateFactorBalance(RandomAccessFile f123) throws Exception {
    boolean isAVL = true;
    MyQueue q = new MyQueue();
    q.enqueue(root);
    Node r;
    while (!q.isEmpty()) {
        r = q.dequeue();
        r.bal = height(r.right) - height(r.left);// int bal in class Node; // balance factor of the node p =
                                                 // height(p.right) - height(p.left)
        if (r.bal >= 2 || r.bal <= -2)
            isAVL = false;
        if (r.left != null)
            q.enqueue(r.left);
        if (r.right != null)
            q.enqueue(r.right);
    }
    breadthBal(root, f123);
    if (!isAVL)
        f123.writeBytes("\r\nThe tree is not an AVL tree\r\n");
    else
        f123.writeBytes("\r\nThe tree is an AVL tree\r\n");
}

public void breadthBal(Node p, RandomAccessFile f) throws Exception {// use for balance factor
    if (p == null)
        return;
    MyQueue q = new MyQueue();
    q.enqueue(p);
    Node r;
    while (!q.isEmpty()) {
        r = q.dequeue();
        fvisitBal(r, f);
        if (r.left != null)
            q.enqueue(r.left);
        if (r.right != null)
            q.enqueue(r.right);
    }
}

void fvisitBal(Node p, RandomAccessFile f) throws Exception {
    if (p != null)
        f.writeBytes("(" + p.info.name + "," + p.info.age + "," + p.bal + ") ");
}

// Calculate level all node
void calculateLevelAllNode() {
    MyQueue q = new MyQueue();
    if (isEmpty())
        return;
    root.level = 1;
    q.enqueue(root);
    Node r;
    while (!q.isEmpty()) {
        r = q.dequeue();
        if (r.left != null)
            r.left.level = r.level + 1;// level in class node
        if (r.right != null)
            r.right.level = r.level + 1;
        if (r.left != null)
            q.enqueue(r.left);
        if (r.right != null)
            q.enqueue(r.right);
    }
}

void fvisitLevel(Node p, RandomAccessFile f) throws Exception {
    if (p != null)
        f.writeBytes("(" + p.info.name + "," + p.info.age + "," + p.level + ") ");
}

// balance a binary search tree
void balance() {
    ArrayList<Person> t;
    t = new ArrayList<Person>();
    inOrder(t, root);
    int n = t.size();
    clear();
    balance(t, 0, n - 1);
}

void inOrder(ArrayList<Person> t, Node p) {
    if (p == null)
        return;
    inOrder(t, p.left);
    t.add(p.info);
    inOrder(t, p.right);
}

void balance(ArrayList<Person> t, int i, int j) {
    if (i > j)
        return;
    int k = (i + j) / 2;
    insert(t.get(k));// insert person
    balance(t, i, k - 1);
    balance(t, k + 1, j);
}
