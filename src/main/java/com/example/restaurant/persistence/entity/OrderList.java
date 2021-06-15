package com.example.restaurant.persistence.entity;


import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "order_list")
public class OrderList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_list_id")
    private Integer orderListId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "orderList", fetch = FetchType.LAZY, cascade = CascadeType.ALL)//не с rest  полей, а с помощью getOrderItem()(slowly)
    private List<OrderItem> orderItems;

    @Column(name = "created_date", nullable = false)
    private Date createdDate = new Date();

    @Transient//don`t save attribute
    private Map<Dish, Long> groupedCategories;

    public Integer getOrderListId() {
        return orderListId;
    }

    public void setOrderListId(Integer orderListId) {
        this.orderListId = orderListId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Map<Dish, Long> getGroupedCategories() {
        return groupedCategories;
    }

    public void setGroupedCategories(Map<Dish, Long> groupedCategories) {
        this.groupedCategories = groupedCategories;
    }
}
