package com.youtube.jwt.controller;

import com.youtube.jwt.entity.Instrument;
<<<<<<< Updated upstream
import com.youtube.jwt.service.IInstrumentService;
=======
import com.youtube.jwt.service.Interfaces.IInstrumentService;
>>>>>>> Stashed changes
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/instruments")
public class InstrumentController {
    private IInstrumentService instrumentService;

    @GetMapping
    public List<Instrument> getAllInstruments() {
        return instrumentService.selectAll();
    }

    @GetMapping("/{id}")
    public Instrument getInstrumentById(@PathVariable Integer id) {
<<<<<<< Updated upstream
        return instrumentService.selectById(id);
=======
        return instrumentService.selectBySymbol(id);
>>>>>>> Stashed changes
    }

    @PostMapping
    public Instrument saveInstrument(@RequestBody Instrument instrument) {
        return instrumentService.add(instrument);
    }

    @DeleteMapping("/{id}")
    public void deleteInstrument(@PathVariable Integer id) {
        instrumentService.deleteById(id);
    }
<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
