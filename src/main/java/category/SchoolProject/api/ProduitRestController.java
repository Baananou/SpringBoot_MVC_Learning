package category.SchoolProject.api;

import category.SchoolProject.entities.Product;
import category.SchoolProject.services.IProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
public class ProduitRestController {
    private IProductService productService;
    @GetMapping("/all")
    public List<Product> getAllProducts(@RequestParam(name = "mc", defaultValue = "") String mc,
                                        @RequestParam(name = "size", defaultValue = "6" ) int size,
                                        @RequestParam(name = "page", defaultValue = "0") int page)
    {
        Page<Product> liste= productService.getProductsByMc(mc, PageRequest.of(page,size));
        return liste.getContent();
    }
    @DeleteMapping("/remove/{id}")
    public String deleteProduct(@PathVariable(name = "id") long idProduct)
    {
        productService.deleteProduct(idProduct);
        return "Deleted Successfully";
    }

    @PostMapping("/add")
    public void  saveProduct(@RequestParam("product") String p, @RequestParam("file")
    MultipartFile mf) throws IOException {
        Product product = new ObjectMapper().readValue(p,Product.class);
        productService.saveProduct(product, mf);
    }

    @GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable long id) throws IOException {
        return productService.getImage(id);
    }
}
