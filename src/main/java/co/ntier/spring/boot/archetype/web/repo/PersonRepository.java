package co.ntier.spring.boot.archetype.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.ntier.spring.boot.archetype.web.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
	
}
