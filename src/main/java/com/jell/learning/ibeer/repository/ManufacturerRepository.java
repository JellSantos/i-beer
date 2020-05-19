package com.jell.learning.ibeer.repository;

import com.jell.learning.ibeer.domain.manufacturer.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {}

