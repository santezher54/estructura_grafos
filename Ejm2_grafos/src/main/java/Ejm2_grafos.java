import java.util.*;

class Grafo {
    private int numVertices;
    private ArrayList<ArrayList<Integer>> listaAdyacencia;
    private boolean esDirigido;
    
    // Constructor
    public Grafo(int vertices, boolean dirigido) {
        this.numVertices = vertices;
        this.esDirigido = dirigido;
        listaAdyacencia = new ArrayList<>(numVertices);
        
        // Inicializar la lista de adyacencia
        for (int i = 0; i < numVertices; i++) {
            listaAdyacencia.add(new ArrayList<>());
        }
    }
    
    // Constructor por defecto (grafo no dirigido)
    public Grafo(int vertices) {
        this(vertices, false);
    }
    
    // Agregar arista
    public void agregarArista(int origen, int destino) {
        // Validar vértices
        if (origen < 0 || origen >= numVertices || destino < 0 || destino >= numVertices) {
            System.out.println("Error: Vértice fuera de rango");
            return;
        }
        
        listaAdyacencia.get(origen).add(destino);
        
        // Si no es dirigido, agregar la arista en ambas direcciones
        if (!esDirigido) {
            listaAdyacencia.get(destino).add(origen);
        }
    }
    
    // Eliminar arista
    public void eliminarArista(int origen, int destino) {
        if (origen < 0 || origen >= numVertices || destino < 0 || destino >= numVertices) {
            System.out.println("Error: Vértice fuera de rango");
            return;
        }
        
        listaAdyacencia.get(origen).remove(Integer.valueOf(destino));
        
        if (!esDirigido) {
            listaAdyacencia.get(destino).remove(Integer.valueOf(origen));
        }
    }
    
    // Imprimir el grafo
    public void imprimirGrafo() {
        System.out.println("\n=== Estructura del Grafo " + (esDirigido ? "(Dirigido)" : "(No Dirigido)") + " ===");
        for (int i = 0; i < listaAdyacencia.size(); i++) {
            System.out.print("Vértice " + i + ":");
            if (listaAdyacencia.get(i).isEmpty()) {
                System.out.print(" (sin conexiones)");
            } else {
                for (int j : listaAdyacencia.get(i)) {
                    System.out.print(" -> " + j);
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    
    // Obtener el grado de un vértice
    public int obtenerGrado(int vertice) {
        if (vertice < 0 || vertice >= numVertices) {
            return -1;
        }
        return listaAdyacencia.get(vertice).size();
    }
    
    // Verificar si existe una arista
    public boolean existeArista(int origen, int destino) {
        if (origen < 0 || origen >= numVertices || destino < 0 || destino >= numVertices) {
            return false;
        }
        return listaAdyacencia.get(origen).contains(destino);
    }
    
    // Obtener vecinos de un vértice
    public ArrayList<Integer> obtenerVecinos(int vertice) {
        if (vertice < 0 || vertice >= numVertices) {
            return new ArrayList<>();
        }
        return new ArrayList<>(listaAdyacencia.get(vertice));
    }
    
    // Recorrido BFS (Búsqueda en Anchura)
    public void recorridoBFS(int inicio) {
        if (inicio < 0 || inicio >= numVertices) {
            System.out.println("Vértice inicial inválido");
            return;
        }
        
        boolean[] visitado = new boolean[numVertices];
        Queue<Integer> cola = new LinkedList<>();
        
        visitado[inicio] = true;
        cola.add(inicio);
        
        System.out.print("Recorrido BFS desde vértice " + inicio + ": ");
        
        while (!cola.isEmpty()) {
            int vertice = cola.poll();
            System.out.print(vertice + " ");
            
            for (int vecino : listaAdyacencia.get(vertice)) {
                if (!visitado[vecino]) {
                    visitado[vecino] = true;
                    cola.add(vecino);
                }
            }
        }
        System.out.println("\n");
    }
    
    // Recorrido DFS (Búsqueda en Profundidad)
    public void recorridoDFS(int inicio) {
        if (inicio < 0 || inicio >= numVertices) {
            System.out.println("Vértice inicial inválido");
            return;
        }
        
        boolean[] visitado = new boolean[numVertices];
        System.out.print("Recorrido DFS desde vértice " + inicio + ": ");
        recorridoDFSUtil(inicio, visitado);
        System.out.println("\n");
    }
    
    // Método auxiliar para DFS recursivo
    private void recorridoDFSUtil(int vertice, boolean[] visitado) {
        visitado[vertice] = true;
        System.out.print(vertice + " ");
        
        for (int vecino : listaAdyacencia.get(vertice)) {
            if (!visitado[vecino]) {
                recorridoDFSUtil(vecino, visitado);
            }
        }
    }
}

public class Ejm2_grafos {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  EJEMPLOS DE GRAFOS EN JAVA           ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        // Ejemplo 1: Grafo No Dirigido
        System.out.println("--- EJEMPLO 1: GRAFO NO DIRIGIDO ---");
        Grafo grafoNoDirigido = new Grafo(5);
        
        grafoNoDirigido.agregarArista(0, 1);
        grafoNoDirigido.agregarArista(0, 2);
        grafoNoDirigido.agregarArista(0, 3);
        grafoNoDirigido.agregarArista(1, 2);
        grafoNoDirigido.agregarArista(3, 4);
        
        grafoNoDirigido.imprimirGrafo();
        
        System.out.println("Grado del vértice 0: " + grafoNoDirigido.obtenerGrado(0));
        System.out.println("¿Existe arista 0->2? " + grafoNoDirigido.existeArista(0, 2));
        System.out.println();
        
        grafoNoDirigido.recorridoBFS(0);
        grafoNoDirigido.recorridoDFS(0);
        
        // Ejemplo 2: Grafo Dirigido
        System.out.println("--- EJEMPLO 2: GRAFO DIRIGIDO ---");
        Grafo grafoDirigido = new Grafo(4, true);
        
        grafoDirigido.agregarArista(0, 1);
        grafoDirigido.agregarArista(0, 2);
        grafoDirigido.agregarArista(1, 2);
        grafoDirigido.agregarArista(2, 0);
        grafoDirigido.agregarArista(2, 3);
        
        grafoDirigido.imprimirGrafo();
        grafoDirigido.recorridoBFS(0);
        grafoDirigido.recorridoDFS(2);
        
        // Ejemplo 3: Operaciones adicionales
        System.out.println("--- EJEMPLO 3: OPERACIONES ADICIONALES ---");
        Grafo grafo = new Grafo(4);
        
        grafo.agregarArista(0, 1);
        grafo.agregarArista(0, 2);
        grafo.agregarArista(1, 3);
        
        System.out.println("Grafo original:");
        grafo.imprimirGrafo();
        
        System.out.println("Vecinos del vértice 0: " + grafo.obtenerVecinos(0));
        
        grafo.eliminarArista(0, 1);
        System.out.println("\nDespués de eliminar arista 0-1:");
        grafo.imprimirGrafo();
    }
}
