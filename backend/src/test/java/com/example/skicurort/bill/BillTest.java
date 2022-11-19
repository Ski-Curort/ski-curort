package com.example.skicurort.bill;

import com.example.skicurort.item.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BillTest {

private static final Item ITEM=new Item(1L, )
    private static final Bill BILL = new Bill(1L, "10", 29-11-1986, 1L, []
    private static final CurortDTO CURORT_DTO =
            new CurortDTO("Zakopane", "Długa", "a@a.a", 12345678L);
    private static final CurortDTO NEW_CURORT_DTO =
            new CurortDTO("Wisła", "Długa", "a@a.a", 12345678L);

    @Test
    void shouldMaptoDto() {
        // GIVEN
        // WHEN
        CurortDTO result = CurortMapper.mapToDTO(CURORT);
        // THEN
        assertEquals(CURORT_DTO, result);
    }

    @Test
    void shouldBeSave() {
        // GIVEN
        CurortRepo mockCurortRepo = mock(CurortRepo.class);
        when(mockCurortRepo.save(any())).thenReturn(CURORT);
        // WHEN
        CurortService curortService = new CurortService(mockCurortRepo);
        // THEN
        assertEquals(CURORT_DTO, curortService.save(CURORT_DTO));
    }

    @Test
    void shouldFindById() {
        // GIVEN
        CurortRepo mockCurortRepo = mock(CurortRepo.class);
        when(mockCurortRepo.findById(1L)).thenReturn(Optional.of(CURORT));
        // WHEN
        CurortService curortService = new CurortService(mockCurortRepo);
        // THEN
        assertEquals(CURORT_DTO, curortService.findById(1L));
    }

    @Test
    void shouldBeDelete() {
        // GIVEN
        CurortRepo mockCurortRepo = mock(CurortRepo.class);

        doNothing().when(mockCurortRepo).deleteById(any());
        when(mockCurortRepo.existsById(any())).thenReturn(true);
        // WHEN
        CurortService curortService = new CurortService(mockCurortRepo);
        curortService.delete(any());

        // THEN
        verify(mockCurortRepo).deleteById(any());
    }

    @Test
    void shouldThrowExceptionWhenDelete() {
        // GIVEN
        CurortRepo mockCurortRepo = mock(CurortRepo.class);
        CurortService curortService = new CurortService(mockCurortRepo);

        doNothing().when(mockCurortRepo).deleteById(any());

        // WHEN and THEN

        assertThatThrownBy(() -> curortService.delete(any())).isInstanceOf(Exception.class);
    }

    @Test
    void shouldUpdateDto() {
        // GIVEN
        CurortRepo mockCurortRepo = mock(CurortRepo.class);
        CurortService curortService = new CurortService(mockCurortRepo);
        // WHEN
        when(mockCurortRepo.findById(any())).thenReturn(Optional.of(CURORT));
        when(mockCurortRepo.save(any())).thenReturn(CURORT);
        curortService.updateDto(NEW_CURORT_DTO, 1L);
        // THEN
        assertEquals(CURORT.getCurortName(), NEW_CURORT_DTO.curortName());
    }

    @Test
    void shouldThrowExceptionWhenUseFindByWrongId() {

        // GIVEN
        CurortRepo mockCurortRepo = mock(CurortRepo.class);
        // WHEN
        when(mockCurortRepo.findById(any())).thenReturn(null);
        CurortService curortService = new CurortService(mockCurortRepo);
        // THEN

        assertThatThrownBy(() -> curortService.findById(any())).isInstanceOf(Exception.class);
    }
}

}