abstract class BalancedBST<K : Comparable<K>, V> : BinarySearchTree<K, V>() {
    protected abstract fun balance(inserted: BinaryNode<K, V>)

    protected fun <T : BinaryNode<K, V>> leftChildOf(node: T?) = node?.left as T?
    protected fun <T : BinaryNode<K, V>> rightChildOf(node: T?) = node?.right as T?
    protected fun <T : BinaryNode<K, V>> parentOf(node: T?) = node?.parent as T?

    final override fun insert(key: K, value: V): Boolean {
        val inserted = basicInsert(key, value) ?: return false

        balance(inserted)
        return true
    }

    fun rotateRight(node: BinaryNode<K, V>) = TODO()

    fun rotateLeft(node: BinaryNode<K, V>) = TODO()
}