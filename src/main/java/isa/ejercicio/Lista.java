package isa.ejercicio;

public class Lista {
    private Nodo cabeza;  // Puntero al primer nodo de la lista

    // Constructor
    public Lista() {
        this.cabeza = null;  // Al inicio, la lista está vacía
    }

    // Método para verificar si la lista está vacía
    public boolean estaVacia() {
        return cabeza == null;
    }

    // Método para insertar un nuevo teléfono al inicio de la lista
    public void insertarAlInicio(Telefono telefono) {
        Nodo nuevoNodo = new Nodo(telefono);
        nuevoNodo.setSiguiente(cabeza);  // El siguiente nodo del nuevo nodo es la cabeza actual
        cabeza = nuevoNodo;  // El nuevo nodo se convierte en la nueva cabeza
    }

    // Método para insertar un nuevo teléfono al final de la lista
    public void insertarAlFinal(Telefono telefono) {
        Nodo nuevoNodo = new Nodo(telefono);
        if (cabeza == null) {
            cabeza = nuevoNodo;  // Si la lista está vacía, el nuevo nodo se convierte en la cabeza
        } else {
            Nodo actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);  // Enlazar el nuevo nodo al final de la lista
        }
    }

    // Método para insertar un nuevo teléfono en una posición específica
    public void insertarEnPosicion(Telefono telefono, int posicion) {
        if (posicion < 0) {
            System.out.println("La posición no puede ser negativa.");
            return;
        }

        Nodo nuevoNodo = new Nodo(telefono);

        if (posicion == 0) {
            nuevoNodo.setSiguiente(cabeza);
            cabeza = nuevoNodo;
            return;
        }

        Nodo actual = cabeza;
        int contador = 0;

        while (actual != null && contador < posicion - 1) {
            actual = actual.getSiguiente();
            contador++;
        }

        if (actual == null) {
            System.out.println("La posición excede el tamaño de la lista.");
            return;
        }

        nuevoNodo.setSiguiente(actual.getSiguiente());
        actual.setSiguiente(nuevoNodo);
    }

    // Método para eliminar un teléfono de la lista
    public void eliminar(Telefono telefono) {
        if (cabeza == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        if (cabeza.getTelefono().equals(telefono)) {
            cabeza = cabeza.getSiguiente();  // Eliminar el primer nodo si coincide con el teléfono
            return;
        }

        Nodo actual = cabeza;
        while (actual.getSiguiente() != null && !actual.getSiguiente().getTelefono().equals(telefono)) {
            actual = actual.getSiguiente();
        }

        if (actual.getSiguiente() == null) {
            System.out.println("El teléfono no se encontró en la lista.");
        } else {
            actual.setSiguiente(actual.getSiguiente().getSiguiente());  // Eliminar el nodo que contiene el teléfono
        }
    }

    // Método para buscar un teléfono en la lista
    public boolean buscar(Telefono telefono) {
        Nodo actual = cabeza;
        while (actual != null) {
            if (actual.getTelefono().equals(telefono)) {
                return true;  // Teléfono encontrado en la lista
            }
            actual = actual.getSiguiente();
        }
        return false;  // Teléfono no encontrado en la lista
    }

    // Método para obtener el tamaño (cantidad de elementos) de la lista
    public int obtenerTamano() {
        int tamano = 0;
        Nodo actual = cabeza;
        while (actual != null) {
            tamano++;
            actual = actual.getSiguiente();
        }
        return tamano;
    }

    // Método para mostrar todos los teléfonos en la lista
    public void mostrarLista() {
        Nodo actual = cabeza;
        while (actual != null) {
            System.out.println(actual.getTelefono());  // Imprimir el teléfono almacenado en el nodo
            actual = actual.getSiguiente();
        }
    }
}
