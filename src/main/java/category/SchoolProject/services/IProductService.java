package category.SchoolProject.services;

import category.SchoolProject.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

public interface IProductService {
public void saveProduct(Product p, MultipartFile mf) throws IOException;
public List<Product> getAllProducts();
public Page<Product> getProductsByMc(String mc, Pageable p);
public List<Product> getProductsByCat(String idCat);
public void  deleteProduct(Long id);
public Product getProduct(Long id);




}
