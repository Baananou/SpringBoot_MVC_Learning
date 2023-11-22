package category.SchoolProject.services;

import category.SchoolProject.entities.Product;
import category.SchoolProject.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@Service
@AllArgsConstructor
public class ProductService implements IProductService {
    private ProductRepository productRepository;
    @Override
    public void saveProduct(Product p ,MultipartFile mf) throws IOException {
        if (!mf.isEmpty()){
            p.setImage(saveImage(mf));
        }
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

    private String saveImage(MultipartFile mf) throws IOException {
      String nameImg= mf.getOriginalFilename();
      String tab[]= nameImg.split("\\.");
      String newNameImg = tab[0]+ System.currentTimeMillis()+"."+tab[1];
      File file = new ClassPathResource("static/images").getFile();
      String filePath= file.getAbsolutePath();
      Path p= Paths.get(filePath,newNameImg);
      Files.write(p, mf.getBytes());

     return newNameImg;
    }
}
