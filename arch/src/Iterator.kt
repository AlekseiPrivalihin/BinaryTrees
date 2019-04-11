import kotlin.collections.Iterator

class Iterator<K : Comparable<K>, V>(
        begin: BinaryNode<K, V>?
) : Iterator<V> {

    private var current: BinaryNode<K, V>? = TODO()

    override operator fun next(): V = TODO()

    override operator fun hasNext(): Boolean = TODO()
}