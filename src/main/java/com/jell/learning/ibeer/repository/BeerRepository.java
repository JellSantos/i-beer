package com.jell.learning.ibeer.repository;

import com.jell.learning.ibeer.domain.beer.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends JpaRepository<Beer, Long> {}
