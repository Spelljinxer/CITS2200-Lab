/**
 * @author Reiden Rufin
 * @studentNumber 22986337
 */

import CITS2200.IllegalValue;
import CITS2200.Iterator;
import CITS2200.OutOfBounds;
import CITS2200.PriorityQueue;
import CITS2200.Underflow;

@SuppressWarnings("unchecked")
public class PriorityQueueLinked implements PriorityQueue<Object> {
    private Link front;

    public PriorityQueueLinked() {
        front = null;
    }

    private class Link {
        public Object element;
        public int priority;
        public Link next;

        public Link(Object element, int priority, Link next) {
            this.element = element;
            this.priority = priority;
            this.next = next;
        }
    }

    public boolean isEmpty() {
        return front == null;
    }

    public Object examine() throws Underflow {
        if (isEmpty()) {
            throw new Underflow("Empty priority queue");
        }
        return front.element;
    }

    public Object dequeue() throws Underflow {
        if (isEmpty()) {
            throw new Underflow("Empty priority queue");
        }

        Object temp = front.element;
        front = front.next;
        return temp;
    }

    public void enqueue(Object element, int priority) {
        if (isEmpty() || priority > front.priority) {
            front = new Link(element, priority, front);
            return;
        }

        Link curr = front;
        while (curr.next != null && curr.next.priority >= priority) {
            curr = curr.next;
        }
        curr.next = new Link(element, priority, curr.next);
    }

    /**
     * Creating an internal class to implement Iterator
     */
    public class PriorityQueueIterator <E> implements Iterator<E>
    {
        private Link currentWindow;

        public PriorityQueueIterator()
        {
            currentWindow = PriorityQueueLinked.this.front;
        }

        /**
         * Checks if a next item exists
         * @return true if the next item is not null
         */
        public boolean hasNext()
        {
            return currentWindow != null;
        }

        /**
         * If hasNext() returns true, this will return the next element as it moves to the next item
         * @return next item in link
         * @throws OutOfBounds once it reaches the end of the queue
         */
        public E next() throws OutOfBounds
        {
            if(hasNext())
            {
                E temp = (E) currentWindow.element;
                currentWindow = currentWindow.next;
                return temp;
            }
            else
            {
                throw new OutOfBounds("out of bounds");
            }
        }
    }

    /**
     * Calls internal class PriorityQueueIterator
     * @return a new iterator
     */
    public Iterator iterator() {
        // TODO: Implement iterator()
        return new PriorityQueueIterator();
    }
}