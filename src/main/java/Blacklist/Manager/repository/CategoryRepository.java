package Blacklist.Manager.repository;

import Blacklist.Manager.entity.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<ItemCategory, Long> {
    ItemCategory findByCategoryName(String categoryName);

}
