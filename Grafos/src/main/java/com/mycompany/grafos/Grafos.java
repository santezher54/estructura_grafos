/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.grafos;

// Clase Nodo para la lista enlazada
class Nodo {
    int vertice;
    Nodo siguiente;
    
    public Nodo(int vertice) {
        this.vertice = vertice;
        this.siguiente = null;
    }
}

// Clase ListaEnlazada manual
class ListaEnlazada {
    private Nodo cabeza;
    
    public ListaEnlazada() {
        this.cabeza = null;
    }
    
    // Agregar un vértice al final de la lista
    public void agregar(int vertice) {
        Nodo nuevoNodo = new Nodo(vertice);
        
        if (cabeza == null) {
            cabeza = nuevoNodo;
            return;
        }
        
        Nodo actual = cabeza;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }
        actual.siguiente = nuevoNodo;
    }
    
    // Eliminar un vértice de la lista
    public boolean eliminar(int vertice) {
        if (cabeza == null) {
            return false;
        }
        
        // Si el vértice está en la cabeza
        if (cabeza.vertice == vertice) {
            cabeza = cabeza.siguiente;
            return true;
        }
        
        Nodo actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.vertice == vertice) {
                actual.siguiente = actual.siguiente.siguiente;
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }
    
    // Verificar si un vértice existe en la lista
    public boolean contiene(int vertice) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.vertice == vertice) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }
    
    // Obtener la cabeza de la lista
    public Nodo getCabeza() {
        return cabeza;
    }
    
    // Verificar si la lista está vacía
    public boolean estaVacia() {
        return cabeza == null;
    }
    
    // Imprimir la lista
    public void imprimir() {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.print(actual.vertice + " -> ");
            actual = actual.siguiente;
        }
        System.out.print("null");
    }
}

// Clase Cola manual para BFS
class Cola {
    private NodoCola frente;
    private NodoCola fin;
    
    private class NodoCola {
        int dato;
        NodoCola siguiente;
        
        NodoCola(int dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }
    
    public Cola() {
        this.frente = null;
        this.fin = null;
    }
    
    public void encolar(int dato) {
        NodoCola nuevoNodo = new NodoCola(dato);
        if (fin == null) {
            frente = fin = nuevoNodo;
            return;
        }
        fin.siguiente = nuevoNodo;
        fin = nuevoNodo;
    }
    
    public int desencolar() {
        if (frente == null) {
            return -1;
        }
        int dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
        return dato;
    }
    
    public boolean estaVacia() {
        return frente == null;
    }
}

// Clase Grafo con lista de adyacencia manual
public class Grafos {
    private int numVertices;
    private ListaEnlazada[] listaAdyacencia;
    private boolean dirigido;
    
    // Constructor
    public Grafos(int vertices, boolean esDirigido) {
        this.numVertices = vertices;
        this.dirigido = esDirigido;
        listaAdyacencia = new ListaEnlazada[vertices];
        
        // Inicializar cada lista de adyacencia
        for (int i = 0; i < vertices; i++) {
            listaAdyacencia[i] = new ListaEnlazada();
        }
    }
    
    // Agregar una arista
    public void agregarArista(int origen, int destino) {
        if (origen >= numVertices || destino >= numVertices || origen < 0 || destino < 0) {
            System.out.println("Error: Vértice fuera de rango");
            return;
        }
        
        // Verificar si ya existe la arista para evitar duplicados
        if (!listaAdyacencia[origen].contiene(destino)) {
            listaAdyacencia[origen].agregar(destino);
        }
        
        // Si el grafo no es dirigido, agregar la arista en ambas direcciones
        if (!dirigido && !listaAdyacencia[destino].contiene(origen)) {
            listaAdyacencia[destino].agregar(origen);
        }
    }
    
    // Eliminar una arista
    public void eliminarArista(int origen, int destino) {
        if (origen >= numVertices || destino >= numVertices || origen < 0 || destino < 0) {
            return;
        }
        
        listaAdyacencia[origen].eliminar(destino);
        
        if (!dirigido) {
            listaAdyacencia[destino].eliminar(origen);
        }
    }
    
    // Obtener la lista de adyacencia de un vértice
    public ListaEnlazada obtenerVecinos(int vertice) {
        if (vertice >= numVertices || vertice < 0) {
            return new ListaEnlazada();
        }
        return listaAdyacencia[vertice];
    }
    
