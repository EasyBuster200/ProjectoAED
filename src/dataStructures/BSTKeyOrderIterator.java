package dataStructures;

public class BSTKeyOrderIterator<K,V> implements Iterator<Entry<K,V>> {

    private Stack<BSTNode<K,V>> stack;
    private BSTNode<K,V> root;

    public BSTKeyOrderIterator(BSTNode<K,V> root) {
        this.root = root;
        rewind();
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Entry<K,V> next() throws NoSuchElementException {

        if (!this.hasNext())
           throw new NoSuchElementException();

        BSTNode<K,V> nextToReturn = stack.pop();

        if (nextToReturn.getRight() != null)
            pushPathToMin(nextToReturn.getRight());

        return nextToReturn.getEntry();
    }

    @Override
    public void rewind() {
        this.stack = new StackInList<BSTNode<K,V>>();
        pushPathToMin(root);
    }

    private void pushPathToMin(BSTNode<K,V> node) {
        stack.push(node);

        while (node.getLeft() != null) {
            stack.push(node.getLeft());
            node = node.getLeft();
        }
    }

}
