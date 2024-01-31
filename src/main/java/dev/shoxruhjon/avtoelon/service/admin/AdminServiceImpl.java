package dev.shoxruhjon.avtoelon.service.admin;

import dev.shoxruhjon.avtoelon.dto.request.CarDto;
import dev.shoxruhjon.avtoelon.dto.request.CarDtoListDto;
import dev.shoxruhjon.avtoelon.dto.request.SearchCarDto;
import dev.shoxruhjon.avtoelon.entity.BookEntity;
import dev.shoxruhjon.avtoelon.entity.BookRepository;
import dev.shoxruhjon.avtoelon.entity.CarEntity;
import dev.shoxruhjon.avtoelon.entity.enums.BookCarStatus;
import dev.shoxruhjon.avtoelon.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;

    @Override
    public boolean postCar(CarDto carDto) {
        CarEntity carEntity = modelMapper.map(carDto, CarEntity.class);
        carRepository.save(carEntity);
        return true;
    }

    @Override
    public List<CarEntity> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public CarDto getCarById(Long id) {
        CarEntity carEntity = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id : " + id));
        return modelMapper.map(carEntity, CarDto.class);
    }

    @Override
    public boolean updateCar(Long id, CarDto carDto) {
        CarEntity carEntity = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        if (carDto.getName() != null)
            carEntity.setName(carEntity.getName());
        if (carDto.getBrand() != null)
            carEntity.setBrand(carDto.getBrand());
        if(carDto.getPrice() != null)
            carEntity.setColor(carEntity.getColor());
        if(carDto.getDescription() != null)
            carEntity.setDescription(carDto.getDescription());
        if(carDto.getTransmission() != null)
            carEntity.setTransmission(carDto.getTransmission());
        if(carDto.getType() != null)
            carEntity.setType(carDto.getType());
        carRepository.save(carEntity);
        return true;
    }

    @Override
    public List<BookEntity> getBookings() {
        return bookRepository.findAll();
    }

    @Override
    public boolean changeBookingStatus(Long bookingId, String status) {
        Optional<BookEntity> optionalBookEntity = bookRepository.findById(bookingId);
        if (optionalBookEntity.isPresent()){
            BookEntity existingBookCar = optionalBookEntity.get();
            if (Objects.equals(status, "Approve"))
                existingBookCar.setBookCarStatus(BookCarStatus.REJECTED);
            bookRepository.save(existingBookCar);
            return true;
        }
        return false;
    }

    @Override
    public CarDtoListDto searchCar(SearchCarDto searchCarDto) {
        CarEntity carEntity = modelMapper.map(searchCarDto, CarEntity.class);
        ExampleMatcher exampleMatcher =
                ExampleMatcher.matchingAll()
                        .withMatcher("brand", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher("transmission", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                        .withMatcher("color", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        Example<CarEntity> carEntityExample = Example.of(carEntity, exampleMatcher);
        List<CarEntity> carEntityList = carRepository.findAll(carEntityExample);
        return null;
    }
}
