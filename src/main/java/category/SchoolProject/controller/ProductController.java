package category.SchoolProject.controller;

import category.SchoolProject.entities.Product;
import category.SchoolProject.services.ICategoryService;
import category.SchoolProject.services.IProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
public class ProductController {
    private IProductService productService;
    private ICategoryService categoryService;
    @GetMapping("/user/index")
    public String getAllProducts(@RequestParam(name = "mc", defaultValue = "") String mc,
                                 @RequestParam(name = "size", defaultValue = "6" ) int size,
                                 @RequestParam(name = "page", defaultValue = "0") int page,
                                 Model m){
/*
        List<Product> liste= productService.getAllProducts();
*/
        m.addAttribute("mc",mc);
        Page<Product> liste= productService.getProductsByMc(mc, PageRequest.of(page,size));
        m.addAttribute("products",liste.getContent());
        m.addAttribute("pages", new int[liste.getTotalPages()]);
        m.addAttribute("currentPage", liste.getNumber());
        return "vue";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") long idProduct){
        productService.deleteProduct(idProduct);
        return "redirect:/user/index";
    }

    @GetMapping("/admin/create")
    public String showAddProductForm(Model m){
        m.addAttribute("categories",categoryService.getAllCategories());
        m.addAttribute("product",new Product());
        return "addProduct";
    }

    @PostMapping("/admin/add")
    public String addProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult, Model m, @RequestParam(name = "file") MultipartFile mf) throws IOException {
        if (bindingResult.hasErrors()){
            m.addAttribute("categories",categoryService.getAllCategories());

            return "addProduct";
        }
        productService.saveProduct(product, mf);
        return "redirect:/user/index";
    }

    @GetMapping("/admin/edit/{id}")
    public String showUpdateForm(@PathVariable(name = "id") long idProduct, Model m) {
        Product p = productService.getProduct(idProduct);
        m.addAttribute("product", p);
        m.addAttribute("categories", categoryService.getAllCategories());
        return "editProduct";
    }
    @PostMapping("/admin/update/{id}")
    public String updateProduct(@PathVariable(name = "id") long idProduct, @ModelAttribute Product updatedProduct, @RequestParam(name = "file") MultipartFile mf) throws IOException {
        updatedProduct.setId(idProduct);
        productService.saveProduct(updatedProduct, mf);

        return "redirect:/user/index";
    }
    @GetMapping("/")
    public String home(){
        return "redirect:/user/index";
    }
}
