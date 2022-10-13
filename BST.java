/**
 * BST
 * COSC2203 Data Structures
 * Assignment 05 Component B
 * 10/14/2022
 *
 * @author Chacko Happy
 *         This class implements a Binary Search Tree tailored towards this
 *         problem
 */
public class BST {

    BTNode root;

    BST() {
    }

    /**
     * search() This method searches for the given value from the intensity
     * values in the BST
     *
     * @param target The value that is being searched for
     * @return BTNode Returns the Node with the given value
     */
    public BTNode search(int target) {
        return searchR(root, target);
    }

    /**
     * searchR() This method is the helper function for search()
     *
     * @param node   Used for recursion
     * @param target The value that is being searched for
     * @return BTNode Used for recursion
     */
    public BTNode searchR(BTNode node, int target) {
        if (node == null || node.data.intensity == target) {
            return node;
        }

        if (node.data.intensity < target) {
            return searchR(node.right, target);
        }

        return searchR(node.left, target);
    }

    /**
     * searchNew() This method searches for the given value from the new
     * intensity values in the BST
     *
     * @param target The value that is being searched for
     * @return BTNode Returns the Node with the given value
     */
    public BTNode searchNew(int target) {
        return searchNewR(root, target);
    }

    /**
     * searchNewR() This method is the helper function for searchNew()
     *
     * @param node   Used for recursion
     * @param target The value that is being searched for
     * @return BTNode Used for recursion
     */
    public BTNode searchNewR(BTNode node, int target) {
        if (node == null || node.data.newIntensity == target) {
            return node;
        }

        if (node.data.newIntensity < target) {
            return searchNewR(node.right, target);
        }

        return searchNewR(node.left, target);
    }

    /**
     * add() This method adds the given value to the BST
     *
     * @param value The value to be added to the BST
     */
    public void add(Pixel value) {
        addR(root, value);
    }

    /**
     * addR() This method is the helper function for add()
     *
     * @param root Used for recursion
     * @param key  The pixel to be added to the tree
     * @return BTNode Used for recursion
     */
    private BTNode addR(BTNode root, Pixel key) {
        if (root == null) {
            root = new BTNode(key);
            return root;
        }
        if (key.intensity < root.data.intensity) {
            root.left = addR(root.left, key);
        } else if (key.intensity > root.data.intensity) {
            root.right = addR(root.right, key);
        }

        return root;
    }

    /**
     * countNodes() This method counts the nodes in the tree
     *
     * @return int The number of nodes in the tree
     */
    public int countNodes() {
        return countR(root);
    }

    /**
     * countR() This method is the helper function for countNodes()
     *
     * @param node Used for recursion
     * @return int The number of nodes in the tree
     */
    private int countR(BTNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + countR(node.left) + countR(node.right);
    }

    /**
     * height() This method finds the height of the tree
     *
     * @return int The height of the tree
     */
    public int height() {
        return heightR(root);
    }

    /**
     * heightR() This method is the helper function for height()
     *
     * @param node Used for recursion
     * @return int The height of the tree
     */
    private int heightR(BTNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(1 + heightR(node.left), 1 + heightR(node.right));
    }
}
