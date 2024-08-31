 public class LinkedSortedList<T extends Comparable<? super T>> implements SortedListInterface<T> {

    private Node firstNode;
    private int numberOfEntries;

    public LinkedSortedList() {
        numberOfEntries = 0;
        firstNode = null;
    }
    

    private class Node {
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
    public void add(T newEntry) {
        Node newNode = new Node(newEntry);
        Node nodeBefore = getNodeBefore(newEntry);

        if (isEmpty() || (nodeBefore == null)) {
            newNode.setNextNode(firstNode);
            firstNode = newNode;
        } else {
            Node nodeAfter = nodeBefore.getNextNode();
            newNode.setNextNode(nodeAfter);
            nodeBefore.setNextNode(newNode);
        }
        numberOfEntries++;
    }

    private Node getNodeBefore(T anEntry) {
        Node currentNode = firstNode;
        Node nodeBefore = null;

        while ((currentNode != null) && (anEntry.compareTo(currentNode.getData()) > 0)) {
            nodeBefore = currentNode;
            currentNode = currentNode.getNextNode();
        }
        return nodeBefore;
    }

    @Override
    public boolean remove(T anEntry) {
        boolean result = false;
        Node nodeToRemove = getNodeBefore(anEntry);
        if (nodeToRemove != null) {
            Node nodeAfter = nodeToRemove.getNextNode();
            if (nodeAfter != null && anEntry.equals(nodeAfter.getData())) {
                nodeToRemove.setNextNode(nodeAfter.getNextNode());
                numberOfEntries--;
                result = true;
            }
        }
        return result;
    }

    @Override
    public int getPosition(T anEntry) {
        int position = 1;
        Node currentNode = firstNode;

        while (currentNode != null && anEntry.compareTo(currentNode.getData()) > 0) {
            currentNode = currentNode.getNextNode();
            position++;
        }

        if (currentNode != null && anEntry.equals(currentNode.getData())) {
            return position;
        } else {
            return -position;
        }
    }

    @Override
    public T getEntry(int givenPosition) {
        if (givenPosition >= 1 && givenPosition <= numberOfEntries) {
            Node currentNode = firstNode;
            for (int i = 1; i < givenPosition; i++) {
                currentNode = currentNode.getNextNode();
            }
            return currentNode.getData();
        } else {
            throw new IndexOutOfBoundsException("Invalid position.");
        }
    }

    @Override
    public boolean contains(T anEntry) {
        Node currentNode = firstNode;
        while (currentNode != null) {
            if (anEntry.equals(currentNode.getData())) {
                return true;
            }
            currentNode = currentNode.getNextNode();
        }
        return false;
    }

    @Override
    public int getLength() {
        return numberOfEntries;
    }

    @Override
    public T remove(int givenPosition) {
        T result = null;
        if (givenPosition >= 1 && givenPosition <= numberOfEntries) {
            if (givenPosition == 1) {
                result = firstNode.getData();
                firstNode = firstNode.getNextNode();
            } else {
                Node nodeBefore = firstNode;
                for (int i = 1; i < givenPosition - 1; i++) {
                    nodeBefore = nodeBefore.getNextNode();
                }
                Node nodeToRemove = nodeBefore.getNextNode();
                result = nodeToRemove.getData();
                Node nodeAfter = nodeToRemove.getNextNode();
                nodeBefore.setNextNode(nodeAfter);
            }
            numberOfEntries--;
            return result;
        } else {
            throw new IndexOutOfBoundsException("Invalid position.");
        }
    }

    @Override
    public void clear() {
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Comparable[numberOfEntries];
        int index = 0;
        Node currentNode = firstNode;
        while (currentNode != null) {
            result[index] = currentNode.getData();
            currentNode = currentNode.getNextNode();
            index++;
        }
        return result;
    }
    
    public T removeFirst() {
    	if (isEmpty()) {
    		throw new IllegalStateException("List is Empty");
    	}
    	else {
    		T first = firstNode.getData();
    		firstNode = firstNode.getNextNode();
    		numberOfEntries--;
    		return first;
    	}
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
