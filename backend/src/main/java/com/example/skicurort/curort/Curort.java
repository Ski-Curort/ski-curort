package com.example.skicurort.curort;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "curort")
public class Curort {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "curort_name")
  private String curortName;

  @Column(name = "curort_address")
  private String curortAdress;

  @Column(name = "curort_email")
  private String currortEmail;

  @Column(name = "curort_phone")
  private Long curortPhonenumber;

  // TODO adding relation to person
  // @OneToMany
  // private <List> Person;
  // TODO adding relation to person
  // @OneToMany
  // private <List> Shop;

}
