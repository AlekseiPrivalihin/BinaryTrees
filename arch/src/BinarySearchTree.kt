import kotlin.collections.Iterator
import kotlin.math.max

open class BinarySearchTree<K : Comparable<K>, V> : Iterable<V> {

    protected var root: BinaryNode<K, V>? = null

    val height: Int
        get() = TODO()

    fun clear() = TODO()

    fun find(key: K): V? = TODO()

    protected open fun createNode(key: K, value: V, parent: BinaryNode<K, V>?): BinaryNode<K, V> {
        return BinaryNode(key, value, parent)
    }

    protected fun basicInsert(key: K, value: V): BinaryNode<K, V>? {
        if (root == null) {
            root = createNode(key, value)
            return root
        }

        var node = root!!

        while (true) {
            val comparisonResult = key.compareTo(node.key)

            if (comparisonResult == 0) {
                node.value = value
                return null
            }

            if (comparisonResult < 0) {
                if (node.left == null) {
                    node.left = createNode(key, value, node)
                    return node.left
                }

                node = node.left!!
            } else {
                if (node.right == null) {
                    node.right = createNode(value, key, node)
                    return node.right
                }

                node = node.right!!
            }
        }
    }

    open fun insert(key: K, value: V): Boolean {
        if (basicInsert(key, value) == null)
            return false
        return true
    }

    final override operator fun iterator(): Iterator<V> {
        return Iterator(root)
    }
}