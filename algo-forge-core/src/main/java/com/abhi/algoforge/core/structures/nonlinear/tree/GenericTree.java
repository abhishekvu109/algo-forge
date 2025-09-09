package com.abhi.algoforge.core.structures.nonlinear.tree;

import com.abhi.algoforge.core.structures.linear.queue.LinkedQueue;
import com.abhi.algoforge.core.structures.linear.queue.Queue;
import com.abhi.algoforge.core.structures.linear.sequence.DoublyLinkedSequence;
import com.abhi.algoforge.core.structures.linear.sequence.Sequence;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class GenericTree<T> implements Tree<T> {

    private Node<T> root;
    private int size;
    private final int CHILDREN_COUNT;

    public GenericTree() {
        this.CHILDREN_COUNT = 5;
    }

    public GenericTree(int childrenCount) {
        this.CHILDREN_COUNT = childrenCount;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.root = null;
    }

    @Override
    public void add(T element) {
        if (this.root == null) {
            this.root = new Node<T>(element);
            this.size++;
            return;
        }
        Queue<Node<T>> queue = new LinkedQueue<>();
        queue.offer(this.root);
        while (!queue.isEmpty()) {
            Node<T> temp = queue.poll();
            if (temp.getChildren().size() < CHILDREN_COUNT) {
                Sequence<Node<T>> children = temp.getChildren();
                children.add(new Node<T>(element));
                temp.setChildren(children);
                this.size++;
                return;
            } else {
                for (int i = 0; i < temp.getChildren().size(); i++) {
                    Node<T> child = temp.getChildren().get(i);
                    queue.offer(child);
                }
            }
        }
    }

    @Override
    public boolean contains(T element) {
        if (root == null)
            return false;
        return sequence().contains(element);
    }

    @Override
    public Sequence<T> sequence() {
        Sequence<T> output = new DoublyLinkedSequence<>();
        if (root == null) {
            return null;
        }
        Queue<Node<T>> queue = new LinkedQueue<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<T> temp = queue.poll();
            for (int i = 0; i < temp.getChildren().size(); i++) {
                queue.offer(temp.getChildren().get(i));
            }
            output.add(temp.getData());
        }
        return output;
    }

    @ToString
    private static class Node<T> {
        @Getter
        @Setter
        @ToString.Include
        private T data;

        @Getter
        @ToString.Exclude
        private int childCount;

        @Getter
        @Setter
        @ToString.Exclude
        private Sequence<Node<T>> children;

        public Node(T data) {
            this.data = data;
            this.childCount = 0;
            this.children = new DoublyLinkedSequence<>();
        }

        public Node(T data, Sequence<Node<T>> children) {
            this.data = data;
            this.childCount = 0;
            this.children = children;
        }

        public void addChildren(Node<T> node) {
            this.children.add(node);
            this.childCount++;
        }
    }
}
