/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.listas_grafos;

/**
 *
 * @author santiago
 */
import java.util.LinkedList;

public class Listas_grafos {
    private int vertices; // Número de vértices
    private LinkedList<Integer>[] listaAdyacencia; // Arreglo de listas enlazadas

    // Constructor
    public Listas_grafos(int v) {
        vertices = v;
        listaAdyacencia = new LinkedList[v];

        // Inicializar cada lista enlazada
        for (int i = 0; i < v; i++) {
            listaAdyacencia[i] = new LinkedList<>();
        }
    }

    // Agregar arista (de origen a destino)
    public void agregarArista(int origen, int destino) {
        listaAdyacencia[origen].add(destino);
        // Si el grafo es no dirigido, también agregamos la inversa:
        // listaAdyacencia[destino].add(origen);
    }

    // Mostrar el grafo
    public void mostrarGrafo() {
        for (int i = 0; i < vertices; i++) {
            System.out.print("Vértice " + i + ": ");
            for (Integer nodo : listaAdyacencia[i]) {
                System.out.print(" -> " + nodo);
            }
            System.out.println();
        }
    }

    // Main para probar
    public static void main(String[] args) {
        Listas_grafos g = new Listas_grafos(5);

        g.agregarArista(0, 1);
        g.agregarArista(0, 4);
        g.agregarArista(1, 2);
        g.agregarArista(1, 3);
        g.agregarArista(1, 4);
        g.agregarArista(2, 3);
        g.agregarArista(3, 4);

        g.mostrarGrafo();
    }
}

