package com.example.mscourierservice.repository;

import com.example.mscourierservice.dao.entity.CourierEntity;
import com.example.mscourierservice.dao.entity.CourierStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourierRepository extends JpaRepository<CourierEntity, Long> {
    Optional<CourierEntity> findFirstByStatus(CourierStatus status);
}
