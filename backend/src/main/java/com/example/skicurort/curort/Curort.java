package com.example.skicurort.curort;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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


  /*@OneToMany
   private <List>Person;

  @OneToMany
  private <List>Shop;*/

}
