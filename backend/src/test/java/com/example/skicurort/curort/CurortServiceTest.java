package com.example.skicurort.curort;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.*;
import org.junit.jupiter.api.*;

class CurortServiceTest {

  private static final Curort CURORT = new Curort(1L, "Zakopane", "Długa", "a@a.a", 12345678L);
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