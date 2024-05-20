package ApiAgenda.demoAgenda.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ApiAgenda.demoAgenda.Entity.CategoriaContacto_Entity;
import ApiAgenda.demoAgenda.Entity.CustomErrorResponse;
import ApiAgenda.demoAgenda.Service.CategoriaContactoService;

@RestController
@RequestMapping("/api/v1/apiMyAgenda/categorias")
public class CategoriaContactoController {
	
	@Autowired
	private CategoriaContactoService cc_service;
	
	@GetMapping
	public ResponseEntity<?> getAllCategorias(){
		try {
		List<CategoriaContacto_Entity> categorias = cc_service.getAllCategorias();
		if(!categorias.isEmpty()) {
			return new ResponseEntity<>(categorias, HttpStatus.OK);
									
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
			
		} catch (Exception e) {
			// TODO: handle exception
		CustomErrorResponse errorReponse = new CustomErrorResponse("Ocurrio un Error Al Obetener LAS CAtegorias");
			return new ResponseEntity<>(errorReponse, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
}
