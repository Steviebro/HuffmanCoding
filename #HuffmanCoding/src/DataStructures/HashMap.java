package DataStructures;


/**
 * A HashMap implementation for char keys and generic V values. 
 * @author Steven Gingras (40098045)
 *
 * @param <V> values assigned to a char key
 */
public class HashMap<V> {
	
	@SuppressWarnings("hiding")
	/**
	 * Entries with char keys and generic V values.
	 * @author Steven Gingras (40098045)
	 *
	 * @param <V> values assigned to a char key
	 */
	private class Entry<V> {
		
		private char key;
		private V value;
		private Entry<V> next;
		
		public Entry(char key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public void setValue(V value) {
			this.value = value;
		}
		
		public void setNext(Entry<V> next) {
			this.next = next;
		}
		
		public char getKey() {
			return this.key;
		}
		
		public V getValue() {
			return this.value;
		}
		
		public Entry<V> getNext() {
			return this.next;
		}
		
		public String toString() {
			return "Key: "+key+"\tValue: "+value;
		}
	}
	
	private Entry<V>[] entries;
	private static final int SIZE = 67;
	private int numOfEntries;
	
	@SuppressWarnings("unchecked")
	public HashMap() {
		entries = new Entry[SIZE];
	}
	/**
	 * Creates a char array containing all the keys in the HashMap
	 * @return a char array of keys
	 */
	public char[] getKeys() {
		if (numOfEntries == 0)
			return null;
		
		int index = 0;
		char[] result = new char[numOfEntries];
		
		
		for (Entry<V> e : entries) {
			while (e != null && index < result.length	) {
				result[index++] = e.getKey();
				e = e.next;
			}
		}
		return result;
	}
	/**
	 * Contains the hash function: ASCII code of the char
	 * @param key a char to pass through the hashing function
	 * @return the hash value assigned to the key
	 */
	public int hash(char key) {
		return ((int)key) % SIZE;
	}
	/**
	 * Inserts the key/value pair if the key is absent or updates the value if the key is present.
	 * @param key a char key
	 * @param value a generic value
	 */
	public void put(char key, V value) {
		int hash = hash(key);
		Entry<V> probe = entries[hash];
		
		if (probe == null) {
			entries[hash] = new Entry<>(key, value);
			numOfEntries++;
		}
			
		else {
			
			while (probe.getNext() != null) {
				if (probe.getKey() == key) {
					probe.setValue(value);
					return;
				}
				probe = probe.getNext();
			}
			
			if (probe.getKey() == key) {
				probe.setValue(value);
				return;
			}
			probe.setNext(new Entry<>(key, value));
			numOfEntries++;
		}
	}
	/**
	 * Obtains the value assigned to the passed key or null if the key is absent.
	 * @param key a char key
	 * @return a generic value
	 */
	public V get(char key) {
		int hash = hash(key);
		Entry<V> probe = entries[hash];
		
		if (probe == null)
			return null;
		
		while (probe != null) {
			if (probe.getKey() == key) {
				return probe.getValue();
			}
				
			probe = probe.getNext();	
		}
		return null;
	}
	/**
	 * Obtains the value assigned to the passed key or the default value if the key is absent.
	 * @param key a char key
	 * @param defaultValue a generic default value
	 * @return a generic value
	 */
	public V getOrDefault(char key, V defaultValue) {
		V result = get(key);
		
		if (result != null)
			return result;
		else
			return defaultValue;
	}
	
	public String toString() {
		String result = "";
		for (Entry<V> e : entries) {
			while(e != null) {
				result = result.concat("Key: "+e.getKey()+"\tValue: "+e.getValue()+"\tHash "+hash(e.getKey())+"\tNext: "+e.getNext()+"\n\n");
				e = e.getNext();
			}
		}
		return result;
	}
}
