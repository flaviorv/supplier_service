package com.provider_service.controller;

import com.provider_service.model.domain.Supplier;
import com.provider_service.model.service.SupplierService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public ResponseEntity<List<Supplier>> getAll(){
        List<Supplier> suppliers = supplierService.findAll();
        if(suppliers.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(suppliers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getById(@PathVariable int id) {
        try {
            return ResponseEntity.ok(supplierService.findById(id));
        }catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Supplier> create(@RequestBody Supplier supplier) {
        try {
            supplierService.save(supplier);
            return ResponseEntity.ok(supplier);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Supplier> update(@PathVariable int id, @RequestBody Supplier supplier) {
        try {
            return ResponseEntity.ok(supplierService.update(id,supplier));
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Supplier> delete(@PathVariable int id) {
        try {

            return ResponseEntity.ok(supplierService.delete(id));
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
