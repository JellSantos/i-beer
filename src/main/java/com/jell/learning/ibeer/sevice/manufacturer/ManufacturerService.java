package com.jell.learning.ibeer.sevice.manufacturer;

import com.jell.learning.ibeer.repository.ManufacturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ManufacturerService {
    private final ManufacturerRepository repository;
}
