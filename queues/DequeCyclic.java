/*
        @author Reiden Rufin
        @studentNumber 22986337
 */

import CITS2200.Deque;
import CITS2200.Overflow;
import CITS2200.Underflow;


public class DequeCyclic<E> implements Deque<E>
{
    private Object [] deque;
    private int left;
    private int right;
    private int size;

    /*
            Constructor. Initialize the deque Array of Object with @param Size
     */
    public DequeCyclic(int size)
    {
        deque = new Object[size];
        left = -1;
        right = 0;
        this.size = size;

    }

    /*
            Check whether the Deque Array is empty
            Return true iff empty;
     */

    public boolean isEmpty()
    {
        return left == -1;
    }

    /*
           Check whether Deque array is full
           Return true iff full
     */
    public boolean isFull()
    {
        return ((left == 0 && right == size - 1) || (left == right + 1));
    }

    /*
            Push @param item to the left side of the array
            iff it is not full
     */
    public void pushLeft(E item) throws Overflow
    {
        if(!isFull())
        {
            if (left == -1)
            {
                left = 0;
                right = 0;
            }
            else if (left == 0)
            {
                left = size - 1;
            }
            else
            {
                left -= 1;
            }
            deque[left] = item;
        }
        else throw new Overflow("Overflow");
    }

    /*
           Push @param item to the right side of the array
           iff it is not full
    */
    public void pushRight(E item) throws Overflow
    {
        {
            if (isFull())
            {
                throw new Overflow("Overflow");
            }
            if (left == -1)
            {
                left = 0;
                right = 0;
            }

            else if (right == size-1)
            {
                right = 0;
            }

            else
            {
                right = right + 1;
            }
            deque[right] = item;
        }
    }


    /*
           Examine the left item in the array
           This throws an unchecked cast
           @SupresssWarnings used to ignore the compilation error
           @return the left side item
     */
    public E peekLeft() throws Underflow
    {
        if (!isEmpty())
        {
            @SuppressWarnings("unchecked")
            final E e = (E) deque[left];
            return e;
        }
        else throw new Underflow("Underflow");
    }

    /*
           Examine the right item in the array
           This throws an unchecked cast
           @SupresssWarnings used to ignore the compilation error
           @return the right side item
     */
    public E peekRight() throws Underflow
    {
        if (!isEmpty())
        {
            @SuppressWarnings("unchecked")
            final E e = (E) deque[right];
            return e;

        }
        else throw new Underflow("Underflow");
    }

    /*
            Remove the the first item on the left side of the array
            @return the left side item on the array
     */
    public E popLeft() throws Underflow
    {
        if(!isEmpty())
        {
            E temp = peekLeft();
            if (left == right)
            {
                left = -1;
                right = -1;
            }
            else
            {
                if (left == size - 1)
                {
                    left = 0;
                }
                else
                {

                    left = left + 1;
                }
            }
            return temp;
        }
        else throw new Underflow("Underflow");
    }

    /*
            Remove the the first item on the right side of the array
            @return the right side item on the array
     */
    public E popRight() throws Underflow
    {
        if (!isEmpty())
        {
            E temp =peekRight();
            if (left == right)
            {
                left = -1;
                right = -1;
            }
            else if (right == 0)
            {
                right = size - 1;
            }
            else
            {
                right -= 1;
            }
            return temp;
        }
        else throw new Underflow("Underflow");
    }
}