package com.youtube.jwt.controller;

import com.youtube.jwt.entity.Ordre;
<<<<<<< Updated upstream
import com.youtube.jwt.service.IOrdreService;
import org.springframework.beans.factory.annotation.Autowired;
=======
import com.youtube.jwt.service.Interfaces.IOrderService;
import lombok.AllArgsConstructor;
>>>>>>> Stashed changes
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
<<<<<<< Updated upstream
@RequestMapping("/ordres")
public class OrdreController {

    @Autowired
    private IOrdreService ordreService;
=======
@AllArgsConstructor
@RequestMapping("/ordres")
public class OrdreController {
    private IOrderService ordreService;
>>>>>>> Stashed changes

    @GetMapping
    public List<Ordre> getAllOrdres() {
        return ordreService.selectAll();
    }

    @GetMapping("/{id}")
    public Ordre getOrdreById(@PathVariable Integer id) {
        return ordreService.selectById(id);
    }

<<<<<<< Updated upstream
    @PostMapping("/sell")
    public void sellInstrument(@RequestBody Ordre ordre) {
        ordreService.sellInstrument(ordre.getQuantite(), ordre.getPrixLimite(), ordre.getInstrument().getSymbole());
    }

    @PostMapping("/buy")
    public void buyInstrument(@RequestBody Ordre ordre) {
        ordreService.buyInstrument(ordre);
=======
    @PostMapping
    public Ordre saveOrdre(@RequestBody Ordre ordre) {
        return ordreService.add(ordre);
>>>>>>> Stashed changes
    }

    @DeleteMapping("/{id}")
    public void deleteOrdre(@PathVariable Integer id) {
        ordreService.deleteById(id);
    }
}
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
