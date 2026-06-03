package com.example.mscourierservice.mapper;

import com.example.mscourierservice.dao.dto.CourierRequestDto;
import com.example.mscourierservice.dao.dto.CourierResponseDto;
import com.example.mscourierservice.dao.entity.CourierEntity;
import com.example.mscourierservice.dao.entity.CourierStatus;

public class CourierMapper {
    public static CourierEntity maptoEntity(CourierRequestDto dto) {
        return CourierEntity.builder()
                .name(dto.getName())
                .phone(dto.getPhone())
                .status(CourierStatus.FREE)
                .build();
    }
    public static CourierResponseDto maptoDto(CourierEntity courierEntity) {
        return CourierResponseDto.builder()
                .id(courierEntity.getId())
                .name(courierEntity.getName())
                .phone(courierEntity.getPhone())
                .status(courierEntity.getStatus().name())
                .createdAt(courierEntity.getCreatedAt())
                .updatedAt(courierEntity.getUpdatedAt())
                .build();
    }
}
