package ApiAgenda.demoAgenda.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ApiAgenda.demoAgenda.Entity.Contacto_Entity;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto_Entity, Long>{
	
	//metodo para fitar contactos por categoria (familiar, otros, trabajo)
	/*
	//metodo para fitar contactos por categoria (familiar, otros, trabajo)
	@Query("SELECT c FROM view_contactos_por_categoria c WHERE c.categoria = ?1")
	public List<Contacto_Entity> findByCategoriaNombre(String nombreCategoria);

	-Esta consultya nevesita una interrfaz o una clase que se mapp3e con las vista 
	*/
	
	//para este metodfo utiliza consulta antiva de sql para evitar errorres y mapoeaos .
	//public List<Contacto_Entity> findByCategoriaNombre(String nombreCategoria);
	/*
	 @Query(value = "SELECT id_contacto, nombre, telefono, email, direccion, categoria FROM view_contactos_por_categoria WHERE nombre_categoria = ?", nativeQuery = true)
	    List<Contacto_Entity> findByCategoriaNombre(String nombreCategoria);*/
	    

	    @Query("SELECT c FROM Contacto_Entity c WHERE c.categoria.nombre = :nombreCategoria")
	    List<Contacto_Entity> findByCategoriaNombre(@Param("nombreCategoria") String nombreCategoria);

}
