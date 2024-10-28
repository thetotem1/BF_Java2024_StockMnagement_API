package be.bstorm.bf_java2024_stockmanagement.dal.repositories;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.person.Extern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExternRepository extends JpaRepository<Extern, UUID> {

    @Query("select count(e) > 0 from Extern e where e.email ilike :email")
    boolean existsByEmail(String email);
}
