package Blacklist.Manager.repository;

import Blacklist.Manager.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByItemName(String itemName);
    @Query("SELECT e FROM Item e WHERE e.deleted = false")
    Page<Item> findAllNotDeleted(Pageable pageable);

}

