package co.ntier.spring.boot.archetype.web.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.ntier.spring.boot.archetype.web.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
	
}
