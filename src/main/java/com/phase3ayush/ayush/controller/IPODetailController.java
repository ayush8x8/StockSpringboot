package com.phase3ayush.ayush.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phase3ayush.ayush.dao.CompanyRepository;
import com.phase3ayush.ayush.dao.IPODetailRepository;
import com.phase3ayush.ayush.entities.Company;
import com.phase3ayush.ayush.entities.IPODetail;
//import com.fasterxml.jackson.datatype:jackson-datatype-jsr310;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class IPODetailController {
	
	@Autowired
	private IPODetailRepository ipoRepo;
	
	@Autowired
	private CompanyRepository companyRepo;
	
//	public int addIPODetails(@RequestBody Map<String, Object> model)
	
	@PostMapping("/addIPO")
	public int addIPODetails(@RequestBody IPODetail ipodetail) {
//		String company_name=(String)model.get("company_name");
		Company company = companyRepo.findByCompanyName(ipodetail.getcName());
		ipodetail.setStockExchanges(ipoRepo.findStockExchangesFromCompanyName(ipodetail.getcName()));
		ipodetail.setCompany(company);
		ipoRepo.save(ipodetail);
		return 111;
	}
	
	@PutMapping("/updateIPO")
	public int updateIPODetails(@RequestBody IPODetail ipodetail) {
		Company company = companyRepo.findByCompanyName(ipodetail.getcName());
		ipodetail.setStockExchanges(ipoRepo.findStockExchangesFromCompanyName(ipodetail.getcName()));
		ipodetail.setCompany(company);
		ipoRepo.save(ipodetail);
		return 2;
	}
	
	@GetMapping("/getIPOs")
	public List<IPODetail> getIPODetails() {
		List<IPODetail> ipodetails = new ArrayList<IPODetail>();
		ipoRepo.findAll().forEach(ipo1 -> ipodetails.add(ipo1));
		return ipodetails;
	}
	
	@GetMapping("/getIPOByCompanyName")
	public List<IPODetail> getIPODetailsByCompanyName(@RequestParam String companyName) {
//		Company company = companyRepo.findByCompanyName(companyName);
//		Long companyId = company.getId();
		return ipoRepo.findByCName(companyName);
	}
	
	@GetMapping("/getIPOChronologically")
	public List<IPODetail> getIPOsChronologically() {
		return ipoRepo.getIPOChronologically();
	}
	
	
//	ObjectMapper objectMapper = new ObjectMapper();
//	IPODetail ipodetail = objectMapper.convertValue(model.get("ipo_name"), IPODetail.class);
//	IPODetail ipodetail = (IPODetail)model.get("ipo_name");
}
