package ApiAgenda.demoAgenda.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria_contacto")
public class CategoriaContacto_Entity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_categoria;
	
	private String nombre;

	public CategoriaContacto_Entity(Long id_categoria, String nombre) {
		this.id_categoria = id_categoria;
		this.nombre = nombre;
	}
	
	
	public CategoriaContacto_Entity() {}
	public Long getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(Long id_categoria) {
		this.id_categoria = id_categoria;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
