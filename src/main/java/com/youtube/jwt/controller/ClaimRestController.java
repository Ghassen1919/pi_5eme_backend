package com.youtube.jwt.controller;


import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.youtube.jwt.entity.Claim;
import com.youtube.jwt.entity.Transaction;
import com.youtube.jwt.entity.TypeClaim;
import com.youtube.jwt.service.ClaimService;
import com.youtube.jwt.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

@EnableScheduling
@RestController
@AllArgsConstructor
@RequestMapping("/claim")
public class ClaimRestController {
    private ClaimService claimService;

    private EmailService emailService;



    //------------------{ Affichage }------------------
    // http://localhost:8080/pidev/claim/afficher

    @GetMapping("/afficher")
    @PreAuthorize("hasRole('Admin')")
    public List<Claim> afficher() {

        return claimService.selectAll();
    }

    //------------------{ Recherche par type claim }------------------
    //  http://localhost:8080/pidev/claim/search?ref=49.Sun Apr 02 23:47:19 EET 2023
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/search")
    @PreAuthorize("hasRole('Admin')")
    public List<Claim> searchClaims(@RequestParam("ref") String q) {
        return claimService.search(q);
    }


    //------------------{ Recherche par date }------------------
    //  http://localhost:8080/claim/search2?date=01-02-2023
    @GetMapping("/search2")
    @PreAuthorize("hasRole('Admin')")
    public List<Claim> searchcl(@RequestParam("date") Date q) {
        return claimService.search2(q);
    }


    //------------------{ Export PDF }------------------
    //localhost:8080/claim/export/pdf
   // @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/export/pdf")
    @PreAuthorize("hasRole('Admin')")

    public ResponseEntity<byte[]> exportToPdf() throws IOException, DocumentException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);

        document.open();
        com.lowagie.text.Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph("Claims List", fontTitle);
        Paragraph paragraph2 = new Paragraph("                        ", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);
        document.add(paragraph2);
        PdfPTable table = new PdfPTable(5);
        table.setWidths(new int[]{1, 1, 1, 1, 1});
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.black);
        cell.setPadding(5);
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.WHITE);
        cell.setPhrase(new Phrase("Date Claim", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Date Traite", font));
        table.addCell(cell);
         cell.setPhrase(new Phrase("description", font));
         table.addCell(cell);

       // cell.setPhrase(new Phrase("Desc Claim", font));
       // table.addCell(cell);



        cell.setPhrase(new Phrase("Transaction", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("User", font));
        table.addCell(cell);

        //table.addCell("User");


        List<Claim> myObjects = claimService.selectAll();
        for (Claim myObject : myObjects) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = dateFormat.format(myObject.getDateClaim());
            table.addCell(dateString);
            String dateString1 = (myObject.getDateTraite() != null) ? dateFormat.format(myObject.getDateTraite()) : null;
             table.addCell(dateString1);
            table.addCell(myObject.getDescClaim());


            table.addCell("a");
            table.addCell(myObject.getUserc().getUserName());


        }

        document.add(table);
        document.close();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "document.pdf");

        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }

    //------------------{ taiter claim }------------------
    //http://localhost:8080/pidev/claim/modify/1
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping ("/modify/{id}")
    @PreAuthorize("hasRole('Admin')")
    public void changetraite(@PathVariable("id") Integer id ,
                             @RequestParam("traite") boolean traite){
        claimService.modifier(id, traite);

    }

    //------------------{ ajout claim reel }------------------
    // http://localhost:8080/pidev/claim/add
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping ("/add")
    @PreAuthorize("hasRole('User')")
    public void add(@RequestParam("file") MultipartFile file,
                    @RequestParam("desc") String desc, @RequestParam("type") TypeClaim type,@RequestParam("trans")String t )
    {
        claimService.ajouter(file,desc,type,t);

    }

    //------------------{ envoi de mail en cas de retard de traitement (automatique) }------------------
