package com.example.skicurort.curort;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurortRepo extends JpaRepository<Curort, Long> {

  Optional<Curort> findById(Long id);
}
