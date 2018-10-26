package pl.ines.shipcompany.model;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int thickness;
    private int width;
    private int length;
    private int quantity;
    private String grade;
    private String tolerance;

    public Product() {
    }

//    public Product(int thickness, int width, int length, int quantity, String grade, String tolerance) {
//        this.thickness = thickness;
//        this.width = width;
//        this.length = length;
//        this.quantity = quantity;
//        this.grade = grade;
//        this.tolerance = tolerance;
//    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTolerance() {
        return tolerance;
    }

    public void setTolerance(String tolerance) {
        this.tolerance = tolerance;
    }

    @Override
    public String toString() {
        return "Product{" +
                "thickness=" + thickness +
                ", width=" + width +
                ", length=" + length +
                ", quantity=" + quantity +
                ", grade='" + grade + '\'' +
                ", tolerance='" + tolerance + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
