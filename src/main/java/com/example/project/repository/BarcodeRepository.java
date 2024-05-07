package com.example.project.repository;

import com.example.project.model.Barcode;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BarcodeRepository extends MongoRepository<Barcode, String> {
}