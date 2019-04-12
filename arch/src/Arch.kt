import kotlin.collections.Iterator

class BinarySearchTreeIterator<T>: Iterator<T>(
    node: BinarySearchTree<T, *>.Node?
) {
    override operator fun hasNext(): Boolean = TODO()

    override operator fun next(): T = TODO()
}

open class BinarySearchTree<T, K : Comparable<K>>: Iterable<T> {
    open inner class Node(_value: T, _key: K, _parent: Node?) {
        var left: Node? = null
        var right: Node? = null
        var parent: Node? = _parent
        var value = _value
        val key = _key
    }

    protected var root: Node? = null

    fun find(key: K): T? {
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

    protected open fun createNode(value: T, key: K, parent: Node?): Node {
        return Node(value, key, parent)
    }

    protected fun basicInsert(value: T, key: K): Node? {

        if (this.root == null) {
            this.root = createNode(value, key, null)
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
                    curNode.left = createNode(value, key, curNode)
                    return curNode.left
                }

                curNode = curNode.left!!
            } else {
                if (curNode.right == null) {
                    curNode.right = createNode(value, key, curNode)
                    return curNode.right
                }

                curNode = curNode.right!!
            }
        }
    }

    public open fun insert(value: T, key: K) {
        basicInsert(value, key)
    }

    override operator fun iterator(): Iterator<V> {
        return BinarySearchTreeIterator(root)
    }
}

abstract class BalancedSearchTree<T, K : Comparable<K>> : BinarySearchTree<T, K>() {

    inner class BalancedNode(_value: T, _key: K, _parent: Node?): Node(_value, _key, _parent) {
        var flag = 0

        fun rotateLeft() = TODO()

        fun rotateRight() = TODO()
    }

    protected override fun createNode(value: T, key: K, parent: Node?): Node {
        return BalancedNode(value, key, parent)
    }

    protected abstract fun balance(node: Node)

    override fun insert(value: T, key: K) {
        val insertedNode = basiInsert(value, key)

        if (insertedNode != null)
            balance(insertedNode)
    }
}


class AVLTree<T, K : Comparable<K>> : BalancedSearchTree<T, K>() {

    override fun balance(node: Node) {
        //TODO
    }
}

class RBTree<T, K : Comparable<K>> : BalancedSearchTree<T, K>() {

    override fun balance(node: Node) {
        //TODO
    }
}
