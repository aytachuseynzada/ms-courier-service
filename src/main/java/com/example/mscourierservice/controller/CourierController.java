package com.example.mscourierservice.controller;

import com.example.mscourierservice.dao.dto.CourierRequestDto;
import com.example.mscourierservice.dao.dto.CourierResponseDto;
import com.example.mscourierservice.dao.dto.UpdateCourierStatusRequest;
import com.example.mscourierservice.service.CourierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/couriers")
@RequiredArgsConstructor
public class CourierController {
    private final CourierService courierService;
    @PostMapping
    @ResponseStatus(CREATED)
    public void createCourier(@RequestBody CourierRequestDto dto) {
        courierService.createCourier(dto);
    }
    @GetMapping("/available")
    public CourierResponseDto getAvailableCourier(@PathVariable Long id) {
        return courierService.getAvailableCourier(id);
    }
    @GetMapping("/{id}")
    public CourierResponseDto getCourierById(@PathVariable Long id) {
        return courierService.getCourierById(id);
    }
    @GetMapping
    public List<CourierResponseDto> getAllCouriers(){
        return courierService.getAllCouriers();
    }
    @PatchMapping("/{id}/status")
    public CourierResponseDto updateCourierStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCourierStatusRequest request ) {
        return courierService.updateCourierStatus(id, request);
    }
}
