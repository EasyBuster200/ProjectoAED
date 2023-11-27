package dataStructures;

public class BSTWithComparator<K extends Comparable<K>, V>  extends BinarySearchTree<K,V> {
    
    protected Comparator<K> comparator;

    public BSTWithComparator(Comparator<K> comparator) {
        super();
        this.comparator = comparator;
    }

    // Returns the node whose key is the specified key;
    // or null if no such node exists.
    // Moreover, stores the last step of the path in lastStep.
    protected BSTNode<K,V> findNode( K key, PathStep<K,V> lastStep ){
        BSTNode<K,V> node = root;
        while ( node != null )
        {
            int compResult = comparator.compare(key, node.getKey());
            if ( compResult == 0 )
                return node;
            else if ( compResult < 0 )
            {
                lastStep.set(node, true);
                node = node.getLeft();
            }
            else
            {
                lastStep.set(node, false);
                node = node.getRight();
            }
        }
        return null;
    }
}
