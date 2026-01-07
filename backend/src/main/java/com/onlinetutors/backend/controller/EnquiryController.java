 package com.onlinetutors.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlinetutors.backend.entity.Enquiry;
import com.onlinetutors.backend.repository.EnquiryRepository;
import com.onlinetutors.backend.service.EmailService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/enquiry")
@CrossOrigin(origins = "*")
public class EnquiryController {

    private final EnquiryRepository enquiryRepository;
    private final EmailService emailService;

    public EnquiryController(EnquiryRepository enquiryRepository, EmailService emailService) {
        this.enquiryRepository = enquiryRepository;
        this.emailService = emailService;
    }
    @GetMapping("/all")
    public List<Enquiry> getAllEnquiries() {
        return enquiryRepository.findAll();
    }
    @PutMapping("/status/{id}")
public void updateStatus(@PathVariable Long id,
                         @RequestParam String status) {
    Enquiry e = enquiryRepository.findById(id).orElseThrow();
    e.setStatus(status);
    enquiryRepository.save(e);
}
@DeleteMapping("/{id}")
public void deleteEnquiry(@PathVariable Long id) {
    enquiryRepository.deleteById(id);
}


    @PostMapping(consumes = "application/json")
    public Enquiry saveEnquiry(@RequestBody Enquiry enquiry) {

        enquiry.setStatus("NEW");

        // 1️⃣ Save to database
        Enquiry saved = enquiryRepository.save(enquiry);

        // 2️⃣ Admin email
        emailService.sendEnquiryMail(
            enquiry.getName(),
            enquiry.getEmail(),
            enquiry.getPhone(),
            enquiry.getGrade(),
            enquiry.getMessage()
        );

        // 3️⃣ Student auto-reply email (ONLY THIS LINE)
        emailService.sendStudentAutoReply(enquiry);

        return saved;
    }
}
