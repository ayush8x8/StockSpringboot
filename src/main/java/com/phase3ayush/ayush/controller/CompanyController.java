package com.phase3ayush.ayush.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phase3ayush.ayush.dao.CompanyRepository;
import com.phase3ayush.ayush.dao.SectorRepository;
import com.phase3ayush.ayush.entities.Company;
import com.phase3ayush.ayush.entities.Sector;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class CompanyController {
	
	
	@Autowired
	private CompanyRepository companyRepo;
	
	@Autowired
	private SectorRepository sectorRepo;
	
	@PostMapping("/addCompany")
	public int addCompanyDetails(@RequestBody Company company) {
		Sector sector = sectorRepo.findBySectorName(company.getSectorName());
		company.setSector(sector);
		companyRepo.save(company);
		return 1;
	}
	
	
	@GetMapping("/getCompanies")
	public List<Company> getCompanyDetails() {
		List<Company> companies = new ArrayList<Company>();
		companyRepo.findAll().forEach(company1 -> companies.add(company1));
		return companies;
	}
	
	@GetMapping("/getMatchingCompanies")
	public List<Company> getMatchingCompanies(@RequestParam String companyName) {
//		List<Company> companies = new ArrayList<Company>();
		return companyRepo.searchByTitleLike(companyName);
	}
	
//	@GetMapping("/getMatchingCompaniesFromCode")
//	public List<String> getMatchingCompaniesFromCode(@RequestParam String companyCode) {
//		return companyRepo.searchByCodeLike(companyCode);
//	}
	
	
	@GetMapping("/getCompanyByName")
	public Company getCompanyDetailsByName(@RequestParam String companyName) {
		return companyRepo.findByCompanyName(companyName);
	}
	
	@PutMapping("/updateCompany")
	public int updateCompanyDetails(@RequestBody Company company) {
		company.setSector(sectorRepo.findBySectorName(company.getSectorName()));
		companyRepo.save(company);
		return 2;
	}
	
	@DeleteMapping("/deleteCompanyByName/{companyName}")
	public int deleteCompanyByName(@PathVariable String companyName) {
		companyRepo.deleteById(companyRepo.findByCompanyName(companyName).getId());
		return 3;
	}
	
	@GetMapping("/getMatchingCompaniesByCode/{companyCode}")
	public List<String> getMatchingCompaniesByCode(@PathVariable String companyCode){
		List<String> companies = companyRepo.findMatchingCompaniesByCode(companyCode);
		return companies;
	}
	
	@PostMapping("/getExchangesOfCompany/{companyName}")
	public List<String> getExchangesOfCompany(@PathVariable String companyName){
		return companyRepo.findExchangesOfCompany(companyName);
	}
	
	
	
}
