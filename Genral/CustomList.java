import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class CustomList<T> extends AbstractList {
    private class Node<T> {
        T storedElement;
        Node next;

        public Node(T object) {
            storedElement = object;
            next = null;
        }
    }

    Node<T> head, tail;

    public CustomList() {
        head = null;
        tail = null;
    }


    public void addLast(T value) {
        if (head == null) {
            head = tail = new Node(value);
            head.next = tail;
        } else if (tail == head) {
            tail = new Node(value);
            head.next = tail;
        } else {
            Node<T> temp = new Node(value);
            tail.next = temp;
            tail = tail.next;
        }
    }

    public T getLast() {
        return tail.storedElement;
    }

    public T getFirst() {
        return head.storedElement;
    }

    public void addFirst(T value) {
        if (head == null) {
            head = tail = new Node(value);
            head.next = tail;
        } else if (tail == head) {
            head = new Node(value);
            head.next = tail;
        } else {
            Node<T> temp = new Node(value);
            temp.next = head;
            head = temp;
        }
    }

    public T removeFirst() {
        T result = null;
        ;
        if (head != null)
            result = head.storedElement;
        head = head.next;

        if(result == null)
        {

            throw new NoSuchElementException();
        }
        return result;
    }

    public T removeLast() throws NoSuchElementException{
        T result = null;
        if (tail != null)
            result = tail.storedElement;

        Node<T> replacement = head;
        while (replacement.next != tail)
            replacement = replacement.next;

        replacement.next = null;
        tail = replacement;

        if(result == null)
        {
            throw new NoSuchElementException();
        }
        return result;
    }

    public int size() {
        int result = 0;
        Node<T> travelAgent = head;
        if (travelAgent == null)
            return 0;

        while (travelAgent != null) {
            ++result;
            travelAgent = travelAgent.next;
        }
        return result;
    }

    public T get(int index) throws NoSuchElementException{
        if(index == 0)
        {
            if(head != null)
                return head.storedElement;
            else
            {
                throw new NoSuchElementException();
            }

        }
        Node<T> travelAgent = head;
        for(int i =0; i<index; ++i)
        {
            if(travelAgent == null)
            {
                throw new NoSuchElementException();
            }

            travelAgent = travelAgent.next;
        }

        return travelAgent.storedElement;
    }


    public Iterator<T> iterator()
    {
        return new Iterator<T>() {
            private Node<T> i = null;
            @Override
            public boolean hasNext() {
                return i != tail;
            }

            @Override
            public T next() {
                if(i == null)
                    i = head;
                else
                    i = i.next;
                return i.storedElement;
            }
        };
    }

    public Stream<T> stream()
    {
       Stream.Builder<T> streamBuilder=  Stream.builder();

       for (var i: this)
           streamBuilder.add(i);

       return streamBuilder.build();
    }
}
