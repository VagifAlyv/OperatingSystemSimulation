import java.util.EmptyStackException;

public class LinkedStack<T> implements StackInterface<T>{

	private Node topNode;
	private int size;
	public LinkedStack() {
		topNode = null;
		size = 0;
	}

	private class Node{
		private T data;
		private Node next;
		
		private Node(T dataPortion) {
			this(dataPortion, null);
		}

		public Node(T dataPortion, Node nextNode) {
			data = dataPortion;
			next = nextNode;
		}
		
		private T getData() {
			return data;
		}
		private void setData(T newData) {
			data = newData;
		}
		private Node getNextNode() {
			return next;
		}
		private void setNextNode(Node nextNode) {
			next = nextNode;
		}

	}
	@Override
	public void push(T newEntry) {
		Node newNode = new Node(newEntry);
		
		if(isEmpty() || compareTasks(newEntry, topNode.data) <= 0) {
			newNode.next = topNode;
			topNode = newNode;
		}
		else {
			Node currentNode = topNode;
			while(currentNode.next != null && compareTasks(newEntry, currentNode.next.data)> 0) {
				currentNode = currentNode.next;
			}
			
			newNode.next = currentNode.next;
			currentNode.next = newNode;
		}
		size++;
	}
	
	private int compareTasks(T task1, T task2) {
		Task t1 = (Task) task1;
		Task t2 = (Task) task2;
		
		
		int burstTime = Integer.compare(t1.getBurstTime(), t2.getBurstTime());
		if(burstTime != 0) { // to check if they have the same burst time or not
			return burstTime;
		}
		return t1.getArrivalDateTime().compareTo(t2.getArrivalDateTime());
		
	}
	

	@Override
	public T pop() {
		T top = peek();
		
		assert topNode != null;
		topNode = topNode.getNextNode();
		size--;
		return top;
		
	}
	
	@Override
	public T peek() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		else {
			return topNode.getData();
		}
	}
	@Override
	public boolean isEmpty() {
		return topNode == null;
	}
	@Override
	public void clear() {
		topNode = null;
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public void display()
	{
		Node currentNode = topNode;
		while (currentNode!=null)
		{
			System.out.println(currentNode.data);
			currentNode = currentNode.next;
		}
		 System.out.println();
	}
	
	
}

