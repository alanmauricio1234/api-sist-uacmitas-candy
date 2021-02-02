package mx.edu.uacm.dominio.persistencia;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dulces")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
@SuppressWarnings(value = "serial")
public class Dulce implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message = "El nombre no debe ser vacio")
	private String nombre;
	@NotEmpty(message = "La marca no debe ser vacia")
	private String marca;
	@Positive(message = "El stock debe ser mayor que cero")
	private Integer cantidad;
	@NotEmpty(message = "La descripcion no debe ser vacia")
	private String descripcion;
	@NotEmpty(message = "La imagen no debe ser vacia")
	private String imagen;
	
	@NotNull(message = "El descuento es un campo requerido")
	@Min(value = 0, message = "El descuento mínimo es 0")
	@Max(value = 70, message = "El descuento máximo es 100")
	private Double descuento;
	@NotNull(message = "El precio es un campo requerido requerido")
	@Min(value = 1, message = "El valor mínimo del precio es $1")
	private Double precio;
	@NotNull(message = "La categoria no debe ser vacia")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;
}
