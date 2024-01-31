package dev.shoxruhjon.avtoelon.repository;

import dev.shoxruhjon.avtoelon.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
}