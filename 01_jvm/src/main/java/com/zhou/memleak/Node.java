package com.zhou.memleak;

import java.util.HashSet;
import java.util.Objects;

/**
 * Hash值改变 会导致内存泄漏
 *
 * @author zhoubing
 * @date 2021-08-30 23:34
 */
public class Node {

    private int x;
    private int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return x == node.x && y == node.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public void setY(int y) {
        this.y = y;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1, 2);
        Node node2 = new Node(2, 5);

        HashSet<Node> nodeSet = new HashSet<>();
        nodeSet.add(node1);
        nodeSet.add(node2);

        node2.setY(6);
        nodeSet.remove(node2);

        // 一直都会是2  因为node2的hashCode变了 所以存在内存泄漏
        System.out.println(nodeSet.size());
    }
}
