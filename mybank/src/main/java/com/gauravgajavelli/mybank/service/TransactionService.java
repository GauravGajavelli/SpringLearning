package com.gauravgajavelli.mybank.service;

import com.gauravgajavelli.mybank.model.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

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
        return jdbcTemplate.query("select id, user_id, pdf_url, amount from invoices", (resultSet, rowNum) -> {
            Integer id = Integer.valueOf(resultSet.getObject("id"));
            Integer amount = Integer.valueOf(transaction.getAmount());
            String timestamp = String.valueOf(transaction.getTimestamp());
            String reference = transaction.getReference();

            model.addAttribute("userId", userId);

            Transaction transaction = new Transaction(id, amount, timestamp, reference);

            transactionService.create(id, amount, timestamp, reference);

        });
    }
    public List<Transaction> getAccount(int id) {
        return transactions.stream()
                .filter(t -> t.id == id)
                .collect(Collectors.toList());
    }

    public Transaction create(int id, int amount, String timestamp, String reference) {
        Transaction transaction = new Transaction(id, amount, timestamp, reference);
        transactions.add(transaction);
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
