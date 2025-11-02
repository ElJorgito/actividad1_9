package es.cifpcarlos3.actividad1_9;

import es.cifpcarlos3.actividad1_9.vo.Cliente;
import es.cifpcarlos3.actividad1_9.vo.ListaClientes;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class GestorClientes {
    public static void main(String[] args) {
        Path base = Path.of("src","main","java","es","cifpcarlos3","actividad1_9");
        Path XML = base.resolve("clientes.xml");

        var xmlMapper = XmlMapper.builder().enable(DeserializationFeature.UNWRAP_ROOT_VALUE).build();
        try (var br = Files.newBufferedReader(XML, StandardCharsets.UTF_8)) {
            ListaClientes datos = xmlMapper.readValue(br, ListaClientes.class);
            for (Cliente cliente : datos.clientes) {
                if (cliente.getId() == 0 || cliente.getNombre() == null) {
                    System.out.println("Cliente no valido");
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
