package com.example.mscourierservice.service;

import com.example.mscourierservice.dao.dto.CourierRequestDto;
import com.example.mscourierservice.dao.dto.CourierResponseDto;
import com.example.mscourierservice.dao.dto.UpdateCourierStatusRequest;
import com.example.mscourierservice.dao.entity.CourierEntity;
import com.example.mscourierservice.dao.entity.CourierStatus;
import com.example.mscourierservice.exception.CourierNotFoundException;
import com.example.mscourierservice.exception.InvalidCourierStatusException;
import com.example.mscourierservice.mapper.CourierMapper;
import com.example.mscourierservice.repository.CourierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourierService {

    private final CourierRepository courierRepository;

    public void createCourier(CourierRequestDto dto){
        var courier = CourierMapper.maptoEntity(dto);
        courierRepository.save(courier);
    }
    public CourierResponseDto getAvailableCourier(Long id) {
        var courier = courierRepository
                .findFirstByStatus(CourierStatus.FREE)
                .orElseThrow(() ->
                        new RuntimeException("No available courier found"));
        return CourierMapper.maptoDto(courier);
    }
    public CourierResponseDto getCourierById(Long id) {
        var courier = courierRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Courier not found"));
        return CourierMapper.maptoDto(courier);
    }
    public List<CourierResponseDto> getAllCouriers(){
        var couriers = courierRepository.findAll()
                .stream()
                .map(CourierMapper::maptoDto)
                .toList();
        return couriers;
    }
    public CourierResponseDto updateCourierStatus(Long id, UpdateCourierStatusRequest request) {
        CourierEntity courier = courierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Courier not found"));
        courier.setStatus(request.getStatus());
        CourierEntity savedCourier = courierRepository.save(courier);
        return CourierMapper.maptoDto(savedCourier);
    }
    private CourierEntity findCourierById(Long id) {
        return courierRepository.findById(id)
                .orElseThrow(() -> new CourierNotFoundException("Courier not found with id: " + id));
    }

    private void validateStatusTransition(CourierStatus currentStatus, CourierStatus nextStatus) {
        if (currentStatus == nextStatus) {
            throw new InvalidCourierStatusException("Courier is already " + nextStatus);
        }
    }
    public void markCourierAsOnDelivery(Long courierId) {
        CourierEntity courier = findCourierById(courierId);

        validateStatusTransition(courier.getStatus(), CourierStatus.BUSY);

        courier.setStatus(CourierStatus.BUSY);
        courierRepository.save(courier);
    }

    public void markCourierAsAvailable(Long courierId) {
        CourierEntity courier = findCourierById(courierId);

        validateStatusTransition(courier.getStatus(), CourierStatus.FREE);

        courier.setStatus(CourierStatus.FREE);
        courierRepository.save(courier);
    }
}

