package com.example.skicurort.equipment;

import com.example.skicurort.curort.Curort;
import com.example.skicurort.shop.Shop;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;

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
    @ManyToOne
    private Shop shop;
}
