package dev.shoxruhjon.avtoelon.controller;

import dev.shoxruhjon.avtoelon.dto.request.BookDto;
import dev.shoxruhjon.avtoelon.entity.BookEntity;
import dev.shoxruhjon.avtoelon.entity.CarEntity;
import dev.shoxruhjon.avtoelon.service.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/cars")
    public ResponseEntity<List<CarEntity>> getAllCars(){
        return ResponseEntity.ok(customerService.getAllCars());
    }

    @PostMapping("/car/book")
    public ResponseEntity<Void> bookCar(@RequestBody BookDto bookDto){
        boolean success = customerService.bookCar(bookDto);
        if (success) return ResponseEntity.status(HttpStatus.CREATED).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<CarEntity> getCarById(@PathVariable Long carId){
        CarEntity carEntity = customerService.getCarById(carId);
        if (carEntity == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(carEntity);
    }


    public ResponseEntity<List<BookEntity>> getBookingsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(customerService.getBookingsByUserId(userId));
    }
}
