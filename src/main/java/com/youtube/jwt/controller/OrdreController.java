package com.youtube.jwt.controller;

import com.youtube.jwt.dao.OrdreRepository;
import com.youtube.jwt.entity.Ordre;
import com.youtube.jwt.entity.TypeOrdre;
import com.youtube.jwt.service.IOrdreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordres")
public class OrdreController {

    @Autowired
    private IOrdreService ordreService;

    private OrdreRepository ordreRepository;

    @GetMapping
    public List<Ordre> getAllOrdres() {
        return ordreService.selectAll();
    }

    @GetMapping("/{id}")
    public Ordre getOrdreById(@PathVariable Integer id) {
        return ordreService.selectById(id);
    }

    @PostMapping("/sell")
    public void sellInstrument(@RequestBody Ordre ordre) {
        ordreService.sellInstrument(ordre.getQuantite(), ordre.getPrixLimite(), ordre.getInstrument().getSymbole());
    }

    @PostMapping("/buy")
    public void buyInstrument(@RequestBody Ordre ordre) {
        ordreService.buyInstrument(ordre);
    }

    @DeleteMapping("/{id}")
    public void deleteOrdre(@PathVariable Integer id) {
        ordreService.deleteById(id);
    }

    @GetMapping("/listbuy")
            public List<Ordre> OrdreBuy() {
        return ordreRepository.findAllByTypeOrdre(TypeOrdre.VENTE);

    }}
