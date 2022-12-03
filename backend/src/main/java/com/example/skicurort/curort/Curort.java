package com.example.skicurort.curort;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.skicurort.equipment.Equipment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

  @OneToMany (mappedBy = "curort")
  List<Equipment> equipmentList;

}
