package com.jell.learning.ibeer.sevice.beer.validator;

import com.jell.learning.ibeer.sevice.beer.dto.BeerDTO;
import com.jell.learning.ibeer.sevice.beer.exception.BeerNameDuplicatedException;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.function.Consumer;

@Slf4j
public class BeerValidator implements Consumer<BeerDTO> {

    @Override
    public void accept(BeerDTO dto) {
        if (!Objects.isNull(dto)) {
            final var e = new BeerNameDuplicatedException("Name Duplicated!");
            log.error("m=accept, status=error, message={}", e.getMessage(), e);
            throw e;
        }
    }
}
