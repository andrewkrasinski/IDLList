package CS284;
import java.util.ArrayList;

/** I pledge my honor that I have abided by the Stevens Honor System
 *  Andrew Krasinski
 *  Implements a double-linked list with fast accessing provided by an internal index
 */

public class IDLList<E> {

//  data fields of IDLList Class
//  private Node<E> head;
//  private Node<E> tail;
//  private int size;
//  private ArrayList<Node<E>> indices;
	
    private Node<E> head;
    private Node<E> tail;
    private int size;
    private ArrayList<Node<E>> indices;
    
    public static void main(String[] args) {
//  Main method included so code compiles properly
    }
    
//  inner class Node<E>
        private class Node<E>{
//          data fields
//          private E data;
//          private Node<E> next;
//          private Node<E> prev;
            private E data;
            private Node<E> next;
            private Node<E> prev;

//          constructor1
            /**
             * This constructor creates a node holding elem
             * The next and prev pointing to null
             *         prev|elem|next
             *         null|elem|null
             * @param elem : data to be stored in the Node
             */
            public Node (E elem){
//              assign values to the variables in the data fields.
                this.data = elem;
            }

//          constructor2
            /**
             * This constructor creates a node holding elem
             * Next pointing to the next param and prev pointing to the prev param
             * prev node <- prev|elem|next -> next node
             * @param elem
             * @param prev
             * @param next
             */
            Node (E elem, Node<E> prev, Node<E> next){
//              assign values to the variables in the data fields.
                this.data = elem;
                this.prev = prev;
                this.next = next;
            }
        }

//      Constructor for IDLList
        /**
         * This constructor creates an empty IDLList
         * Head and tail are set to null and size is set to zero.
         * assign an empty ArrayList to indices and what will be stored in ArrayList are nodes' references
         */
    public IDLList (){
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.indices = new ArrayList<Node<E>>();
    }
    
//  methods
    
    /**
     * This method add elem at position index and use the index for fast access
     * @param index the position in which the elem to be placed in the IDLL
     * @param elem the elem to be placed
     * @return always return true
     */
    public boolean add (int index, E elem){
        Node node1 = new Node(elem);

        if(index < 0 || index > this.size){
            throw new IllegalArgumentException("index is out of bounds");
        }

        if(index == 0){
            if(size != 0){
                head.prev = node1;
                node1.next = head;
                head = node1;
                indices.add(0, head);
            }
            else{
                head = node1;
                tail = node1;
                indices.add(0, head);
            }
        }

        else if(index == size){
            tail.next = node1;
            node1.prev = tail;
            tail = node1;
            indices.add(size, tail);

        }
        
        else {
        	indices.add(index, node1);
        	indices.get(index-1).next = node1;
        	node1.prev = indices.get(index-1);
        	indices.get(index+1).prev = node1;
        	node1.next = indices.get(index+1);
        }
        size++;
        return true;
    }
    
    /**
     * This method add elem at the head. (it becomes the first element of the list)
     * @param elem the elem to be placed to be placed at the head.
     * @return always return ture
     */
    public boolean add (E elem){
    	this.add(0, elem);
        return true;
    }
    
    /**
     * This method adds elem as the new last element of the list (i.e. at the tail).
     * @param elem the elem to be placed at the tail
     * @return always return true
     */
    public boolean append (E elem){
    	this.add(size, elem);
        return true;
    }
    
    /**
     * This method returns the object at position index from the head. It uses the index for fast access
     * The return type is E, which is the element type.
     * @param index the index of the node
     * @return the data value of that node
     */
    public E get (int index){
    	if(index < 0 || index > this.size){
            throw new IllegalArgumentException("index is out of bounds");
        }
        return indices.get(index).data;
    }
    
    /**
     * This method returns the object at the head.
     * The return type is E, which is the element type.
     * @return the data value at the head
     */
    public E getHead (){
    	if(size == 0) {
    		throw new IllegalStateException("list is empty");
    	}
        return this.head.data;
    }
    
    /**
     * This method returns the object at the tail.
     * The return type is E, which is the element type.
     * @return the data value at the tail
     */
    public E getLast (){
    	if(size == 0) {
    		throw new IllegalStateException("list is empty");
    	}
        return this.tail.data;
    }
    
    /**
     * this method returns the list size.
     * @return the size of the list
     */
    public int size(){
        return size;
    }
    
    /**
     * removes and returns the element at the head.
     * Should throw an IllegalStateException if there is no such element.
     * @return the data value of the head being removed
     */
    public E remove(){
    	if(size == 0) {
    		throw new IllegalStateException("list is empty");
    	}
    	E headVal = this.head.data;
    	if(size == 1) {
    		this.head = null;
    		this.tail = null;
    		this.size --;
    		indices.remove(0);
    		headVal = this.head.data;
    		return headVal;
    	}
    		Node<E> node1 = head;
    		this.head = node1.next;
    		this.head.prev = null;
    		indices.remove(0);
    		this.size--;
    		return node1.data;
    	}    
    
    /**
     * This method removes and returns the element at the tail.
     * Should throw an IllegalStateException if there is no such element.
     * @return the data value of the tail being removed
     */
    public E removeLast (){   	
    	if(size == 0) {
    		throw new IllegalStateException("list is empty");
    	}
    	E tailVal = this.tail.data;
    	if(size == 1) {
    		return remove();
    	}
    	else {
    		Node<E> node1 = tail;
    		tail = node1.prev;
    		tail.next = null;
    		indices.remove(size-1);
    		this.size --;
			return tailVal;
    	}  
    }
    
    /**
     * This method removes and returns the element at the index.
     * @param index the index of the element to be removed
     * @return the data value of the node being removed
     */
    public E removeAt (int index){
    	if(index < 0 || index > size-1) {
    		throw new IllegalStateException("index out of bounds");
    	}
    	Node<E> node1 = this.indices.get(index);
    	if(index == 0) {
    		return remove();
    	}
    	if(index == size-1) {
    		return removeLast();
    	}
    	else {
    		Node<E> nPrev = node1.prev;
    		Node<E> nNext = node1.next;
    		nPrev.next = node1.next;
    		nNext.prev = node1.prev;
    		this.size --;
    		indices.remove(index);
    		return node1.data;
    	}
    }
    
    /**
     * This method removes the first occurrence of elem in the list
     * @param elem
     * @return returns true if elem was in the list, false if not.
     */
    public boolean remove (E elem){
        if (size == 0) {
        	throw new IllegalStateException("list is empty");
        }
        for(int i = 0; i<size; i++) {
        	if (indices.get(i).data == elem) {
        		removeAt(i);
        		return true;
        	}
        }
        return false;
    }

    /**
     * This method resents a string representation of the list.
     * @return a string
     */
    public String toString(){
        String str = "";
        Node node = head;
        while(node != null){
            str += node.data.toString();
            node = node.next;
        }
        return str;
    }
}