package com.cybersoft.cozastore.service;

import com.cybersoft.cozastore.entity.CategoryEntity;
import com.cybersoft.cozastore.payload.request.CategoryRequest;
import com.cybersoft.cozastore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {
    @Autowired
    private CategoryRepository categoryRepository;
    public List<CategoryRequest> getCategory(){

        List<CategoryRequest> categoryRequestList = new ArrayList<>();

        List<CategoryEntity> entityList = categoryRepository.findAll();

        for (CategoryEntity item:entityList
             ) {
            CategoryRequest categoryRequest = new CategoryRequest();

            categoryRequest.setName(item.getName());

            categoryRequest.setId(item.getId());

            categoryRequestList.add(categoryRequest);
        }

        return categoryRequestList;
    }
}
