package uz.mohirdev.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.mohirdev.spring.entity.PostData;

@Repository
public interface PostDataRepository extends JpaRepository<PostData, Long> {

}
