import java.util.HashMap;
import java.util.Map;


class Node {

    Character key;
    Integer value;
    Node prev;
    Node next;

    Node() {
        this(null, null);
    }

    Node(Character key, Integer value) {
        this.key = key;
        this.value = value;
        this.prev = null;
        this.next = null;
    }
}


class LRUCache {

    private int capacity;
    private Map <Character, Node> cache;
    private Node head;
    private Node tail;
    
    LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new Node();
        this.tail = new Node();
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    void addNode(Node node) {
    node.prev = this.head;
    node.next = this.head.next;
    this.head.next.prev = node;
    this.head.next = node;
    }

    void removeNode(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    void moveToTop(Node node) {
        this.removeNode(node);
        this.addNode(node);
    }

    Node getAndRemoveTail() {
        Node node = this.tail.prev;
        this.removeNode(node);
        return node;
    }

    int get(int key) {
        Node node = this.cache.get((char) key);
        if (node == null) return -1;
        this.moveToTop(node);
        return node.value;
        
    }
    
    void put(int key, int value) {
        Node node = this.cache.get((char) key);

        if (node == null) {
            Node newNode = new Node((char) key, value);
            this.cache.put((char) key, newNode);
            this.addNode(newNode);
            if (this.cache.size() > this.capacity) {
                Node tail = this.getAndRemoveTail();
                this.cache.remove(tail.key);
            }
        } else {
            node.value = value;
            this.moveToTop(node);
        }

    }
}
