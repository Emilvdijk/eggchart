package com.example.eggchart.repository;

import com.example.eggchart.entity.PriceNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<PriceNode, Long> {}
