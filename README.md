# Tabla de Símbolos Ordenada - Listas Enlazadas

Este proyecto implementa una **Tabla de Símbolos (ST)** utilizando una estructura de **lista lineal encadenada y ordenada**. El objetivo es gestionar pares llave-valor manteniendo las llaves en un orden constante para permitir búsquedas eficientes de rangos y extremos.

## 🚀 Características del API

La clase `ST<Key, Value>` ofrece un conjunto completo de operaciones basadas en el orden de las llaves:

* **Básicas:** `put()`, `get()`, `delete()`, `contains()`, `size()`.
* **De Orden:** `min()`, `max()`, `floor()` (mayor llave ≤ k), `ceiling()` (menor llave ≥ k).
* **Estadísticas:** `rank()` (número de llaves < k) y `select()` (llave en posición k).
* **Rangos:** `keys(lo, hi)` para obtener llaves en un intervalo específico.

## 🧠 Conceptos Técnicos Aplicados

### 1. Genéricos y Contravarianza
Se utiliza la firma `Key extends Comparable<? super Key>` para asegurar que:
1. Las llaves se puedan comparar entre sí (orden alfabético/numérico).
2. Sea compatible con jerarquías de clases donde el método de comparación esté definido en una superclase.

### 2. Ordenamiento en Tiempo Real
A diferencia de una lista común, la inserción (`put`) se realiza de forma ordenada. El sistema utiliza el método `compareTo()` para encontrar la posición exacta de cada elemento antes de insertarlo.



## 📊 Análisis de Complejidad

Al utilizar una lista enlazada, el rendimiento se define de la siguiente manera:

| Operación | Complejidad | Razón |
| :--- | :--- | :--- |
| **Búsqueda (get)** | $O(n)$ | Recorrido secuencial hasta encontrar la llave. |
| **Inserción (put)** | $O(n)$ | Debe recorrer la lista para mantener el orden. |
| **Eliminación** | $O(n)$ | Búsqueda y reconexión de nodos. |
| **Mínimo** | $O(1)$ | Acceso directo al primer nodo (`first`). |
| **Máximo** | $O(n)$ | Recorrido hasta el último nodo de la lista. |



## 🛠️ Requisitos
* Java JDK 8 o superior.
* Entorno de desarrollo (NetBeans, IntelliJ, Eclipse o VS Code).

## ✒️ Autor
* **Juan Santiago Vega** 
