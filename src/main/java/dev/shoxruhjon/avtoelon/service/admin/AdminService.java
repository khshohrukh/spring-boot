package dev.shoxruhjon.avtoelon.service.admin;

import dev.shoxruhjon.avtoelon.dto.request.CarDto;
import dev.shoxruhjon.avtoelon.dto.request.CarDtoListDto;
import dev.shoxruhjon.avtoelon.dto.request.SearchCarDto;
import dev.shoxruhjon.avtoelon.entity.BookEntity;
import dev.shoxruhjon.avtoelon.entity.CarEntity;

import java.util.List;

public interface AdminService {

    boolean postCar(CarDto carDto);
    List<CarEntity> getAllCars();
    void deleteCar(Long id);
    CarDto getCarById(Long id);
    boolean updateCar(Long id, CarDto carDto);
    List<BookEntity> getBookings();
    boolean changeBookingStatus(Long bookingId, String status);
    CarDtoListDto searchCar(SearchCarDto searchCarDto);
}
