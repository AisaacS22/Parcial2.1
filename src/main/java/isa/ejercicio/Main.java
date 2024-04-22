package isa.ejercicio;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Establecer conexión con la base de datos MongoDB
        String connectionString = "mongodb://localhost:27017";
        try (var mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("miBaseDeDatos");
            MongoCollection<Document> collection = database.getCollection("telefonos");

            // Crear instancia de TelefonoManager
            Manager manager = new Manager(collection);

            // Crear algunos teléfonos y almacenarlos en la base de datos
            Telefono telefono1 = new Telefono("Samsung", "Galaxy S21", "Android", 6.2,
                    8, 128, true, 64, true, "123456789012345");
            Telefono telefono2 = new Telefono("Apple", "iPhone 13", "iOS", 6.1,
                    4, 256, true, 48, true, "987654321098765");

            manager.crearTelefono(telefono1);
            manager.crearTelefono(telefono2);

            // Leer todos los teléfonos almacenados en la base de datos
            List<Telefono> telefonos = manager.leerTelefonos();
            for (Telefono telefono : telefonos) {
                System.out.println("Marca: " + telefono.getMarca() + ", Modelo: " + telefono.getModelo());
            }

            // Actualizar un teléfono en la base de datos
            Telefono nuevoTelefono1 = new Telefono("Samsung", "Galaxy S22", "Android", 6.5,
                    12, 256, true, 108, true, "123456789012345");
            manager.actualizarTelefono("123456789012345", nuevoTelefono1);

            // Eliminar un teléfono de la base de datos
            manager.eliminarTelefono("987654321098765");
        } catch (Exception e) {
            System.err.println("Error al conectar con la base de datos MongoDB: " + e.getMessage());
        }
    }
}
