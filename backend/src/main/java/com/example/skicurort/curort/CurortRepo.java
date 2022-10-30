package com.example.skicurort.curort;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurortRepo extends JpaRepository<Curort,Long> {

    Optional<Curort> findById(Long id);

}
