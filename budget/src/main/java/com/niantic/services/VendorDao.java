package com.niantic.services;

import com.niantic.models.Categories;
import com.niantic.models.Vendors;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;

public class VendorDao
{
    private JdbcTemplate jdbcTemplate;

    public VendorDao()
    {
        String databaseUrl = "jdbc:mysql://localhost:3306/budget";
        String userName = "root";
        String password = "P@ssw0rd";
        DataSource dataSource = new BasicDataSource(){{
            setUrl(databaseUrl);
            setUsername(userName);
            setPassword(password);
        }};

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public ArrayList<Vendors> getVendors()
    {
        ArrayList<Vendors> vendors = new ArrayList<>();

        String sql = """
                SELECT vendor_id
                    , vendor_name
                    , website
                FROM vendors
                """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql);

        while(row.next())
        {
            int vendorId = row.getInt("vendor_id");
            String vendorName  = row.getString("vendor_name");
            String website = row.getString("website");

            Vendors vendor = new Vendors(vendorId, vendorName, website);

            vendors.add(vendor);
        }

        return vendors;
    }

    public void addVendor(Vendors vendors)
    {
        String sql = """
                INSERT INTO vendors(vendor_id, vendor_name, website)
                VALUES(?, ?, ?);
                """;

        jdbcTemplate.update(sql
                , vendors.getVendorId()
                , vendors.getVendorName()
                , vendors.getWebsite());
    }

}