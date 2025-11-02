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

        var xmlMapper = XmlMapper.builder().enable(DeserializationFeature.UNWRAP_ROOT_VALUE).build();
        try (var br = Files.newBufferedReader(XML, StandardCharsets.UTF_8)) {
            ListaClientes datos = xmlMapper.readValue(br, ListaClientes.class);
            validarClientes(datos);
            mostrarClientes(datos);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void validarClientes(ListaClientes datos){
        for (Cliente cliente : datos.clientes) {
            if (cliente.getId() == 0 || cliente.getNombre() == null) {
                System.out.println("Cliente no valido");
                System.exit(0);
            }
            List<Sucursal> sucursales = cliente.getSucursal();
            if (sucursales == null || sucursales.isEmpty()) {
                System.err.println("Error: Cliente sin sucursales.");
                System.exit(0);
            }
            for (Sucursal sucurse : sucursales) {
                if (sucurse.getCalle() == null || sucurse.getCp() == null ||
                    sucurse.getCiudad() == null || sucurse.getProvincia() == null) {
                    System.err.println("Cliente no valido.");
                    System.exit(0);
                }
            }
        }
    }

    public static void mostrarClientes(ListaClientes datos){

    }
}
