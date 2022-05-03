/*
 * @author Spelljinxer
 */

import CITS2200.Stack;
import CITS2200.Overflow;
import CITS2200.Underflow;
public class StackBlock implements Stack
{
    public Object [] items;
    public int size;
    private int first;

    /*
        Constructor.
        Initialize a stack of Object named items, taking a "size" parameter
        Initialize first to index 0
     */
    public StackBlock(int size)
    {
        this.size = size;
        first = 0;
        items = new Object [size];
    }

    /*
        Return true if stack is empty
        else false
     */
    public boolean isEmpty()
    {

        return first == 0;
    }
    /*
        Return true if stack is full
        else false
     */
    public boolean isFull()
    {

        return first == size;
    }
    /*
        Pushes (adds) 'item' to the first spot in the stack.
     */
    public void push(Object item) throws Overflow
    {
        if (!isFull())
        {
            items[first] = item;
            first++;
        }
        else throw new Overflow("Overflow");
    }
    /*
        Check and return what the first item in the stack is
     */
    public Object examine() throws Underflow
    {
        if (!isEmpty())
        {
            return items[first - 1];
        }
        else throw new Underflow("Underflow");
    }
    /*
        Removes the first item of the stack and return it.
     */
    public Object pop() throws Underflow
    {
        if (!isEmpty())
        {
            first--;
            return items[first];
        } else throw new Underflow("Underflow");
    }
}
