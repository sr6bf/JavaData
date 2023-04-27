//Sarah Raza
//sr6bf
//Homework 7b
//Resources used: n/a

package tree;

/**
 * Self-balancing AVL Tree
 * @author CS 2100 Team
 *
 * @param <T>
 */
 
 // Don't worry about this class for the first assignment in the module.
 // You WILL use this class in the second assignment on AVL trees.

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T>{
	
	@Override
	public void insert(T data) {
		this.root = insert(data, this.root);
	}
	protected TreeNode<T> insert(T data, TreeNode<T> curNode) {
		curNode = super.insert(data,curNode);
		if(curNode == null) {
			return null;
		}
		curNode.height = Math.max(height(curNode.left), height(curNode.right))+1;
		curNode = this.balance(curNode);
		return curNode;
	}

	
	/**
	 * Balances the given node. Assumes it is the lowest unbalanced node if unbalanced
	 * @param node
	 * @return
	 */
	private TreeNode<T> balance(TreeNode<T> curNode) {
		if(balanceFactor(curNode) < -1) {
			if(balanceFactor(curNode.left) <= 0){
				curNode = rotateRight(curNode);
			}
			else {
				curNode.left = rotateLeft(curNode.left);
				curNode = rotateRight(curNode);
				}
			}
		else if(balanceFactor(curNode) > 1){
			if(balanceFactor(curNode.right) >= 0){
				curNode = rotateLeft(curNode);
			}
			else {
				curNode.right = rotateRight(curNode.right);
				curNode = rotateLeft(curNode);
				}
			}
			return curNode;
		}
	
	private TreeNode<T> rotateRight(TreeNode<T> curNode) {
		TreeNode<T> leftLeaf = curNode.left;
		curNode.left = leftLeaf.right;
		leftLeaf.right = curNode;
		curNode.height = Math.max(height(curNode.left), height(curNode.right))+1;
		leftLeaf.height = Math.max(height(leftLeaf.left), height(leftLeaf.right))+1; //update heights

		return leftLeaf;
	}
	
	private TreeNode<T> rotateLeft(TreeNode<T> curNode){
		TreeNode<T> rightLeaf = curNode.right;
		curNode.right = rightLeaf.left;
		rightLeaf.left = curNode;
		curNode.height = Math.max(height(curNode.left), height(curNode.right))+1;
		rightLeaf.height = Math.max(height(rightLeaf.left), height(rightLeaf.right))+1;

		return rightLeaf;

	}
	
	private int balanceFactor(TreeNode<T> node) {
		return height(node.right)-height(node.left);
	}
	@Override
	public void remove(T data) {
		/* Call remove starting at the root of the tree */
		this.root = remove(data, this.root);
	}
	protected TreeNode<T> remove(T data, TreeNode<T> curNode) {
		/* Call BST remove before balancing, use “super” to achieve this */
		curNode = super.remove(data,  curNode);
		
		/* Handle the case when remove returns null */
		if(curNode == null) 
			return null;
		
		/* update the height of this node if necessary (if no change, that’s OK) */
		curNode.height = Math.max(height(curNode.left), height(curNode.right))+1;
		
		/* rotate if necessary (call balance() method to balance the node) */
		curNode = this.balance(curNode);
		
		return curNode;
		}
}

