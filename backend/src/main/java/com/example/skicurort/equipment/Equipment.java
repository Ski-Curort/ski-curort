package com.example.skicurort.equipment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Set;

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
  private float cost;
  //private Set<LocalDateTime> reservationDate;

  //@ManyToOne
  //private Shop shop;
}
