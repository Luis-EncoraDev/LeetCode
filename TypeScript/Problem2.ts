class CustomNode {

    key: string | null;
    value: number | null;
    prev: CustomNode | null;
    next: CustomNode | null ;

    constructor(key: string | null = null, value: number | null = null) {
        this.key = key;
        this.value = value;
        this.prev = null;
        this.next = null;
    }
}


class LRU {

    private capacity: number;
    private cache: { [key: string]: CustomNode };
    private head: CustomNode;
    private tail: CustomNode;

    constructor(capacity: number) {
        this.capacity = capacity;
        this.cache = {};
        this.head = new CustomNode();
        this.tail = new CustomNode();
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    addNode(node: CustomNode) {
        node.prev = this.head;
        node.next = this.head.next;
        this.head.next!.prev = node;
        this.head.next = node;
    }

    removeNode(node: CustomNode) {
        const prevNode = node.prev!;
        const nextNode = node.next!;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    moveToTop(node: CustomNode) {
        this.removeNode(node);
        this.addNode(node);
    }

    getAndRemoveTail(): CustomNode {
        const tailNode = this.tail.prev;
        this.removeNode(tailNode!);
        return tailNode!;
    }

    get(key: number): number {
        const node = this.cache[String(key)];
        if (node == null) return -1;
        this.moveToTop(node);
        return node.value!;
    }

    put(key: number, value: number) {
        const node = this.cache[String(key)];

        if (node == null) {
            const newNode = new CustomNode(String(key), value);
            this.cache[String(key)] = newNode;
            this.addNode(newNode);

            if (Object.keys(this.cache).length > this.capacity) {
                const tailNode = this.getAndRemoveTail();
                delete this.cache[tailNode.key!];
            }
        } else {
            node.value = value;
            this.moveToTop(node);
        }
    }
}