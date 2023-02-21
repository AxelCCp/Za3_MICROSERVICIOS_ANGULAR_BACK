package ms.students.server.models.dao;

import org.springframework.data.repository.CrudRepository;

import ms.students.commons.models.entity.Country;

public interface ICountryDao extends CrudRepository<Country, Long>{

}
