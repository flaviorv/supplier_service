package com.provider_service.model.service;

import com.provider_service.model.domain.Supplier;
import com.provider_service.model.repository.SupplierRespository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    SupplierRespository supplierRespository;

    public List<Supplier> findAll() {
        return (ArrayList<Supplier>) supplierRespository.findAll();
    }

    public Supplier findById(int id) {
        Optional<Supplier> supplier = supplierRespository.findById(id);
        if(supplier.isPresent()) {
            return supplier.get();
        }
        throw new EntityNotFoundException("Supplier with id " + id + " not found");
    }

    public Supplier save(Supplier supplier) {
        return supplierRespository.save(supplier);
    }

    public Supplier delete(int id) {
        Optional<Supplier> supplier = supplierRespository.findById(id);
        if(supplier.isPresent()) {
            supplierRespository.deleteById(id);
            return supplier.get();
        }
        throw new EntityNotFoundException("Supplier with id " + id + " not found");
    }

    public Supplier update(int id, Supplier newSup) {
        Optional<Supplier> old = supplierRespository.findById(id);
        if (old.isPresent()) {
            if(newSup.getName() != null){old.get().setName(newSup.getName());}
            if(newSup.getCnpj() != null) {old.get().setCnpj(newSup.getCnpj());}
            if(newSup.getEmail() != null) {old.get().setEmail(newSup.getEmail());}
            if(newSup.getPhone() != null) {old.get().setPhone(newSup.getPhone());}

            return supplierRespository.save(old.get());
        }
        throw new EntityNotFoundException("Supplier not found");
    }
}
