package com.example.skicurort.curort;

import java.util.List;

public class CurortMapper {
  private CurortMapper() {}

  static CurortDTO mapCurortToCurortDTO(Curort curort) {
    return new CurortDTO(
        curort.getCurortName(),
        curort.getCurortAdress(),
        curort.getCurrortEmail(),
        curort.getCurortPhonenumber());
  }

  static Curort mapCurortDTOToCurort(CurortDTO curortDto) {
    Curort curort = new Curort();
    curort.setCurortName(curortDto.curortName());
    curort.setCurortAdress(curortDto.curortAdress());
    curort.setCurortPhonenumber(curortDto.curortPhonenumber());
    curort.setCurrortEmail(curortDto.currortEmail());
    return curort;
  }

  static List<CurortDTO> mapCurortsToCurortDTOs(List<Curort> curorts) {
    return curorts.stream().map(CurortMapper::mapCurortToCurortDTO).toList();
  }
}
