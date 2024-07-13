package com.lms.lomboktest.food.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.*;

@Entity(name = "food")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Food {

    @Id
    private String food;
    private Long searchCnt;

    @Version
    private Long version;

    public Food(String food, Long searchCnt) {
        this.food = food;
        this.searchCnt = searchCnt;
    }

    public void increaseSearchCnt() {
        searchCnt += 1;
    }

}
