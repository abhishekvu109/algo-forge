package com.abhi.algoforge.core.structures.nonlinear.tree;

import com.abhi.algoforge.core.structures.common.Cursor;
import com.abhi.algoforge.core.structures.linear.queue.LinkedQueue;
import com.abhi.algoforge.core.structures.linear.queue.Queue;
import com.abhi.algoforge.core.structures.linear.sequence.DoublyLinkedSequence;
import com.abhi.algoforge.core.structures.linear.sequence.Sequence;
import lombok.*;

import java.util.Objects;

public class GenericBinaryTree<T> implements BinaryTree<T> {

    @Getter
    private Node<T> root;
    private int size;

    @Override
    public void insert(T element) {
        if (this.root == null) {
            this.root = new Node<>(element, null, null);
            this.size++;
            return;
        }
        Node<T> data = new Node<>(element, null, null);
        Queue<Node<T>> queue = new LinkedQueue<>();
        queue.offer(this.root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                Node<T> node = queue.poll();
                if (node.getLeft() == null) {
                    node.setLeft(data);
                    this.size++;
                    return;
                }
                if (node.getRight() == null) {
                    node.setRight(data);
                    this.size++;
                    return;
                }
                queue.offer(node.getLeft());
                queue.offer(node.getRight());
            }
        }
    }

    //TODO: We will do it later.
    @Override
    public boolean remove(T element) {
        new Remove<T>(this.root).remove(RemoveStrategy.BRUTE_FORCE, element);
        this.size--;
        return true;
    }

    @Override
    public boolean contains(T element) {
        return sequence(TreeCursorStrategy.IN_ORDER).contains(element);
    }

    @Override
    public void clear() {
        this.size = 0;
        this.root = null;
    }

    @Override
    public Sequence<T> sequence(TreeCursorStrategy strategy) {
        return new Traversal<T>(this.root).execute(strategy);
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
    public int height() {
        return height(this.getRoot());
    }

    private int height(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.getLeft()), height(node.getRight())) + 1;
    }

    @Override
    public int depth(T element) {
        return depth(element, this.getRoot(), 0);
    }

    private int depth(T element, Node<T> node, int level) {
        if (node == null) {
            return 0;
        }
        if (node.getData().equals(element)) {
            return level;
        }
        return Math.max(depth(element, node.getLeft(), level + 1), depth(element, node.getRight(), level + 1));
    }


    @Override
    public int leafCount() {
        return leafCount(this.getRoot());
    }

    private int leafCount(Node<T> node) {
        if (node == null) {
            return 0;
        }
        int left = leafCount(node.getLeft());
        int right = leafCount(node.getRight());
        boolean isLeaf = node.getLeft() == null && node.getRight() == null;
        return left + right + (isLeaf ? 1 : 0);
    }

    @Override
    public boolean isBalanced() {
        return isBalanced(this.root);
    }

    private boolean isBalanced(Node<T> node) {
        if (node == null) {
            return true;
        }
        int leftHeight = height(node.getLeft());
        int rightHeight = height(node.getRight());
        int diff = Math.abs(leftHeight - rightHeight);
        return diff < 2;
    }

    @Override
    public boolean isFull() {
        return isFull(this.root);
    }

    private boolean isFull(Node<T> node) {
        if (node == null) {
            return true;
        }
        boolean left = isFull(node.getLeft());
        boolean right = isFull(node.getRight());
        return (node.getLeft() == null && node.getRight() == null) || (node.getLeft() != null && node.getRight() != null) && left && right;
    }

    @Override
    public boolean isPerfect() {
        if (root == null) {
            return true;
        }
        if (height(root.getLeft()) != height(root.getRight()))
            return false;
        return isPerfect(root);
    }

    public boolean isPerfect(Node<T> node) {
        if (node == null) {
            return true;
        }
        boolean left = isPerfect(node.getLeft());
        boolean right = isPerfect(node.getRight());
        boolean current = (node.getLeft() != null && node.getRight() != null) ||
                (node.getRight() == null && node.getLeft() == null);
        return left && right && current;
    }


    @Override
    public boolean isComplete() {
        return false;
    }


    @Override
    public Sequence<T> pathTo(T element) {
        return pathTo(root, element, new DoublyLinkedSequence<>());
    }

    private Sequence<T> pathTo(Node<T> node, T element, Sequence<T> path) {
        if (node == null) {
            return null;
        }
        if (element.equals(node.getData())) {
            return path;
        }
        path.add(node.getData());
        Sequence<T> left = pathTo(node.getLeft(), element, path);
        Sequence<T> right = pathTo(node.getRight(), element, path);
        if (left != null && !left.isEmpty()) {
            return left;
        } else if (right != null && !right.isEmpty()) {
            return right;
        } else {
            return new DoublyLinkedSequence<>();
        }
    }

    @Override
    public boolean areSiblings(T element1, T element2) {
        return areSiblings(this.root, element1, element2);
    }

    private boolean areSiblings(Node<T> node, T element1, T element2) {
        if (node == null) {
            return false;
        }
        boolean left = areSiblings(node.getLeft(), element1, element2);
        boolean right = areSiblings(node.getRight(), element1, element2);
        boolean current = (Objects.equals(node.getLeft(), element1) && Objects.equals(node.getRight(), element2)) ||
                (Objects.equals(node.getLeft(), element2) && Objects.equals(node.getRight(), element1));
        return left || right || current;
    }


    @Override
    public Sequence<T> nodesAtDistanceK(int k) {
        return nodesAtDistanceK(this.root, k, new DoublyLinkedSequence<>());
    }

    private Sequence<T> nodesAtDistanceK(Node<T> node, int k, Sequence<T> result) {
        if (node == null || k < 0) {
            return result;
        }
        if (k == 0) {
            result.add(node.getData());
        }
        Sequence<T> left = nodesAtDistanceK(node.getLeft(), k - 1, result);
        return nodesAtDistanceK(node.getRight(), k - 1, left);
    }


    @Override
    public Cursor<T> cursor() {
        return new Cursor<T>() {
            private Sequence<T> data = sequence(TreeCursorStrategy.LEVEL_ORDER);
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                if (data != null && !data.isEmpty()) {
                    return currentIndex < data.size();
                }
                return false;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    return data.get(currentIndex++);
                }
                return null;
            }

            @Override
            public void reset() {
                currentIndex = 0;
            }
        };
    }

    @Override
    public void add(T element) {
        insert(element);
    }

    @Override
    public Sequence<T> sequence() {
        return sequence(TreeCursorStrategy.LEVEL_ORDER);
    }

    /************************************************************* Count based methods **********************************************************************/

    @Override
    public int count(T element) {
        return countBFS(element);
    }

    @Override
    public int count(T element, TraversalStrategy strategy) {
        switch (strategy) {
            case BFS -> {
                return countBFS(element);
            }
            case DFS -> {
                return countDFS(root, element);
            }
            default -> {
                throw new IllegalArgumentException();
            }
        }
    }

    private int countBFS(T element) {
        int result = 0;
        Queue<Node<T>> queue = new LinkedQueue<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                Node<T> temp = queue.poll();
                result = (Objects.equals(temp.getData(), element)) ? result + 1 : result;
                if (temp.getLeft() != null) {
                    queue.offer(temp.getLeft());
                }
                if (temp.getRight() != null) {
                    queue.offer(temp.getRight());
                }
            }
        }
        return result;
    }

    private int countDFS(Node<T> node, T element) {
        if (node == null) {
            return 0;
        }
        int left = countDFS(node.getLeft(), element);
        int right = countDFS(node.getRight(), element);
        int current = Objects.equals(element, node.getData()) ? 1 : 0;
        return left + right + current;
    }

    @Override
    public int internalNodeCount() {
        return internalNodeCountBFS();
    }

    @Override
    public int internalNodeCount(TraversalStrategy strategy) {
        switch (strategy) {
            case BFS -> {
                return internalNodeCount();
            }
            case DFS -> {
                return internalNodeCountDFS(root);
            }
            default -> {
                throw new IllegalArgumentException();
            }
        }
    }

    public int internalNodeCountDFS(Node<T> node) {
        if (node == null || node == root) {
            return 0;
        }
        int left = internalNodeCountDFS(node.getLeft());
        int right = internalNodeCountDFS(node.getRight());
        int current = node.isLeaf() ? 0 : 1;
        return left + right + current;
    }

    public int internalNodeCountBFS() {
        int result = 0;
        Queue<Node<T>> queue = new LinkedQueue<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                Node<T> temp = queue.poll();
                if (temp == root)
                    continue;
                result = !temp.isLeaf() ? result + 1 : result;
                if (temp.getLeft() != null) {
                    queue.offer(temp.getLeft());
                }
                if (temp.getRight() != null) {
                    queue.offer(temp.getRight());
                }
            }
        }
        return result;
    }

    @Override
    public int singleChildCount() {
        return singleChildCountBFS();
    }

    @Override
    public int singleChildCount(TraversalStrategy strategy) {
        switch (strategy) {
            case BFS -> {
                return singleChildCountBFS();
            }
            case DFS -> {
                return singleChildCountDFS(root);
            }
            default -> {
                throw new IllegalArgumentException();
            }
        }
    }

    public int singleChildCountBFS() {
        int result = 0;
        Queue<Node<T>> queue = new LinkedQueue<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                Node<T> temp = queue.poll();
                result = temp.hasOneChild() ? result + 1 : result;
                if (temp.getLeft() != null) {
                    queue.offer(temp.getLeft());
                }
                if (temp.getRight() != null) {
                    queue.offer(temp.getRight());
                }
            }
        }
        return result;
    }

    private int singleChildCountDFS(Node<T> node) {
        if (node == null) {
            return 0;
        }
        int left = singleChildCountDFS(node.getLeft());
        int right = singleChildCountDFS(node.getRight());
        int current = node.hasOneChild() ? 0 : 1;
        return left + right + current;
    }

    @Override
    public int countAtLevel(int level) {
        return countAtLevelBFS(level);
    }

    @Override
    public int countAtLevel(int level, TraversalStrategy strategy) {
        switch (strategy) {
            case BFS -> {
                return countAtLevelBFS(level);
            }
            case DFS -> {
                return countAtLevelDFS(root, level);
            }
            default -> {
                throw new IllegalArgumentException();
            }
        }
    }

    private int countAtLevelDFS(Node<T> node, int count) {
        if (node == null || count < 0) {
            return 0;
        }
        int left = singleChildCountDFS(node.getLeft());
        int right = singleChildCountDFS(node.getRight());
        int current = node.hasOneChild() ? 0 : 1;
        return left + right + current;
    }

    private int countAtLevelBFS(int level) {
        int result = 0;
        int currentLevel = 0;
        Queue<Node<T>> queue = new LinkedQueue<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                Node<T> temp = queue.poll();
                if (temp.getLeft() != null) {
                    queue.offer(temp.getLeft());
                }
                if (temp.getRight() != null) {
                    queue.offer(temp.getRight());
                }
                result = currentLevel == level ? result + 1 : result;
            }
            currentLevel++;

        }
        return result;
    }


    @Override
    public int countNodesInRange(int minDepth, int maxDepth) {
        return countNodesInRangeBFS(minDepth, maxDepth);
    }

    @Override
    public int countNodesInRange(int minDepth, int maxDepth, TraversalStrategy strategy) {
        switch (strategy) {
            case BFS -> {
                return countNodesInRangeBFS(minDepth, maxDepth);
            }
            case DFS -> {
                return countNodesInRangeDFS(root, 0, minDepth, maxDepth);
            }
            default -> {
                throw new IllegalArgumentException();
            }
        }
    }

    private int countNodesInRangeBFS(int minDepth, int maxDepth) {
        int result = 0;
        int currentLevel = 0;
        Queue<Node<T>> queue = new LinkedQueue<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                Node<T> temp = queue.poll();
                if (temp.getLeft() != null) {
                    queue.offer(temp.getLeft());
                }
                if (temp.getRight() != null) {
                    queue.offer(temp.getRight());
                }
                result = currentLevel >= minDepth && currentLevel <= maxDepth ? result + 1 : result;
            }
            currentLevel++;

        }
        return result;
    }

    private int countNodesInRangeDFS(Node<T> node, int level, int minDepth, int maxDepth) {
        if (node == null) {
            return 0;
        }
        int left = countNodesInRangeDFS(node.getLeft(), level + 1, minDepth, maxDepth);
        int right = countNodesInRangeDFS(node.getRight(), level + 1, minDepth, maxDepth);
        int current = level >= minDepth && level <= maxDepth ? 1 : 0;
        return left + right + current;
    }

    @Override
    public int maxLevelNodes() {
        return countAtLevel(height() - 1);
    }

    /************************************************************* Count based methods **********************************************************************/

    @AllArgsConstructor
    @Data
    @EqualsAndHashCode
    @ToString
    private static class Node<T> {
        private T data;
        @ToString.Exclude
        @EqualsAndHashCode.Exclude
        private Node<T> left;
        @ToString.Exclude
        @EqualsAndHashCode.Exclude
        private Node<T> right;

        private boolean isLeaf() {
            return left == null && right == null;
        }

        private boolean hasOneChild() {
            return (left == null && right != null) || (left != null && right == null);
        }

        private boolean isFull() {
            return left != null && right != null;
        }
    }

    private record Traversal<T>(Node<T> root) {
        Sequence<T> execute(TreeCursorStrategy strategy) {
            switch (strategy) {
                case PRE_ORDER -> {
                    return executePreOrder(root, new DoublyLinkedSequence<>());
                }
                case IN_ORDER -> {
                    return executeInOrder(root, new DoublyLinkedSequence<>());
                }
                case POST_ORDER -> {
                    return executePostOrder(root, new DoublyLinkedSequence<>());
                }
                case LEVEL_ORDER -> {
                    return executeLevelOrder();
                }
                default -> {
                    throw new IllegalArgumentException();
                }
            }
        }

        private Sequence<T> executePreOrder(Node<T> node, Sequence<T> output) {
            if (node == null) {
                return output;
            }
            output.add(node.getData());
            return executePreOrder(node.right, executePreOrder(node.left, output));
        }

        private Sequence<T> executePostOrder(Node<T> node, Sequence<T> output) {
            if (node == null) {
                return output;
            }
            Sequence<T> collect = executePostOrder(node.right, executePostOrder(node.left, output));
            collect.add(node.data);
            return collect;
        }

        private Sequence<T> executeInOrder(Node<T> node, Sequence<T> output) {
            if (node == null) {
                return output;
            }
            Sequence<T> left = executeInOrder(node.left, output);
            left.add(node.data);
            return executeInOrder(node.right, left);
        }

        private Sequence<T> executeLevelOrder() {
            Sequence<T> result = new DoublyLinkedSequence<>();
            Queue<Node<T>> queue = new LinkedQueue<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int n = queue.size();
                for (int i = 0; i < n; i++) {
                    Node<T> node = queue.poll();
                    if (node.getLeft() != null) {
                        queue.offer(node.getLeft());
                    }
                    if (node.getRight() != null) {
                        queue.offer(node.getRight());
                    }
                    result.add(node.getData());
                }
            }
            return result;
        }
    }

    private enum RemoveStrategy {
        BRUTE_FORCE
    }

    private static class Remove<T> {
        private Node<T> node;

        Remove(Node<T> node) {
            this.node = node;
        }

        private void remove(RemoveStrategy strategy, T element) {
            bruteForce(element);
        }

        private void bruteForce(T element) {

            Node<T> elementNode = find(node, element);
            if (elementNode == null)
                return;
            Node<T> parent = getParent(node, elementNode);
            if (Objects.equals(parent.getLeft(), elementNode))
                parent.setLeft(null);
            if (Objects.equals(parent.getRight(), elementNode))
                parent.setRight(null);
            Sequence<Node<T>> elementChildren = new DoublyLinkedSequence<>();
            Sequence<Node<T>> leftSubTree = findAll(elementNode.getLeft(), elementChildren);
            Sequence<Node<T>> rightSubTree = findAll(elementNode.getRight(), elementChildren);
            for (int i = 0; i < leftSubTree.size(); i++) {
                elementChildren.add(leftSubTree.get(i));
            }
            for (int i = 0; i < rightSubTree.size(); i++) {
                elementChildren.add(rightSubTree.get(i));
            }
            for (int i = 0; i < elementChildren.size(); i++) {
                add(elementChildren.get(i));
            }

        }

        private Node<T> getParent(Node<T> rootNode, Node<T> nodeObject) {
            if (rootNode == null) {
                return null;
            }
            Node<T> left = getParent(rootNode.getLeft(), nodeObject);
            Node<T> right = getParent(rootNode.getRight(), nodeObject);
            if (Objects.equals(rootNode.getLeft(), nodeObject) || Objects.equals(rootNode.getRight(), nodeObject))
                return rootNode;
            else if (left != null)
                return left;
            else
                return right;
        }

        private Node<T> find(Node<T> object, T element) {
            if (object == null) {
                return null;
            }
            Node<T> left = find(object.getLeft(), element);
            Node<T> right = find(object.getRight(), element);
            Node<T> current = element.equals(object.getData()) ? object : null;
            if (current != null) {
                return current;
            } else if (left != null) {
                return left;
            } else return right;
        }


        private Sequence<Node<T>> findAll(Node<T> obj, Sequence<Node<T>> result) {
            if (obj == null) {
                return result;
            }
            Sequence<Node<T>> right = findAll(obj.getRight(), findAll(obj.getLeft(), result));
            right.add(obj);
            return right;
        }

        private void add(Node<T> element) {
            if (this.node == null) {
                this.node = element;
                return;
            }
            Queue<Node<T>> queue = new LinkedQueue<>();
            queue.offer(this.node);
            while (!queue.isEmpty()) {
                int n = queue.size();
                for (int i = 0; i < n; i++) {
                    Node<T> node = queue.poll();
                    if (node.getLeft() == null) {
                        node.setLeft(element);
                        return;
                    }
                    if (node.getRight() == null) {
                        node.setRight(element);
                        return;
                    }
                    queue.offer(node.getLeft());
                    queue.offer(node.getRight());
                }
            }
        }
    }
}
