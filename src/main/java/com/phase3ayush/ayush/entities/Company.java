package com.phase3ayush.ayush.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Company")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String companyName;

	@Column(nullable = false)
	private Double turnover;

	@Column(nullable = false)
	private String ceo;

	@Column(nullable = false)
//	@Type(type = "text")
	private String boardOfDirectors;

	@Column(nullable = false)
//	@Type(type = "text")
	private String companyBrief;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "company", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private IPODetail ipo;

	@OneToMany(mappedBy="company", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Companystockexchangemap> compstockmap;

	@ManyToOne(fetch = FetchType.EAGER)
//	@JsonIgnore
	private Sector sector;
	
	@OneToMany(mappedBy="company", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<StockPrice> stockprice;
	
	private String sectorName;

	protected Company() {

	}

	public Company(String companyName, double turnover, String ceo, String boardOfDirectors, String companyBrief) {

		super();

		this.companyName = companyName;

		this.turnover = turnover;

		this.ceo = ceo;

		this.boardOfDirectors = boardOfDirectors;

		this.companyBrief = companyBrief;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Double getTurnover() {
		return turnover;
	}

	public void setTurnover(Double turnover) {
		this.turnover = turnover;
	}

	public String getCeo() {
		return ceo;
	}

	public void setCeo(String ceo) {
		this.ceo = ceo;
	}

	public String getBoardOfDirectors() {
		return boardOfDirectors;
	}

	public void setBoardOfDirectors(String boardOfDirectors) {
		this.boardOfDirectors = boardOfDirectors;
	}

	public String getCompanyBrief() {
		return companyBrief;
	}

	public void setCompanyBrief(String companyBrief) {
		this.companyBrief = companyBrief;
	}

	public IPODetail getIpo() {
		return ipo;
	}

	public void setIpo(IPODetail ipo) {
		this.ipo = ipo;
	}
	
	

	public List<StockPrice> getStockprice() {
		return stockprice;
	}

	public void setStockprice(List<StockPrice> stockprice) {
		this.stockprice = stockprice;
	}

	public List<Companystockexchangemap> getCompstockmap() {
		return compstockmap;
	}

	public void setCompstockmap(List<Companystockexchangemap> compstockmap) {
		this.compstockmap = compstockmap;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public String getSectorName() {
		return sectorName;
	}

	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}
	
	

}

//Build the rest of the getter setters and constructors
