package com.interview.points.record;

import java.math.BigDecimal;

public record PointsRecord(String username, String cpf, Integer tier, BigDecimal points) {
}
