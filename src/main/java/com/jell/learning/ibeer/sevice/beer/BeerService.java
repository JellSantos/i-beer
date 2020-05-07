package com.jell.learning.ibeer.sevice.beer;

import com.jell.learning.ibeer.repository.BeerRepository;
import com.jell.learning.ibeer.sevice.beer.dto.BeerDTO;
import com.jell.learning.ibeer.sevice.beer.request.BeerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BeerService {

    private final BeerRepository repository;

    public BeerDTO create(BeerRequest beerRequest) {
        return null;
    }


}
