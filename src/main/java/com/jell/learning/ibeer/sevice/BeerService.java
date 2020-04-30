package com.jell.learning.ibeer.sevice;

import com.jell.learning.ibeer.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BeerService {

    private final BeerRepository repository;
}
