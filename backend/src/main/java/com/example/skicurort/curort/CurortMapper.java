package com.example.skicurort.curort;

import java.util.List;

public class CurortMapper {
  public CurortMapper() {}

  static CurortDTO mapToDTO(Curort curort) {
    return new CurortDTO(
        curort.getCurortName(),
        curort.getCurortAdress(),
        curort.getCurrortEmail(),
        curort.getCurortPhonenumber());
  }

  static Curort mapToEntity(CurortDTO curortDto) {
    Curort curort = new Curort();
    curort.setCurortName(curortDto.curortName());
    curort.setCurortAdress(curortDto.curortAdress());
    curort.setCurortPhonenumber(curortDto.curortPhonenumber());
    curort.setCurrortEmail(curortDto.currortEmail());
    return curort;
  }

  static List<CurortDTO> mapToDTOs(List<Curort> curorts) {
    return curorts.stream().map(CurortMapper::mapToDTO).toList();
  }
}
