package category.SchoolProject.services;

import category.SchoolProject.entities.Product;
import category.SchoolProject.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ProductService implements IProductService {
    private ProductRepository productRepository;
    @Override
    public void saveProduct(Product p) {
        productRepository.save(p);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> getProductsByMc(String mc, Pageable p) {
        return productRepository.findByNameContains(mc, p);
    }

    @Override
    public List<Product> getProductsByCat(String idCat) {
        return productRepository.searchProdByIdCat(idCat);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
