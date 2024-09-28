package com.parcial.backend.model.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private Object data;
    private boolean success;
    private boolean error;
    private String message;
}
