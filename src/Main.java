/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author pingvii
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ST<String, Integer> tabla = new ST<>();

        // 1. Probar insercion (put)
        // Insertamos en desorden para verificar que la lista los ordene
        tabla.put("S", 1);
        tabla.put("E", 2);
        tabla.put("A", 3);
        tabla.put("R", 4);
        tabla.put("C", 5);
        tabla.put("H", 6);

        System.out.println("--- Contenido de la tabla (debe estar en orden alfabetico) ---");
        for (String s : tabla.keys()) {
            System.out.println(s + " " + tabla.get(s));
        }

        // 2. Probar metodos de orden
        System.out.println("\n=== Pruebas de Orden ===");
        System.out.println("Minimo (min): " + tabla.min()); // Debe ser A
        System.out.println("Maximo (max): " + tabla.max()); // Debe ser S
        System.out.println("Floor de 'G': " + tabla.floor("G")); // Debe ser E
        System.out.println("Ceiling de 'G': " + tabla.ceiling("G")); // Debe ser H
        System.out.println("Rank de 'H': " + tabla.rank("H")); // Cuantos hay antes de H (A, C, E = 3)
        System.out.println("Select(2): " + tabla.select(2)); // El tercero (indice 2): E

        // 3. Probar rangos
        System.out.println("\n=== Pruebas de Rangos [C..R] ===");
        System.out.println("Cantidad entre C y R: " + tabla.size("C", "R"));
        for (String s : tabla.keys("C", "R")) {
            System.out.print(s + " ");
        }

        // 4. Probar eliminacion
        System.out.println("\n\n=== Pruebas de Eliminacion ===");
        tabla.deleteMin();
        tabla.deleteMax();
        System.out.println("Despues de borrar min y max, nuevo min: " + tabla.min() + ", nuevo max: " + tabla.max());
    }

}
