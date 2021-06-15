package com.example.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;



@SpringBootTest
@WebAppConfiguration
@Transactional
@Rollback
class RestaurantApplicationTests {


}
