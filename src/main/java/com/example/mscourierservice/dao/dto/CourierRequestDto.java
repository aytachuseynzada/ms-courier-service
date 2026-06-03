package com.example.mscourierservice.dao.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourierRequestDto {
    @NotBlank(message = "Name cannot be empty")
    private String name;
    @NotBlank(message = "Phone cannot be empty")
    private String phone;
}
