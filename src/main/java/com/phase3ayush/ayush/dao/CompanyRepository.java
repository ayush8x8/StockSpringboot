package com.phase3ayush.ayush.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.phase3ayush.ayush.entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
	public Company findByCompanyName(String companyName);
	
	@Query("select companyName from Company where companyName like %:companyName%")
	List<String> searchByTitleLike(@Param("companyName") String companyName);
	
//	@Query("select companyName from Company where id in (select company_id from Companystockexchangemap where companyCode like %:companyCode%)")
//	List<String> searchByCodeLike(@Param("companyCode") String companyCode);
	
	@Query("Select c.companyName FROM Company c JOIN c.compstockmap p WHERE p.companyCode LIKE %:companyCode%")
	List<String> findMatchingCompaniesByCode(@Param("companyCode") String companyCode);
	
	@Query("Select q.name FROM Company c JOIN c.compstockmap p JOIN p.stockexchange q WHERE c.companyName = :companyName")
	List<String> findExchangesOfCompany(@Param("companyName") String companyName);
}
