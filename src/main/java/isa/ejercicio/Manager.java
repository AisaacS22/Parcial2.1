package isa.ejercicio;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class Manager {
    private MongoCollection<Document> collection;

    public Manager(MongoCollection<Document> collection) {
        this.collection = collection;
    }

    // Método para crear un nuevo teléfono en la base de datos
    public void crearTelefono(Telefono telefono) {
        Document telefonoDoc = new Document()
                .append("marca", telefono.getMarca())
                .append("modelo", telefono.getModelo())
                .append("sistemaOperativo", telefono.getSistemaOperativo())
                .append("tamanoPantalla", telefono.getTamanoPantalla())
                .append("memoriaRAM", telefono.getMemoriaRAM())
                .append("almacenamientoInterno", telefono.getAlmacenamientoInterno())
                .append("tieneCamara", telefono.isTieneCamara())
                .append("resolucionCamara", telefono.getResolucionCamara())
                .append("esSmartphone", telefono.isEsSmartphone())
                .append("imei", telefono.getImei());

        collection.insertOne(telefonoDoc);
    }

    // Método para leer todos los teléfonos de la base de datos y almacenarlos en una lista
    public List<Telefono> leerTelefonos() {
        List<Telefono> telefonos = new ArrayList<>();

        MongoCursor<Document> cursor = collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                Telefono telefono = new Telefono(
                        doc.getString("marca"),
                        doc.getString("modelo"),
                        doc.getString("sistemaOperativo"),
                        doc.getDouble("tamanoPantalla"),
                        doc.getInteger("memoriaRAM"),
                        doc.getInteger("almacenamientoInterno"),
                        doc.getBoolean("tieneCamara"),
                        doc.getInteger("resolucionCamara"),
                        doc.getBoolean("esSmartphone"),
                        doc.getString("imei")
                );
                telefonos.add(telefono);
            }
        } finally {
            cursor.close();
        }

        return telefonos;
    }

    // Método para actualizar un teléfono en la base de datos
    public void actualizarTelefono(String imei, Telefono nuevoTelefono) {
        Document filtro = new Document("imei", imei);
        Document updateDoc = new Document()
                .append("$set", new Document()
                        .append("marca", nuevoTelefono.getMarca())
                        .append("modelo", nuevoTelefono.getModelo())
                        .append("sistemaOperativo", nuevoTelefono.getSistemaOperativo())
                        .append("tamanoPantalla", nuevoTelefono.getTamanoPantalla())
                        .append("memoriaRAM", nuevoTelefono.getMemoriaRAM())
                        .append("almacenamientoInterno", nuevoTelefono.getAlmacenamientoInterno())
                        .append("tieneCamara", nuevoTelefono.isTieneCamara())
                        .append("resolucionCamara", nuevoTelefono.getResolucionCamara())
                        .append("esSmartphone", nuevoTelefono.isEsSmartphone())
                        .append("imei", nuevoTelefono.getImei()));

        collection.updateOne(filtro, updateDoc);
    }

    // Método para eliminar un teléfono de la base de datos
    public void eliminarTelefono(String imei) {
        Document filtro = new Document("imei", imei);
        collection.deleteOne(filtro);
    }
}
