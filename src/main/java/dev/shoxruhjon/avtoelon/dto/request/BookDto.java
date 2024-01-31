package dev.shoxruhjon.avtoelon.dto.request;

import dev.shoxruhjon.avtoelon.entity.enums.BookCarStatus;
import lombok.Data;

import java.util.Date;

@Data

public class BookDto {

    private Long id;
    private Date fromDate;
    private Date toDate;
    private Long days;
    private Long price;
    private BookCarStatus bookCarStatus;
    private Long carId;
    private Long userId;
}
