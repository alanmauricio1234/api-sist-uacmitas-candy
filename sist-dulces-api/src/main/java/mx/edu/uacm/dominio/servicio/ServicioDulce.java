package mx.edu.uacm.dominio.servicio;

import java.util.List;

import mx.edu.uacm.dominio.persistencia.Dulce;

public interface ServicioDulce {
	public List<Dulce> obtenerDulces();
	public Dulce getDulce(Long id);
	public Dulce crearDulce(Dulce dulce);
	public Dulce actualizarDulce(Dulce dulce);
	public Dulce eliminarDulce(Long id);
	public Dulce actualzarStock(Long id, Integer cantidad);
	
}
