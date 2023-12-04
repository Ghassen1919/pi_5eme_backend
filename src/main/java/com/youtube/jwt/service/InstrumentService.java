package com.youtube.jwt.service;

<<<<<<< Updated upstream


import com.youtube.jwt.dao.InstrumentRepository;
import com.youtube.jwt.entity.Instrument;
import com.youtube.jwt.service.IInstrumentService;
=======
import com.youtube.jwt.dao.InstrumentRepo;
import com.youtube.jwt.entity.Instrument;
import com.youtube.jwt.service.Interfaces.IInstrumentService;
>>>>>>> Stashed changes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstrumentService implements IInstrumentService {
    @Autowired
<<<<<<< Updated upstream
    private InstrumentRepository instrumentRepository;
=======
    private InstrumentRepo instrumentRepository;
>>>>>>> Stashed changes

    @Override
    public Instrument add(Instrument instrument) {
        // You can add additional business logic/validation here before saving to the repository
        return instrumentRepository.save(instrument);
    }

    @Override
    public Instrument edit(Instrument instrument) {
        // Check if the instrument with the given ID exists before updating
        Optional<Instrument> existingInstrument = instrumentRepository.findById(instrument.getIdInstrument());

        if (existingInstrument.isPresent()) {
            // Update the existing instrument with the new values
            Instrument updatedInstrument = instrumentRepository.save(instrument);
            return updatedInstrument;
        } else {
            // Handle the case where the instrument with the given ID is not found
            // You might want to throw an exception or handle it according to your requirements
            return null;
        }
    }

    @Override
    public List<Instrument> selectAll() {
        return instrumentRepository.findAll();
    }

    @Override
<<<<<<< Updated upstream
    public Instrument selectById(int id) {

=======
    public Instrument selectBySymbol(int id) {
        // You can add additional error handling or business logic as needed
>>>>>>> Stashed changes
        Optional<Instrument> instrument = instrumentRepository.findById(id);
        return instrument.orElse(null);
    }

    @Override
    public void deleteById(int id) {
<<<<<<< Updated upstream
        // You can add additional error handling or business logic as needed
=======

>>>>>>> Stashed changes
        instrumentRepository.deleteById(id);
    }


<<<<<<< Updated upstream
}
=======
}
>>>>>>> Stashed changes
