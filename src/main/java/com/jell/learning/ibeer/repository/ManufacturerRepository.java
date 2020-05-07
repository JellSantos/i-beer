package com.jell.learning.ibeer.repository;

import com.jell.learning.ibeer.domain.beer.Beer;
import com.jell.learning.ibeer.domain.manufacturer.Manufacturer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends CrudRepository<Manufacturer, Long> {}
