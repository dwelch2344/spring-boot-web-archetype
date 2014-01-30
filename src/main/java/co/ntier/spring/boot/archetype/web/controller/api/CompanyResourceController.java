package co.ntier.spring.boot.archetype.web.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ntier.spring.boot.archetype.web.model.Person;
import co.ntier.spring.boot.archetype.web.repo.PersonRepository;

@RestController
@RequestMapping("/people")
public class CompanyResourceController extends AbstractResourceController<Person, PersonRepository>{

}
