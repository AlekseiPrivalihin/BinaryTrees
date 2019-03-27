open class BinarySearchTree<T, K: Comparable<K>>
{
    protected open inner class BinaryTreeNode(_value: T, _key: K) {
        open var left: BinaryTreeNode? = null
        open var right: BinaryTreeNode? = null
        open var parent: BinaryTreeNode? = null
        var value = _value
        val key = _key
    }


    protected open var root: BinaryTreeNode? = null

    public open fun find(key: K): T? {
        var curNode: BinaryTreeNode? = this.root

        while (curNode != null) {
            when (curNode.key) {
                key -> return curNode.value
                (curNode.key > key) -> curNode = curNode.left
                else -> curNode = curNode.right
            }
        }

        return null
    }

    protected fun basicInsert(value: T, key: K): BinaryTreeNode {

        if (this.root == null) {
            this.root = BinaryTreeNode(value, key)
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
                    curNode.left = BinaryTreeNode(value, key)
                    curNode.left!!.parent = curNode
                    return curNode.left!!
                }

                curNode = curNode.left!!
            }
            else {
                if (curNode.right == null) {
                    curNode.right = BinaryTreeNode(value, key)
                    curNode.right!!.parent = curNode
                    return curNode.right!!
                }

                curNode = curNode.right!!
            }
        }

    }

    public open fun insert(value: T, key: K) {
        basicInsert(value, key)
    }
}

abstract class BalancedSearchTree<T, K: Comparable<K>>: BinarySearchTree<T, K>() {

    protected enum class Side {LEFT, RIGHT}

    protected fun BinaryTreeNode.rotate(side: Side) {
        //TODO
    }

    protected abstract fun balance(node: BinaryTreeNode)

    override fun insert(value: T, key: K) {
        balance(basicInsert(value, key))
    }
}


class AVLTree<T, K: Comparable<K>>: BalancedSearchTree<T, K>() {
    protected inner class AVLNode(_value: T, _key: K): BinaryTreeNode(_value, _key) {
        var flag = 0

    }

    override fun balance(node: BinaryTreeNode) {
        //TODO
    }
}

class RBTree<T, K: Comparable<K>>: BalancedSearchTree<T, K>()
{
    protected inner class RBNode(_value: T, _key: K): BinaryTreeNode(_value, _key)
    {
        var color = 0
    }

    override fun balance(node: BinaryTreeNode) {
        //TODO
    }
}
