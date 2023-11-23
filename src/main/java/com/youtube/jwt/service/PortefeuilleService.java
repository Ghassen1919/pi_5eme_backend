package com.youtube.jwt.service;



import com.youtube.jwt.dao.PortefeuilleRepository;
import com.youtube.jwt.entity.Portefeuille;
import com.youtube.jwt.service.IPortefeuilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PortefeuilleService implements IPortefeuilleService {
    @Autowired
    private PortefeuilleRepository portefeuilleRepository;

    @Override
    public Portefeuille add(Portefeuille portefeuille) {
        // You can add additional business logic/validation here before saving to the repository
        return portefeuilleRepository.save(portefeuille);
    }

    @Override
    public Portefeuille edit(Portefeuille portefeuille) {
        // Check if the portefeuille with the given ID exists before updating
        Optional<Portefeuille> existingPortefeuille = portefeuilleRepository.findById(portefeuille.getIdPortefeuille());

        if (existingPortefeuille.isPresent()) {
            // Update the existing portefeuille with the new values
            Portefeuille updatedPortefeuille = portefeuilleRepository.save(portefeuille);
            return updatedPortefeuille;
        } else {
            // Handle the case where the portefeuille with the given ID is not found
            // You might want to throw an exception or handle it according to your requirements
            return null;
        }
    }

    @Override
    public List<Portefeuille> selectAll() {
        return portefeuilleRepository.findAll();
    }

    @Override
    public Portefeuille selectById(int id) {
        // You can add additional error handling or business logic as needed
        Optional<Portefeuille> portefeuille = portefeuilleRepository.findById(id);
        return portefeuille.orElse(null);
    }

    @Override
    public void deleteById(int id) {
        // You can add additional error handling or business logic as needed
        portefeuilleRepository.deleteById(id);
    }
}

