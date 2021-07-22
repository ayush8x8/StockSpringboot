package com.phase3ayush.ayush.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phase3ayush.ayush.entities.Companystockexchangemap;

@Repository
public interface CompanystockexchangemapRepository extends JpaRepository<Companystockexchangemap, Long> {

}
