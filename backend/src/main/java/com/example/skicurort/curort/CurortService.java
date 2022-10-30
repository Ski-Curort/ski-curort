package com.example.skicurort.curort;

import com.example.skicurort.exception.NoIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CurortService {

  private final CurortRepo curortRepo;

  public void save(Curort curort) {
    curortRepo.save(curort);
  }

  public List<Curort> getAll() {
    return curortRepo.findAll();
  }

  public Curort findById(Long id) throws NoIdException {
    return curortRepo.findById(id).orElseThrow(() -> new NoIdException(id));
  }

  public void delete(Long id) throws NoIdException {
    if (!curortRepo.existsById(id)) {
      throw new NoIdException(id);
    }
    curortRepo.deleteById(id);
  }

  public void updateDto(CurortDTO curortDTO, Long id) throws NoIdException {
    if (curortRepo.existsById(id)) {
      curortRepo.findById(id).get().setCurortName(curortDTO.curortName());
      curortRepo.findById(id).get().setCurortAdress(curortDTO.curortAdress());
      curortRepo.findById(id).get().setCurortPhonenumber(curortDTO.curortPhonenumber());
      curortRepo.findById(id).get().setCurrortEmail(curortDTO.currortEmail());

    } else {
      throw new NoIdException(id);
    }
  }
}
