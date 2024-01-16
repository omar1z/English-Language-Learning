package Container;

import java.util.ArrayList;

public class MyHashMap<K,V> implements Container{
	private ArrayList<Entry<K,V>> map = new ArrayList<>();
	public void put(K key, V value) {
		for(Entry i : map) {
			if(i.getKey().equals(key)) {
				i.setValue(value);
				return;
			}
		}
		Entry<K,V> en = new Entry<>(key,value);
		map.add(en);
		
	}
	public int size() {
		return map.size();
	}
	public MyIterator getIterator() {
		return new ObjectIterator();
	}
	public static class Entry<K,V>{
		private K key;
		private V value;
		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		public K getKey() {
			return key;
		}
		public V getValue() {
			return value;
		}
		public void setValue(V value) {
			this.value = value;
		}
		public void setKey() {
			this.key = key;
		}
	}
	private class ObjectIterator implements MyIterator<Entry>{
		int index = 0;
		public boolean hasNext() {
			if(index < map.size()) return true;
			else return false;
		}
		public Entry next() {
			Entry  e = map.get(index);
			index ++;
			return e;
		}
		
	}

}
