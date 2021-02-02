package mx.edu.uacm.dominio.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.edu.uacm.dominio.persistencia.Dulce;

@Repository
public interface RepositorioDulce extends JpaRepository<Dulce, Long>{
	public Optional<Dulce> findByNombre(String nombre);

}
