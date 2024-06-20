package com.interview.points.record;

import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

public record RedeemRecord(String message,BigDecimal previousBalance,BigDecimal currentBalance ,BigDecimal redeemPoints) {
}
