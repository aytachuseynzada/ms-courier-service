package com.example.mscourierservice.dao.dto;

import com.example.mscourierservice.dao.entity.CourierStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCourierStatusRequest {
    @NotNull(message = "Courier status is required")
    private CourierStatus status;
}
