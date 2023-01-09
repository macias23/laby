import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class NodeList {
    private Node2 root;
    private int size;

    public NodeList() {
        root = null;
        size = 0;
    }

    public static void main(String[] args) {
        NodeList nodeList = new NodeList();
        nodeList.add(5);
        nodeList.add(9);
        nodeList.add(7);
        nodeList.add(7);
        nodeList.removeDuplicator();
        NodeList withoutDuplicates = nodeList.removeDuplicator();
        System.out.println(withoutDuplicates);
    }

    public void add(int value) {
        Node2 node2 = root;
        Node2 newNode2 = new Node2(value);
        // we have the empty list, so add the new node as the root
        if (root == null) {
            root = newNode2;
        } else {
            // traverse through the list to find the last node
            while (node2.getNext() != null) {
                node2 = node2.getNext();
            }
            // add the new node after the last one
            node2.setNext(new Node2(value));
            node2.getNext().prev = node2;
        }
        // increase the size of the list
        ++size;
    }

    public int remove(int value) {
        Node2 node2 = root;
        boolean changed = false;
        int changes = 0;
        //traverse through the list to find nodes with given value
        while (node2 != null) {
            if (node2.getValue() == value) {
                changed = true;
                //case when there is only one element in the list
                if (size == 1) {
                    root = null;
                    size--;
                    changes++;
                    break;
                }
                //deleting the element
                node2.setValue(node2.getNext().getValue());
                //one element before the last one is being deleted case
                if (node2.getNext().getNext() == null && node2.getNext().getValue() == node2.getValue()) {
                    node2.setNext(null);
                    size--;
                    break;
                }
                //setting next element for the one using for replacement
                if (node2.getNext().getNext() != null)
                    node2.setNext(node2.getNext().getNext());
                size--;
                changes++;
            }
            //setting next to be checked
            if (node2.getNext() != null) {
                //for last element
                if (node2.getNext().getNext() == null && node2.getNext().getValue() == value) {
                    node2.setNext(null);
                    size--;
                    changes++;
                    break;
                }
                node2 = node2.getNext();
            } else if (changed)
                return changes;
            else return -1;
        }
        return -1;
    }

    public int remove(int[] values) {
        int changes = 0;
        for (int value : values) {
            changes += remove(value);
        }
        return changes;
    }

    /**
     * Removes the head from the list.
     *
     * @return true if the operations was done correctly, false otherwise
     */
    public boolean pop() {
        //for empty list
        if (size == 0) return false;
        //for one element list
        if (root.getNext() == null) {
            root = null;
            size--;
        }
        //normal case
        else {
            root = root.getNext();
            size--;
        }
        return false;
    }

    /**
     * Returns the size of the list
     *
     * @return the integer saying, how many elements the list contains
     */
    public int length() {
        return size;
    }

    /**
     * Finds a node in the list with the given value
     *
     * @param value what value should be found
     * @return first node with a given value or null if none is present in the list
     */
    public Node2 getNode(int value) {
        Node2 result = root;
        while (result != null) {

            if (result.getValue() == value) {
                result.counter++;
                sortNodes(result);
                return result;
            }
            result = result.getNext();
        }
        return null;
    }

    public Node2 getRoot() {
        if (root != null) {
            root.counter++;
        }
        return root;
    }

    public void sortNodes(Node2 node2) {
        if (node2.prev != null) {
            if (node2.getValue() > node2.prev.getValue()) {
                int value = node2.prev.value;
                int counter = node2.prev.counter;

                node2.prev.counter = node2.counter;
                node2.prev.value = node2.value;

                node2.value = value;
                node2.counter = counter;

                sortNodes(node2.prev);
            }
        }
    }

    public void sortList() {
        NodeListIterator iterator = new NodeListIterator();
        while (iterator.hasNext()) {
            sortNodes(iterator.next());
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node2 node2 = root;
        while (node2 != null) {
            s.append(node2.getValue());
            s.append(" ");
            node2 = node2.getNext();
        }
        return s.toString();
    }

    public void joinList(NodeList listToBeJoined) {
        NodeListIterator iterator1 = new NodeListIterator();
        Node2 node = this.root;
        while (iterator1.hasNext()) {
            node = iterator1.next();
        }
        node.setNext(listToBeJoined.getRoot());
        this.sortList();
    }

    private class NodeListIterator implements Iterator<Node2> {
        Node2 current = root;

        @Override
        public boolean hasNext() {
            if (current.getNext() != null) return true;
            return false;
        }

        @Override
        public Node2 next() {
            if (current.getNext() != null) return current = current.getNext();
            return null;
        }
    }

    public NodeList removeDuplicator() {
        NodeListIterator iterator = new NodeListIterator();
        LinkedHashSet<Integer> noDuplicates = new LinkedHashSet();
        Node2 current = iterator.current;
        while (iterator.hasNext()) {
            noDuplicates.add(current.getValue());
            current = iterator.next();
        }
        System.out.println(noDuplicates);
        NodeList withoutDuplicates = new NodeList();
        for (int x: noDuplicates) {
            withoutDuplicates.add(x);
        }
        return withoutDuplicates;
    }
}
