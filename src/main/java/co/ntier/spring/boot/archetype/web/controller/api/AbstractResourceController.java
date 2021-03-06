package co.ntier.spring.boot.archetype.web.controller.api;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class AbstractResourceController<T, R extends JpaRepository<T, Long>> {

	@Inject
	private R repo;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public List<T> getAll(){
		return repo.findAll();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public T getOne(@PathVariable Long id){
		return repo.findOne(id);
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public T postOne(@RequestBody T one){
		return repo.save(one);
	}

}
