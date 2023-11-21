package category.SchoolProject.services;

import category.SchoolProject.entities.Category;
import category.SchoolProject.entities.Product;

import java.util.List;

public interface ICategoryService {
    public void saveCategory(Category c);
    public List<Category> getAllCategories();
    public void  deleteCategory(Long id);
    public void  getCategory(Long id);
}
