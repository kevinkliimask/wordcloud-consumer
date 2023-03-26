package kevink.consumer.repository;

import jakarta.transaction.Transactional;
import kevink.consumer.entity.WordCountsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WordCountsRepository extends JpaRepository<WordCountsEntity, Long> {

    @Transactional
    WordCountsEntity findByMessageId(UUID uuid);

}
