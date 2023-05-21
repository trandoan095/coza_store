package com.cybersoft.cozastore.controller;

import com.cybersoft.cozastore.payload.request.CategoryRequest;
import com.cybersoft.cozastore.payload.response.BaseResponse;
import com.cybersoft.cozastore.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {
   @Autowired
   private HomeService homeService;
    @GetMapping("/category")
    public ResponseEntity<?> getCategory () {

        BaseResponse baseResponse = new BaseResponse();
       baseResponse.setData(homeService.getCategory());
    //List<CategoryRequest> list = homeService.getCategory();
       return new ResponseEntity<>(baseResponse,HttpStatus.OK);
   }
    }


