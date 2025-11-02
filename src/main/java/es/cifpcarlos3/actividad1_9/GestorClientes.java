package es.cifpcarlos3.actividad1_9;

import es.cifpcarlos3.actividad1_9.vo.Cliente;
import es.cifpcarlos3.actividad1_9.vo.ListaClientes;
import es.cifpcarlos3.actividad1_9.vo.Sucursal;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GestorClientes {
    public static void main(String[] args) {
        Path base = Path.of("src","main","java","es","cifpcarlos3","actividad1_9");
        Path XML = base.resolve("clientes.xml");

        var xmlMapper = new XmlMapper();
        try (var br = Files.newBufferedReader(XML, StandardCharsets.UTF_8)) {
            ListaClientes datos = xmlMapper.readValue(br, ListaClientes.class);
            validarClientes(datos);
            mostrarClientes(datos);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void validarClientes(ListaClientes datos){
        for (Cliente cliente : datos.getClientes()) {
            if (cliente.getNombre() == null) {
                System.out.println("Cliente no valido");
                System.exit(0);
            }
            List<Sucursal> sucursales = cliente.getSucursal();
            if (sucursales == null || sucursales.isEmpty()) {
                System.err.println("Error: Cliente sin sucursales.");
                System.exit(0);
            }
            for (Sucursal sucurse : sucursales) {
                if (sucurse.getCalle() == null || sucurse.getCiudad() == null ) {
                    System.err.println("Cliente no valido.");
                    System.exit(0);
                }
            }
        }
    }

    public static void mostrarClientes(ListaClientes datos){
        for (Cliente cliente : datos.getClientes()) {
            System.out.println("Cliente: " + cliente.getNombre() + "(id:" + cliente.getId() + ")");
            System.out.println("Sucursales: (" + datos.getClientes().size() + ")");
            for (Sucursal sucursal : cliente.getSucursal()) {
                System.out.println("· " + sucursal.getCalle() + ", " + sucursal.getCiudad()
                + " [" + sucursal.getProvincia() + "] - CP " + sucursal.getCp());
            }
        }
    }
}
