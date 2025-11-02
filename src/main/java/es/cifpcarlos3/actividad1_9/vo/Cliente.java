package es.cifpcarlos3.actividad1_9.vo;

import lombok.Data;
import tools.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import tools.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;

@Data
public  class Cliente {
    @JacksonXmlProperty(isAttribute = true)
    private long id;
    private String nombre;

    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Sucursal> sucursal;
}
