package com.niantic.services;

import com.niantic.models.Transactions;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;

public class TransactionDao
{
    private JdbcTemplate jdbcTemplate;

    public TransactionDao()
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

    public ArrayList<Transactions> getTransactions()
    {
        ArrayList<Transactions> transactions = new ArrayList<>();

        String sql = """
                SELECT transaction_id
                    , user_id
                    , category_id
                    , vendor_id
                    , transaction_date
                    , amount
                    , notes
                FROM transactions
                """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql);

        while(row.next())
        {

            int transactionId = row.getInt("transaction_id");
            int userId = row.getInt("user_id");
            int categoryId = row.getInt("category_id");
            int vendorId = row.getInt("vendor_id");
            LocalDate transactionDate = row.getDate("transaction_date").toLocalDate();
            double amount = row.getDouble("amount");
            String notes = row.getString("notes");



            Transactions transaction = new Transactions(transactionId,
                                                        userId, categoryId,
                                                         vendorId, transactionDate
                                                        , amount, notes);

            transactions.add(transaction);
        }

        return transactions;
    }

    public Transactions getTransactionById(int queryId)
    {
        Transactions transaction = null;

        String sql = """
              SELECT transaction_id
                    , user_id
                    , category_id
                    , vendor_id
                    , transaction_date
                    , amount
                    , notes
                FROM transactions
                WHERE transaction_id = ?
        """;

        SqlRowSet row = jdbcTemplate.queryForRowSet(sql, queryId);

        if(row.next())
        {
            int transactionId = row.getInt("transaction_id");
            int userId = row.getInt("user_id");
            int categoryId = row.getInt("category_id");
            int vendorId = row.getInt("vendor_id");
            LocalDate transactionDate = row.getDate("transaction_date").toLocalDate();
            double amount = row.getDouble("amount");
            String notes = row.getString("notes");

            transaction = new Transactions(transactionId,
                    userId, categoryId,
                    vendorId, transactionDate
                    , amount, notes);

        }

        return transaction;
    }
}
