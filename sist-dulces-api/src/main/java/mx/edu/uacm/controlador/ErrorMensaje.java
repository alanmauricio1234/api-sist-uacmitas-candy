package mx.edu.uacm.controlador;

import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class ErrorMensaje {
	private String codigo;
	private List< Map<String, String> > mensajes;
}
