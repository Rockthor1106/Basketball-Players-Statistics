package datastructure;

import java.util.ArrayList;
import java.util.List;

public class AVLTree <T extends Comparable<T>, K> implements IAVLTree<T, K>{

	AVLTreeNode<T, K> root;
	
	public AVLTree() {
		root = null;
	}
	
	public AVLTree(T r, K k) {
		root = new AVLTreeNode<T, K>(r, k);
	}
	//Revisa si el arbol se encuentra vacio.
	@Override
	public boolean isEmpty() {
		return root == null;
	}
	//Obtener la altura de un arbol. Evita complicaciones con nodos == null.
	@Override
	public int getHeight(AVLTreeNode<T, K> n) {
		if(n == null) {
			return 0;
		}else return n.getHeight();
	}
	@Override
	public void changeHeight(AVLTreeNode<T, K> node) {
		int hL = getHeight(node.getLeft());
		int hR = getHeight(node.getRight());
		node.setHeight(((hL>hR)?hL:hR)+1);
	}
	//Obtiene el factor de balance del subarbol n.
	@Override
	public int getBalance(AVLTreeNode<T, K> n) {
		if(n == null) {
			return 0;
		}else return getHeight(n.getLeft())-getHeight(n.getRight());
	}
	//Rotaci�n izquierda del subarbol node.
	@Override
	public AVLTreeNode<T, K> leftRotate(AVLTreeNode<T, K> node){
		AVLTreeNode<T, K> temp = node.getRight();
		
		node.setRight(temp.getLeft());

		int hL = getHeight(node.getLeft());
		int hR = getHeight(node.getRight());
		node.setHeight(((hL>hR)?hL:hR)+1);
		
		temp.setLeft(node);
				
		hL = getHeight(temp.getLeft());
		hR = getHeight(temp.getRight());
		temp.setHeight(((hL>hR)?hL:hR)+1);
		
		return temp;
	}
	//Rotaci�n derecha del subarbol node.
	@Override
	public AVLTreeNode<T, K> rightRotate(AVLTreeNode<T, K> node){
		AVLTreeNode<T, K> temp = node.getLeft();
		
		node.setLeft(temp.getRight());
		
		int hL = getHeight(node.getLeft());
		int hR = getHeight(node.getRight());
		node.setHeight(((hL>hR)?hL:hR)+1);
		
		temp.setRight(node);
		
		hL = getHeight(temp.getLeft());
		hR = getHeight(temp.getRight());
		temp.setHeight(((hL>hR)?hL:hR)+1);
		
		return temp;
	}
	//Insertar un nuevo nodo.
	@Override
	public AVLTreeNode<T, K> insert(T element, K key) {
		if(root == null) {
			root = new AVLTreeNode<>(element, key);
			return root;
		}else return insert(element, key, root);
	}
	
	public AVLTreeNode<T, K> insert(T element, K key, AVLTreeNode<T, K> node) {
		if(node == null) {
			return (new AVLTreeNode<>(element, key));
		}
		if(element.compareTo(node.getData())>=0) {
			node.setRight(insert(element, key, node.getRight())); 
		}else {
			node.setLeft(insert(element, key, node.getLeft()));
		}
		int hL = getHeight(node.getLeft());
		int hR = getHeight(node.getRight());
		node.setHeight(((hL>hR)?hL:hR)+1);
		
		int balance = getBalance(node);
		
		if(balance > 1) {
			if(element.compareTo(node.getLeft().getData()) < 0) {
				return rightRotate(node);
			}else if(element.compareTo(node.getLeft().getData()) >= 0) {
				node.setLeft(leftRotate(node.getLeft()));
				return rightRotate(node);
			}
		}else if(balance < -1) {
			if(element.compareTo(node.getRight().getData()) >= 0) {
				return leftRotate(node);
			}else if(element.compareTo(node.getRight().getData()) < 0) {
				node.setRight(rightRotate(node.getRight()));
				return leftRotate(node);
			}
		}
		return node;
	}
	//Eliminar un nodo.
	@Override
	public AVLTreeNode<T, K> delete(T element){
		if(isEmpty()) {
			return root;
		}else return delete(element, root);
	}
	
	public AVLTreeNode<T, K> delete(T element, AVLTreeNode<T, K> node){
		if(element.compareTo(node.getData()) > 0) {
			node.setLeft(delete(element, node.getRight()));
		}else if(element.compareTo(node.getData()) < 0) {
			node.setRight(delete(element, node.getRight()));
		}else {
			if(node.getLeft() == null || node.getRight() == null) {
				AVLTreeNode<T, K> temp;
				if(node.getLeft() != null) {
					temp = node.getLeft();
				}else {
					temp = node.getRight();
				}
				
				if(temp == null) {
					temp = node;
					node = null;
				}else node = temp;
			}else {
				AVLTreeNode<T, K> temp = getMin(node.getRight());
				node.setData(temp.getData());
				node.setRight(delete(temp.getData(), node.getRight()));
			}
			changeHeight(node);
			int balance = getBalance(node);
			
			if(balance > 1) {
				if(getBalance(node.getLeft()) >= 0) {
					return rightRotate(node);
				}else {
					node.setLeft(leftRotate(node.getLeft()));
					return rightRotate(node);
				}
			}else if(balance < -1) {
				if(getBalance(node.getRight()) <= 0) {
					return leftRotate(node);
				}else {
					node.setRight(rightRotate(node.getRight()));
					return leftRotate(node);
				}
			}
		}
		return node;
	}
	//Obtener el valor minimo.
	@Override
	public AVLTreeNode<T, K> getMin(AVLTreeNode<T, K> root){
		while(root.getLeft() != null) {
			root = root.getLeft();
		}
		return root;
	}
	//Obtener el valor m�ximo.
	@Override
	public AVLTreeNode<T, K> getMax(AVLTreeNode<T, K> root){
		while(root.getRight() != null) {
			root = root.getRight();
		}
		return root;
	}
	
	public List<K> getEquals(T value){
		if(!isEmpty()) {		
			return getEquals(value, root);
		} else return null;
	}
	public List<K> getEquals(T value, AVLTreeNode<T, K> node){
		List<K> players = new ArrayList<K>();
		while(!value.equals(node.getData()) && node != null) {
			if(value.compareTo(node.getData()) > 0) {
				if(node.getRight() != null) {
					node = node.getRight();
				}else return null;
			}else if(value.compareTo(node.getData()) < 0) {
				if(node.getRight() != null) {
					node = node.getLeft();
				}else return null;
			}
		}
		if(node != null) {
			boolean equals = true;
			players.add(node.getKey());
			while(node.getRight() != null && equals) {
				node = node.getRight();
				if(value.equals(node.getData())) {
					players.add(node.getKey());
				}else equals = false;
			}
		}
		return players;
	}
	public List<K> getLess(){
		return null;
	}
	public List<K> getHigher(){
		return null;
	}
}
