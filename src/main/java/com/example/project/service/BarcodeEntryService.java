package com.example.project.service;

import com.example.project.model.Barcode;
import com.example.project.repository.BarcodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BarcodeEntryService {
    @Autowired
    private BarcodeRepository barcodeRepository;

    public Barcode createBarcodeEntry(Barcode barcode, String userEmail) {
        return barcodeRepository.save(barcode);
    }
}