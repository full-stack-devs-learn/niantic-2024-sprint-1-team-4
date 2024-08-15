package com.niantic.models;

import java.time.LocalDate;

public class Transactions
{
        private int transactionId;
        private int userId;
        private int categoryId;
        private int vendorId;
        private LocalDate transactionDate;
        private double amount;
        private String notes;

        public Transactions(int transactionId, int userId, int categoryId, int vendorId, LocalDate transactionDate, double amount, String notes)
        {
            this.transactionId = transactionId;
            this.userId = userId;
            this.categoryId = categoryId;
            this.vendorId = vendorId;
            this.transactionDate = transactionDate;
            this.amount = amount;
            this.notes = notes;
        }



}
