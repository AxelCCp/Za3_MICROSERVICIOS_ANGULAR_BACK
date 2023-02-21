package ms.students.server.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ms.commons.service.rest.server.controllers.CommonRestController;
import ms.students.commons.models.entity.Country;
import ms.students.server.models.services.ICountryService;


@RestController
@RequestMapping("/co")
public class CountryController extends CommonRestController<Country,ICountryService>{
	
	@PutMapping("/{id}")
	public ResponseEntity<?>update(@RequestBody Country country, @PathVariable Long id){
		Optional<Country> optionalCountry  = service.getById(id);
		if(optionalCountry.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Country  countryDB = optionalCountry.get();
		countryDB.setName(country.getName());
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(countryDB));
	}
	
}
