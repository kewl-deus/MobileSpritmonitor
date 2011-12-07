package de.dengot.spritmonitor.persistence;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public abstract class PersistentListProxy<E> implements List<E> {

    protected abstract List<E> getWrappedList();

    public void add(int index, E element) {
        getWrappedList().add(index, element);
    }

    public boolean add(E e) {
        return getWrappedList().add(e);
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        return getWrappedList().addAll(index, c);
    }

    public boolean addAll(Collection<? extends E> c) {
        return getWrappedList().addAll(c);
    }

    public void clear() {
        getWrappedList().clear();
    }

    public boolean contains(Object o) {
        return getWrappedList().contains(o);
    }

    public boolean containsAll(Collection<?> c) {
        return getWrappedList().containsAll(c);
    }

    @Override
    public boolean equals(Object o) {
        return getWrappedList().equals(o);
    }

    public E get(int index) {
        return getWrappedList().get(index);
    }

    @Override
    public int hashCode() {
        return getWrappedList().hashCode();
    }

    public int indexOf(Object o) {
        return getWrappedList().indexOf(o);
    }

    public boolean isEmpty() {
        return getWrappedList().isEmpty();
    }

    public Iterator<E> iterator() {
        return getWrappedList().iterator();
    }

    public int lastIndexOf(Object o) {
        return getWrappedList().lastIndexOf(o);
    }

    public ListIterator<E> listIterator() {
        return getWrappedList().listIterator();
    }

    public ListIterator<E> listIterator(int index) {
        return getWrappedList().listIterator(index);
    }

    public E remove(int index) {
        return getWrappedList().remove(index);
    }

    public boolean remove(Object o) {
        return getWrappedList().remove(o);
    }

    public boolean removeAll(Collection<?> c) {
        return getWrappedList().removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return getWrappedList().retainAll(c);
    }

    public E set(int index, E element) {
        return getWrappedList().set(index, element);
    }

    public int size() {
        return getWrappedList().size();
    }

    public List<E> subList(int fromIndex, int toIndex) {
        return getWrappedList().subList(fromIndex, toIndex);
    }

    public Object[] toArray() {
        return getWrappedList().toArray();
    }

    public <T> T[] toArray(T[] a) {
        return getWrappedList().toArray(a);
    }
}
