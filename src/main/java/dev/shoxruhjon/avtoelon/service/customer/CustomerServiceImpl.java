package dev.shoxruhjon.avtoelon.service.customer;

import dev.shoxruhjon.avtoelon.dto.request.BookDto;
import dev.shoxruhjon.avtoelon.dto.request.CarDtoListDto;
import dev.shoxruhjon.avtoelon.dto.request.SearchCarDto;
import dev.shoxruhjon.avtoelon.entity.BookEntity;
import dev.shoxruhjon.avtoelon.entity.BookRepository;
import dev.shoxruhjon.avtoelon.entity.CarEntity;
import dev.shoxruhjon.avtoelon.entity.UserEntity;
import dev.shoxruhjon.avtoelon.entity.enums.BookCarStatus;
import dev.shoxruhjon.avtoelon.repository.CarRepository;
import dev.shoxruhjon.avtoelon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<CarEntity> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public boolean bookCar(BookDto bookDto) {
        Optional<CarEntity> carEntity = carRepository.findById(bookDto.getId());
        Optional<UserEntity> userEntity = userRepository.findById(bookDto.getUserId());
        if (carEntity.isPresent() && userEntity.isPresent()){
            BookEntity book = getBookEntity(bookDto, carEntity, userEntity);
            bookRepository.save(book);
            return true;
        }
        return false;
    }

    @Override
    public CarEntity getCarById(Long carId) {
        return carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + carId));
    }

    @Override
    public List<BookEntity> getBookingsByUserId(Long userId) {
        return bookRepository.findAllByUserId(userId);
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

    private static BookEntity getBookEntity(BookDto bookDto, Optional<CarEntity> carEntity, Optional<UserEntity> userEntity) {
        CarEntity existingCar = carEntity.get();
        BookEntity book = new BookEntity();
        book.setUser(userEntity.get());
        book.setCar(existingCar);
        book.setBookCarStatus(BookCarStatus.PENDING);
        long diffInMilliSeconds = bookDto.getToDate().getTime() - bookDto.getFromDate().getTime();
        long days = TimeUnit.MICROSECONDS.toDays(diffInMilliSeconds);
        book.setDays(days);
        book.setPrice(existingCar.getPrice().multiply(new BigDecimal(days)));
        return book;
    }
}
