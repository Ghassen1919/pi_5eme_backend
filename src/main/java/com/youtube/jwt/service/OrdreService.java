package com.youtube.jwt.service;

import com.youtube.jwt.dao.InstrumentRepository;
import com.youtube.jwt.dao.OrdreRepository;
import com.youtube.jwt.dao.PortefeuilleRepository;
import com.youtube.jwt.dao.TransactionRepository;
import com.youtube.jwt.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrdreService implements IOrdreService {
    @Autowired
    private OrdreRepository ordreRepository;
    @Autowired
    private PortefeuilleRepository portefeuilleRepository;
    @Autowired
    private InstrumentRepository instrumentRepository;
    @Autowired
    private TransactionRepository transactionRepository;


    @Override
    public Ordre add(Ordre ordre) {
        // You can add additional business logic/validation here before saving to the repository
        return ordreRepository.save(ordre);
    }

    @Override
    public Ordre edit(Ordre ordre) {
        // Check if the ordre with the given ID exists before updating
        Optional<Ordre> existingOrdre = ordreRepository.findById(ordre.getIdOrdre());

        if (existingOrdre.isPresent()) {
            // Update the existing ordre with the new values
            Ordre updatedOrdre = ordreRepository.save(ordre);
            return updatedOrdre;
        } else {
            // Handle the case where the ordre with the given ID is not found
            // You might want to throw an exception or handle it according to your requirements
            return null;
        }
    }

    @Override
    public List<Ordre> selectAll() {
        return ordreRepository.findAll();
    }

    @Override
    public Ordre selectById(int id) {
        // You can add additional error handling or business logic as needed
        Optional<Ordre> ordre = ordreRepository.findById(id);
        return ordre.orElse(null);
    }

    @Override
    public void deleteById(int id) {
        // You can add additional error handling or business logic as needed
        ordreRepository.deleteById(id);
    }

    @Override
    public void sellInstrument(float quantite, float prix, String symbol) {
        // Get the username of the connected user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Find the portefeuille of the connected user
        Optional<Portefeuille> portefeuilleOptional = portefeuilleRepository.findByUserUsername(username);

        if (portefeuilleOptional.isPresent()) {
            Portefeuille portefeuille = portefeuilleOptional.get();

            // Find the instrument by symbol
            Optional<Instrument> instrumentOptional = instrumentRepository.findBySymboleAndPortefeuilleUserUsername(symbol, username);

            if (instrumentOptional.isPresent()) {
                Instrument instrument = instrumentOptional.get();

                // Check if the quantity is sufficient
                if (instrument.getQuantite() >= quantite) {
                    // Update the quantity of the instrument
                    instrument.setQuantite(instrument.getQuantite() - quantite);
                    instrumentRepository.save(instrument);



                    // Create a new Ordre with "Vente" as TypeOrdre
                    Ordre sellOrder = new Ordre();
                    sellOrder.setTypeOrdre(TypeOrdre.VENTE);
                    sellOrder.setQuantite(quantite);
                    sellOrder.setPrixLimite(prix);
                    sellOrder.setInstrument(instrument);
                    sellOrder.setUser(portefeuille.getUser()); // Add the connected user
                    ordreRepository.save(sellOrder);
                } else {

                }
            } else {

            }
        } else {

        }
    }

    @Override
    public void buyInstrument(Ordre ordre) {
        // Get the username of the connected user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Find the portefeuille of the connected user
        Optional<Portefeuille> portefeuilleOptional1 = portefeuilleRepository.findByUserUsername(username);
        Optional<Portefeuille> portefeuilleOptional2 = portefeuilleRepository.findByUserUsername(ordre.getUser().getUserName());
        if ((portefeuilleOptional1.isPresent())&&(portefeuilleOptional2.isPresent())) {
            Portefeuille portefeuille1 = portefeuilleOptional1.get();
            Portefeuille portefeuille2 = portefeuilleOptional1.get();
            // Check if the user has sufficient funds
            float totalCost = ordre.getQuantite() * ordre.getPrixLimite();
            if (portefeuille1.getSolde() >= totalCost) {
                Optional<Instrument> instrumentOptional1 = instrumentRepository.findBySymboleAndPortefeuilleUserUsername(ordre.getInstrument().getSymbole(), username);
                Optional<Instrument> instrumentOptional2 = instrumentRepository.findBySymboleAndPortefeuilleUserUsername(ordre.getInstrument().getSymbole(), ordre.getUser().getUserName());
                if ((instrumentOptional1.isPresent())&&(instrumentOptional2.isPresent())) {
                    Instrument instrument1 = instrumentOptional1.get();
                    Instrument instrument2 = instrumentOptional2.get();
                    instrument1.setQuantite(instrument1.getQuantite()+ordre.getQuantite());


                    portefeuille1.setSolde(portefeuille1.getSolde() - totalCost);
                    portefeuille2.setSolde(portefeuille2.getSolde() + totalCost);
                    portefeuilleRepository.save(portefeuille1);
                    portefeuilleRepository.save(portefeuille2);
                    instrumentRepository.save(instrument1);
                    instrumentRepository.save(instrument2);
                    Transaction transaction = new Transaction();
                    transaction.setDateTransaction(new Date());
                    transaction.setMontant(ordre.getQuantite() * ordre.getPrixLimite());

                    transaction.setBuyer(portefeuille1.getUser().getUserName());
                    transaction.setSeller(portefeuille2.getUser().getUserName());

                    // Set additional properties based on your requirements

                    // Save the transaction
                    transactionRepository.save(transaction);
                    ordreRepository.delete(ordre);

                }
                // Add the connected user to the list of users for the ordre

                // Save the ordre

            } else {

            }
        } else {

        }
    }

}
