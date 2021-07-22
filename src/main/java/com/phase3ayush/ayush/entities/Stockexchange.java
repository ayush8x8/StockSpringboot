package com.phase3ayush.ayush.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "StockExchange")
public class Stockexchange {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	
	private String brief;
	private String address;
	private String remarks;
	
	
	@OneToMany(mappedBy="stockexchange")
	@JsonIgnore
	private List<Companystockexchangemap> compstockmap;

	public List<Companystockexchangemap> getCompstockmap() {
		return compstockmap;
	}

	public void setCompstockmap(List<Companystockexchangemap> compstockmap) {
		this.compstockmap = compstockmap;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public void setId(long id) {
		this.id = id;
	}
}
