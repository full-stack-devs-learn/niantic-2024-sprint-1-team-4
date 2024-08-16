package com.niantic.services;

import com.niantic.models.Categories;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;

public class CategoryDao
{
    private JdbcTemplate jdbcTemplate;

    public CategoryDao()
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

    public ArrayList<Categories> getCategories()
    {
        ArrayList<Categories> categories = new ArrayList<>();

        String sql = """
                SELECT category_id
                    , category_name
                    , description
                FROM categories
                """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql);

        while(row.next())
        {
            int categoryId = row.getInt("category_id");
            String categoryName  = row.getString("category_name");
            String description = row.getString("description");

            Categories category = new Categories(categoryId, categoryName, description);

            categories.add(category);
        }

        return categories;
    }

    public void addCategory(Categories categories)
    {
        String sql = """
                INSERT INTO categories(category_id. category_name, description)
                VALUES(?, ?, ?);
                """;

        jdbcTemplate.update(sql
                , categories.getCategoryId()
                , categories.getCategoryName()
                , categories.getDescription());
    }

}
