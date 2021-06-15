package com.example.restaurant.persistence.entity;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_id")
    private Integer dishId;

    @Column(name = "dish_name", nullable = false)
    private String nameDish;

    @Column(name = "dish_comment", nullable = false)
    private String comment;

    @Column(name = "photo_path")
    private String photoPath;

    @Column(name = "dish_price")
    private Double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public String getNameDish() {
        return nameDish;
    }

    public void setNameDish(String nameDish) {
        this.nameDish = nameDish;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategoryId(Integer categoryId) {

        if (this.category == null) {
            this.setCategory(new Category());
        }
        this.getCategory().setCategoryId(categoryId);

    }
    public Integer getCategoryId() {
        return this.category == null ? null : this.category.getCategoryId();
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Objects.equals(dishId, dish.dishId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dishId);
    }
}
