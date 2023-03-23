package com.mlv.springloggingcustomannotation;

import com.mlv.springloggingcustomannotation.customannotation.LogReequest;
import com.mlv.springloggingcustomannotation.customannotation.LogResponse;
import com.mlv.springloggingcustomannotation.customannotation.LogTime;
import com.mlv.springloggingcustomannotation.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {

    @GetMapping("/name")
    @LogTime
    public String getName() throws InterruptedException {
        Thread.sleep(5000);
        return "Mano";

    }
    @GetMapping("/create/{id}/{name}")
    @LogReequest
    @LogResponse
    @LogTime
    public Product createProduct(@PathVariable("id") Integer id, @PathVariable("name") String name){

        return new Product(id,name);
    }
}
