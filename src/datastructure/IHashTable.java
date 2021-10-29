package datastructure;

import java.util.ArrayList;

public interface IHashTable<K, V> {

	public int getSize();
	public int addItem(K key, V item);
	public ArrayList<V> getTable();
	public V getItem(K key);
}
