package com.interview.points.record;


import java.math.BigDecimal;

public record IssueRecord(String message,BigDecimal previousBalance,BigDecimal currentBalance ,BigDecimal redeemPoints) {
}
