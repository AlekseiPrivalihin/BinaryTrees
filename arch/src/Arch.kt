open class BinarySearchTree<T, K : Comparable<K>> {
    public open inner class Node(_value: T, _key: K) {
        open var left: Node? = null
        open var right: Node? = null
        open var parent: Node? = null
        var value = _value
        val key = _key
    }


    protected open var root: Node? = null

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

    public open fun insert(value: T, key: K): Node {

        if (this.root == null) {
            this.root = createNode(value, key)
            return this.root!!
        }

        var curNode = this.root!!

        while (true) {
            if (curNode.key == key) {
                curNode.value = value
                return curNode
            }

            if (curNode.key > key) {
                if (curNode.left == null) {
                    curNode.left = createNode(value, key)
                    curNode.left!!.parent = curNode
                    return curNode.left!!
                }

                curNode = curNode.left!!
            } else {
                if (curNode.right == null) {
                    curNode.right = createNode(value, key)
                    curNode.right!!.parent = curNode
                    return curNode.right!!
                }

                curNode = curNode.right!!
            }
        }

    }

}

abstract class BalancedSearchTree<T, K : Comparable<K>> : BinarySearchTree<T, K>() {

    protected fun Node.rotateLeft() {
        //TODO
    }
    protected fun Node.rotateRight() {
        //TODO
    }

}


class AVLTree<T, K : Comparable<K>> : BalancedSearchTree<T, K>() {
    public inner class AVLNode(_value: T, _key: K) : Node(_value, _key) {
        var flag = 0

    }

    protected override fun createNode(value: T, key: K): Node {
        return AVLNode(value, key)
    }

    override fun insert(value: T, key: K): Node {
        //TODO
        return super<BalancedSearchTree>.insert(value, key)
    }
}

class RBTree<T, K : Comparable<K>> : BalancedSearchTree<T, K>() {
    public inner class RBNode(_value: T, _key: K) : Node(_value, _key) {
        var color = 0
    }

    protected override fun createNode(value: T, key: K): Node {
        return RBNode(value, key)
    }

    override fun insert(value: T, key: K): Node {
        //TODO
        return super<BalancedSearchTree>.insert(value, key)
    }
}