// check the claims that are not processed yet
    @Scheduled(cron = "0 15 17 * * *") // Run every day at 9:00 AM

    public  void compare () {

        List<Claim> myObjects = claimService.selectAll();
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        List<String> list = new ArrayList<>();
        for (Claim claim : myObjects) {
            Date dateclaim = claim.getDateClaim();
            boolean traite =claim.getTraiteClaim() ;
            long diffInMillies = Math.abs(date.getTime() - dateclaim.getTime());
            long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            if ((diffInDays > 1) && (traite==false)) {

                list.add(claim.getRefclaim());


            }
        }
        if (list.isEmpty()){
            this.emailService.sendSimpleEmail("addmin.ghassen123@gmail.com", "check claims","you have no claims to check today");

        }else
            this.emailService.sendSimpleEmail1("addmin.ghassen123@gmail.com", "check claims",list);

    }
   // @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/nbclaim")
    public Float getnbclaims(){
        List<Claim> myObjects = claimService.selectAll();
        float count=0;
        for (Claim claim : myObjects) {
            count=count +1;

        }
        return count;

    }
   // @CrossOrigin(origins = "http://localhost:4200")
    //http://localhost:8080/pidev/claim/nbclaim1
    @GetMapping("/nbclaim1")
    @PreAuthorize("hasRole('Admin')")
    public Map<String, Float> getpourcentagetraite(){
        List<Claim> myObjects = claimService.selectAll();
        float b = getnbclaims();
        float count=0;

        for (Claim claim : myObjects){

            if (claim.getTraiteClaim()==true){
                count =count+1;
            }

        }
        float result = (float) count / b;
        float result1 = (float) (b-count) / b;

        Map<String, Float> percentages = new HashMap<>();
        percentages.put("processed", result);
        percentages.put("unprocessed", result1);


        return percentages;

    }

    //http://localhost:8080/pidev/claim/nbclaim2
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/nbclaim2")
    @PreAuthorize("hasRole('Admin')")
    public String getpourcentage(){
        List<Claim> myObjects = claimService.selectAll();
        float b = getnbclaims();

        float count1=0;
        float count2=0;
float count3=0;
        for (Claim claim : myObjects) {

            if (claim.getType() == TypeClaim.Transaction) {
                count1 = count1 + 1;

            } else if (claim.getType() == TypeClaim.Account) {
                count2 = count2 + 1;
            } else {
                count3 = count3 + 1;
            }
        }
            float result1 = (float) count1 / b;
            float result2 = (float) count2 / b;
            float result3 = (float) count3 / b;

            return " le pourcentage des reclamation de type Transaction est " + String.valueOf(result1 * 100) + "% / le pourcentage des reclamation de type Account est " + String.valueOf(result2 * 100) + "%/ le pourcentage des reclamation de type Other est " + String.valueOf(result3 * 100) + "%";

        }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/nbclaim3")
    public Map<String, Float> getPourcentage() {
        List<Claim> myObjects = claimService.selectAll();
        float b = getnbclaims();

        float count1 = 0;
        float count2 = 0;
        float count3=0;
        for (Claim claim : myObjects) {
             if (claim.getType() == TypeClaim.Transaction) {
                count1 = count1 + 1;
            } else if (claim.getType() == TypeClaim.Account) {
                 count2 = count2 + 1;
             } else {
                 count3 = count3 + 1;
             }
        }


        float result1 = count1 / b;
        float result2 = count2 / b;
        float result3 = (float) count3 / b;
        Map<String, Float> percentages = new HashMap<>();

        percentages.put("transaction", result1);
        percentages.put("account", result2);
        percentages.put("other", result3);
        return percentages;
    }
    @GetMapping("/listtran")
    @PreAuthorize("hasRole('User')")

    public List<String> getlist() {
        return claimService.claimtran();
    }

    @GetMapping("/claimperuser")
    @PreAuthorize("hasRole('User')")
    public List<Claim> getclaimperuser() {
        return claimService.getclaimperuser();
    }

}
