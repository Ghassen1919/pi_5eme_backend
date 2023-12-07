package com.youtube.jwt.service;

<<<<<<< Updated upstream
import com.youtube.jwt.dao.InstrumentRepository;
import com.youtube.jwt.dao.OrdreRepository;
import com.youtube.jwt.dao.PortefeuilleRepository;
import com.youtube.jwt.dao.TransactionRepository;
import com.youtube.jwt.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
=======
import com.youtube.jwt.dao.OrdreRepo;
import com.youtube.jwt.entity.Ordre;
import com.youtube.jwt.service.Interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

>>>>>>> Stashed changes
import java.util.List;
import java.util.Optional;

import static com.youtube.jwt.entity.TypeTransaction.Sell;

@Service
<<<<<<< Updated upstream
public class OrdreService implements IOrdreService {
    @Autowired
    private OrdreRepository ordreRepository;
    @Autowired
    private PortefeuilleRepository portefeuilleRepository;
    @Autowired
    private InstrumentRepository instrumentRepository;
    @Autowired
    private TransactionRepository transactionRepository;

=======
public class OrdreService implements IOrderService {
    @Autowired
    private OrdreRepo ordreRepository;
>>>>>>> Stashed changes

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
    public List<Ordre> selectbuy() {
     String  username = SecurityContextHolder.getContext().getAuthentication().getName();

        return ordreRepository.findAllByTypeOrdreAndUserUserNameNot(TypeOrdre.VENTE,username);
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

<<<<<<< Updated upstream
    @Override
    public void sellInstrument(float quantite, float prix, String symbol) {
        try {
            // Get the username of the connected user
            String username = SecurityContextHolder.getContext().getAuthentication().getName();

            // Find the portefeuille of the connected user
            Optional<Portefeuille> portefeuilleOptional = portefeuilleRepository.findByUserUserName(username);

            if (portefeuilleOptional.isPresent()) {
                Portefeuille portefeuille = portefeuilleOptional.get();

                // Find the instrument by symbol
                Optional<Instrument> instrumentOptional = portefeuille.getInstrument()
                        .stream()
                        .filter(instrument -> instrument.getSymbole().equals(symbol))
                        .findFirst();

                if (instrumentOptional.isPresent()) {
                    Instrument instrument = instrumentOptional.get();

                    // Check if the quantity is sufficient
                    if (instrument.getQuantite() >= quantite) {
                        // Update the quantity of the instrument
                        instrument.setQuantite(instrument.getQuantite() - quantite);
                        instrumentRepository.save(instrument);

                        // Create a new Ordre with TypeOrdre.VENTE or handle unknown values
                        Ordre sellOrder = new Ordre();
                        try {
                            sellOrder.setTypeOrdre(TypeOrdre.valueOf("VENTE")); // Use "VENTE" or "ACHAT"
                        } catch (IllegalArgumentException e) {
                            // Handle unknown enum values here (e.g., log a warning)
                            sellOrder.setTypeOrdre(TypeOrdre.VENTE); // Set a default value
                        }
                        sellOrder.setQuantite(quantite);
                        sellOrder.setPrixLimite(prix);
                        sellOrder.setInstrument(instrument);
                        sellOrder.setUser(portefeuille.getUser()); // Add the connected user
                        ordreRepository.save(sellOrder);
                    } else {
                        // Handle insufficient quantity exception
                        throw new InsufficientQuantityException("Insufficient quantity for selling instrument");
                    }
                } else {
                    // Handle missing instrument exception
                    throw new InstrumentNotFoundException("Instrument not found with symbol: " + symbol);
                }
            } else {
                // Handle missing portefeuille exception
                throw new PortefeuilleNotFoundException("Portefeuille not found for user: " + username);
            }
        } catch (Exception | InsufficientQuantityException e) {
            // Handle generic exception
            e.printStackTrace(); // Replace with appropriate logging or rethrow as needed
        } catch (InstrumentNotFoundException e) {
            throw new RuntimeException(e);
        } catch (PortefeuilleNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void buyInstrument(Ordre ordre) {
        try {
            // Get the username of the connected user
            String username = SecurityContextHolder.getContext().getAuthentication().getName();

            // Find the portefeuille of the connected user
            Optional<Portefeuille> portefeuilleOptional1 = portefeuilleRepository.findByUserUserName(username);
            Optional<Portefeuille> portefeuilleOptional2 = portefeuilleRepository.findByUserUserName(ordre.getUser().getUserName());

            if (portefeuilleOptional1.isPresent() && portefeuilleOptional2.isPresent()) {
                Portefeuille portefeuille1 = portefeuilleOptional1.get();
                Portefeuille portefeuille2 = portefeuilleOptional2 .get();

                // Check if the user has sufficient funds
                float totalCost = ordre.getQuantite() * ordre.getPrixLimite();

                if (portefeuille1.getSolde() >= totalCost) {
                    Optional<Instrument> instrumentOptional1 = portefeuille1.getInstrument()
                            .stream()
                            .filter(instrument -> instrument.getSymbole().equals(ordre.getInstrument().getSymbole()))
                            .findFirst();
                    Optional<Instrument> instrumentOptional2 = portefeuille2.getInstrument()
                            .stream()
                            .filter(instrument -> instrument.getSymbole().equals(ordre.getInstrument().getSymbole()))
                            .findFirst();
                    if (instrumentOptional1.isPresent() && instrumentOptional2.isPresent()) {
                        Instrument instrument1 = instrumentOptional1.get();
                        Instrument instrument2 = instrumentOptional2.get();
                        instrument1.setQuantite(instrument1.getQuantite() + ordre.getQuantite());

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
                    } else {
                        throw new InstrumentNotFoundException("Instrument not found for the given user.");
                    }
                } else {
                    throw new InsufficientFundsException("Insufficient funds for the user.");
                }
            } else {
                throw new UserNotFoundException("User not found.");
            }
        } catch (Exception e) {
            // Handle the exception or log it
            e.printStackTrace();
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstrumentNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
=======
    // Implement your additional business logic or methods here
    // Example: public List<Ordre> getOrdresByType(String type) { ... }
}
>>>>>>> Stashed changes
