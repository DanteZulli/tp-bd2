package com.grupoy.tpbd2.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.grupoy.tpbd2.model.Venta;

@Repository
public interface VentaRepository extends MongoRepository<Venta, Integer> {
}
