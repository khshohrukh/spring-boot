package dev.shoxruhjon.avtoelon.service.customer;

import dev.shoxruhjon.avtoelon.dto.request.BookDto;
import dev.shoxruhjon.avtoelon.dto.request.CarDtoListDto;
import dev.shoxruhjon.avtoelon.dto.request.SearchCarDto;
import dev.shoxruhjon.avtoelon.entity.BookEntity;
import dev.shoxruhjon.avtoelon.entity.CarEntity;

import java.util.List;

public interface CustomerService {
    List<CarEntity> getAllCars();
    boolean bookCar(BookDto bookDto);
    CarEntity getCarById(Long carId);
    List<BookEntity> getBookingsByUserId(Long userId);
    CarDtoListDto searchCar(SearchCarDto searchCarDto);
}
