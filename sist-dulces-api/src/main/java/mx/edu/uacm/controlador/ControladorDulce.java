package mx.edu.uacm.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.edu.uacm.dominio.persistencia.Categoria;
import mx.edu.uacm.dominio.persistencia.Dulce;
import mx.edu.uacm.dominio.servicio.ServicioCategoria;
import mx.edu.uacm.dominio.servicio.ServicioDulce;

@RestController
@RequestMapping(value = "/api")
public class ControladorDulce {
	@Autowired
	private ServicioDulce servicioDulce;
	@Autowired
	private ServicioCategoria servicioCategoria;
	
	@GetMapping(value = "/dulces")
	public ResponseEntity<List<Dulce>> listaDulces() {
		List<Dulce> dulces = servicioDulce.obtenerDulces();
		if (dulces.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(dulces);
	}
	
	@GetMapping(value = "/dulces/categorias")
	public ResponseEntity<List<Categoria>> obtenerCategoria() {
		List<Categoria> categorias = servicioCategoria.obtenerCategorias();
		if (categorias.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(categorias);
	}
	
	@GetMapping(value = "/dulces/{id}")
	public ResponseEntity<Dulce> getDulce(@PathVariable("id") Long id) {
		Dulce d = servicioDulce.getDulce(id);
		if (d == null) {
			return ResponseEntity.notFound().build();
		} 
		return ResponseEntity.ok(d);
	}
	
	@PostMapping(value = "/dulces")
	public ResponseEntity<Dulce> crearDulce(@Valid @RequestBody Dulce dulce, 
			BindingResult result) {
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatoMensaje(result));
		}
		Dulce dulceCreado = servicioDulce.crearDulce(dulce);
		return ResponseEntity.status(HttpStatus.CREATED).body(dulceCreado);
	}
	
	@PutMapping(value = "/dulces/{id}")
	public ResponseEntity<Dulce> actualizarProducto(@PathVariable("id") Long id, 
			@Valid @RequestBody Dulce dulce, BindingResult result) {
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatoMensaje(result));
		}
		dulce.setId(id);
		Dulce dulceDb = servicioDulce.actualizarDulce(dulce);
		if (dulceDb == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(dulceDb);
	}
	
	@DeleteMapping(value = "/dulces/{id}")
	public ResponseEntity<Dulce> eliminarDulce(@PathVariable("id") Long id) {
		Dulce dulceEliminado = servicioDulce.eliminarDulce(id);
		if (dulceEliminado == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(dulceEliminado);
	}
	
	@GetMapping(value = "/dulces/{id}/{stock}")
	public ResponseEntity<Dulce> actualizarStock(@PathVariable("id") Long id,
			@RequestParam(value = "cantidad", required = true) Integer cantidad) {
		Dulce dulce = servicioDulce.actualzarStock(id, cantidad);
		if (dulce == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(dulce);
	}
	
	
	
	
	private String formatoMensaje(BindingResult result) {
		List< Map<String, String> > errores = result.getFieldErrors().stream()
				.map(err -> {
					Map<String, String> error = new HashMap<>();
					error.put(err.getField(), err.getDefaultMessage());
					return error;
				}).collect(Collectors.toList());
		
		ErrorMensaje errorMensaje = ErrorMensaje.builder()
				.codigo("01")
				.mensajes(errores).build();
		//Lo pasamos a formato JSON
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(errorMensaje);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return jsonString;
	}
	
	

}
