package com.phase3ayush.ayush.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phase3ayush.ayush.dao.SectorRepository;
import com.phase3ayush.ayush.dao.StockPriceRepository;
import com.phase3ayush.ayush.entities.Company;
import com.phase3ayush.ayush.entities.Sector;

@RestController
public class SectorController {
	
	@Autowired
	private SectorRepository sectorRepo;
	
	@PostMapping("/addSector")
	public int addCompanyDetails(@RequestBody Sector sector) {
		sectorRepo.save(sector);
		return 55;
	}
	
	@GetMapping("/getSectors")
	public List<Sector> getSectorDetails() {
		List<Sector> sectors = new ArrayList<Sector>();
		sectorRepo.findAll().forEach(sector1 -> sectors.add(sector1));
		return sectors;
	}
	
	@GetMapping("/getSectorByName")
	public Sector getSectorDetailsByName(@RequestParam String sectorName) {
		return sectorRepo.findBySectorName(sectorName);
	}
	
	@GetMapping("/getCompaniesInSector")
	public List<String> getCompaniesInSector(@RequestParam String sectorName) {
		
		Sector sector = sectorRepo.findBySectorName(sectorName);
		return sectorRepo.findCompaniesInSector(sector.getId());
		
	}
	
	@GetMapping("/getSectorPriceInRange")
	public Float getSectorPriceInRange(@RequestBody Map<String, String> details) throws ParseException {
		Sector sector = sectorRepo.findBySectorName(details.get("sectorName"));
		Date start = new SimpleDateFormat("yyyy-MM-dd").parse(details.get("start"));
		Date end = new SimpleDateFormat("yyyy-MM-dd").parse(details.get("end"));
		return sectorRepo.getSectorPriceInRange(start, end, details.get("sectorName"));
	}
	
}
