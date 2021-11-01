package datastructure;

import java.util.List;

public interface IAVLTree<T, K> {
	public boolean isEmpty();
	public int getSize();
	public int getHeight(AVLTreeNode<T, K> n);
	public int changeHeight(AVLTreeNode<T, K> node);
	public int getBalance(AVLTreeNode<T, K> n);
	public AVLTreeNode<T, K> leftRotate(AVLTreeNode<T, K> n);
	public AVLTreeNode<T, K> rightRotate(AVLTreeNode<T, K> node);
	public void insert(T element, K key);
	public AVLTreeNode<T, K> delete(T element);
	public AVLTreeNode<T, K> getMin();
	public AVLTreeNode<T, K> getMax();
	public List<K> getEquals(T value);
	public List<K> getLess(T value);
	public List<K> getHigher(T value);
	public String preOrder(AVLTreeNode<T, K> node);
}
