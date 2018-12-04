package com.vkonchuk.catalog.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vkonchuk.catalog.model.Phone;
import com.vkonchuk.catalog.service.CatalogService;

@RestController
public class PhoneResource {

    @Autowired
    private CatalogService catalogService;

    @GetMapping("phones")
    public ResponseEntity<List<Phone>> getPhones() {
        return new ResponseEntity<>(catalogService.getAllPhones(), HttpStatus.OK);
    }

}
