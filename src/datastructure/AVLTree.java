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
	public AVLTreeNode<T, K> getRoot(){
		return root;
	}
	//Cantidad de nodos en el arbol.
	@Override
	public int getSize() {
		return getSize(root);
	}
	public int getSize(AVLTreeNode<T, K> root) {
		if(root == null) {
			return 0;
		}else {
			return getSize(root.getLeft())+getSize(root.getRight())+1;
		}
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
	//Actualiza la altura de un arbol.
	@Override
	public int changeHeight(AVLTreeNode<T, K> node) {
		int hL = getHeight(node.getLeft());
		int hR = getHeight(node.getRight());
		return ((hL>hR)?hL:hR)+1;
	}
	//Obtiene el factor de balance del subarbol n.
	@Override
	public int getBalance(AVLTreeNode<T, K> n) {
		if(n == null) {
			return 0;
		}else return getHeight(n.getLeft())-getHeight(n.getRight());
	}
	//Rotación izquierda del subarbol node.
	@Override
	public AVLTreeNode<T, K> leftRotate(AVLTreeNode<T, K> node){
		AVLTreeNode<T, K> temp = node.getRight();
		node.setRight(temp.getLeft());
		node.setHeight(changeHeight(node));
		temp.setLeft(node);
		temp.setHeight(changeHeight(temp));
		return temp;
	}
	//Rotación derecha del subarbol node.
	@Override
	public AVLTreeNode<T, K> rightRotate(AVLTreeNode<T, K> node){
		AVLTreeNode<T, K> temp = node.getLeft();
		node.setLeft(temp.getRight());
		node.setHeight(changeHeight(node));
		temp.setRight(node);
		temp.setHeight(changeHeight(temp));
		return temp;
	}
	//Insertar un nuevo nodo.
	@Override
	public void insert(T element, K key) {
		root = insert(element, key, root);
	}
	public AVLTreeNode<T, K> insert(T element, K key, AVLTreeNode<T, K> node) {
		if (node == null) {
			node = new AVLTreeNode<T, K>(element, key);
		}else if(element.compareTo(node.getData()) < 0) {
			node.setLeft(insert(element, key, node.getLeft()));
			if (getBalance(node) == 2) {
				if (element.compareTo(node.getLeft().getData()) < 0) {
					node = rightRotate(node);
				} else {
					node.setLeft(leftRotate(node.getLeft()));
					node = rightRotate(node);
				}
			}
		} else if (element.compareTo(node.getData()) >= 0) {
			node.setRight(insert(element, key, node.getRight()));
			if (getBalance(node) == -2) {
				if (element.compareTo(node.getRight().getData()) >= 0) {
					node = leftRotate(node);
				} else {
					node.setRight(rightRotate(node.getRight()));
					node = leftRotate(node);
				}
			}
		}
		node.setHeight(changeHeight(node));
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
			node.setHeight(changeHeight(node));
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
	public AVLTreeNode<T, K> getMin(){
		return getMin(root);
	}
	public AVLTreeNode<T, K> getMin(AVLTreeNode<T, K> root){
		while(root.getLeft() != null) {
			root = root.getLeft();
		}
		return root;
	}
	//Obtener el valor máximo.
	@Override
	public AVLTreeNode<T, K> getMax(){
		return getMax(root);
	}
	public AVLTreeNode<T, K> getMax(AVLTreeNode<T, K> root){
		while(root.getRight() != null) {
			root = root.getRight();
		}
		return root;
	}
	//Obtener jugadores con valor igual a T:value
	@Override
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
	//Obtener jugadores con valor menor a T:value
	@Override
	public List<K> getLess(T value){
		if(!isEmpty()) {
			return getLess(value, root);
		}else return null;
	}
	public List<K> getLess(T value, AVLTreeNode<T, K> node){
		List<K> players = new ArrayList<K>();
		while(node.getData().compareTo(value) >= 0 && node != null){
			node = node.getLeft();
		}
		players.add(node.getKey());
		players.addAll(getLess(node.getLeft()));
		players.addAll(getLess(node.getRight(), value));
		return players;
	}
	public List<K> getLess(AVLTreeNode<T, K> node){
		if(node == null) {
			return null;
		}else {
			List<K> pK = new ArrayList<>();
			pK.add(node.getKey());
			if(node.getLeft() != null)
				pK.addAll(getLess(node.getLeft()));
			if(node.getRight() != null)
				pK.addAll(getLess(node.getRight()));
			return pK;
		}
	}
	public List<K> getLess(AVLTreeNode<T, K> node, T value){
		if(node == null) {
			return null;
		}else {
			List<K> pK =  new ArrayList<>();
			if(node.getLeft() != null) {
				pK.addAll(getLess(node.getLeft(), value));
			}
			if(node.getData().compareTo(value) < 0)
				pK.add(node.getKey());
			if(node.getRight() != null) {
				pK.addAll(getLess(node.getRight(), value));
			}
			return pK;
		}
	}
	//Obtener jugadores con valor mayor a T:value
	@Override
	public List<K> getHigher(T value){
		if(!isEmpty()) {
			return getHigher(value, root);
		}else return null;
	}
	public List<K> getHigher(T value, AVLTreeNode<T,K> node){
		List<K> players = new ArrayList<K>();
		while(node.getData().compareTo(value) <= 0 && node != null) {
			node = node.getRight();
		}
		players.add(node.getKey());
		players.addAll(getHigher(node.getRight()));
		players.addAll(getHigher(node.getLeft(), value));
		return players;
	}
	public List<K> getHigher(AVLTreeNode<T, K> node) {
		if(root == null) {
			return null;
		}else {
			List<K> pK = new ArrayList<>();
			pK.add(node.getKey());
			if(node.getLeft() != null) {
				pK.addAll(getLess(node.getLeft()));
			}
			if(node.getRight() != null) {
				pK.addAll(getLess(node.getRight()));
			}
			return pK;
		}
	}
	public List<K> getHigher(AVLTreeNode<T, K> node, T value){
		if(node == null) {
			return null;
		}else {
			List<K> pK = new ArrayList<K>();
			if(node.getLeft() != null) {
				pK.addAll(getHigher(node.getLeft(), value));
			}
			if(node.getData().compareTo(value) > 0)
				pK.add(node.getKey());
			if(node.getRight() != null) {
				pK.addAll(getHigher(node.getRight(), value));
			}
			return pK;
		}
	}
	//Obtener las K:keys de un arbol en pre orden.
	@Override
	public String preOrder(AVLTreeNode<T, K> node) {
		if(node == null) {
			return "";
		}
		String str = "";
		str += " "+node.getData();
		str += preOrder(node.getLeft());
		str += preOrder(node.getRight());
		return str;
	}
}
