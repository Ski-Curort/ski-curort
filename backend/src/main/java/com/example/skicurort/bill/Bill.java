package com.example.skicurort.bill;

import com.example.skicurort.item.Item;
import com.example.skicurort.curort.Curort;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

@Entity
@Data
public class Bill {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "total_cost")
  private BigDecimal totalCost;

  @Column(name = "creation_date")
  private SimpleDateFormat creationDate;

 @ManyToOne

  private User user;

  //@ManyToOne

  //private Curort curort;

  @OneToMany (mappedBy = "bill")
  List<Item> itemList;
}
