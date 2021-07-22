package com.phase3ayush.ayush.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.phase3ayush.ayush.entities.IPODetail;
import com.phase3ayush.ayush.entities.Stockexchange;

public interface IPODetailRepository extends JpaRepository<IPODetail, Long>{
	
	@Query("Select i FROM IPODetail i JOIN i.company c WHERE c.id = :id")
	List<IPODetail> findIPOByCompanyId(@Param("id") Long id);
	
	@Query("Select i FROM IPODetail i WHERE i.cName = :cName")
	List<IPODetail> findByCName(@Param("cName") String cName);
	
	@Query("select i from IPODetail i order by cast(i.openDateTime as timestamp)")
	List<IPODetail> getIPOChronologically();
	
//	Select q FROM Company c JOIN c.compstockmap p JOIN p.stockexchange q WHERE c.companyName = :CompanyName
	
	@Query("Select s from Company c JOIN c.compstockmap cse JOIN cse.stockexchange s WHERE c.companyName=:companyName")
//	@Query("select s from Stockexchange s JOIN s.compstockmap cse JOIN cse.company c WHERE c.companyName=:companyName")
	List<Stockexchange> findStockExchangesFromCompanyName(@Param("companyName") String companyName);
}
