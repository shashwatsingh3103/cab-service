package com.springboot.cab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.cab.model.license;

public interface licenserepository extends JpaRepository<license, Integer> {

}
