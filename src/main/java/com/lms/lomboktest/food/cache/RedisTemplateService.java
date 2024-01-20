package com.lms.lomboktest.food.cache;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.lomboktest.food.model.Food;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisTemplateService {

    private static final String CACHE_KEY = "FOOD";
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;
    private HashOperations<String, String, String> hashOperations;

    @PostConstruct
    public void init(){
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void save(Food foodDto){
        if(foodDto == null || foodDto.getFood() == null){
            log.error("Required Values must not be null");
            return;
        }

        try{
            hashOperations.put(CACHE_KEY,
                    foodDto.getFood(),
                    serializeFoodDto(foodDto));
            log.info("[RedisTemplateService save success] id: {}", foodDto.getFood());
        }catch(Exception e){
            log.error("[RedisTemplateService save error] {}", e.getMessage());
        }
    }

    public List<Food> findAll(){

        try{
            List<Food> list = new ArrayList<>();
            for(String value : hashOperations.entries(CACHE_KEY).values()){
                Food foodDto = deserializeFoodDto(value);
                list.add(foodDto);
            }
            log.info("[RedisTemplateService findAll success] list: {}", list.toString());
            return list;

        }catch (Exception e){
            log.error("[RedisTemplateService findAll error] {}", e.getMessage());
            return Collections.emptyList();
        }

    }

    public void delete(Long id){
        hashOperations.delete(CACHE_KEY, String.valueOf(id));
        log.info("[RedisTemplateService delete]: {} ", id);
    }

    private String serializeFoodDto(Food foodDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(foodDto);
    }

    private Food deserializeFoodDto(String value) throws JsonProcessingException {
        return objectMapper.readValue(value, Food.class);
    }








}
