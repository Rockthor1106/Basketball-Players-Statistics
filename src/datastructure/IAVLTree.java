package datastructure;

public interface IAVLTree<T, K> {
	public boolean isEmpty();
	public int getHeight(AVLTreeNode<T, K> n);
	public void changeHeight(AVLTreeNode<T, K> node);
	public int getBalance(AVLTreeNode<T, K> n);
	public AVLTreeNode<T, K> leftRotate(AVLTreeNode<T, K> n);
	public AVLTreeNode<T, K> rightRotate(AVLTreeNode<T, K> node);
	public AVLTreeNode<T, K> insert(T element);
	public AVLTreeNode<T, K> delete(T element);
	public AVLTreeNode<T, K> getMin(AVLTreeNode<T, K> root);
	public AVLTreeNode<T, K> getMax(AVLTreeNode<T, K> root);
}
