package ApiAgenda.demoAgenda.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "contacto")
public class Contacto_Entity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_contacto; 
	
	private String nombre;
	private String telefono;
	private String email;
	private String direccion;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_categoria")
	private CategoriaContacto_Entity categoria;

	public Contacto_Entity(Long id_contacto, String nombre, String telefono, String email, String direccion,
			CategoriaContacto_Entity categoria) {
		this.id_contacto = id_contacto;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
		this.categoria = categoria;
	}
	public Contacto_Entity() {}
	public Long getId_contacto() {
		return id_contacto;
	}
	public void setId_contacto(Long id_contacto) {
		this.id_contacto = id_contacto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public CategoriaContacto_Entity getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaContacto_Entity categoria) {
		this.categoria = categoria;
	}
	
}
