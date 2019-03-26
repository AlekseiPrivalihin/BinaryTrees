open class BinarySearchTree<T, K: Comparable<K>>
{
    protected open inner class BinaryTreeNode(_value: T, _key: K)
    {
        var left: BinaryTreeNode? = null
        var right: BinaryTreeNode? = null
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

    public open fun insert(value: T, key: K): Unit {
        var curNode = this.root

        if (curNode == null) {
            this.root = BinaryTreeNode(value, key)
        }

        while (curNode != null) {
            if (curNode.key == key) {
                curNode.value = value
                return
            }

            if (curNode.key > key) {
                if (curNode.left == null) {
                    curNode.left = BinaryTreeNode(value, key)
                    return
                }

                curNode = curNode.left
            }
            else {
                if (curNode.right == null) {
                    curNode.right = BinaryTreeNode(value, key)
                    return
                }

                curNode = curNode.right
            }
        }

    }
}


class AVLTree<T, K: Comparable<K>>: BinarySearchTree<T, K>()
{
    protected inner class AVLNode(_value: T, _key: K): BinaryTreeNode(_value, _key) {
        var flag = 0
        var parent: AVLNode? = null

        constructor(_value: T, _key: K, _flag: Int, _parent: AVLNode): AVLNode(_value, _key) {
            flag = _flag
            parent = _parent
        }

        fun rotate() {

        }
    }

    override var root: AVLNode? = null

    override fun insert(value: T, key: K) {
        //TODO
    }
}

class RBTree<T, K: Comparable<K>>: BinarySearchTree<T, K>()
{
    protected inner class RBNode(_value: T, _key: K): BinaryTreeNode(_value, _key)
    {
        var color = 0
        var parent: RBNode? = null

        constructor(_value: T, _key: K, _color: Int, _parent: RBNode): RBNode(_value, _key) {
            color = _color
            parent = _parent
        }

        fun rotate() {

        }
    }

    override var root: RBNode? = null

    override fun insert(value: T, key: K) {
        //TODO
    }
}
