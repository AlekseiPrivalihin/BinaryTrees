open class BinaryNode<K: Comparable<K>, V>(
        val key: K,
        var value: V,
        var parent: BinaryNode<K, V>?
) {
    var left: BinaryNode<K, V>? = null
    var right: BinaryNode<K, V>? = null
}
