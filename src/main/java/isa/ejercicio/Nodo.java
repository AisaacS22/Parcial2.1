package isa.ejercicio;

public class Nodo {
    private Telefono telefono;  // Dato almacenado en el nodo
    private Nodo siguiente;     // Referencia al siguiente nodo en la lista

    // Constructor
    public Nodo(Telefono telefono) {
        this.telefono = telefono;
        this.siguiente = null;  // Al inicio, el siguiente nodo es nulo
    }

    // Método Getter para el dato (Teléfono)
    public Telefono getTelefono() {
        return telefono;
    }

    // Método Setter para el dato (Teléfono)
    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
    }

    // Método Getter para el siguiente nodo
    public Nodo getSiguiente() {
        return siguiente;
    }

    // Método Setter para el siguiente nodo
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}
