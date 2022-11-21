package com.example.skicurort.equipment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Equipment {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String type;
  private String mark;
  private boolean available;
  private BigDecimal cost;
  // private Set<LocalDateTime> reservationDate;

  // @ManyToOne
  // private Shop shop;
}