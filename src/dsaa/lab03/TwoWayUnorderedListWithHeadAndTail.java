package dsaa.lab03;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class TwoWayUnorderedListWithHeadAndTail<E> implements IList<E>{

    private class Element{
        public Element(E e) {
            this.object=e;
        }
        public Element(E e, Element next, Element prev) {
            this.object=e;
            this.next=next;
            this.prev=prev;
        }
        E object;
        Element next=null;
        Element prev=null;
    }

    Element head;
    Element tail;
    // can be realization with the field size or without
    int size;

    private class InnerIterator implements Iterator<E>{
        Element e;
        // TODO maybe more fields....

        public InnerIterator() {
            e=head;
        }
        @Override
        public boolean hasNext() {
            return e.next!=null && e.next!=tail;
        }

        @Override
        public E next() {
            e = e.next;
            return e.object;
        }
    }

    private class InnerListIterator implements ListIterator<E>{
        Element element = head;
        // TODO maybe more fields....

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return element.next!=null && element.next!=tail;
        }

        @Override
        public boolean hasPrevious() {
            return element.prev!=null && element.prev!=head;
        }

        @Override
        public E next() {
            element=element.next;
            return element.object;
        }

        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public E previous() {
            element=element.prev;
            return element.object;
        }

        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E e) {
            element.object=e;
        }
    }

    public TwoWayUnorderedListWithHeadAndTail() {
        // make a head and a tail
        head=new Element(null);
        tail=new Element(null);
        head.next=tail;
        tail.prev=head;
    }

    @Override
    public boolean add(E e) {
        Element newElement=new Element(e);
        newElement.next = tail;
        newElement.prev = tail.prev;
        tail.prev.next = newElement;
        tail.prev = newElement;
        return true;
    }

    @Override
    public void add(int index, E element) {
        InnerListIterator iter=new InnerListIterator();
        for(int i=0;i<index;i++)
            if(iter.hasNext())
                iter.next();
            else
                throw new NoSuchElementException();
        Element newElement=new Element(element);
        newElement.next = iter.element.next;
        newElement.prev = iter.element;
        iter.element.next.prev = newElement;
        iter.element.next = newElement;
    }

    @Override
    public void clear() {
        head.next=tail;
        tail.prev=head;
    }

    @Override
    public boolean contains(E element) {
        InnerListIterator iter=new InnerListIterator();
        while(iter.hasNext())
            if(iter.next().equals(element))
                return true;
        return false;
    }

    @Override
    public E get(int index) {
        InnerListIterator iter=new InnerListIterator();
        for(int i=0;i<index;i++)
            if(iter.hasNext())
                iter.next();
            else
                throw new NoSuchElementException();
        return iter.element.next.object;
    }

    @Override
    public E set(int index, E element) {
        InnerListIterator iter=new InnerListIterator();
        for(int i=0;i<index;i++)
            if(iter.hasNext())
                iter.next();
            else
                throw new NoSuchElementException();
        E temp = iter.element.next.object;
        iter.element.next.object = element;
        return temp;
    }

    @Override
    public int indexOf(E element) {
        InnerListIterator iter=new InnerListIterator();
        int i=0;
        while(iter.hasNext()) {
            if(iter.next().equals(element))
                return i;
            i++;
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return head.next==tail;
    }

    @Override
    public Iterator<E> iterator() {
        return new InnerIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        //Possible problem
        InnerListIterator iter=new InnerListIterator();
        for(int i=0;i<index;i++)
            if(iter.hasNext())
                iter.next();
            else
                throw new NoSuchElementException();
        E temp = iter.element.next.object;
        iter.element.next = iter.element.next.next;
        iter.element.next.prev = iter.element;
        return temp;
    }

    @Override
    public boolean remove(E e) {
        InnerListIterator iter=new InnerListIterator();
        while(iter.hasNext()) {
            if(iter.next().equals(e)) {
                iter.element.next = iter.element.next.next;
                iter.element.next.prev = iter.element;
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        InnerIterator iter=new InnerIterator();
        int i=0;
        while(iter.hasNext()) {
            iter.next();
            i++;
        }
        return i;
    }
    public String toStringReverse() {
        InnerListIterator it = new InnerListIterator();
        String retStr = "";

        while (it.hasNext()) {
            it.next();
        }

        //TODO use reverse direction of the iterator

        retStr += "\n";

        while (it.hasPrevious()) {
            retStr = retStr + it.element.object.toString() + "\n";
            it.previous();
        }

        if (!this.isEmpty()) {
            retStr += it.element.object.toString();
        } else {
            return "";
        }

        return retStr;
    }

    public void add(TwoWayUnorderedListWithHeadAndTail<E> other) {
        if(!this.equals(other)) {
            Element thisTail = this.tail.prev;
            Element otherHead = other.head.next;

            thisTail.next = otherHead;
            otherHead.prev = thisTail;

            this.tail.prev = other.tail.prev;
            other.tail.prev.next = this.tail;

            other.clear();
        }
    }
}

