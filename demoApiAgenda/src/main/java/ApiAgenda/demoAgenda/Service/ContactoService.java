package ApiAgenda.demoAgenda.Service;

import java.util.List;
import java.util.Optional;

import ApiAgenda.demoAgenda.Entity.Contacto_Entity;

public interface ContactoService {
	List<Contacto_Entity> getAllContactos();
	Optional<Contacto_Entity> getContactoById(Long id);
	Contacto_Entity saveContacto(Contacto_Entity newContacto);
	void deleteContacto(Long id_contacto);
	List<Contacto_Entity> getCategoriaByNombre(String nombreCategoria);
	
	
}
