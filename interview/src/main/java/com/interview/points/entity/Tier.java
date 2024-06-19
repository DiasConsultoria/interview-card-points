package com.interview.points.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "tier")
@AllArgsConstructor
@NoArgsConstructor
public class Tier {


    @Id
    private Integer id;

    @Column(nullable = false, name = "multiplier")
    private BigDecimal multiplier;
}
