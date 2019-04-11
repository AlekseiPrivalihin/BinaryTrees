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

    protected fun basicInsert(key: K, value: V): BinaryNode<K, V>? = TODO()

    open fun insert(key: K, value: V): Boolean {
        if (basicInsert(key, value) == null)
            return false
        return true
    }

    final override operator fun iterator(): Iterator<V> {
        return Iterator(root)
    }
}