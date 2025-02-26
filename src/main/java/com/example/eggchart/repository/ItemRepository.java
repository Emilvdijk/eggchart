package com.example.eggchart.repository;

import com.example.eggchart.Entity.PriceNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<PriceNode, Long> {}
