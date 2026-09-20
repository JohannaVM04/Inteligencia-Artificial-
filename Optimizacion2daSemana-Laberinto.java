import java.util.*;

/**
 * Ejercicio 1: Cruzar un laberinto
 *
 * <p>Encuentra un camino desde el punto de inicio ('A') hasta la
 * salida ('B') dentro de una matriz que representa un laberinto.
 *
 * <p>Se resuelve con BFS (Breadth-First Search) de forma iterativa,
 * usando una cola. BFS explora por niveles de distancia, así que al
 * encontrar la salida, el camino obtenido es el más corto. Usar una
 * cola en lugar de recursión cumple el requisito de iteratividad.
 *
 * <p>Complejidad: tiempo O(F × C), espacio O(F × C),
 * donde F = filas y C = columnas.
 */
public class Laberinto {

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    public static List<int[]> resolver(char[][] laberinto) {
        int filas = laberinto.length;
        int columnas = laberinto[0].length;

        int inicio = -1;
        int fin = -1;

        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                if (laberinto[f][c] == 'S') inicio = f * columnas + c;
                if (laberinto[f][c] == 'E') fin = f * columnas + c;
            }
        }

        if (inicio == -1 || fin == -1) {
            return Collections.emptyList();
        }

        ArrayDeque<Integer> cola = new ArrayDeque<>();
        boolean[][] visitado = new boolean[filas][columnas];
        int[] padre = new int[filas * columnas];
        Arrays.fill(padre, -1);

        cola.add(inicio);
        visitado[inicio / columnas][inicio % columnas] = true;

        while (!cola.isEmpty()) {
            int actual = cola.poll();
            if (actual == fin) break;

            int fila = actual / columnas;
            int columna = actual % columnas;

            for (int d = 0; d < 4; d++) {
                int nuevaFila = fila + DR[d];
                int nuevaColumna = columna + DC[d];

                if (nuevaFila < 0 || nuevaFila >= filas ||
                    nuevaColumna < 0 || nuevaColumna >= columnas) continue;
                if (laberinto[nuevaFila][nuevaColumna] == '1') continue;
                if (visitado[nuevaFila][nuevaColumna]) continue;

                int siguiente = nuevaFila * columnas + nuevaColumna;
                visitado[nuevaFila][nuevaColumna] = true;
                padre[siguiente] = actual;
                cola.add(siguiente);
            }
        }

        if (!visitado[fin / columnas][fin % columnas]) {
            return Collections.emptyList();
        }

        List<int[]> camino = new ArrayList<>();
        int actual = fin;
        while (actual != -1) {
            camino.add(new int[]{actual / columnas, actual % columnas});
            if (actual == inicio) break;
            actual = padre[actual];
        }

        Collections.reverse(camino);
        return camino;
    }
}