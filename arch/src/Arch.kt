open class BinarySearchTree<T, K: Comparable<K>> {
    protected open inner class BinaryTreeNode(...)

    protected open var root: BinaryTreeNode? = null

    val height: Boolean
        get() = ...

    open fun find(key: K): T? {
        //...
        return null
    }

    open fun insert(value: T, key: K): Boolean {
        return basicInsert(value, key) != null
    }

    protected open fun createNode(...): BinaryTreeNode {
        return BinaryTreeNode(...)
    }

    protected fun basicInsert(value: T, key: K): BinaryTreeNode? {
        // returns 'null' if key was found

        // root = createNode(...)
        // node.left = createNode(...)
        // node.right = createNode(...)
    }
}

abstract class BalancedSearchTree<T, K: Comparable<K>>: BinarySearchTree<T, K>() {
    protected abstract fun balance(node: BinaryTreeNode)

    override fun insert(value: T, key: K): Boolean {
        val inserted = basicInsert(value, key) ?: return false
        balance(inserted)
        return true
    }
}


class AVLTree<T, K: Comparable<K>>: BalancedSearchTree<T, K>() {
    protected inner class AVLNode(...): BinaryTreeNode(...)

    protected override fun createNode(...): BinaryTreeNode {
        return AVLNode(...)
    }

    override fun balance(node: BinaryTreeNode) {
        //...
    }
}

class RBTree<T, K: Comparable<K>>: BalancedSearchTree<T, K>() {
    protected inner class RBNode(...): BinaryTreeNode(...)

    protected override fun createNode(...): BinaryTreeNode {
        return RBNode(...)
    }

    override fun balance(node: BinaryTreeNode) {
        //...
    }
}
