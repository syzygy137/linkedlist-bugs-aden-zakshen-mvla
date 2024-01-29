package MyLinkedList;
import java.util.AbstractList;

// TODO: Auto-generated Javadoc
/**
 *  A class that implements a doubly linked list.
 *
 * @author UC San Diego Intermediate Programming MOOC team/Scott Murray
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	
	/** The head. */
	final LLNode<E> head;
	
	/** The tail. */
	final LLNode<E> tail;
	
	/** The size. */
	int size;

	/**
	 *  Create a new empty LinkedList.
	 */
	public MyLinkedList() {
		head = new LLNode<>(null);
		tail = new LLNode<>(null);
		tail.prev = head;
		head.prev = tail;
		size = 0;
	}

	/**
	 * Appends an element to the end of the list.
	 *
	 * @param element The element to add.
	 * @return true if the add was successful (which it should always be)
	 */
	public boolean add(E element ) 
	{
		LLNode<E> node = new LLNode<E>(element,tail.prev,tail);
		tail.prev.next = node;
		tail.prev = node;
		size++;
		return true;
	}

	/**
	 * Returns the node at position specified by index.
	 * Challenge: Can you take advantage of the doubly-linked lists to
	 * improve performance?
	 *
	 * @param index the index of the node to access.
	 * @return the node at the specified index
	 */
	private LLNode<E> getNodeAtIndex(int index) {
		LLNode<E> node;

		if (index/size<0.5) {
			node = head.next;
			for (int i = 0; i < index; i++) 
				node = node.next;
		} else {
			node = tail.prev;
			for (int i = (size); i > index; i--)
				node = node.prev;
		}
		return node;
	}

	/**
	 *  Get the element at position index .
	 *
	 * @param index the index
	 * @return the data element associated with this node.
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E get(int index) throws IndexOutOfBoundsException 
	{
		if (index < 0 || index >=size) 
			throw new IndexOutOfBoundsException();

		LLNode<E> node = getNodeAtIndex(index);
		return node.data;
	}

	/**
	 * Add an element to the list at the specified index.
	 *
	 * @param index the index
	 * @param element The element to add
	 * @throws IndexOutOfBoundsException the index out of bounds exception
	 */
	public void add(int index, E element ) throws IndexOutOfBoundsException 
	{
		LLNode<E> node;

		if (index < 0 || index > size) 
			throw new IndexOutOfBoundsException();

		node = getNodeAtIndex(index);
		node = new LLNode<E>(element,node.prev,node);
		size++;
	}


	/**
	 *  Return the size of the list.
	 *
	 * @return size of the linked list
	 */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed from the list
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) throws IndexOutOfBoundsException 
	{
		if (index < 0 || index >=size) 
			throw new IndexOutOfBoundsException();

		LLNode<E> node = getNodeAtIndex(index);
		node.prev.next = node.next;
		node.next.prev = node.prev;	
		node.next = null;
		node.prev = null;
		size--;
		return node.data;
	}

	/**
	 * Set an index position in the list to a new element.
	 *
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) throws IndexOutOfBoundsException 
	{
		if (index < 0 || index >=size) 
			throw new IndexOutOfBoundsException();

		LLNode<E> node = getNodeAtIndex(index);
		E old = node.data;
		node.data = element;
		return old;
	}
	
	
	/**
	 * Gets the head.
	 *
	 * @return the head
	 */
	public LLNode<E> getHead() {
		return head;
	}

	/**
	 * Gets the tail.
	 *
	 * @return the tail
	 */
	public LLNode<E> getTail() {
		return tail;
	}

}

