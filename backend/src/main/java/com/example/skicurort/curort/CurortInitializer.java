package com.example.skicurort.curort;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurortInitializer implements InitializingBean {

  private final CurortService curortService;

  @Override
  public void afterPropertiesSet() throws Exception {
    curortService.save(
        new CurortDTO(
            null,
            "Ski resort in Zakopane",
            "Zakopane",
            "Długa ",
            "zakopane@ski-resort.com",
            123123120L));
    curortService.save(
        new CurortDTO(
            null,
            "Ski resort in Zieleniec",
            "Zieleniec",
            "Długa ",
            "zieleniec@ski-resort.com",
            123123121L));
    curortService.save(
        new CurortDTO(
            null,
            "Ski resort in Karpacz",
            "Karpacz",
            "Długa ",
            "karpacz@ski-resort.com",
            123123122L));
  }
}
