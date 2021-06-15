package com.example.restaurant.service;
import com.example.restaurant.cache.data.BasketData;
import com.example.restaurant.persistence.entity.Dish;
import com.example.restaurant.persistence.entity.OrderItem;
import com.example.restaurant.persistence.entity.OrderList;
import com.example.restaurant.persistence.entity.User;
import com.example.restaurant.persistence.repository.OrderListRepository;
import com.example.restaurant.translator.BasketDataTranslator;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderListService {

    @Autowired
    private OrderListRepository orderListRepository;

    @Autowired
    OrderItemService orderItemService;

    @Autowired
    private BasketDataTranslator basketDataTranslator;

    @Transactional
    public void saveListFromBasket(BasketData basketData, User user) {
        OrderList orderList = basketDataTranslator.userBasketDataToOrder(basketData, user);
        //donationListRepository.save(donationList);
        // DonationList savedList = donationListRepository.save(donationList);
        //??????????????
        //donationItemService.saveDonationItems(donationList.getDonationItems());

    }

    private Map<Dish, Long> groupByDish(List<OrderItem> orderItems) {
        return orderItems.stream().collect(Collectors.groupingBy(OrderItem::getDish, Collectors.counting()));
    }

    public Iterable<OrderList> findListsByUser(User user) {
        Iterable<OrderList> result = new ArrayList<>();//donationListRepository.findAllByUserOrderByCreatedDate(user);
        Iterator iterator = result.iterator();
        while (iterator.hasNext()) {
            OrderList orderList = (OrderList) iterator.next();
            List<OrderItem> orderItemList = Lists.newArrayList(orderItemService.getOrderItems(orderList));
            orderList.setGroupedCategories(groupByDish(orderItemList));

        }
        return result;


    }


}
