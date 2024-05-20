package ApiAgenda.demoAgenda.Service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ApiAgenda.demoAgenda.Entity.Contacto_Entity;
import ApiAgenda.demoAgenda.Repository.ContactoRepository;
import ApiAgenda.demoAgenda.Service.ContactoService;

@Service
public class ContactoServiceImpl implements ContactoService{

	@Autowired
	private ContactoRepository c_repo;
	
	@Override
	public List<Contacto_Entity> getAllContactos() {
		// TODO Auto-generated method stub
		return c_repo.findAll();
	}

	@Override
	public Optional<Contacto_Entity> getContactoById(Long id) {
		// TODO Auto-generated method stub
		return c_repo.findById(id);
	}

	@Override
	public Contacto_Entity saveContacto(Contacto_Entity newContacto) {
		// TODO Auto-generated method stub
		return c_repo.save(newContacto);
	}

	@Override
	public void deleteContacto(Long id_contacto) {
		// TODO Auto-generated method stub
		c_repo.deleteById(id_contacto);
		
	}

	@Override
	public List<Contacto_Entity> getCategoriaByNombre(String nombreCategoria) {
		// TODO Auto-generated method stub
		return c_repo.findByCategoriaNombre(nombreCategoria);
	}
	
	

}
