package br.com.masterclass.superpecas.controller;

import br.com.masterclass.superpecas.Components.Pageable.CPageable;
import br.com.masterclass.superpecas.DTO.CarroDTO;
import br.com.masterclass.superpecas.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @PostMapping
    public ResponseEntity<Page<CarroDTO>> findAll(@RequestBody CPageable pageable) {
        return ResponseEntity.ok(carroService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarroDTO> getCarroById(@PathVariable Integer id) {
        CarroDTO carroDTO = carroService.findById(id);
        if (carroDTO != null) {
            return ResponseEntity.ok(carroDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/salvar")
    public ResponseEntity<CarroDTO> createCarro(@RequestBody CarroDTO carroDTO) {
        CarroDTO savedCarro = carroService.save(carroDTO);
        return ResponseEntity.ok(savedCarro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarroDTO> updateCarro(@PathVariable Integer id, @RequestBody CarroDTO carroDTO) {
        CarroDTO updatedCarro = carroService.update(id, carroDTO);
        if (updatedCarro != null) {
            return ResponseEntity.ok(updatedCarro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarro(@PathVariable Integer id) {
        if (carroService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}