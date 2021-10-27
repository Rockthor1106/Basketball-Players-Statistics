package datastructure;

public interface IAVLTree<T> {
	public boolean isEmpty();
	public int getHeight(AVLTreeNode<T> n);
	public void changeHeight(AVLTreeNode<T> node);
	public int getBalance(AVLTreeNode<T> n);
	public AVLTreeNode<T> leftRotate(AVLTreeNode<T> n);
	public AVLTreeNode<T> rightRotate(AVLTreeNode<T> node);
	public AVLTreeNode<T> insert(T element);
	public AVLTreeNode<T> delete(T element);
	public AVLTreeNode<T> getMin(AVLTreeNode<T> root);
	public AVLTreeNode<T> getMax(AVLTreeNode<T> root);
}
