package com.example.demo.dto;

import lombok.Data;

@Data
public class GuestDto {
    private int id;
    private String name;
    private String lastName;
    private String middleName;
    private Education education;

    private class Education {
        private int course;
        private int group;
        private String direction;
    }
}
