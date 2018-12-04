package com.vkonchuk.catalog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vkonchuk.catalog.dao.PhoneDao;
import com.vkonchuk.catalog.model.Phone;

@Service
public class CatalogService {

    @Autowired
    private PhoneDao phoneDao;

    public List<Phone> getAllPhones() {
        return phoneDao.selectAllPhones();
    }

}
