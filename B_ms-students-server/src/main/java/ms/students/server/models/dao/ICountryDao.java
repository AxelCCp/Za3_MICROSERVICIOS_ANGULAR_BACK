package ms.students.server.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import ms.students.commons.models.entity.Country;

public interface ICountryDao extends PagingAndSortingRepository<Country, Long>{

}
