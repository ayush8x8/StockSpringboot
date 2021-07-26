package com.phase3ayush.ayush.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phase3ayush.ayush.dao.CompanyRepository;
import com.phase3ayush.ayush.dao.IPODetailRepository;
import com.phase3ayush.ayush.dao.StockPriceRepository;
import com.phase3ayush.ayush.entities.Company;
import com.phase3ayush.ayush.entities.StockPrice;


@RestController
@CrossOrigin
public class StockPriceController {
	
	@Autowired
	private StockPriceRepository stockpriceRepo;
	
	@Autowired
	private CompanyRepository companyRepo;
	
	@PostMapping("/addStockPrice")
	public int addStockPriceDetails(@RequestBody StockPrice stockprice) {
		Company company = stockpriceRepo.findCompanyByCompanyCode(stockprice.getCompanycode());
//		System.out.println(Arrays.toString(company_name.toArray()));
//		Company company = companyRepo.findByCompanyName(company_name);
		stockprice.setCompany(company);
		stockpriceRepo.save(stockprice);
		return 9;
	}
	
	@GetMapping("/getStockPrices")
	public List<StockPrice> getStockPrices(){
		return stockpriceRepo.findAll();
	}
	
//	ToDO : get sp from cid
	@GetMapping("/getStockPriceFromCompanyName")
	public List<StockPrice> getStockPriceFromCompanyName(@RequestParam String companyName) {
		
		Company company = companyRepo.findByCompanyName(companyName);
		return stockpriceRepo.getStockPriceFromCID(company.getId());
	}
	
	@PostMapping("/getCompanyStockPriceInRange")
	public List<Float> getCompanyStockPriceInRange(@RequestBody Map<String, String> details) throws ParseException {
		
		Company company = companyRepo.findByCompanyName(details.get("companyName"));
		Date start = new SimpleDateFormat("yyyy-MM-dd").parse(details.get("start"));
		Date end = new SimpleDateFormat("yyyy-MM-dd").parse(details.get("end"));
		
		long company_id = company.getId();
		return stockpriceRepo.getCompanyStockPriceInRange(start, end, company_id);
	}
	
}
