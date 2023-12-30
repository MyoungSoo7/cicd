package com.lms.lomboktest.food.dto.naver;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchLocalItem{
    private String title;  // 검색 결과 업체, 기관명을 나타낸다.
    private String link;  // 검색 결과 업체, 기관의 상세 정보가 제공되는 네이버 페이지의 하이퍼텍스트 link를 나타낸다.
    private String category; // 검색 결과 업체, 기관의 분류 정보를 제공한다.
    private String description;  // 검색 결과 업체, 기관명에 대한 설명을 제공한다.
    private String telephone;  // 빈 문자열 반환. 과거에 제공되던 항목이라 하위 호환성을 위해 존재한다.
    private String address;  // 검색 결과 업체, 기관명의 주소를 제공한다.
    private String roadAddress;  // 검색 결과 업체, 기관명의 도로명 주소를 제공한다.
    private double mapx;  // 검색 결과 업체, 기관명 위치 정보의 x좌표를 제공한다. 제공값은 카텍좌표계 값으로 제공된다. 이 좌표값은 지도 API와 연동 가능하다.
    private double mapy;  // 검색 결과 업체, 기관명 위치 정보의 y좌표를 제공한다. 제공값은 카텍 좌표계 값으로 제공된다. 이 좌표값은 지도 API와 연동 가능하다.

    private String bloggerName;  // 빈 문자열 반환. 과거에 제공되던 항목이라 하위 호환성을 위해 존재한다.
    private String id;
    private String categoryGroupCode;
    private double distance;
}