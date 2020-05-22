package com.jell.learning.ibeer.service.manufacturer;


import com.jell.learning.ibeer.repository.ManufacturerRepository;
import com.jell.learning.ibeer.sevice.manufacturer.ManufacturerService;
import com.jell.learning.ibeer.sevice.manufacturer.mapper.ManufacturerMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;

import static com.jell.learning.ibeer.service.manufacturer.mock.ManufacturerMockFactory.manufacturer;
import static com.jell.learning.ibeer.service.manufacturer.mock.ManufacturerMockFactory.manufacturerDTO;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ManufacturerServiceTest {
    @Mock
    private ManufacturerRepository repository;
    @Mock
    private ManufacturerMapper mapper;

    @InjectMocks
    private ManufacturerService service;

    @Test
    public void shouldReturnAManufacturerSuccessfully_whenCreateIsCalled() {
        final var manufacturer = manufacturer();
        final var manufacturerDTO = manufacturerDTO();

        Mockito.when(mapper.toEntity(manufacturerDTO)).thenReturn(manufacturer);
        Mockito.when(repository.save(manufacturer)).thenReturn(manufacturer);
        Mockito.when(mapper.toDTO(manufacturer)).thenReturn(manufacturerDTO);

        final var response = service.create(manufacturerDTO);

        assertThat(response, is(manufacturerDTO));
        verify(repository).save(manufacturer);
        verify(mapper).toDTO(manufacturer);
    }

    @Test
    public void shouldReturnAllManufacturersSuccessfully_whenGetAllIsCalled() {
        final var manufacturer = manufacturer();
        final var manufacturerDTO = manufacturerDTO();
        final var manufacturers = new PageImpl<>(Collections.singletonList(manufacturer));
        final var pageRequest = PageRequest.of(1, 1);

        Mockito.when(repository.findAll(pageRequest)).thenReturn(manufacturers);
        Mockito.when(mapper.toDTO(manufacturer)).thenReturn(manufacturerDTO);

        final var response = service.getAll(pageRequest);

        assertThat(response, hasItem(manufacturerDTO));
        verify(repository).findAll(pageRequest);
        verify(mapper).toDTO(manufacturer);
    }

    @Test
    public void shouldReturnManufacturersSuccessfully_whenGetByIdManufacturerIsCalled() {
        final var manufacturer = manufacturer();
        final var manufacturerDTO = manufacturerDTO();
        final long id = 50;

        Mockito.when(repository.getOne(id)).thenReturn(manufacturer);
        Mockito.when(mapper.toDTO(manufacturer)).thenReturn(manufacturerDTO);

        final var response = service.getById(id);

        assertThat(response, is(manufacturerDTO));

        verify(repository).getOne(id);
        verify(mapper).toDTO(manufacturer);
    }

    @Test
    public void shouldReturnAManufacturerSuccessfully_whenUpdateIsCalled() {
        final var manufacturer = manufacturer();
        final var manufacturerDTO = manufacturerDTO();
        ;

        Mockito.when(mapper.toEntity(manufacturerDTO)).thenReturn(manufacturer);
        Mockito.when(repository.save(manufacturer)).thenReturn(manufacturer);
        Mockito.when(mapper.toDTO(manufacturer)).thenReturn(manufacturerDTO);

        final var response = service.update(manufacturerDTO);

        assertThat(response, is(manufacturerDTO));
        verify(repository).save(manufacturer);
        verify(mapper).toDTO(manufacturer);
    }

    @Test
    public void shouldReturnSuccessfully_whenDeleteByIdIsCalled() {
        final long id = 50;
        final var idCaptor = ArgumentCaptor.forClass(Long.class);

        Mockito.doNothing().when(repository).deleteById(anyLong());
        service.deleteById(id);

        verify(repository).deleteById(idCaptor.capture());
        assertThat(id, is(idCaptor.getValue()));
    }
}
