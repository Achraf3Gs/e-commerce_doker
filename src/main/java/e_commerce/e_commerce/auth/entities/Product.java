package e_commerce.e_commerce.auth.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ProductId;

    @NotBlank(message = "ProductSKU is mandatory")
    @Column(name = "ProductSKU")
    private String ProductSKU;

    @NotBlank(message = "ProductName is mandatory")
    @Column(name = "ProductName")
    private String ProductName;

    @NotBlank(message = "ProductShortName is mandatory")
    @Column(name = "ProductShortName")
    private String ProductShortName;

    @Column(name = "ProductPrice")
    private float ProductPrice;

    @Column(name = "DeliveryTimeSpan")
    private String DeliveryTimeSpan;

    @Column(name = "CreatedDate")
    private LocalDateTime CreatedDate;
    @Column(name = "LastModificactionDate")
    private LocalDateTime LastModificaction;

    @Column(name = "ProductImageUrl")
    private String ProductImageUrl;

    @Column(name = "ProductDescription")
    private String ProductDescription;

    /**** Many To One ****/

    /****
     *
     FetchType.LAZY = Doesn’t load the relationships unless explicitly “asked for” via getter
     FetchType.EAGER = Loads ALL relationships
     */

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Category category;


}


