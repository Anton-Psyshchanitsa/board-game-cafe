package com.boardgame.cafe.repository;

import com.boardgame.cafe.entity.CafeTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CafeTableRepository extends JpaRepository<CafeTable, Long> {

    Page<CafeTable> findByIsActiveTrue(Pageable pageable);

    Page<CafeTable> findByCapacityGreaterThanEqual(Integer capacity, Pageable pageable);

    boolean existsByTableNumber(String tableNumber);

    Page<CafeTable> findAll(Pageable pageable);
}