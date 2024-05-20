package ApiAgenda.demoAgenda.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ApiAgenda.demoAgenda.Entity.Contacto_Entity;
import ApiAgenda.demoAgenda.Entity.CustomErrorResponse;
import ApiAgenda.demoAgenda.Service.ContactoService;

@RestController
@RequestMapping("/api/v1/apiMyAgenda/contactos")
public class ContactoController {
	
	@Autowired
	private ContactoService c_service;
	
	
	@GetMapping
	public ResponseEntity<List<Contacto_Entity>> getAllConctactos() {
		List<Contacto_Entity> contactos = c_service.getAllContactos();
		return new ResponseEntity<>(contactos, HttpStatus.OK);	
	}
	
	@PostMapping
	public ResponseEntity<Contacto_Entity >saveContacto(@RequestBody Contacto_Entity contacto){
		Contacto_Entity newContacto= c_service.saveContacto(contacto);
		return new ResponseEntity<>(newContacto, HttpStatus.OK);
	}
	
	@GetMapping("/{idContacto}")
	public ResponseEntity<Contacto_Entity >getContactoById(@PathVariable Long idContacto){
		if(idContacto != null) {
			Contacto_Entity contacto= c_service.getContactoById(idContacto).orElse(null);
			return new ResponseEntity<>(contacto, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(value = "/{idContacto}")
	public ResponseEntity<Void > deleteContacto(@PathVariable Long idContacto){
		c_service.deleteContacto(idContacto);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "/categoria/{nombre}")
	public ResponseEntity<?> getAllConctactosByCategoria(@PathVariable String nombre ) {
		if(nombre != null) {
			List<Contacto_Entity> contactos = c_service.getCategoriaByNombre(nombre);
			if(contactos == null || contactos.isEmpty()) {
				CustomErrorResponse errResponse = new CustomErrorResponse("No Hay Considencias :(");
				return new ResponseEntity<>( errResponse, HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(contactos, HttpStatus.OK);
		}else {
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
}

