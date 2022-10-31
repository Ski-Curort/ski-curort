package com.example.skicurort.curort;

import static com.example.skicurort.curort.CurortMapper.mapToDTO;
import static com.example.skicurort.curort.CurortMapper.mapToDTOs;
import static com.example.skicurort.curort.CurortMapper.mapToEntity;

import com.example.skicurort.exception.NoIdException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurortService {

  private final CurortRepo curortRepo;

  public CurortDTO save(CurortDTO curortDto) {

    return mapToDTO(curortRepo.save(mapToEntity(curortDto)));
  }

  public List<CurortDTO> getAll() {
    return mapToDTOs(curortRepo.findAll());
  }

  public CurortDTO findById(Long id) throws NoIdException {

    return mapToDTO(curortRepo.findById(id).orElseThrow(() -> new NoIdException(id)));
  }

  public void delete(Long id) throws NoIdException {
    if (!curortRepo.existsById(id)) {
      throw new NoIdException(id);
    }
    curortRepo.deleteById(id);
  }

  public CurortDTO updateDto(CurortDTO curortDTO, Long id) throws NoIdException {

    Curort curort = curortRepo.findById(id).orElseThrow(() -> new NoIdException(id));

    curort.setCurortName(curortDTO.curortName());
    curort.setCurortAdress(curortDTO.curortAdress());
    curort.setCurortPhonenumber(curortDTO.curortPhonenumber());
    curort.setCurrortEmail(curortDTO.currortEmail());

    return mapToDTO(curortRepo.save(curort));
  }
}
