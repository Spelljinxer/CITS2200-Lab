/*
 * @author Spelljinxer
 */
import CITS2200.BinaryTree;
import CITS2200.Iterator;
import CITS2200.OutOfBounds;
import java.util.ArrayDeque;

/**
 *
 * @SuppressWarnings to cover the unchecked cast found within the class
 */
@SuppressWarnings("unchecked")
public class BinTree<E> extends BinaryTree<E> {

    public BinTree() {
        super();
    }

    public BinTree(E item, BinaryTree<E> ltree, BinaryTree<E> rtree) {
        super(item, ltree, rtree);
    }


    /**
     * Checks if the binary tree is equal to another binary tree
     * @param o Object
     * @return true if both are equal, else false
     */
    public boolean equals(Object o) {
        // TODO: Implement equals
        if (!(o instanceof BinaryTree))
            return false;

        BinTree<E> otherTree = (BinTree<E>) o;

        if (isEmpty() && otherTree.isEmpty())
            return true;
        if (isEmpty() || otherTree.isEmpty())
            return false;

        return getItem().equals(otherTree.getItem())
                && getLeft().equals(otherTree.getLeft())
                && getRight().equals(otherTree.getRight());

    }

    public class BinTreeIterator<E> implements Iterator<E> {

        /**
         * Using ArrayDeque ADT
         */
        private ArrayDeque<E> trees;

        /**
         * Constructor for iterator implementing the ArrayDeque
         * @param root
         */
        public BinTreeIterator(BinTree<E> root)
        {
            trees = new ArrayDeque<E>();
            inOrder(root);
        }

        private void inOrder(BinaryTree<E> b)
        {
            if(!b.isEmpty())
            {
                inOrder(b.getLeft());
                trees.add(b.getItem());
                inOrder(b.getRight());
            }
        }

        /**
         * Checks if there is any other item that hasn't been seen in the tree
         * @return true iff there are other items that have yet to be iterated, else false
         */

        public boolean hasNext() {
            return !trees.isEmpty();
        }

        /**
         *
         * @return the next item in tree
         * @throws OutOfBounds if there is no next element
         */
        public E next() throws OutOfBounds {
            if(trees.isEmpty())
            {
                throw new OutOfBounds("OutOfBounds");
            }
            return trees.remove();

        }
    }

    /**
     *
     * @return an iterator of E type
     */
    public Iterator<E> iterator()
    {
        // TODO: Implement iterator
        // NOTE: You may need to create an inner class to implement the iterator
        return new BinTreeIterator<E>(this);
    }
}
