package com.phase3ayush.ayush;

import static org.junit.jupiter.api.Assertions.assertEquals;


import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
class FinalTesting {
	
	@Autowired
	MockMvc mvc;
	
	@Test
	 @Order(3)  
	void getAllSec() throws Exception {
		MvcResult m=mvc.perform(MockMvcRequestBuilders.get("/getSectors"))
		.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
		.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$[0].sectorName").value("hello"))
		.andReturn();
		
		
//		assertEquals(2, content);
	}
	
	@Test
	@Order(1) 
	void addSec() throws Exception {
		
		String json="{\"sectorName\":\"hello\",\"brief\":\"hello\"}";
		MvcResult m=mvc.perform(MockMvcRequestBuilders.post("/addSector")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String content = m.getResponse().getContentAsString();
		assertEquals("55",content);
	}
//	
	@Test
	 @Order(2)  
	void getSec() throws Exception {
		
//		String json="{\"i\"sectorName\":\"Jack\",\"brief\":\"Bauer\"}";
		MvcResult m=mvc.perform(MockMvcRequestBuilders.get("/getSectorByName?sectorName=hello"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.brief").value("hello"))
				.andReturn();
		
	}
	
	// Companies
	@Test
	 @Order(7)  
	void getAllCom() throws Exception {
		MvcResult m=mvc.perform(MockMvcRequestBuilders.get("/getCompanies"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$[0].companyName").value("hdfc"))
		.andReturn();
		String content = m.getResponse().getContentAsString();
	}
	
	@Test
	 @Order(6)  
	void getCom() throws Exception {
		MvcResult  m=mvc.perform(MockMvcRequestBuilders.get("/getCompanyByName?companyName=hdfc"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2))
		.andReturn();
		String content = m.getResponse().getContentAsString();
	}
	
	@Test
	 @Order(4)  
	void addCom() throws Exception {
		
		String json="{\"companyName\":\"hdfc\",\"turnover\":10,\"ceo\":\"ayush\",\"boardOfDirectors\":\"ayush\",\"companyBrief\":\"ayush\",\"sectorName\":\"hello\"}";
		MvcResult m=mvc.perform(MockMvcRequestBuilders.post("/addCompany")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String content = m.getResponse().getContentAsString();
		assertEquals("1",content);
	}
	
	@Test
	 @Order(5)  
	void updateCom() throws Exception {
		
		String json="{\"id\":2,\"companyName\":\"hdfc\",\"turnover\":20,\"ceo\":\"ayush\",\"boardOfDirectors\":\"ayush\",\"companyBrief\":\"ayush\",\"sectorName\":\"hello\"}";
		MvcResult  m=mvc.perform(MockMvcRequestBuilders.put("/updateCompany")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String content = m.getResponse().getContentAsString();
		assertEquals("2",content);
	}
	
	@Test
	 @Order(8)  
	void deleteCom() throws Exception {
		
		String json="{\"companyName\":\"icic\",\"turnover\":10,\"ceo\":\"ayush\",\"boardOfDirectors\":\"ayush\",\"companyBrief\":\"ayush\",\"sectorName\":\"hel\"}";
		MvcResult m=mvc.perform(MockMvcRequestBuilders.post("/addCompany")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		
		MvcResult m2=mvc.perform(MockMvcRequestBuilders.delete("/deleteCompanyByName/icic"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String content = m2.getResponse().getContentAsString();
		assertEquals("3",content);
	}
	
	@Test
	 @Order(9)  
	void likeCom() throws Exception {
		
		MvcResult m=mvc.perform(MockMvcRequestBuilders.get("/getMatchingCompanies?companyName=hd"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].companyName").value("hdfc"))
				.andReturn();
		String content = m.getResponse().getContentAsString();
	}
	
	// STOCK EXCH
	@Test
	 @Order(10)  
	void addExch() throws Exception {
		
		String json="{ \"name\":\"NSE\",\"brief\":\"bombay\",\"address\":\"mumbai\",\"remarks\":\"Paisa\"}";
		MvcResult m=mvc.perform(MockMvcRequestBuilders.post("/addStockExchange")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String content = m.getResponse().getContentAsString();
		assertEquals("1",content);
	}
	
	
	@Test
	 @Order(11)  
	void getStockExcAll() throws Exception {
		 MvcResult m=mvc.perform(MockMvcRequestBuilders.get("/getStockExchanges"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
			.andReturn();
//		String content = m.getResponse().getContentAsString();
//		System.out.println("St "+content);
	}
	
	@Test
	 @Order(12)  
	void mapStock() throws Exception {
		String json="{\"company_name\":\"hdfc\",\"exchange_name\":\"NSE\"}";
		MvcResult m=mvc.perform(MockMvcRequestBuilders.post("/mapStockExchangeToCompany")
		.contentType(MediaType.APPLICATION_JSON)
		.content(json))
		.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String content = m.getResponse().getContentAsString();
		assertEquals("5",content);
	}
	
	@Test
	 @Order(13)  
	void getComFromEXCh() throws Exception {
		MvcResult m=mvc.perform(MockMvcRequestBuilders.get("/getCompanyByExchange?exchangeName=NSE"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0]").value("hdfc"))
		.andReturn();
		String content = m.getResponse().getContentAsString();
	}
	
	@Test
	 @Order(14)  
	void getComByCode() throws Exception {
	MvcResult m=mvc.perform(MockMvcRequestBuilders.get("/getMatchingCompaniesByCode/SE"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$[0]").value("hdfc"))
			.andReturn();
//		String content = m.getResponse().getContentAsString();
//		System.out.println("Hello "+content);
	}
	

	
	@Test
	 @Order(15)  
	void getList() throws Exception {
		MvcResult mvcResult=mvc.perform(MockMvcRequestBuilders.post("/getExchangesOfCompany/hdfc"))
		.andExpect(MockMvcResultMatchers.status().isOk())
//		.andExpect(MockMvcResultMatchers.jsonPath("$[0]").value("BSE"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0]").value("NSE"))
			.andReturn();
//		String content = mvcResult.getResponse().getContentAsString();
//		System.out.println("Bye2 "+content);
	}
	
	@Test
	 @Order(16)  
	void addIPO() throws Exception {
		
		String json="{\"remarks\": \"hihihih\",\r\n"
				+ "    \"pricePerShare\": 200.90,\r\n"
				+ "    \"totalNumberOfShares\": 1100,\r\n"
				+ "    \"openDateTime\": \"2022-04-20 09:44:42\",\r\n"
				+ "    \"cName\": \"hdfc\"}";
		MvcResult m=mvc.perform(MockMvcRequestBuilders.post("/addIPO")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		String content = m.getResponse().getContentAsString();
		assertEquals("111",content);
	}
	
	@Test
	 @Order(17)  
	void getIPOs() throws Exception {
		
		MvcResult m=mvc.perform(MockMvcRequestBuilders.get("/getIPOs"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].cName").value("hdfc"))
				.andReturn();
	}
	
	@Test
	 @Order(18)  
	void getIPOByName() throws Exception {
		
		MvcResult m=mvc.perform(MockMvcRequestBuilders.get("/getIPOByCompanyName?companyName=hdfc"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].totalNumberOfShares").value(1100))
				.andReturn();
	}
	@Test
	 @Order(19)  
	void getIPOChrono() throws Exception {
//		String json="{\"companyName\":\"POP\",\"turnover\":12040,\"ceo\":\"Ayush\",\"boardOfDirectors\":\"Ayush Faddo\",\"companyBrief\":\"veryRich\",\"sectorName\":\"JACK\"}";
//		MvcResult m=mvc.perform(MockMvcRequestBuilders.post("/addCompany")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(json))
//				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
//		
//		String json2="{\"com_name\":\"POP\",\"name\":\"BSE\"}";
//		MvcResult m2=mvc.perform(MockMvcRequestBuilders.post("/mapStockCompany")
//		.contentType(MediaType.APPLICATION_JSON)
//		.content(json2))
//		.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		
//		String json3="{\"remarks\": \"hihihih\",\r\n"
//				+ "    \"pricePerShare\": 200.90,\r\n"
//				+ "    \"totalNumberOfShares\": 1100,\r\n"
//				+ "    \"openDateTime\": \"2011-04-20 09:44:42\",\r\n"
//				+ "    \"companyName\": \"POP\"}";
//		MvcResult m1=mvc.perform(MockMvcRequestBuilders.post("/addIPO")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(json3))
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andReturn();
		MvcResult m4=mvc.perform(MockMvcRequestBuilders.get("/getIPOChronologically"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].openDateTime").value("2022-04-20 09:44:42"))
				.andReturn();
	}
	
	@Test
	 @Order(20)  
	void updateIPO() throws Exception {
		
		String json3="{\"remarks\": \"hihihih\",\r\n"
				+ "    \"pricePerShare\": 2001.90,\r\n"
				+ "    \"totalNumberOfShares\": 1100,\r\n"
				+ "    \"openDateTime\": \"2022-04-20 09:44:42\",\r\n"
				+ "    \"companyName\": \"hdfc\"}";
		MvcResult m1=mvc.perform(MockMvcRequestBuilders.put("/updateIPO")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json3))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		String content = m1.getResponse().getContentAsString();
		assertEquals("2",content);
	}
	
	@Test
	 @Order(21)  
	void addUser() throws Exception {
		
		String json="{\"name\": \"ayush\",\r\n"
				+ "    \"password\": \"ayush\",\r\n"
				+ "    \"email\": \"ayushtb7@gmail.com\",\r\n"
				+ "    \"confirmed\": true,\r\n"
				+ "    \"admin\": false,\r\n"
				+ "    \"mobilenumber\": \"9999\"}";
		mvc.perform(MockMvcRequestBuilders.post("/setuserapi")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	 @Order(22)  
	void getUsers() throws Exception {
		
		MvcResult m=mvc.perform(MockMvcRequestBuilders.get("/getUsers"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("ayush"))
				.andReturn();
	}
	
	@Test
	 @Order(23)  
	void getUserByNameAndPass() throws Exception {
		String json="{\"name\": \"ayush\",\r\n"
				+ "    \"password\": \"ayush\"}";
		MvcResult m=mvc.perform(MockMvcRequestBuilders.post("/getUserByNameAndPass")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("ayush"))
				.andReturn();
	}
	
	@Test
	 @Order(24)  
	void getUserDetailsByName() throws Exception {
		
		MvcResult m=mvc.perform(MockMvcRequestBuilders.get("/getUserByName?userName=ayush"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("ayush"))
				.andReturn();
	}
	
	@Test
	 @Order(25)  
	void addStockPrice() throws Exception {
		
		String json="{\"exchangename\": \"NSE\",\r\n"
				+ "    \"companycode\": \"hdfcNSE2\",\r\n"
				+ "    \"datee\": \"2014-06-01\",\r\n"
				+ "    \"timee\": \"10:20:00\",\r\n"
				+ "    \"shareprice\": 90.69}";
		mvc.perform(MockMvcRequestBuilders.post("/addStockPrice")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
//	@Test
//	 @Order(26)  
//	void getStockPrices() throws Exception {
//		
//		MvcResult m=mvc.perform(MockMvcRequestBuilders.get("/getStockPrices"))
//				.andExpect(MockMvcResultMatchers.status().isOk())
//				.andExpect(MockMvcResultMatchers.jsonPath("$[0].shareprice").value(90.69))
//				.andReturn();
//	}
	
	
	@Test
	 @Order(27)  
	void getStockPriceFromCNAME() throws Exception {
		
		MvcResult m=mvc.perform(MockMvcRequestBuilders.get("/getStockPriceFromCompanyName?companyName=hdfc"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].shareprice").value(90.69))
				.andReturn();
		String content = m.getResponse().getContentAsString();
		System.out.println(content);
	}
	
	@Test
	 @Order(28)  
	void getStockPriceinRange() throws Exception {
		
		
		String json="{\"companyName\": \"hdfc\",\r\n"
				+ "    \"start\": \"2014-05-01\",\r\n"
				+ "    \"end\": \"2014-07-01\"}";
		MvcResult m=mvc.perform(MockMvcRequestBuilders.post("/getCompanyStockPriceInRange")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0]").value(90.69))
				.andReturn();
		String content = m.getResponse().getContentAsString();
		System.out.println(content);
	}
}
