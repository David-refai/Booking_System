package com.example.bookingsystem.dto;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDTO {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String city;
    private String country;
    private String postalCode;
    private String role;
    private String status;
}
