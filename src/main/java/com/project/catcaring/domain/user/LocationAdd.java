package com.project.catcaring.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LocationAdd {

      S1 ("LOCATION_서울시_강남구"),
      S2 ("LOCATION_서울시_강동구"),
      S3 ("LOCATION_서울시_강북구"),
      S4 ("LOCATION_서울시_강서구"),
      S5 ("LOCATION_서울시_관악구"),
      S6 ("LOCATION_서울시_광진구"),
      S7 ("LOCATION_서울시_구로구"),
      S8 ("LOCATION_서울시_금천구"),
      S9 ("LOCATION_서울시_노원구"),
      S10 ("LOCATION_서울시_도봉구"),
          S11 ("LOCATION_서울시_동대문구"),
          S12 ("LOCATION_서울시_동작구"),
          S13 ("LOCATION_서울시_마포구"),
          S14 ("LOCATION_서울시_서대문구"),
          S15 ("LOCATION_서울시_서초구"),
          S16 ("LOCATION_서울시_성동구"),
          S17 ("LOCATION_서울시_성북구"),
          S18 ("LOCATION_서울시_송파구"),
          S19 ("LOCATION_서울시_양천구"),
          S20 ("LOCATION_서울시_영등포구"),
          S21 ("LOCATION_서울시_용산구"),
          S22 ("LOCATION_서울시_은평구"),
          S23 ("LOCATION_서울시_종로구"),
          S24 ("LOCATION_서울시_중구"),
          S25 ("LOCATION_서울시_중량구");

  private final String key;

}
