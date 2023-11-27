package com.youtube.jwt.controller;

import com.youtube.jwt.entity.Ordre;
import com.youtube.jwt.service.IOrdreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ordres")
public class OrdreController {
    private IOrdreService ordreService;

    @GetMapping
    public List<Ordre> getAllOrdres() {
        return ordreService.selectAll();
    }

    @GetMapping("/{id}")
    public Ordre getOrdreById(@PathVariable Integer id) {
        return ordreService.selectById(id);
    }

    @PostMapping
    public Ordre saveOrdre(@RequestBody Ordre ordre) {
        return ordreService.add(ordre);
    }

    @DeleteMapping("/{id}")
    public void deleteOrdre(@PathVariable Integer id) {
        ordreService.deleteById(id);
    }
}
