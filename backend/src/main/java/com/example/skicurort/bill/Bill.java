package com.example.skicurort.bill;

import com.example.skicurort.curort.Curort;
import com.example.skicurort.item.Item;
import com.example.skicurort.user.User;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Bill {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "total_cost")
  private BigDecimal totalCost;

  @Column(name = "creation_date")
  private Date creationDate;

  @ManyToOne private User user;

  @ManyToOne private Curort curort;

  @OneToMany(mappedBy = "bill")
  List<Item> itemList;
}
