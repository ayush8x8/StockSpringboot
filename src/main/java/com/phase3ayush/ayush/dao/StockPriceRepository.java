package com.phase3ayush.ayush.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phase3ayush.ayush.entities.Company;
import com.phase3ayush.ayush.entities.StockPrice;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice, Long> {
//	@Query("select c.companyName from Company c where c.companyName like %:companyName%")
//	List<String> searchByTitleLike(@Param("companyName") String companyName);
//	public StockPrice findById();
	
	@Query("Select c FROM Company c JOIN c.compstockmap p WHERE p.companyCode = :CompanyCode")
	Company findCompanyByCompanyCode(@Param("CompanyCode") String CompanyCode);
	
	@Query("select s.shareprice from Company c JOIN c.stockprice s WHERE c.id = :company_id and s.datee between :start and :end")
	List<Float> getCompanyStockPriceInRange(@Param("start") Date start, @Param("end") Date end, @Param("company_id") long company_id);
	
	@Query("select s from Company c JOIN c.stockprice s where c.id = :company_id")
	List<StockPrice> getStockPriceFromCID(@Param("company_id") Long company_id);
}
