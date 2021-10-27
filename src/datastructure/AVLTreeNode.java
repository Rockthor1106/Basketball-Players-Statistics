package datastructure;

public class AVLTreeNode<T> {

	private T element;
	
	private int height;
	
	private AVLTreeNode<T> right;
	private AVLTreeNode<T> left;

	public AVLTreeNode() {
		height = 1;
	}
	
	public AVLTreeNode(T data) {
		element = data;
		height = 1;
	}
	
	public T getData() {
		return element;
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
	
	public AVLTreeNode<T> getRight(){
		return right;
	}
	
	public void setRight(AVLTreeNode<T> r) {
		right = r;
	}
	
	public AVLTreeNode<T> getLeft(){
		return left;
	}
	
	public void setLeft(AVLTreeNode<T> l) {
		left = l;
	}
}
