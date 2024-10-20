package com.sayan.enotes.util;

import com.sayan.enotes.dto.CategoryDto;
import com.sayan.enotes.exception.DtoValidationException;
import org.modelmapper.ValidationException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class Validation {

    public void categoryValidation(CategoryDto categoryDto){

        Map<String, Object> errorMap = new LinkedHashMap<>();

        if(ObjectUtils.isEmpty(categoryDto)){
            throw new IllegalArgumentException("CategoryDto is null");
        }else {
            //Name
            if(ObjectUtils.isEmpty(categoryDto.getName())){
                errorMap.put("name","name field is empty");
            }else if(categoryDto.getName().length()<4){
                errorMap.put("name","name length less than 4");
            }else if(categoryDto.getDescription().length() > 100){
                errorMap.put("name","description length should be less than 100");
            }

            //description
            if(ObjectUtils.isEmpty(categoryDto.getDescription())){
                errorMap.put("name","description field is empty");
            }else if(categoryDto.getDescription().length()<4){
                errorMap.put("name","description length less than 4");
            }else if(categoryDto.getDescription().length() > 500){
                errorMap.put("description","description length should be less than 100");
            }
        }

        if(!errorMap.isEmpty()){
            throw new DtoValidationException(errorMap);
        }
    }
}
