package com.jell.learning.ibeer.service.beer;

import com.jell.learning.ibeer.domain.beer.Beer;
import com.jell.learning.ibeer.repository.BeerRepository;
import com.jell.learning.ibeer.sevice.beer.BeerService;
import com.jell.learning.ibeer.sevice.beer.dto.BeerDTO;
import com.jell.learning.ibeer.sevice.beer.mapper.BeerMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BeerServiceTest {

    @Mock
    private BeerRepository repository;

    @Mock
    private BeerMapper mapper;

    @InjectMocks
    private BeerService service;

    @Test
    void shouldSuccessfullyCreateABeer_whenCreateIsCalled() {
        final var beer = Beer.builder().name("Brahma").build();
        final var dto = BeerDTO.builder().name("Brahma").build();

        when(repository.findByName(beer.getName())).thenReturn(Optional.empty());
        when(mapper.toDTO(beer)).thenReturn(null);
        when(mapper.toEntity(dto)).thenReturn(beer);
        when(repository.save(beer)).thenReturn(beer);
        when(mapper.toDTO(beer)).thenReturn(dto);

        final var response = service.create(dto);

        assertSame(response, dto);
        verify(repository).findByName(beer.getName());
        verify(repository).save(beer);
        verify(mapper).toEntity(dto);
        verify(mapper).toDTO(beer);
    }

    @Test
    public void shouldReturnAllBeersSuccessfully_whenGetAllIsCalled() {
        final var beer = Beer.builder().build();
        final var dto = BeerDTO.builder().build();
        final var beers = new PageImpl<>(singletonList(beer));
        final var pageRequest = PageRequest.of(1, 1);

        when(repository.findAll(pageRequest)).thenReturn(beers);
        when(mapper.toDTO(beer)).thenReturn(dto);

        final var response = service.getAll(pageRequest);

        assertThat(response.getContent(), hasItem(dto));
        verify(repository).findAll(pageRequest);
        verify(mapper).toDTO(beer);
    }

    @Test
    public void shouldReturnABeerSuccessfully_whenGetByIdIsCalled() {
        final var id = 10L;
        final var beer = Beer.builder().build();
        final var dto = BeerDTO.builder().build();
        final var idCaptor = ArgumentCaptor.forClass(Long.class);

        when(repository.findById(anyLong())).thenReturn(Optional.of(beer));
        when(mapper.toDTO(beer)).thenReturn(dto);

        final var response = service.getById(id);

        assertSame(response, dto);
        verify(repository).findById(idCaptor.capture());
        assertSame(id, idCaptor.getValue());
        verify(mapper).toDTO(beer);
    }


    @Test
    void shouldEntityNotFoundException_whenGetByIdIsCalled() {
        final var id = 10L;
        final var expectedMessage = "Entity not found.";
        final var idCaptor = ArgumentCaptor.forClass(Long.class);

        when(repository.findById(anyLong())).thenReturn(Optional.empty());
        final var actualException = assertThrows(
                EntityNotFoundException.class,
                () -> service.getById(id)
        );

        assertThat(actualException, instanceOf(EntityNotFoundException.class));
        assertTrue(actualException.getMessage().contains(expectedMessage));
        verify(repository).findById(idCaptor.capture());
        assertSame(id, idCaptor.getValue());
    }

    @Test
    void shouldSuccessfullyDelete_whenDeleteByIdIsCalled() {
        final var id = 10L;
        final var idCaptor = ArgumentCaptor.forClass(Long.class);

        doNothing().when(repository).deleteById(anyLong());

        service.deleteById(id);

        verify(repository).deleteById(idCaptor.capture());
        assertSame(id, idCaptor.getValue());
    }
}
