package com.Assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Assignment.domain.Appointments;

@Repository
public interface AppointmentsRepository  extends JpaRepository<Appointments, Long> {

}
