package com.phase3ayush.ayush;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.phase3ayush.ayush.controller.CompanyController;
import com.phase3ayush.ayush.controller.IPODetailController;
import com.phase3ayush.ayush.controller.SectorController;
import com.phase3ayush.ayush.controller.StockPriceController;
import com.phase3ayush.ayush.controller.StockexchangeController;
import com.phase3ayush.ayush.entities.Company;
import com.phase3ayush.ayush.entities.Sector;

@SpringBootApplication
public class AyushApplication {

	public static void main(String[] args) {
		SpringApplication.run(AyushApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner loadData(SectorController st) {
	    return (args) -> {
	    	st.addCompanyDetails(new Sector("bank", "money"));
	    	st.addCompanyDetails(new Sector("fintech", "more money"));
//	    	cc.addCompanyDetails(new Company("hdfc", 1000, "Ayush", "Tom, Cat", "banking"));
	    };
	}

}
