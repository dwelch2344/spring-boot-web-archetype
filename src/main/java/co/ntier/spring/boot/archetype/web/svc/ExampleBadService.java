package co.ntier.spring.boot.archetype.web.svc;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import co.ntier.spring.boot.archetype.web.model.Person;

/**
 * This is an example of a bad service hitting the {@link EntityManager} directly 
 * (used to verify {@link Inject} is working with Hibernate). 
 * 
 * @author dave
 *
 */
@Service
public class ExampleBadService {
	
	@Inject
	private EntityManager em;
	
	public List<Person> getPeople(){
		List<Person> result = em.createQuery("from Person p", Person.class).getResultList();
		return result;
	}

}
