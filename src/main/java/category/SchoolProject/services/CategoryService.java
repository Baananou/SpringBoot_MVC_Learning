package category.SchoolProject.services;

import category.SchoolProject.entities.Category;
import category.SchoolProject.entities.Product;
import category.SchoolProject.repositories.CategoryRepository;
import category.SchoolProject.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {
    private CategoryRepository categoryRepository;

    @Override
    public void saveCategory(Category c) {
        categoryRepository.save((c));}

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void getCategory(Long id) {
        categoryRepository.findById(id).orElse(null);
    }
}
