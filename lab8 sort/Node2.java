import java.util.Objects;

class Node2 {
    /**
     * Data for the node.
     */
    public int value;
    public int counter = 0;
    /**
     * Next node to this node.
     */
    private Node2 next;
    public Node2 prev;

    public Node2(int value) {
        this.value = value;
        this.next = null;
    }

    public int getValue() {
        return value;
    }

    public Node2 getNext() {
        return next;
    }

    public void setNext(Node2 next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node2 node2 = (Node2) o;
        return getValue() == node2.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public void setValue(int value) {
        this.value = value;
    }
}