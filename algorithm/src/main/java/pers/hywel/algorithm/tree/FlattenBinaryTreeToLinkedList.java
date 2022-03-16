package pers.hywel.algorithm.tree;

/**
 * 114. Flatten Binary Tree to Linked List 【Medium】
 *
 * Given the root of a binary tree, flatten the tree into a "linked list":
 *
 * The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list
 * and the left child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 *
 * Example 1:
 *
 * Input: root = [1,2,5,3,4,null,6]
 * Output: [1,null,2,null,3,null,4,null,5,null,6]
 * Example 2:
 *
 * Input: root = []
 * Output: []
 * Example 3:
 *
 * Input: root = [0]
 * Output: [0]
 *
 * @Date 2022-03-11
 */
public class FlattenBinaryTreeToLinkedList {

    /**
     * 从右 -> 左 -> 中顺序处理
     * 每个节点的左节点都是null，右节点为上一层节点
     */
    private TreeNode preNode = null;
    public void flatten(TreeNode root) {
        if (root == null) return;
        flatten(root.right);
        flatten(root.left);
        root.right = preNode;
        root.left = null;
        preNode = root;
    }
}