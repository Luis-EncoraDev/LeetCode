class Node:

    def __init__(self, key=None, value=None):
        self.key = key
        self.value = value
        self.prev = None
        self.next = None

class LRUCache:
    
    def __init__(self, capacity: int):
        self.capacity = capacity
        self.cache = {}
        self.head = Node()
        self.tail = Node()
        self.head.next = self.tail
        self.tail.prev = self.head

    def addNode(self, node):
        """Agrega un nodo justo después de head"""
        node.prev = self.head
        node.next = self.head.next
        self.head.next.prev = node
        self.head.next = node

    def removeNode(self, node):
        """Desconecta un nodo de la lista"""
        prevNode = node.prev
        nextNode = node.next
        prevNode.next = nextNode
        nextNode.prev = prevNode

    def moveToTop(self, node):
        """Mueve un nodo a la posición más reciente (inmediatamente después de head)"""
        self.removeNode(node)
        self.addNode(node)

    def getAndRemoveTail(self):
        """Remueve y retorna el nodo menos recientemente usado (antes del tail)"""
        node = self.tail.prev
        self.removeNode(node)
        return node

    def get(self, key: int) -> int:
        node = self.cache.get(key)
        if not node:
            return -1
        self.moveToTop(node)
        return node.value

    def put(self, key: int, value: int) -> None:
        node = self.cache.get(key)
        if not node:
            newNode = Node(key, value)
            self.cache[key] = newNode
            self.addNode(newNode)
            if len(self.cache) > self.capacity:
                tail = self.getAndRemoveTail()
                del self.cache[tail.key]
        else:
            node.value = value
            self.moveToTop(node)
