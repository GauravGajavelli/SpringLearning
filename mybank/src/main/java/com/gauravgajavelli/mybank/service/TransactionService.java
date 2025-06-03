package com.gauravgajavelli.mybank.service;

import com.gauravgajavelli.mybank.model.Transaction;
import org.hibernate.validator.constraints.UUID;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Component
public class TransactionService {
    private JdbcTemplate jdbcTemplate;

    public TransactionService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Transaction> findAll() {
        System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());
        return jdbcTemplate.query("select id, time_stamp, reference, amount from transactions", (resultSet, rowNum) -> {
            Transaction transaction = new Transaction(resultSet.getInt("id"),
                    resultSet.getInt("amount"),
                    resultSet.getObject("time_stamp", LocalDateTime.class),
                    resultSet.getString("reference"));
            return transaction;
        });
    }
    public List<Transaction> getAccount(int id) {
        return jdbcTemplate.query("select id, time_stamp, reference, amount from transactions where ", (resultSet, rowNum) -> {
            Transaction transaction = new Transaction(resultSet.getInt("id"),
                    resultSet.getInt("amount"),
                    resultSet.getObject("time_stamp", LocalDateTime.class),
                    resultSet.getString("reference"));
            return transaction;
        });
    }

    public Transaction create(int id, int amount, String timestamp, String reference) {
                /*
create table if not exists transactions
(
    id      uuid  default random_uuid() primary key,
    time_stamp timestamp,
    reference varchar(255),
    amount  int
    );
    * */
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("insert into transactions (id, time_stamp, reference, amount) values (?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, LocalDateTime.parse(timestamp), java.sql.JDBCType.TIMESTAMP);
            ps.setString(2, reference);
            ps.setInt(3, amount);
            return ps;
        }, keyHolder);

        String uuid = !keyHolder.getKeys().isEmpty() ? ((UUID) keyHolder.getKeys().values().iterator().next()).toString()
                : null;

        Transaction transaction = new Transaction(id, amount, timestamp, reference);
        return transaction;
    }

    /*
    @Transactional
    public List<Invoice> findAll() {
        System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());
        return jdbcTemplate.query("select id, user_id, pdf_url, amount from invoices", (resultSet, rowNum) -> {
            Invoice invoice = new Invoice();
            invoice.setId(resultSet.getObject("id").toString());
            invoice.setPdfUrl(resultSet.getString("pdf_url"));
            invoice.setUserId(resultSet.getString("user_id"));
            invoice.setAmount(resultSet.getInt("amount"));
            return invoice;
        });
    }

    @Transactional
    public Invoice create(String userId, Integer amount) {
        System.out.println("Is a database transaction open? = " + TransactionSynchronizationManager.isActualTransactionActive());
        String generatedPdfUrl = cdnUrl + "/images/default/sample.pdf";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("insert into invoices (user_id, pdf_url, amount) values (?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, userId);  //
            ps.setString(2, generatedPdfUrl);
            ps.setInt(3, amount);
            return ps;
        }, keyHolder);

        String uuid = !keyHolder.getKeys().isEmpty() ? ((UUID) keyHolder.getKeys().values().iterator().next()).toString()
                : null;

        Invoice invoice = new Invoice();
        invoice.setId(uuid);
        invoice.setPdfUrl(generatedPdfUrl);
        invoice.setAmount(amount);
        invoice.setUserId(userId);
        return invoice;
    }
    */
}
