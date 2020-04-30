package com.jell.learning.ibeer.repository;

import com.jell.learning.ibeer.domain.Beer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends CrudRepository<Beer, Long> {}
