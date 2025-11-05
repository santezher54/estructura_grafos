/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.grafosl;

/**
 *
 * @author santiago
 */
import java.util.*;

public class GrafosL {
    private int numVertices;
    private LinkedList<Integer>[] listaAdyacencia;
    private boolean dirigido;
    
    // Constructor
    public GrafosL(int vertices, boolean esDirigido) {
        this.numVertices = vertices;
        this.dirigido = esDirigido;
        listaAdyacencia = new LinkedList[vertices];
        
        // Inicializar cada lista de adyacencia
        for (int i = 0; i < vertices; i++) {
            listaAdyacencia[i] = new LinkedList<>();
        }
    }
    
    // Agregar una arista
    public void agregarArista(int origen, int destino) {
        if (origen >= numVertices || destino >= numVertices || origen < 0 || destino < 0) {
            System.out.println("Error: Vértice fuera de rango");
            return;
        }
        
        listaAdyacencia[origen].add(destino);
        
        // Si el grafo no es dirigido, agregar la arista en ambas direcciones
        if (!dirigido) {
            listaAdyacencia[destino].add(origen);
        }
    }
    
    // Eliminar una arista
    public void eliminarArista(int origen, int destino) {
        if (origen >= numVertices || destino >= numVertices) {
            return;
        }
        
        listaAdyacencia[origen].remove(Integer.valueOf(destino));
        
        if (!dirigido) {
            listaAdyacencia[destino].remove(Integer.valueOf(origen));
        }
    }
    
    // Obtener los vecinos de un vértice
    public LinkedList<Integer> obtenerVecinos(int vertice) {
        if (vertice >= numVertices || vertice < 0) {
            return new LinkedList<>();
        }
        return listaAdyacencia[vertice];
    }
    
    // Imprimir el grafo
    public void imprimir() {
        System.out.println("\nRepresentación del grafo:");
        for (int i = 0; i < numVertices; i++) {
            System.out.print("Vértice " + i + ": ");
            for (Integer vecino : listaAdyacencia[i]) {
                System.out.print(vecino + " -> ");
            }
            System.out.println("null");
        }
    }
    
    // Recorrido BFS (Búsqueda en Anchura)
    public void BFS(int verticeInicio) {
        boolean[] visitado = new boolean[numVertices];
        Queue<Integer> cola = new LinkedList<>();
        
        visitado[verticeInicio] = true;
        cola.add(verticeInicio);
        
        System.out.print("\nRecorrido BFS desde " + verticeInicio + ": ");
        
        while (!cola.isEmpty()) {
            int vertice = cola.poll();
            System.out.print(vertice + " ");
            
            for (Integer vecino : listaAdyacencia[vertice]) {
                if (!visitado[vecino]) {
                    visitado[vecino] = true;
                    cola.add(vecino);
                }
            }
        }
        System.out.println();
    }
    
    // Recorrido DFS (Búsqueda en Profundidad)
    public void DFS(int verticeInicio) {
        boolean[] visitado = new boolean[numVertices];
        System.out.print("\nRecorrido DFS desde " + verticeInicio + ": ");
        DFSUtil(verticeInicio, visitado);
        System.out.println();
    }
    
    private void DFSUtil(int vertice, boolean[] visitado) {
        visitado[vertice] = true;
        System.out.print(vertice + " ");
        
        for (Integer vecino : listaAdyacencia[vertice]) {
            if (!visitado[vecino]) {
                DFSUtil(vecino, visitado);
            }
        }
    }
    
    // Método main con ejemplo de uso
    public static void main(String[] args) {
        // Crear un grafo no dirigido con 5 vértices
        GrafosL grafo = new GrafosL(5, false);
        
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
        
        // Mostrar vecinos de un vértice
        System.out.println("\nVecinos del vértice 1: " + grafo.obtenerVecinos(1));
        
        // Crear un grafo dirigido
        System.out.println("\n--- Grafo Dirigido ---");
        GrafosL grafoDirigido = new GrafosL(4, true);
        grafoDirigido.agregarArista(0, 1);
        grafoDirigido.agregarArista(0, 2);
        grafoDirigido.agregarArista(1, 2);
        grafoDirigido.agregarArista(2, 0);
        grafoDirigido.agregarArista(2, 3);
        grafoDirigido.agregarArista(3, 3);
        
        grafoDirigido.imprimir();
        grafoDirigido.BFS(2);
    }
}
