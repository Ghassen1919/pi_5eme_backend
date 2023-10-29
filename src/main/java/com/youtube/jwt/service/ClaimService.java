package com.youtube.jwt.service;

import com.youtube.jwt.dao.ClaimRepo;
import com.youtube.jwt.dao.TransatioRepo;
import com.youtube.jwt.dao.UserDao;
import com.youtube.jwt.entity.Claim;

import com.youtube.jwt.entity.Transaction;
import com.youtube.jwt.entity.TypeClaim;
import com.youtube.jwt.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClaimService implements IclaimService {
    @Autowired
    private UserDao userDao;
    private EmailService emailService;
    /*
    @Autowired
    private TransactionRepo transactionRepo;
    */
    private TransatioRepo transationRepo;
    private ClaimRepo claimRepo;

    /*private EmailService emailService;
    @Autowired
    private com.example.pi_ease.Services.Interface.EmailService emailServiceM;



/*
    @Override
    public Claim edit(Claim cl) {
        return claimRepo.save(cl);
    }
*/
    @Override
    public void modifier(Integer id, boolean traite) {
        Claim cl = claimRepo.findById(id).get();
        cl.setTraiteClaim(traite);
        Calendar cal = Calendar.getInstance();
        Date datetraite = cal.getTime();
        cl.setDateTraite(datetraite);
        String email = cl.getUserc().getEmail();
        claimRepo.save(cl);
        this.emailService.sendSimpleEmail(email, "Status of your claim", "your complaint is processed");
        //emailServiceM.sendEmailRegister(user);
    }

    @Override
    public String getForbiddenWords() {
        String content = "";
        try {

            File file = new File("C:\\Users\\ASUS\\OneDrive\\Bureau\\badwords.txt");
            content = new String(Files.readAllBytes(file.toPath()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return content.trim();

    }

    @Override
    public void ajouter(MultipartFile file, String desc, TypeClaim type, String t) {
        Claim cl = new Claim();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")) {
            System.out.println("not a a valid file");
        }
        try {
            cl.setAttachClaim(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean traite = false;
        /*cl.setAttachClaim(attach);*/
        cl.setType(type);
        cl.setDescClaim(desc);
        cl.setTraiteClaim(traite);
        Calendar cal = Calendar.getInstance();
        Date dateclaim = cal.getTime();
        cl.setDateClaim(dateclaim);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            String currentUserName = authentication.getName();
            System.out.println("Current User Name: " + currentUserName);
            User user = userDao.findByUserName(currentUserName);
            cl.setUserc(user);

            claimRepo.save(cl);
            cl.setRefclaim(String.valueOf(cl.getIdClaim()) + "." + cl.getDateClaim());

            cl.setRefTR(t);
            claimRepo.save(cl);




       /* if ((idt != 0 )&& (idc == 0)&&(cl.getType()==TypeClaim.Transaction)){
            Transaction tr =transactionRepo.findById(idt).get();
            cl.setRefTR("la transaction numero"+tr.getIdTraction());
            claimRepo.save(cl);
        } else if ((idt == 0) && (idc!=0)&&(cl.getType()==TypeClaim.Credit)){
            Credit cr =creditRepo.findByIdAndUserCredit(idc,user);
            cl.setRefCR("le credit numero"+cr.getId());
            claimRepo.save(cl);
        }*/
            ArrayList<String> badwords = new ArrayList<>(Arrays.asList(getForbiddenWords().split(",")));

            System.out.println(badwords);
            int alertCount = 0; // Initialize the alert count outside the loop

            for (String word : badwords) {
                String lowerDescription = cl.getDescClaim().toLowerCase();

                if (lowerDescription.contains(word.toLowerCase())) {
                    // Replace forbidden word with asterisks
                    lowerDescription = lowerDescription.replace(word.toLowerCase(), "****");
                    cl.setDescClaim(lowerDescription);

                    // Increment the alert count
                    alertCount++;

                    claimRepo.save(cl);
                }
            }

            if (alertCount > 0) {
                int updatedAlertCount = user.getCount() + alertCount;
                user.setCount(updatedAlertCount);
                System.out.println(updatedAlertCount);
                userDao.save(user);

                if (updatedAlertCount >= 3) {
                    user.setUserActive(false);
                    userDao.save(user);
                    this.emailService.sendSimpleEmail(user.getEmail(), "Account blocked", "Your account is now blocked due to your bad behavior.");
                }
            }
        }
    }


    @Override
    public List<Claim> selectAll() {
        return claimRepo.findAll();
    }

    @Override
    public List<Claim> search(String q) {
        return claimRepo.findByRefclaim(q);
    }

    @Override
    public List<Claim> search2(Date q) {
        return claimRepo.findByDateClaim(q);
    }

    public List<Claim>getclaimperuser(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            String currentUserName = authentication.getName();
            System.out.println("Current User Name: " + currentUserName);
            User user = userDao.findByUserName(currentUserName);


            List<Claim> l1 = claimRepo.findByUserc(user);
            return l1;
        }
        return null;

    }

    public List<String> claimtran() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            String currentUserName = authentication.getName();
            System.out.println("Current User Name: " + currentUserName);
            User user = userDao.findByUserName(currentUserName);
            List<Transaction> list1 = transationRepo.findByUserc(user);
            List<String> transactionIds = list1.stream()
                    .map(Transaction::getRefTransaction) // Assuming getId() is the method to get the ID from a Transaction object
                    .collect(Collectors.toList());
            return transactionIds;
        }
return null;
    }
}