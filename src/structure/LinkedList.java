package structure;

import model.Entity;

public class LinkedList<T> {

    private int size = 0;
    Node<T> head;
    Node<T> tail;

    public LinkedList() {
        head = tail = null;
    }

    public LinkedList(T element) {
        Node<T> node = new Node<>(element);
        head = node;
        tail = node;
        size = 1;
    }

    public T get(int n) {
        if (isEmpty() || n >= getSize() || n < 0) {
            return null;
        } else {
            Node<T> current = head;
            int i = 0;
            while (i < n) {
                current = current.next;
                i++;
            }
            return current.getElement();
        }
    }

    public T getByCode(int code) {

        Node<T> current = head;

        while (true) {
            Entity entity = (Entity) current.getElement();
            if (entity.getCode() == code) {
                return current.getElement();
            }
            if (current.next == null) {
                return null;
            }
            current = current.next;
        }
    }

    public int index(T element) {
        Node<T> node = head;
        for (int i = 0; i < getSize(); i++) {
            if (node.getElement().equals(element)) {
                return i;
            }
            node = node.next;
        }
        return -1;
    }

    public void add(T element) {

        Node<T> newNode = new Node<>(element);

        if (head == null) {
            head = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
        }
        tail = newNode;
        size++;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public boolean contains(T element) {
        return search(element) != null;
    }

    private Node<T> search(T element) {

        Node<T> current = head;

        while (current != null) {
            if (current.getElement().equals(element)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void deleteHead() {
        if (head == null) return;

        Node<T> nextNode = head.next;

        if (nextNode != null) {
            nextNode.setPrev(null);
            head = nextNode;
        } else {
            head = null;
            tail = null;
        }

        size--;
    }

    public void deleteTail() {
        if (tail == null) return;

        Node<T> prevNode = tail.prev;

        if (prevNode != null) {
            prevNode.setNext(null);
            tail = prevNode;
        } else {
            head = null;
            tail = null;
        }

        size--;
    }

    public void delete(T element) {

        if (head != null && head.getElement().equals(element)) {
            deleteHead();
            return;
        }

        if (tail != null && tail.getElement().equals(element)) {
            deleteTail();
            return;
        }

        Node<T> node = search(element);
        if (node == null) return;


        Node<T> prev = node.prev;
        Node<T> next = node.next;

        if (prev != null) {
            prev.setNext(next);
        }
        if (next != null) {
            next.setPrev(prev);
        }
        size--;

    }

    public int getMergeWithoutDuplicationSize(LinkedList<T> list) {
        int size = this.getSize();
        Node<T> current = list.head;
        while (current != null) {
            if (!this.contains(current.element)) {
                size++;
            }
            current = current.next;
        }
        return size;
    }

    public Object[] toArray() {
        Object[] result = new Object[getSize()];
        Node<T> node = head;
        for (int i = 0; i < getSize() && node != null; i++) {
            result[i] = node.element;
            node = node.next;
        }
        return result;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        Node<T> current = head;
        if (head == null) {
            sb.append("[]");
            return sb.toString();
        }

        sb.append("[");

        while (current != null) {
            sb.append(current.element)
                    .append(current.next == null ? "]" : ",");

            current = current.next;
        }
        return sb.toString();
    }

    private static class Node<T> {

        private T element;
        private Node<T> next;
        private Node<T> prev;

        public Node(T element) {
            this.element = element;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }

    }

}