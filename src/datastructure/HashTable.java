package datastructure;

import java.util.ArrayList;

public class HashTable<K, V> implements IHashTable<K,V>{
	
	protected int size;
	protected ArrayList<V> table;
	
	public HashTable(int s) {
		size = s;
		table = new ArrayList<>(s);
		for(int i = 0; i<s; i++) {
			table.add(i, null);
		}
	}
	@Override
	public int getSize() {
		return size;
	}
	@Override
	public int addItem(K key, V item) {
		int index = (int) key;
		if(index < size){
			if(table.get(index) == null) {
				table.set(index, item);
			}else {
				boolean full = false;
				while(table.get(index) != null && !full) {
					if(index< size-1) {
						index++;
					}else {
						index = 0;
					}
					if(index == (int) key) {
						full = true;
					}
				}
				if(!full) {
					table.set(index, item);				
				}else {
					size++;
					table.add(size, item);
				}
			}
		}else {
			size++;
			table.add(index, item);
		}
		
		return index;
	}
	@Override
	public ArrayList<V> getTable(){
		return table;
	}
	@Override
	public V getItem(K key) {
		int index = (int) key;
		return table.get(index);
	}
}
