package co.ntier.spring.boot.archetype.web.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ntier.spring.boot.archetype.web.model.Company;
import co.ntier.spring.boot.archetype.web.repo.CompanyRepository;

@RestController
@RequestMapping("/companies")
public class PersonResourceController extends AbstractResourceController<Company, CompanyRepository>{

}
