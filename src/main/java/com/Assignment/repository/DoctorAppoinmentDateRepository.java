package com.Assignment.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Assignment.domain.DoctorAppoinmentDate;



@Repository
public interface DoctorAppoinmentDateRepository extends JpaRepository<DoctorAppoinmentDate, Long> {
	
	@Query( value = "SELECT * FROM doctor_appoinment_date", nativeQuery = true)
	List<DoctorAppoinmentDate> listDate();

	

}
