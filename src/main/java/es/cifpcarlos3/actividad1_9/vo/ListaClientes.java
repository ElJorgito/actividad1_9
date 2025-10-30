package es.cifpcarlos3.actividad1_9.vo;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;
import tools.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import tools.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;

@Data
@JsonRootName("clientes")

public class ListaClientes {
    @JacksonXmlProperty(localName = "cliente")
    @JacksonXmlElementWrapper(useWrapping = false)
    public List<Cliente> clientes;
}
