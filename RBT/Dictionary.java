

public interface Dictionary<E>{

    boolean isEmpty();

    boolean contains(E item);

    boolean hasPredecessor(E item);

    boolean hasSuccessor(E item);

    boolean add(E item);

    boolean delete(E item);

    String toString();

}