package com.vkonchuk.catalog.api.resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.vkonchuk.catalog.model.Phone;
import com.vkonchuk.catalog.service.CatalogService;

public class PhoneResourceTest {

    @Mock
    private CatalogService catalogService;

    @InjectMocks
    private PhoneResource phoneResource;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void statusCode200IsReturnedWhenAllPhonesAreReturnedTest() {
        when(catalogService.getAllPhones()).thenReturn(Collections.emptyList());
        ResponseEntity<List<Phone>> responseEntity = phoneResource.getPhones();
        assertThat(responseEntity.getStatusCode()).as("Status code must be 200 OK when all phones are returned").isEqualTo(HttpStatus.OK);
    }

    @Test(expected = RuntimeException.class)
    public void runtimeExceptionIsThrownWhenRuntimeExceptionOccursDuringExecutionTest() {
        when(catalogService.getAllPhones()).thenThrow(new RuntimeException("Test exception"));
        phoneResource.getPhones();
    }

}
