看源码之前，将其功能点，解决的问题，以及其内的属性搞明白，然后我们再来看起实现方式是非常容易的
1. ConcurrentHashMap结构：
    Segment: 保留的内部类，其实在java 1.8 中已经没有使用了
    BulkTask(BulkTask->CountedCompleter->ForkJoinTask->Future): 继承关系如上，但是在类中的作用是什么？
    Spliterator:它的作用类似Iterator,不同的是能够将一个任务分成多个子任务迭代
    Traverser:这是一个静态内部类，它的功能点和作用是什么？
    Entity: Map接口中的内部接口，表示的是存储数据的实体，在ConcurrentHashMap中有两个实现类分别是NOde，MapEntity
    Node:
    其中Node也有很多子类，每个子类的功能点是什么？又是一般什么时候使用呢？
    MapEntity:
    还有单独的两个类TableStack 和TableStack
    TableStack
    CollectionView：功能点是什么，再ConcurrentHashMap中一般什么时候会使用这个类呢？？


2. HashMap中结构：

    内部类：
    EntrySet: 保存的是Map接口中的内部接口Entity,Endity中存储的数据是键值对形式的，在HashMap中是存储的是Node,同样的，EntrySet也是实现自AbstractSet，保证其中的数据不可重复       
    KeySet:存储的是HashMap中所有Key,实现于AbstractSet，使用Set的原因是因为HashMap中的Key是无序的,并且不可以重复的       
    Node: 对于大部分情况下，HashMap中存储的数据都是放在这个Node中的，       
    Entry:Node的子类，功能主要是用于LinkedHashMap的     
    TreeNode:树化之后存储数据的Node，存储的方式是红黑树     
        问题：一般在什么情况下HashMap中额节点可能会发生树化？       
    HashIterator: 通过链表的方式迭代Node，是个抽象的内部类，实现类有KeyIterator,EntryIterator,ValueIterator         
    KeyIterator: 通过链表的方式迭代Key,使用是在KeySet中的iterator()方法。       
    EntryIterator:通过链表的方式迭代Entity(也就是HashMap中的Node<K,V>),使用是在EntrySet中的iterator()       
    ValueIterator:和上面的功能是一样的，只是迭代的对象时键值对中的值        
    HashMapSpliterator:     

    成员变量：      
    DEFAULT_INITIAL_CAPACITY=16：这只无参构造器构造出来的HashMap中的初始容量，如果我们能够预测这个HashMap中存储的元素个数>16*0.75(负载因子) 的时候，我们应该使用HashMap的一个参数的构造器，来指定初始容量，这个容量值一般都是2的指数次方。因为当HashMap中存储的位置不够的时候，它就会扩容，扩容的时候是会有一定的性能损耗的，那么问题来了，为什么会出现性能损耗？           
    DEFAULT_LOAD_FACTOR=0.75：默认的负载因子，一般是当HashMap中的容量*这个值得<现在要存储的元素个数的时候，HashMap就会扩容          
    MAXIMUM_CAPACITY=1<<30：HashMap的最大容量 2^30          
    TREEIFY_THRESHOLD=8：当单个Node中的链表数目大于8的时候才会将链表树化为红黑树，但并不是值需要满足这个条件，还需要满足下面的条件也就是整个HashMap中的容量必须大于64           
    MIN_TREEIFY_CAPACITY=64：这个值最起码是4*TREEIFY_THRESHOLD的值，主要是为了避免在扩容或者树化的时候出现Hash 冲突         
    UNTREEIFY_THRESHOLD=6：移除红黑树，转化为链表的最小容量         
    table：是个Node数组，实际存放数据的地方         
    entrySet：          
    size：就是HashMap中存储的元素的个数，           
    modCount：表述的是HashMap的结构被改变的次数，这个字段主要是用于保证HashMap在进行迭代的过程中，他的数据结构被其他线程更改了，那么就会快速失败抛出一个ConcurrentModificationException         
    threshold：这个值是在HashMap扩容的时候，HashMap中的容量         

    接下来重点逻辑分析在put中

    hash(Key key):基本上所有对键操作的方法都会使用这个方法，这个方法的作用就是


    -101 0101 0110 0011 1100 1111 1000 1100
    0000 0000 0000 0000 1010 1010 1100 0111
                        
    -101 0101 0110 0011 0110 0101 0001 1000
    



    resize():
    1. 判断table,和threshold
