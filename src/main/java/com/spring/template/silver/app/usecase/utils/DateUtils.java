package com.spring.template.silver.app.usecase.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {

  public static ZonedDateTime convertUTCDate(Date date) {

    ZonedDateTime dateFm = null;
    if (date != null) {
      dateFm = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("GMT+07"));
    }

    return dateFm;
  }

  public static ZonedDateTime convertUTCDate(LocalDateTime date) {
    return date.atZone(ZoneId.of("GMT+07"));
  }

}