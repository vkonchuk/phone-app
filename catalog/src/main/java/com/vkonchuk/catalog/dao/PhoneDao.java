package com.vkonchuk.catalog.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.vkonchuk.catalog.model.Phone;

@Component
public class PhoneDao {

    private static final String SELECT_ALL_PHONES_QUERY = "SELECT name, description, image_url, price FROM phone;";

    private static final String FLD_NAME = "name";
    private static final String FLD_DESCRIPTION = "description";
    private static final String FLD_IMAGE_URL = "image_url";
    private static final String FLD_PRICE = "price";

    private NamedParameterJdbcOperations namedParameterJdbcTemplate;

    @Autowired
    private DataSource catalogDataSource;

    @PostConstruct
    private void init() {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(catalogDataSource);
    }

    public List<Phone> selectAllPhones() {
        return namedParameterJdbcTemplate.query(SELECT_ALL_PHONES_QUERY, getPhoneMapper());
    }

    private RowMapper<Phone> getPhoneMapper() {
        return (rs, rowNum) -> {
            Phone phone = new Phone();
            phone.setName(rs.getString(FLD_NAME));
            phone.setDescription(rs.getString(FLD_DESCRIPTION));
            phone.setImageUrl(rs.getString(FLD_IMAGE_URL));
            phone.setPrice(rs.getBigDecimal(FLD_PRICE));
            return phone;
        };
    }

}
