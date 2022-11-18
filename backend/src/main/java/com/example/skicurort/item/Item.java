package com.example.skicurort.item;

import com.example.skicurort.bill.Bill;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@Table(name = "bill_item")
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "item_name")
  private String itemName;

  @Column(name = "quantity")
  private Long quantity;

  @Column(name = "unite_price")
  private BigDecimal unitePrice;
  @ManyToOne
  Bill bill;
}
