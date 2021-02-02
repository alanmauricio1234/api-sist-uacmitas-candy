package mx.edu.uacm.dominio.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.uacm.dominio.persistencia.Dulce;
import mx.edu.uacm.dominio.repositorio.RepositorioDulce;

@Service
public class ServicioDulceImp implements ServicioDulce {
	@Autowired
	private RepositorioDulce repo;
	@Override
	public List<Dulce> obtenerDulces() {
		return repo.findAll();
	}

	@Override
	public Dulce getDulce(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Dulce crearDulce(Dulce dulce) {
		Dulce db = repo.findByNombre(dulce.getNombre()).orElse(null);
		if (db == null) {
			db = repo.save(dulce);
		}
		return db;
	}

	@Override
	public Dulce actualizarDulce(Dulce dulce) {
		Dulce d = null;
		Dulce dulceDB = getDulce(dulce.getId());
		if (dulceDB != null) {
			dulceDB.setNombre(dulce.getNombre());
			dulceDB.setCantidad(dulce.getCantidad());
			dulceDB.setCategoria(dulce.getCategoria());
			dulceDB.setDescripcion(dulce.getDescripcion());
			dulceDB.setImagen(dulce.getImagen());
			dulceDB.setMarca(dulce.getMarca());
			dulceDB.setPrecio(dulce.getPrecio());
			dulceDB.setDescuento(dulce.getDescuento());
			d = repo.save(dulceDB);
		}
		return d;
	}

	@Override
	public Dulce eliminarDulce(Long id) {
		Dulce d = getDulce(id);
		if (d != null) {
			repo.delete(d);
		}
		return d;
	}

	@Override
	public Dulce actualzarStock(Long id, Integer cantidad) {
		Dulce d = getDulce(id);
		if (d != null) {
			Integer stock = d.getCantidad() + cantidad;
			d.setCantidad(stock);
			repo.save(d);
		}
		return d;
	}

}
