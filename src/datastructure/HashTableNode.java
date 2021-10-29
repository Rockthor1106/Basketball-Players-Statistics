package datastructure;

public class HashTableNode <K, V> {
	K key;
	V value;
	
	public HashTableNode(K k, V v) {
		key = k;
		value = v;
	}
	
	public K getKey() {
		return key;
	}
	
	public V getValue() {
		return value;
	}

}
