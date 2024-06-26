package com.example.project.controller;

import com.example.project.service.ServiceLaunchPoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DecodedBarcodeController {

    private final ServiceLaunchPoint serviceLaunchPoint;

    public DecodedBarcodeController(ServiceLaunchPoint serviceLaunchPoint) {
        this.serviceLaunchPoint = serviceLaunchPoint;
    }

    @PostMapping("/api/barcode")
    public ResponseEntity<?> handleBarcode(@RequestBody BarcodeDTO barcodeDTO) {
        return ResponseEntity.ok(serviceLaunchPoint.processBarcode(barcodeDTO.getBarcode()));
    }

}

