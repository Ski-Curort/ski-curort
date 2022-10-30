package com.example.skicurort.curort;

import com.example.skicurort.exception.NoIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.skicurort.curort.CurortMapper.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/curort")
public class CurortControler {

  private final CurortService curortService;

  @GetMapping("/")
  List<CurortDTO> getAll() {
    return mapCurortsToCurortDTOs(curortService.getAll());
  }

  @PostMapping("/")
  public ResponseEntity<CurortDTO> save(@RequestBody CurortDTO curortDto) {

    curortService.save(mapCurortDTOToCurort(curortDto));
    return ResponseEntity.ok(curortDto);
  }

  @GetMapping("/{id}")
  CurortDTO findByid(@PathVariable Long id) throws NoIdException {
    return mapCurortToCurortDTO(curortService.findById(id));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<String> update(@RequestBody CurortDTO curortDTO, @PathVariable Long id) {

    curortService.updateDto(curortDTO, id);

    return ResponseEntity.ok("Done " + curortDTO);
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable Long id) throws NoIdException {

    curortService.delete(id);
    return "Delete object with id: " + id + " sucesfull";
  }

  @ExceptionHandler(NoIdException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String idNotFoundHandler(NoIdException ex) {

    return ex.getMessage();
  }
}
