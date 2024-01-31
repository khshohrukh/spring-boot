package dev.shoxruhjon.avtoelon.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findByUser(UserEntity user);

    List<BookEntity> findAllByUserId(Long id);
}