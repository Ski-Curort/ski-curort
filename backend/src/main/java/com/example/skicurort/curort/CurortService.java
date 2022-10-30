package com.example.skicurort.curort;

import com.example.skicurort.exception.NoIdException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import static com.example.skicurort.curort.CurortMapper.*;

@Service
@RequiredArgsConstructor
public class CurortService {

  private final CurortRepo curortRepo;

  public void save(CurortDTO curortDto) {
    curortRepo.save(mapToEntity(curortDto));
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

  public Curort updateDto(CurortDTO curortDTO, Long id) throws NoIdException {

    return curortRepo
        .findById(id)
        .map(
            curort -> {
              curort.setCurortName(curortDTO.curortName());
              curort.setCurortAdress(curortDTO.curortAdress());
              curort.setCurortPhonenumber(curortDTO.curortPhonenumber());
              curort.setCurrortEmail(curortDTO.currortEmail());
              return curortRepo.save(curort);
            })
        .orElseThrow(() -> new NoIdException(id));
  }
}
