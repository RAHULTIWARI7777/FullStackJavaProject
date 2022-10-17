package foodbox.Capstone1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import foodbox.Capstone1.model.Category;
import foodbox.Capstone1.repository.CategoryRepository;





@Service
public class CategoryService {
	@Autowired
    private CategoryRepository categoryRepository;
	
	public List<Category> getAllcategories() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll() ;
	}

	
	public void addCategory(Category category) {
		// TODO Auto-generated method stub
		this.categoryRepository.save(category);
	}
	
	public Optional<Category> getcategorybyid(int id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id);
	}

	
	public void deleteategorybyid(int id) {
		// TODO Auto-generated method stub
		this.categoryRepository.deleteById(id);
	}

	
}
