package com.example.skicurort.equipment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
class EquipmentServiceTest {
@MockBean
EquipmentRepository equipmentRepository;
    @Test
    void getAll() {
    }

    @Test
    void getAllEquipmentByType() {
    }

    @Test
    void when_add_new_Equipment_than_add_to_repository() {

    }

    @Test
    void editEquipment() {
    }

    @Test
    void deleteEquipment() {
    }
}