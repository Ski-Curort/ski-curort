package com.example.skicurort.equipment;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
  // @ManyToOne
  // private Shop shop;
}
