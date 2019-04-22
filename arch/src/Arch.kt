import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

open class BinarySearchTree<T, K : Comparable<K>> {
    public open inner class Node(_value: T, _key: K, _parent: Node?) {
        var left: Node? = null
        var right: Node? = null
        var parent: Node? = _parent
        var value = _value
        val key = _key
    }

    var size = 0
        protected set

    protected open var root: Node? = null

    public open fun find(key: K): T? {
        var curNode: Node? = this.root

        while (curNode != null) {
            when {
                (curNode.key == key) -> return curNode.value
                (curNode.key > key) -> curNode = curNode.left
                else -> curNode = curNode.right
            }
        }

        return null
    }

    protected open fun createNode(value: T, key: K, parent: Node?): Node {
        return Node(value, key, parent)
    }

    public open fun insert(value: T, key: K): Node {

        if (this.root == null) {
            this.root = createNode(value, key, null)
            size++
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
                    curNode.left = createNode(value, key, curNode)
                    curNode.left!!.parent = curNode
                    size++
                    return curNode.left!!
                }

                curNode = curNode.left!!
            } else {
                if (curNode.right == null) {
                    curNode.right = createNode(value, key, curNode)
                    size++
                    curNode.right!!.parent = curNode
                    return curNode.right!!
                }

                curNode = curNode.right!!
            }
        }

    }
}

abstract class BalancedSearchTree<T, K : Comparable<K>> : BinarySearchTree<T, K>() {

    protected fun Node.grandparent(): Node? {
        return this.parent?.parent
    }

    protected  fun Node.sibling(): Node? {
        if (this.parent == null) {
            return null
        }

        return if (this == this.parent!!.left) {
            this.parent!!.right
            }
            else {
                this.parent!!.left
            }
    }

    protected  fun Node.uncle(): Node? {
        return this.parent?.sibling()
    }

    protected fun Node.rotateLeft() {
        val tmp = this.right?: return
        this.right = tmp.left
        tmp.left = this
        tmp.parent = this.parent
        this.parent = tmp

        if (tmp.parent != null) {
            if (this == tmp.parent!!.left) {
                tmp.parent!!.left = tmp
            }
            else {
                tmp.parent!!.right = tmp
            }
        }
        else {
            root = tmp
        }
    }

    protected fun Node.rotateRight() {
        val tmp = this.left?: return
        this.left = tmp.right
        tmp.right = this
        tmp.parent = this.parent
        this.parent = tmp

        if (tmp.parent != null) {
            if (this == tmp.parent!!.left) {
                tmp.parent!!.left = tmp
            }
            else {
                tmp.parent!!.right = tmp
            }
        }
        else {
            root = tmp
        }
    }

}


class AVLTree<T, K : Comparable<K>> : BalancedSearchTree<T, K>() {
    public inner class AVLNode(_value: T, _key: K, _parent: Node?) : Node(_value, _key, _parent) {
        var flag = 0

    }

    protected override fun createNode(value: T, key: K, parent: Node?): Node {
        return AVLNode(value, key, parent)
    }

    override fun insert(value: T, key: K): Node {
        val oldSize = size
        val inserted = super<BalancedSearchTree>.insert(value, key)

        if (size == oldSize) {
            return inserted
        }

        var curNode = inserted as AVLNode
        var son = curNode
        while (curNode.parent != null) {
            val dad = curNode.parent as AVLNode

            if (dad.left == curNode) {
                dad.flag++
            }
            else {
                dad.flag--
            }

            if (dad.flag == 0) {
                break
            }

            if (abs(dad.flag) > 1) {
                if (curNode == dad.left) {
                    if (son == curNode.right) {
                        curNode.rotateLeft()
                        curNode.flag += min(0, son.flag) - 1
                        son.flag += max(0, curNode.flag) + 1
                    }

                    dad.rotateRight()
                    dad.flag += min(0, curNode.flag) - 1
                    curNode.flag += max(0, dad.flag) + 1
                }
                else {
                    if (son == curNode.left) {
                        curNode.rotateRight()
                        curNode.flag += max(0, son.flag) + 1
                        son.flag += min(0, curNode.flag) - 1
                    }

                    dad.rotateRight()
                    dad.flag += max(0, curNode.flag) + 1
                    curNode.flag += min(0, dad.flag) - 1
                }

                break
            }

            son = curNode
            curNode = dad
        }

        return inserted
    }

    public fun AVLNode.print() {
        print("(")
        this.left?.let { (this.left as AVLNode).print() }
        print(")")
        print("<$key $value $flag>")
        print("(")
        this.right?.let { (this.right as AVLNode).print() }
        print(")")
    }

    public fun print() {
        (root as AVLNode).print()
    }
}

class RBTree<T, K : Comparable<K>> : BalancedSearchTree<T, K>() {
    public inner class RBNode(_value: T, _key: K, _parent: Node?) : Node(_value, _key, _parent) {
        var color = 1 // 1 is red, 0 is black.
                      // Maybe I should replace it with an enum class
    }

    protected override fun createNode(value: T, key: K, parent: Node?): Node {
        return RBNode(value, key, parent)
    }

    override fun insert(value: T, key: K): Node {
        var node = super<BalancedSearchTree>.insert(value, key) as RBNode//!!!!
        val inserted = node
        while (node.parent != null) {
            val dad = node.parent as RBNode
            val u = node.uncle() as RBNode?

            when {

                (dad.color == 0) -> {
                    return inserted
                }

                (u != null && u.color == 1) -> {
                    dad.color = 0
                    u.color = 0
                    val grandDad = dad.parent as RBNode // grandparent exists because uncle exists
                    grandDad.color = 1
                    node = grandDad
                }

                else -> {
                    val grandDad = dad.parent as RBNode // grandparent exists because parent is red
                                                        // and therefore is not root

                    if (node ==  dad.right && dad == grandDad.left) {
                        dad.rotateLeft()
                        node = node.left as RBNode // safe because we just rotated the tree
                    }
                    else if (node ==  dad.left && dad == grandDad.right) {
                        dad.rotateRight()
                        node = node.right as RBNode // safe because we just rotated the tree
                    }

                    val newDad = node.parent as RBNode
                    val newGrandDad = node.grandparent() as RBNode
                    if (node == newDad.left) {
                        newGrandDad.rotateRight()
                    }
                    else {
                        newGrandDad.rotateLeft()
                    }
                    newDad.color = 0
                    newGrandDad.color = 1
                    return inserted
                }
            }
        }

        node.color = 0
        return inserted
    }

    public fun RBNode.print() {
        print("(")
        this.left?.let { (this.left as RBNode).print() }
        print(")")
        print("<$key $value $color>")
        print("(")
        this.right?.let { (this.right as RBNode).print() }
        print(")")
    }

    public fun print() {
        (root as RBNode).print()
    }
}

fun main(args: Array<String>) {
    val rbTree = AVLTree<String, Int>()
    rbTree.insert("fuck", 1)
    rbTree.insert("shit", 2)
    rbTree.insert("hell", 666)
    rbTree.insert("sucker", 4)
    rbTree.insert("nuts", 4)
    rbTree.print()
    print(rbTree.find(4))
    print("\n")
    print(rbTree.find(666))
    print("\n")
    print(rbTree.find(1))
    print("\n${rbTree.size}\n")
}
