class RBTree<K : Comparable<K>, V> : BalancedBST<K, V>() {
    override fun createNode(key: K, value: V, parent: BinaryNode<K, V>?): BinaryNode<K, V> = TODO()

    override fun balance(inserted: BinaryNode<K, V>) = TODO()
}