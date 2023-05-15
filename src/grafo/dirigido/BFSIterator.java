package grafo.dirigido;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BFSIterator<T> implements Iterator<Vertice<T>> {
    private final Queue<Vertice<T>> queue = new LinkedList<>();

    public <T> BFSIterator(Grafo<T> grafo) {
        for (Vertice<T> vertice : grafo.getVertices()) {
            vertice.setStatus(VertexState.Unvisited);
        }
        Vertice<T> vertice = grafo.getVertice(grafo.getVertices().get(0).getCarga());
        queue.offer(vertice);
        vertice.setStatus(VertexState.Visited);
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public Vertice<T> next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Vertice<T> vertice = queue.poll();
        for (Aresta<T> arco : vertice.getAdj()) {
            Vertice<T> vertice1 = arco.getDestino();
            if (vertice1.getStatus() == VertexState.Unvisited) {
                vertice1.setStatus(VertexState.Visited);
                queue.add(vertice1);
            }
        }
        return vertice;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
