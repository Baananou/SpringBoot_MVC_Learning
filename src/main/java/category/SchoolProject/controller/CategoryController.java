package category.SchoolProject.controller;

import category.SchoolProject.services.ICategoryService;
import category.SchoolProject.services.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class CategoryController {
    private ICategoryService categoryService;
    @GetMapping("/categories")
    public String getAllCategories(Model m){
        m.addAttribute("categories",categoryService.getAllCategories());
        return "categorie";
    }
}
