 
public interface PriorityQueueInterface<T extends Comparable<? super T>> {
	
	public void enqueue(T newEntry);
	
	public T dequeue();
	
	public T peek();
	
	public boolean isEmpty();
	
	public void clear();
	
	public int getSize();

	
}