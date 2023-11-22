package category.SchoolProject.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty
    @Size(min = 1, max = 20)
    private String name;
    @Min(1)
    private double price;
    @Min(0)
    private int quantity;
    @ManyToOne
    @NotNull(message = "Category cannot be null")
    private Category category;
    private String image;

}
