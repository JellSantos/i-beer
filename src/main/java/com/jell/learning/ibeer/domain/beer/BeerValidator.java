package com.jell.learning.ibeer.domain.beer;

import com.jell.learning.ibeer.interfaces.incomming.beer.dto.BeerDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

import static java.util.Objects.nonNull;

@Slf4j
public class BeerValidator implements Consumer<BeerDTO> {

    @Override
    public void accept(BeerDTO dto) {
        if (nonNull(dto)) {
            final var e = new BeerNameDuplicatedException("Name Duplicated!");
            log.error("m=accept, status=error, message={}", e.getMessage(), e);
            throw e;
        }
    }
}
