import java.util.ArrayDeque;

/**
 * Ejercicio 2: Contador de islas
 *
 * <p>Se recibe una matriz donde 1 representa tierra y 0 representa
 * agua. Una isla está formada por celdas de tierra conectadas
 * horizontal o verticalmente.
 *
 * <p>Se resuelve con DFS (Depth-First Search) de forma iterativa,
 * usando una pila. Al encontrar una celda de tierra, sabemos que
 * pertenece a una nueva isla; DFS permite recorrer todas las celdas
 * conectadas a ella antes de continuar buscando otra. Se usa una
 * pila en lugar de recursión para cumplir el requisito de
 * iteratividad.
 *
 * <p>Optimización: las celdas visitadas se convierten directamente
 * de 1 a 0, por lo que no es necesario crear una matriz adicional
 * para registrar posiciones visitadas.
 *
 * <p>Complejidad: tiempo O(F × C), espacio O(F × C),
 * donde F = filas y C = columnas.
 */
public class ContadorIslas {

    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    public static int contarIslas(int[][] mapa) {
        int filas = mapa.length;
        int columnas = mapa[0].length;

        int islas = 0;

        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                if (mapa[f][c] == 1) {
                    islas++;
                    visitarIsla(mapa, f, c);
                }
            }
        }

        return islas;
    }

    private static void visitarIsla(int[][] mapa, int filaInicial, int columnaInicial) {
        int filas = mapa.length;
        int columnas = mapa[0].length;

        ArrayDeque<Integer> pila = new ArrayDeque<>();
        pila.push(filaInicial * columnas + columnaInicial);
        mapa[filaInicial][columnaInicial] = 0; // marcamos al agregar, no al procesar

        while (!pila.isEmpty()) {
            int actual = pila.pop();

            int fila = actual / columnas;
            int columna = actual % columnas;

            for (int d = 0; d < 4; d++) {
                int nuevaFila = fila + DR[d];
                int nuevaColumna = columna + DC[d];

                if (nuevaFila < 0 || nuevaFila >= filas ||
                    nuevaColumna < 0 || nuevaColumna >= columnas) continue;
                if (mapa[nuevaFila][nuevaColumna] != 1) continue;

                mapa[nuevaFila][nuevaColumna] = 0;
                pila.push(nuevaFila * columnas + nuevaColumna);
            }
        }
    }
}