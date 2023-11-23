package com.youtube.jwt.controller;

import com.youtube.jwt.entity.Portefeuille;
import com.youtube.jwt.service.IPortefeuilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/portefeuilles")
public class PortefeuilleController {
    private final IPortefeuilleService portefeuilleService;

    @Autowired
    public PortefeuilleController(IPortefeuilleService portefeuilleService) {
        this.portefeuilleService = portefeuilleService;
    }

    @GetMapping
    public List<Portefeuille> getAllPortefeuilles() {
        return portefeuilleService.selectAll();
    }

    @GetMapping("/{id}")
    public Portefeuille getPortefeuilleById(@PathVariable Integer id) {
        return portefeuilleService.selectById(id);
    }

    @PostMapping
    public Portefeuille savePortefeuille(@RequestBody Portefeuille portefeuille) {
        return portefeuilleService.add(portefeuille);
    }

    @PutMapping("/{id}")
    public Portefeuille updatePortefeuille(@PathVariable Integer id, @RequestBody Portefeuille portefeuille) {
        portefeuille.setIdPortefeuille(id);
        return portefeuilleService.edit(portefeuille);
    }

    @DeleteMapping("/{id}")
    public void deletePortefeuille(@PathVariable Integer id) {
        portefeuilleService.deleteById(id);
    }
}