    // Imprimir el grafo
    public void imprimir() {
        System.out.println("\nRepresentación del grafo (Lista de Adyacencia):");
        for (int i = 0; i < numVertices; i++) {
            System.out.print("Vértice " + i + ": ");
            listaAdyacencia[i].imprimir();
            System.out.println();
        }
    }
    
    // Recorrido BFS (Búsqueda en Anchura)
    public void BFS(int verticeInicio) {
        if (verticeInicio < 0 || verticeInicio >= numVertices) {
            System.out.println("Vértice inválido");
            return;
        }
        
        boolean[] visitado = new boolean[numVertices];
        Cola cola = new Cola();
        
        visitado[verticeInicio] = true;
        cola.encolar(verticeInicio);
        
        System.out.print("\nRecorrido BFS desde " + verticeInicio + ": ");
        
        while (!cola.estaVacia()) {
            int vertice = cola.desencolar();
            System.out.print(vertice + " ");
            
            Nodo actual = listaAdyacencia[vertice].getCabeza();
            while (actual != null) {
                int vecino = actual.vertice;
                if (!visitado[vecino]) {
                    visitado[vecino] = true;
                    cola.encolar(vecino);
                }
                actual = actual.siguiente;
            }
        }
        System.out.println();
    }
    
    // Recorrido DFS (Búsqueda en Profundidad)
    public void DFS(int verticeInicio) {
        if (verticeInicio < 0 || verticeInicio >= numVertices) {
            System.out.println("Vértice inválido");
            return;
        }
        
        boolean[] visitado = new boolean[numVertices];
        System.out.print("\nRecorrido DFS desde " + verticeInicio + ": ");
        DFSUtil(verticeInicio, visitado);
        System.out.println();
    }
    
    private void DFSUtil(int vertice, boolean[] visitado) {
        visitado[vertice] = true;
        System.out.print(vertice + " ");
        
        Nodo actual = listaAdyacencia[vertice].getCabeza();
        while (actual != null) {
            int vecino = actual.vertice;
            if (!visitado[vecino]) {
                DFSUtil(vecino, visitado);
            }
            actual = actual.siguiente;
        }
    }
    
    // Verificar si existe una arista
    public boolean existeArista(int origen, int destino) {
        if (origen >= numVertices || destino >= numVertices || origen < 0 || destino < 0) {
            return false;
        }
        return listaAdyacencia[origen].contiene(destino);
    }
    
    // Contar el grado de un vértice
    public int grado(int vertice) {
        if (vertice >= numVertices || vertice < 0) {
            return -1;
        }
        
        int count = 0;
        Nodo actual = listaAdyacencia[vertice].getCabeza();
        while (actual != null) {
            count++;
            actual = actual.siguiente;
        }
        return count;
    }
    
    // Método main con ejemplo de uso
    public static void main(String[] args) {
        System.out.println("=== GRAFO NO DIRIGIDO ===");
        // Crear un grafo no dirigido con 5 vértices
        Grafos grafo = new Grafos(5, false);
        
        // Agregar aristas
        grafo.agregarArista(0, 1);
        grafo.agregarArista(0, 4);
        grafo.agregarArista(1, 2);
        grafo.agregarArista(1, 3);
        grafo.agregarArista(1, 4);
        grafo.agregarArista(2, 3);
        grafo.agregarArista(3, 4);
        
        // Imprimir el grafo
        grafo.imprimir();
        
        // Realizar recorridos
        grafo.BFS(0);
        grafo.DFS(0);
        
        // Mostrar grado de un vértice
        System.out.println("\nGrado del vértice 1: " + grafo.grado(1));
        System.out.println("¿Existe arista 0->1? " + grafo.existeArista(0, 1));
        System.out.println("¿Existe arista 0->3? " + grafo.existeArista(0, 3));
        
        // Crear un grafo dirigido
        System.out.println("\n=== GRAFO DIRIGIDO ===");
        Grafos grafoDirigido = new Grafos(4, true);
        grafoDirigido.agregarArista(0, 1);
        grafoDirigido.agregarArista(0, 2);
        grafoDirigido.agregarArista(1, 2);
        grafoDirigido.agregarArista(2, 0);
        grafoDirigido.agregarArista(2, 3);
        grafoDirigido.agregarArista(3, 3);
        
        grafoDirigido.imprimir();
        grafoDirigido.BFS(2);
        grafoDirigido.DFS(2);
        
        System.out.println("\nGrado de salida del vértice 2: " + grafoDirigido.grado(2));
    }
}
