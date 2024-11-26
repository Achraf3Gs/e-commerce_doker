package e_commerce.e_commerce.auth.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long CategoryId;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "CategoryName")
    private String CategoryName;


    @Column(name = "parentCategoryId")
    private long parentCategoryId;



}
