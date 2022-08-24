package com.example.birthdaydemo.controllers;

import com.example.birthdaydemo.models.BirthdayResponse;
import com.example.birthdaydemo.services.BirthdayService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
@Validated
public class BirthdayController {

    private final BirthdayService birthdayService;

    public BirthdayController(BirthdayService birthdayService) {
        this.birthdayService = birthdayService;
    }

    @GetMapping("/birthday")
    public ResponseEntity<BirthdayResponse> calculateBirthdayDetails(@RequestParam() int month, @RequestParam int day) {

        long startTime = System.currentTimeMillis();
        BirthdayResponse br = this.birthdayService.calculateBirthdayDetails(month, day);
        long endTime = System.currentTimeMillis();
        br.setProcessTime((int) (endTime - startTime));
        return ResponseEntity.ok().body(br);
    }

    @GetMapping("/birthday-sync")
    public ResponseEntity<BirthdayResponse> calculateBirthdayDetailsSync(@RequestParam() int month, @RequestParam int day) {
        long startTime = System.currentTimeMillis();
        BirthdayResponse br = this.birthdayService.calculateBirthdayDetailsSync(month, day);
        long endTime = System.currentTimeMillis();
        br.setProcessTime((int) (endTime - startTime));
        return ResponseEntity.ok().body(br);
    }
}
