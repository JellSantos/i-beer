package com.jell.learning.ibeer.service.beer;

import com.jell.learning.ibeer.domain.beer.Beer;
import com.jell.learning.ibeer.repository.BeerRepository;
import com.jell.learning.ibeer.sevice.beer.BeerService;
import com.jell.learning.ibeer.sevice.beer.dto.BeerDTO;
import com.jell.learning.ibeer.sevice.beer.mapper.BeerMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BeerServiceTest {

    @Mock
    private BeerRepository repository;
    @Mock
    private BeerMapper mapper;

    @InjectMocks
    private BeerService service;

    @Test
    public void shouldReturnAllBeersSuccessfully_whenGetAllIsCalled() {
        final var beer = Beer.builder().build();
        final var beers = new PageImpl<>(Collections.singletonList(beer));
        final var beerDTO = BeerDTO.builder().build();
        final var pageRequest = PageRequest.of(1, 1);

        Mockito.when(repository.findAll(pageRequest)).thenReturn(beers);
        Mockito.when(mapper.toDTO(beer)).thenReturn(beerDTO);

        final var response = service.getAll(pageRequest);

        assertThat(response.getContent(), hasItem(beerDTO));
        verify(repository).findAll(pageRequest);
        verify(mapper).toDTO(beer);
    }

    @Test
    public void shouldReturnABeersSuccessfully_whenGetByIdBeerIsCalled() {
        final var beer = Beer.builder().build();
        final var beerDTO = BeerDTO.builder().build();
        final long id = 50;

        Mockito.when(repository.getOne(id)).thenReturn(beer);
        Mockito.when(mapper.toDTO(beer)).thenReturn(beerDTO);

        final var response = service.getById(id);

        assertThat(response, is(beerDTO));
        verify(repository).getOne(id);
        verify(mapper).toDTO(beer);
    }
}
