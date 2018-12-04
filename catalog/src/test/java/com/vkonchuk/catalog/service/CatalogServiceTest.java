package com.vkonchuk.catalog.service;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.vkonchuk.catalog.dao.PhoneDao;

public class CatalogServiceTest {

    private static final int ONCE = 1;

    @Mock
    private PhoneDao phoneDao;

    @InjectMocks
    private CatalogService catalogService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void catalogServiceGetsPhonesFromDaoTest() {
        catalogService.getAllPhones();
        verify(phoneDao, Mockito.times(ONCE)).selectAllPhones();
    }

}
