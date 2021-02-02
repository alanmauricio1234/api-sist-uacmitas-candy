package mx.edu.uacm.dominio.servicio;

import java.util.List;

import mx.edu.uacm.dominio.persistencia.Categoria;

public interface ServicioCategoria {
	public List<Categoria> obtenerCategorias();
}
