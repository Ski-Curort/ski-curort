package com.example.skicurort.equipment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.equalTo;
@ExtendWith(SpringExtension.class)
@WebMvcTest(EquipmentControler.class)
class EquipmentControlerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        EquipmentService equipmentService;

    @Test
    void when_add_New_Equipment_than_should_be_saved() throws Exception {

        //GIVEN
        EquipmentDTO equipmentDTO = new EquipmentDTO(1L,"snowboard","xxx",true,new BigDecimal(3.5));
        EquipmentDTO addedEquipment = new EquipmentDTO(2L,"ski","yyy",true,new BigDecimal(4.5));
        Mockito.when(equipmentService.addEquipment(equipmentDTO)).thenReturn(addedEquipment);
        //WHEN
        //THAN
        mockMvc.perform(MockMvcRequestBuilders.post("/api/equipment/newEquipment")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(
                        """
                               {   
                                "id" : 1,
                                "type" : "snowboard",
                                "mark" : "xxx",
                                "available" : true,
                                "cost" : 3.5
                               } 
                               """

                )).andExpect(MockMvcResultMatchers.jsonPath("$.id",equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type",equalTo("snowboard")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mark",equalTo("xxx")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.available",equalTo(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cost",equalTo(3.5)));
    }

    @Test
    void when_updateEquipment() throws Exception {
        //GIVEN
        Long id =1L;
        EquipmentDTO equipmentDTO = new EquipmentDTO(id,"snowboard","xxx",true,new BigDecimal(3.5));
        EquipmentDTO updatedEquipment = new EquipmentDTO(2L,"ski","yyy",true,new BigDecimal(4.5));
        Mockito.when(equipmentService.editEquipment(id,equipmentDTO)).thenReturn(updatedEquipment);
        //WHEN
        //THAN
        mockMvc.perform(MockMvcRequestBuilders.put("/api/equipment/update/"+id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                """
                                       {   
                                        "id" : 1,
                                        "type" : "snowboard",
                                        "mark" : "xxx",
                                        "available" : true,
                                        "cost" : 3.5
                                       } 
                                       """

                        )).andExpect(MockMvcResultMatchers.jsonPath("$.id",equalTo(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.type",equalTo("snowboard")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mark",equalTo("xxx")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.available",equalTo(true)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cost",equalTo(3.5)));

    }

    @Test
    void removeEquipment() {
        //GIVEN
        //WHEN
        //THAN
    }
}