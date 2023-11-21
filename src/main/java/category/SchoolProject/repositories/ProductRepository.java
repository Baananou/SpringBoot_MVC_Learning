package category.SchoolProject.repositories;

import category.SchoolProject.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    /*public List<Product> findByNameContains(String mc);*/
    public Page<Product> findByNameContains(String mc, Pageable p);
    @Query("select p from  Product p where p.category.id=:x")
    public List<Product> searchProdByIdCat( @Param("x") String idC);




}
