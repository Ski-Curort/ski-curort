package com.example.skicurort.item;

import com.example.skicurort.bill.Bill;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

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

  @Column(name = "total_price")
  private BigDecimal totalPrice;

  @ManyToOne Bill bill;
}
