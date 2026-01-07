 package com.onlinetutors.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinetutors.backend.entity.Enquiry;

public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {
}
