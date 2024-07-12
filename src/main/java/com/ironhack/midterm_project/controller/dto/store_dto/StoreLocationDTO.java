package com.ironhack.midterm_project.controller.dto.store_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StoreLocationDTO {
    @NotBlank(message = "Location cannot be blank")
    private String location;
}
