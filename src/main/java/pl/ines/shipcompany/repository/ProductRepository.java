package pl.ines.shipcompany.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.ines.shipcompany.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.thickness=:thickness")
    List<Product> getProductsWhereThicknessIs(@Param("thickness") int thickness);

    @Query("select p from Product p where p.width=:width")
    List<Product> getProductsWhereWidthIs(@Param("width") int width);

    @Query("select p from Product p where p.length=:length")
    List<Product> getProductsWhereLengthIs(@Param("length") int length);

    @Query("select p from Product p where p.quantity=:quantity")
    List<Product> getProductsWhereQuantityIs(@Param("quantity") int quantity);

    @Query("select p from Product p order by p.thickness")
    List <Product> orderByThickness();

    @Query("select p from Product p order by p.id")
    List <Product> orderById();

    @Query("select p from Product p order by p.width")
    List <Product> orderByWidth();

    @Query("select p from Product p order by p.length")
    List <Product> orderByLength();

    @Query("select p from Product p order by p.quantity")
    List <Product> orderByQuantity();

    @Query("select p from Product p order by p.grade")
    List <Product> orderByGrade();

    @Query("select p from Product p order by p.tolerance")
    List <Product> orderByTolerance();

}
