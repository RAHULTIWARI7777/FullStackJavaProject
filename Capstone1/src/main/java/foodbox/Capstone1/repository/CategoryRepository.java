package foodbox.Capstone1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import foodbox.Capstone1.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
