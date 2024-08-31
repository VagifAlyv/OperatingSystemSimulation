public class LinkedPriorityQueue<T extends Comparable<? super T>> implements PriorityQueueInterface<T> {
   
	private Node firstNode;
	private Node lastNode;
    private int size;

    public LinkedPriorityQueue() {
        firstNode = null;
        firstNode = null;
        size = 0;
    }

    private class Node {
        private T data;
        private Node next;

        private Node(T data) {
            this(data, null);
        }

        private Node(T data, Node nextNode) {
            this.data = data;
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


   
    public void enqueue(T newEntry) {
		if (size==0)
		{
			firstNode = new Node (newEntry);
			lastNode = firstNode;
		}
		else
		{
			boolean added = false;
			Node newNode = new Node (newEntry);
			Node currentNode = firstNode;
			Node nodeBefore = null;
			while (!added && currentNode != null)
			{
				if (compareTasks(newNode.getData(), currentNode.getData()) < 0)
				{
					newNode.setNextNode(currentNode);
					if (nodeBefore!=null)
					{
						nodeBefore.setNextNode(newNode);
					}
					else
						firstNode = newNode;
					added = true;
				}
				nodeBefore = currentNode;
				currentNode = currentNode.getNextNode();
			
			}
			if (!added)
			{
				lastNode.setNextNode(newNode);
				lastNode = lastNode.getNextNode();
				added = true;
			}
		}
		size++;
	}

    private int compareTasks(T task1, T task2) {
		Task t1 = (Task) task1;
		Task t2 = (Task) task2;
		

		int priorityComparison = Integer.compare(t2.getPriority(), t1.getPriority());
		if(priorityComparison != 0) {
			return priorityComparison;
		}
		return t1.getArrivalDateTime().compareTo(t2.getArrivalDateTime());
	}
    
    @Override
    public T dequeue() {
        T result = null;

        if (!isEmpty()) {
            result = firstNode.getData();
            firstNode = firstNode.getNextNode();
            size--;
        }
        if (firstNode == null) {
        	lastNode = null;
        }
        return result;
    }

    @Override
    public T peek() {
        T result = null;

        if (!isEmpty()) {
            result = firstNode.getData();
        }

        return result;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }
    
    public void display()
	{
		Node currentNode = firstNode;
		while (currentNode!=null)
		{
			System.out.println(currentNode.data);
			currentNode = currentNode.next;
		}
		 System.out.println();
	}
}