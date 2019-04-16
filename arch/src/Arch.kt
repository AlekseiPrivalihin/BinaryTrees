open class BinarySearchTree<T, K : Comparable<K>>: Iterable<T> {
    public open inner class Node(_value: T, _key: K) {
        var left: Node? = null
        var right: Node? = null
        var parent: Node? = null
        var value = _value
        val key = _key
    }

    public inner class Iterator: kotlin.collections.Iterator<T> {
        // TODO
    }

    protected open var root: Node? = null

    final override operator fun iterator() = Iterator()

    public open fun find(key: K): T? {
        var curNode: Node? = this.root

        while (curNode != null) {
            when (curNode.key) {
                key -> return curNode.value
                (curNode.key > key) -> curNode = curNode.left
                else -> curNode = curNode.right
            }
        }

        return null
    }

    protected open fun createNode(value: T, key: K): Node {
        return Node(value, key)
    }

    protected fun basicInsert(value: T, key: K): Node? {

        if (this.root == null) {
            this.root = createNode(value, key)
            return this.root
        }

        var curNode = this.root!!

        while (true) {
            if (curNode.key == key) {
                curNode.value = value
                return null
            }

            if (curNode.key > key) {
                if (curNode.left == null) {
                    curNode.left = createNode(value, key)
                    curNode.left!!.parent = curNode
                    return curNode.left
                }

                curNode = curNode.left!!
            } else {
                if (curNode.right == null) {
                    curNode.right = createNode(value, key)
                    curNode.right!!.parent = curNode
                    return curNode.right
                }

                curNode = curNode.right!!
            }
        }
    }

    public open fun insert(value: T, key: K) {
        basicInsert(value, key)
    }
}

abstract class BalancedSearchTree<T, K : Comparable<K>> : BinarySearchTree<T, K>() {

    protected fun Node.rotateLeft() {
        //TODO
    }
    protected fun Node.rotateRight() {
        //TODO
    }

    protected abstract fun balance(node: Node)

    override fun insert(value: T, key: K) {
        val insertedNode = basicInsert(value, key)

        if (insertedNode != null)
            balance(insertedNode)
    }
}


class AVLTree<T, K : Comparable<K>> : BalancedSearchTree<T, K>() {
    public inner class AVLNode(_value: T, _key: K) : Node(_value, _key) {
        var flag = 0
    }

    protected override fun createNode(value: T, key: K): Node {
        return AVLNode(value, key)
    }

    override fun balance(node: Node) {
        //TODO
    }
}

class RBTree<T, K : Comparable<K>> : BalancedSearchTree<T, K>() {
    public inner class RBNode(_value: T, _key: K) : Node(_value, _key) {
        var color = 0
    }

    protected override fun createNode(value: T, key: K): Node {
        return RBNode(value, key)
    }

    override fun balance(node: Node) {
        //TODO
    }
}
