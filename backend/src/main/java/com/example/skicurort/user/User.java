package com.example.skicurort.user;

import java.util.UUID;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(
    name = "users",
    uniqueConstraints = {
      @UniqueConstraint(columnNames = {"username"}),
      @UniqueConstraint(columnNames = {"email"})
    })
public class User {
  @Id
  @Column(columnDefinition = "uuid")
  private UUID id;

  private String name;
  private String username;
  private String email;
  private String password;

  /* @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
  @Enumerated(EnumType.STRING)
  @CollectionTable(name = "user_roles")
  @Column(name = "role")
  private Set<Role> roles;*/
}
