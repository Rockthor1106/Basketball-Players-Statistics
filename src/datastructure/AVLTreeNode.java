package datastructure;

public class AVLTreeNode<T, K> {

	private T element;
	private K key;
	
	private int height;
	
	private AVLTreeNode<T, K> right;
	private AVLTreeNode<T, K> left;

	public AVLTreeNode() {
		height = 1;
	}
	
	public AVLTreeNode(T data, K k) {
		element = data;
		key = k;
		height = 1;
	}
	
	public T getData() {
		return element;
	}
	
	public K getKey() {
		return key;
	}
	
	public void setData(T newE) {
		element = newE;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int h) {
		height = h;
	}
	
	public AVLTreeNode<T, K> getRight(){
		return right;
	}
	
	public void setRight(AVLTreeNode<T, K> r) {
		right = r;
	}
	
	public AVLTreeNode<T, K> getLeft(){
		return left;
	}
	
	public void setLeft(AVLTreeNode<T, K> l) {
		left = l;
	}
}
