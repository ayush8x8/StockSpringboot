package com.phase3ayush.ayush.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phase3ayush.ayush.entities.Company;
import com.phase3ayush.ayush.entities.Sector;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {
	public Sector findBySectorName(String sectorName);
	
	@Query("select c.companyName from Company c JOIN c.sector s WHERE s.id = :id")
	List<String> findCompaniesInSector(@Param("id") Long id);
	
	@Query("select avg(sp.shareprice) from Sector s JOIN s.companies c JOIN c.stockprice sp WHERE c.sectorName=:sectorName and sp.datee between :start and :end")
	Float getSectorPriceInRange(@Param("start") Date start, @Param("end") Date end, @Param("sectorName") String sectorName);
}
