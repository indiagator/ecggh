package com.ecggh.testservice4fetchuserinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@SpringBootApplication
@EnableEurekaClient
@RestController
@RequestMapping("api/1.1")
@CrossOrigin
public class TestService4FetchuserinfoApplication {

    @Autowired
    public UserinfoRepository userinfoRepository;

    @Autowired
    public ProductofferRepository productofferRepository;

    @Autowired
    public ProductofferRepositoryJpa productofferRepositoryJpa;

    public static void main(String[] args) {
        SpringApplication.run(TestService4FetchuserinfoApplication.class, args);
    }


    @PostMapping("/userinfo")
    public Userinfo fetchSpecificUserInfo(@RequestParam("username") String username)
    {
        Optional<Userinfo> userinfo =  userinfoRepository.findById(username);

        return userinfo.get();
    }

    @PostMapping("/saveproductoffer")
    public Message saveProductOffer(@RequestParam("username") String username,@RequestParam("hscode") String hscode, @RequestParam("qty") int qty, @RequestParam("price") int price, @RequestParam("currency") String currency)
    {

        String offerid = (new Integer(((Double.valueOf(Math.random()*100000)).intValue()))).toString();

        Productoffer productoffer = new Productoffer();
        productoffer.setId(offerid);
        productoffer.setUsername(username);
        productoffer.setHscode(hscode);
        productoffer.setQty(qty);
        productoffer.setPrice(price);
        productoffer.setCurrency(currency);

        //productofferRepository.
        productofferRepositoryJpa.save(productoffer);

        Message msg = new Message();
        msg.setMsgString("new product offer saved");
        return msg;
    }

}
