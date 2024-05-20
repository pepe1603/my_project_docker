package ApiAgenda.demoAgenda.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ApiAgenda.demoAgenda.Entity.CategoriaContacto_Entity;
import ApiAgenda.demoAgenda.Repository.CategoriaContactoRepository;
import ApiAgenda.demoAgenda.Service.CategoriaContactoService;

@Service
public class CategoriaContactoServiceImpl implements CategoriaContactoService{

	@Autowired
	private CategoriaContactoRepository cc_repo; 
	
	@Override
	public List<CategoriaContacto_Entity> getAllCategorias() {
		// TODO Auto-generated method stub
		return cc_repo.findAll();
	}
	

}
