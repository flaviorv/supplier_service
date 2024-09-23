package com.provider_service.model.repository;

import com.provider_service.model.domain.Supplier;
import org.springframework.data.repository.CrudRepository;

public interface SupplierRespository extends CrudRepository<Supplier, Integer> {
}
