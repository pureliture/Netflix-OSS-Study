package com.lguplus.nucube.order;

import com.lguplus.nucube.order.client.OrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@RestController
public class HelloController {

    @Autowired
    OrderClient orderClient;


    @GetMapping("/hello")
    public ResponseEntity<String> sayHello(){
        return new ResponseEntity<>("hello, order", HttpStatus.OK);
    }

    @GetMapping("/api")
    public ResponseEntity<String> getOderApi() {



        String productMessage = orderClient.getProductSayHello();

        String returnMessage = "order to : " + productMessage;

        return new ResponseEntity<>(returnMessage, HttpStatus.OK);

    }
}
