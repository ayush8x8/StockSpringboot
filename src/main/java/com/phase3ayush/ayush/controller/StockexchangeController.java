package com.phase3ayush.ayush.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.phase3ayush.ayush.dao.CompanyRepository;
import com.phase3ayush.ayush.dao.CompanystockexchangemapRepository;
import com.phase3ayush.ayush.dao.StockexchangeRepository;
import com.phase3ayush.ayush.entities.Company;
import com.phase3ayush.ayush.entities.Companystockexchangemap;
import com.phase3ayush.ayush.entities.Stockexchange;


@RestController
@CrossOrigin
public class StockexchangeController {

	@Autowired
	private StockexchangeRepository stockexRepo;
	
	@Autowired
	private CompanyRepository companyRepo;
	
	@Autowired
	private CompanystockexchangemapRepository companystockexRepo;

	@PostMapping("/addStockExchange")
	public int addStockExchangeDetails(@RequestBody Stockexchange stockexchange) {
		stockexRepo.save(stockexchange);
		return 1;
	}

	@GetMapping("/getStockExchanges")
	public List<Stockexchange> getStockExchanges() {
		List<Stockexchange> stockexchanges = new ArrayList<Stockexchange>();
		stockexRepo.findAll().forEach(se1 -> stockexchanges.add(se1));
		return stockexchanges;
	}

//	@RequestMapping(value = "/process", method = RequestMethod.POST)
//	public void process(@RequestBody Map<String, Object>[] payload) throws Exception {
//
//		System.out.println(payload.toString());
//
//	}
	
//	@PostMapping("/mapStockCompany")
//	public int mapStockCompany(@RequestBody Map<String, Object> model) {
//		String name=(String)model.get("name");
//		String com_name=(String)model.get("com_name");
//		
//		Company company = companyRepo.findByCompanyName(com_name);
//		Stockexchange stockExchange = stockexRepo.findByName(name);
//		
//		Companystockexchangemap companystockexchangemap = new Companystockexchangemap();
//		companystockexchangemap.setCompany(company);
//		companystockexchangemap.setStockexchange(stockExchange);
//		companystockexchangemap.setCompanyCode("1101");
//		
//		companystockexRepo.save(companystockexchangemap);
//
//		return 5;
//	}
	
	@PostMapping("/mapStockExchangeToCompany")
	public int mapStockExchangeToCompany(@RequestBody Map<String, Object> model) {
		String exchange_name=(String)model.get("exchange_name");
		String company_name=(String)model.get("company_name");
		
		Company company = companyRepo.findByCompanyName(company_name);
		Stockexchange stockExchange = stockexRepo.findByName(exchange_name);
		
		Companystockexchangemap companystockexchangemap=new Companystockexchangemap();
		companystockexchangemap.setCompany(company);
		companystockexchangemap.setStockexchange(stockExchange);
		String companyCode = company_name+exchange_name+company.getId();
		companystockexchangemap.setCompanyCode(companyCode);
		companystockexRepo.save(companystockexchangemap);

		return 5;
	}
	
	@GetMapping("/getCompanyByExchange")
	public List<String> getCompanyByExchange(@RequestParam String exchangeName){
		return stockexRepo.findCompanyByExchange(exchangeName);
	}

}
