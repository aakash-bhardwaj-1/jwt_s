package com.example.demo.JWT;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class JwtRequestDTO {
    private int doctor_id;
    private String password;
}
