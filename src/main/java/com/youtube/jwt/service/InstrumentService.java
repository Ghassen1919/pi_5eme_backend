package com.youtube.jwt.service;



import com.youtube.jwt.dao.InstrumentRepository;
import com.youtube.jwt.entity.Instrument;
import com.youtube.jwt.service.IInstrumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstrumentService implements IInstrumentService {
    @Autowired
    private InstrumentRepository instrumentRepository;

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
    public Instrument selectById(int id) {

        Optional<Instrument> instrument = instrumentRepository.findById(id);
        return instrument.orElse(null);
    }

    @Override
    public void deleteById(int id) {
        // You can add additional error handling or business logic as needed
        instrumentRepository.deleteById(id);
    }


}
