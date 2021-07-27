package com.sorting.common;

/**
 * @author Eric
 * @date create in 2018/12/1
 */
public abstract class BaseGraph {
    /**
     * 节点数
     */
    public int n;
    /**
     * 边数
     */
    public int m;
    /**
     * 是否为有向图
     */
    public boolean directed;


    public BaseGraph(int n, boolean directed) {
        assert n >= 0;
        this.n = n;
        this.m = 0;
        this.directed = directed;

    }
    public int V(){
        return n;
    }

    /**
     * 边的个数
     * @return
     */
    public int E() {
        return m;
    }

    /**
     * 验证是否有从v到w的边
     * @param v
     * @param w
     * @return
     */
    public boolean hasEdge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;
        return false;
    }

    public void addEdge(int v, int w) {
        assert v >= 0 && v < n;
        assert w >= 0 && w < n;
    }

    /**
     * 返回图中一个顶点的所有邻边
     * 由于java使用引用机制，返回一个Vector不会带来额外开销,
     */
    public abstract Iterable<Integer> adj(int v);

    public abstract  void show();
}
