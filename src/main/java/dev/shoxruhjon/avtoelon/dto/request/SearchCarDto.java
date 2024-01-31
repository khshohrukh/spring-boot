package dev.shoxruhjon.avtoelon.dto.request;

import lombok.Data;

@Data
public class SearchCarDto {
    private String brand;
    private String type;
    private String transmission;
    private String color;
}
