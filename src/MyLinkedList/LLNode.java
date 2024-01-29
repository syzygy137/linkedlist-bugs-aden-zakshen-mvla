package MyLinkedList;

// TODO: Auto-generated Javadoc
/**
 * The Class LLNode.
 *
 * @param <E> the element type
 */
public class LLNode<E> {
	
	/** The prev. */
	LLNode<E> prev;
	
	/** The next. */
	LLNode<E> next;
	
	/** The data. */
	E data;



	/**
	 * Instantiates a new LL node.
	 *
	 * @param e the e
	 */
	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	
	// TODO: Add a constructor that also inserts the node in the correct location
	/**
	 * Instantiates a new fully connected node in the list
	 *
	 * @param e the e
	 * @param prev the prev
	 * @param next the next
	 */
	//       in the list
	public LLNode(E e, LLNode<E> prev, LLNode<E> next) 
	{
		this.data = e;
		this.prev = prev;
		prev.next = this;
		this.next = next;
		next.prev = this;
	}

}
