package ApiAgenda.demoAgenda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ApiAgenda.demoAgenda.Entity.CategoriaContacto_Entity;


@Repository
public interface CategoriaContactoRepository extends JpaRepository<CategoriaContacto_Entity, Long>{

}
