package kevink.consumer.repository;

import kevink.consumer.entity.WordCountsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordCountsRepository extends JpaRepository<WordCountsEntity, Long> {
}
