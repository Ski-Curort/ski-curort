package com.example.skicurort.curort;

import com.example.skicurort.exception.Error;
import com.example.skicurort.exception.NoIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/curort")
public class CurortControler {

  private final CurortService curortService;

  @GetMapping("/")
  ResponseEntity<List<CurortDTO>> getAll() {
    return ResponseEntity.ok(curortService.getAll());
  }

  @PostMapping("/")
  public ResponseEntity<CurortDTO> save(@RequestBody CurortDTO curortDto) {

    curortService.save(curortDto);
    return ResponseEntity.ok(curortDto);
  }

  @GetMapping("/{id}")
  ResponseEntity<CurortDTO> findByid(@PathVariable Long id) throws NoIdException {
    return ResponseEntity.ok(curortService.findById(id));
  }

  @PatchMapping("/{id}")
  public ResponseEntity<CurortDTO> update(@RequestBody CurortDTO curortDTO, @PathVariable Long id) {

    curortService.updateDto(curortDTO, id);

    return ResponseEntity.ok(curortDTO);
  }

  @DeleteMapping("/{id}")
  public HttpStatus delete(@PathVariable Long id) throws NoIdException {

    curortService.delete(id);
    return HttpStatus.OK;
  }

  @ExceptionHandler(NoIdException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Error idNotFoundHandler(NoIdException ex) {

    return new Error(ex.getMessage());
  }
}
