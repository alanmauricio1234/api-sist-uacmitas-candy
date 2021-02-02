package mx.edu.uacm.dominio.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.edu.uacm.dominio.persistencia.Categoria;

@Repository
public interface RepositorioCategoria extends JpaRepository<Categoria, Long>{

}
