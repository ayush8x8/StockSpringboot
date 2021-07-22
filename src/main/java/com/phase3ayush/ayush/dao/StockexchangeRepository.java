package com.phase3ayush.ayush.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phase3ayush.ayush.entities.Stockexchange;

@Repository
public interface StockexchangeRepository extends JpaRepository<Stockexchange, Long> {
	public Stockexchange findByName(String Name);
	
	@Query("Select c.companyName FROM Company c JOIN c.compstockmap p JOIN p.stockexchange q WHERE q.name = :name")
	public List<String> findCompanyByExchange(@Param("name") String name);
}
