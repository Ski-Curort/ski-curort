package com.example.skicurort.equipment;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EquipmentInitializer implements InitializingBean {

  private final EquipmentService equipmentService;

  @Override
  public void afterPropertiesSet() throws Exception {
    equipmentService.addEquipment(
        new EquipmentDTO(null, "Google", "L", "4F", true, new BigDecimal("12.5")));
  }
}
