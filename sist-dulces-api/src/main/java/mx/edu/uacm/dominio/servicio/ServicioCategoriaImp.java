package mx.edu.uacm.dominio.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.uacm.dominio.persistencia.Categoria;
import mx.edu.uacm.dominio.repositorio.RepositorioCategoria;

@Service
public class ServicioCategoriaImp implements ServicioCategoria {
	@Autowired
	private RepositorioCategoria repo;
	@Override
	public List<Categoria> obtenerCategorias() {
		return repo.findAll();
	}

}
