package com.example.demo.Repository;

import com.example.demo.Entity.doctor.doctorDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface doctorDetailsRepository extends JpaRepository<doctorDetails,Integer> {
}
