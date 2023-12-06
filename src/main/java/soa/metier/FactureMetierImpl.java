package soa.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import soa.entities.Client;
import soa.entities.Facture;
import soa.entities.FactureLigne;
import soa.entities.ReglementLigne;
import soa.repository.ClientRepository;
import soa.repository.FactureLigneRepository;
import soa.repository.FactureRepository;
import soa.repository.ReglementLigneRepository;

import java.util.*;

@Service
public class FactureMetierImpl implements FactureMetierInterface{

    @Autowired
    private final FactureRepository factureRepository;
    @Autowired
    private final FactureLigneRepository factureLigneRepository;
    @Autowired
    private final ClientRepository clientRepository;
    @Autowired
    private final ReglementLigneRepository reglementLigneRepository;

    public FactureMetierImpl(FactureRepository factureRepository, FactureLigneRepository factureLigneRepository, ClientRepository clientRepository, ReglementLigneRepository reglementLigneRepository) {
        this.factureRepository = factureRepository;
        this.factureLigneRepository = factureLigneRepository;
        this.clientRepository = clientRepository;
        this.reglementLigneRepository = reglementLigneRepository;
    }

    @Override
    public void ajouterFacture(Facture f, Client c, ArrayList<FactureLigne> fr,ArrayList<ReglementLigne> rl) {
        f.setReglementLignes(rl);
        f.setFactureLignes(fr);
        f.setClient(c);
        factureRepository.save(f);
    }

    @Override
    public void modifierFacture(Facture f) {
        factureRepository.save(f);
    }

    @Override
    public Facture getFacture(Long id) {
        return factureRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimerFacture(Long id) {
        factureRepository.deleteById(id);
    }

    @Override
    public List<Facture> listesFactures() {
        return factureRepository.findAll();
    }

    @Override
    public List<FactureLigne> getFacturesLignes(Long idFact) {
        Optional<Facture> factureOptional = factureRepository.findById(idFact);
        return (List<FactureLigne>) factureOptional.map(Facture::getFactureLignes).orElse(null);
    }

    @Override
    public List<ReglementLigne> getReglementsLignes(Long idFact) {
        Optional<Facture> factureOptional = factureRepository.findById(idFact);
        return (List<ReglementLigne>) factureOptional.map(Facture::getReglementLignes).orElse(null);
    }

    @Override
    public Float totalFacturesClient(Long clientId) {
        float totalPrice = 0.0f;
        // Assuming there's a method getClientById() in your service/repository to retrieve the client by ID
        Client client = clientRepository.getById(clientId);

        if (client != null) {
            for (Facture facture : client.getFactures()) {
                totalPrice += facture.getSomme();
            }
        } else {
            // Handle case where the client with the given ID is not found
            // You can throw an exception or handle it based on your application's logic
            // For example:
            throw new IllegalArgumentException("Client with ID " + clientId + " not found.");
        }
        return totalPrice;
    }

    @Override
    public Float totalFacturesClients() {
        float total = 0.0f;
        List<Facture> factures = factureRepository.findAll(); // Assuming a method to get all invoices exists in the repository

        for (Facture facture : factures) {
            total += facture.getSomme();
        }
        return total;
    }

    @Override
    public Float totalPaiementClient(Long id) {
        float totalPayment = 0.0f;
        Client c=new Client();
        c=clientRepository.getById(id);
        for (Facture facture : c.getFactures()) {
            for (ReglementLigne reglementLigne : facture.getReglementLignes()) {
                totalPayment += reglementLigne.getMontantpaye();
            }
        }

        return totalPayment;
    }

    @Override
    public Float totalPaiementClients() {
        float total = 0.0f;
        List<ReglementLigne> factures = reglementLigneRepository.findAll(); // Assuming a method to get all invoices exists in the repository

        for (ReglementLigne facture : factures) {
            total += facture.getMontantpaye();
        }
        return total;
    }

    @Override
    public Float totalDettesClient(Client c) {
        float totalDebt = 0.0f;

        for (Facture facture : c.getFactures()) {
            float invoiceAmount = facture.getSomme();
            float paidAmount = 0.0f;

            for (ReglementLigne reglementLigne : facture.getReglementLignes()) {
                paidAmount += reglementLigne.getMontantpaye();
            }

            totalDebt += Math.max(0, invoiceAmount - paidAmount);
        }

        return totalDebt;
    }

    @Override
    public Float totalDettesClients() {
        float totalDebt = 0.0f;
        List<Facture> allInvoices = factureRepository.findAll(); // Assuming a method to get all invoices exists

        for (Facture invoice : allInvoices) {
            float invoiceAmount = invoice.getSomme();
            float paidAmount = 0.0f;

            for (ReglementLigne reglementLigne : invoice.getReglementLignes()) {
                paidAmount += reglementLigne.getMontantpaye();
            }

            totalDebt += Math.max(0, invoiceAmount - paidAmount);
        }

        return totalDebt;
    }

    @Override
    public Float chiffreAnnuelPrevueClient(Date d, Client c) {
        // Assuming d represents the provided date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        int currentYear = calendar.get(Calendar.YEAR);

        // Set the date to one year ago from the provided date
        calendar.add(Calendar.YEAR, -1);
        Date oneYearAgo = calendar.getTime();

        float yearlyRevenue = 0.0f;

        for (Facture facture : c.getFactures()) {
            Date invoiceDate = facture.getDate();

            // Check if the invoice date is within the previous year
            calendar.setTime(invoiceDate);
            int invoiceYear = calendar.get(Calendar.YEAR);

            if (invoiceDate.after(oneYearAgo) && invoiceYear == currentYear) {
                for (FactureLigne factureLigne : facture.getFactureLignes()) {
                    // Assuming you have a method 'getMontant()' in FactureLigne class to retrieve the amount
                    yearlyRevenue += factureLigne.getPrixTot();
                }
            }
        }

        return yearlyRevenue;
    }

    @Override
    public Float chiffreAnnuelPrevueClients(Date d) {
        float yearlyRevenue = 0.0f;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);

        // Set the date to one year ago from the provided date
        calendar.add(Calendar.YEAR, -1);
        Date oneYearAgo = calendar.getTime();

        List<Facture> allInvoices = factureRepository.findAll(); // Assuming a method to get all invoices exists

        for (Facture invoice : allInvoices) {
            Date invoiceDate = invoice.getDate();

            if (invoiceDate.after(oneYearAgo) && invoiceDate.before(d)) {
                for (FactureLigne factureLigne : invoice.getFactureLignes()) {
                    // Assuming you have a method 'getMontant()' in FactureLigne class to retrieve the amount
                    yearlyRevenue += factureLigne.getPrixTot();
                }
            }
        }

        return yearlyRevenue;
    }


    @Override
    public Float chiffreMensuellePrevueClient(Date d, Client c) {
        float monthlyRevenue = 0.0f;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);

        List<Facture> clientInvoices = (List<Facture>) c.getFactures(); // Assuming a method to get the client's invoices exists

        for (Facture invoice : clientInvoices) {
            Date invoiceDate = invoice.getDate();

            calendar.setTime(invoiceDate);
            int invoiceYear = calendar.get(Calendar.YEAR);
            int invoiceMonth = calendar.get(Calendar.MONTH);

            if (invoiceYear == currentYear && invoiceMonth == currentMonth) {
                for (FactureLigne factureLigne : invoice.getFactureLignes()) {
                    // Assuming you have a method 'getMontant()' in FactureLigne class to retrieve the amount
                    monthlyRevenue += factureLigne.getPrixTot();
                }
            }
        }

        return monthlyRevenue;
    }


    @Override
    public Float chiffreMensuellePrevueClients(Date d) {
        float totalMonthlyRevenue = 0.0f;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);

        List<Facture> allInvoices = factureRepository.findAll(); // Assuming a method to get all invoices exists

        for (Facture invoice : allInvoices) {
            Date invoiceDate = invoice.getDate();

            calendar.setTime(invoiceDate);
            int invoiceYear = calendar.get(Calendar.YEAR);
            int invoiceMonth = calendar.get(Calendar.MONTH);

            if (invoiceYear == currentYear && invoiceMonth == currentMonth) {
                for (FactureLigne factureLigne : invoice.getFactureLignes()) {
                    // Assuming you have a method 'getMontant()' in FactureLigne class to retrieve the amount
                    totalMonthlyRevenue += factureLigne.getPrixTot();
                }
            }
        }

        return totalMonthlyRevenue;
    }


    @Override
    public Float chiffreHebdomadairePrevueClient(Date d, Client c) {
        float weeklyRevenue = 0.0f;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.setFirstDayOfWeek(Calendar.MONDAY); // Set Monday as the first day of the week
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // Set the calendar to the first day of the week

        Date startOfWeek = calendar.getTime();

        calendar.add(Calendar.DAY_OF_WEEK, 6); // Move calendar to the end of the week (Sunday)
        Date endOfWeek = calendar.getTime();

        List<Facture> clientInvoices = (List<Facture>) c.getFactures(); // Assuming a method to get the client's invoices exists

        for (Facture invoice : clientInvoices) {
            Date invoiceDate = invoice.getDate();

            if (invoiceDate.after(startOfWeek) && invoiceDate.before(endOfWeek)) {
                for (FactureLigne factureLigne : invoice.getFactureLignes()) {
                    // Assuming you have a method 'getMontant()' in FactureLigne class to retrieve the amount
                    weeklyRevenue += factureLigne.getPrixTot();
                }
            }
        }

        return weeklyRevenue;
    }


    @Override
    public Float chiffreHebdomadairePrevueClients(Date d) {
        float totalWeeklyRevenue = 0.0f;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.setFirstDayOfWeek(Calendar.MONDAY); // Set Monday as the first day of the week
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // Set the calendar to the first day of the week

        Date startOfWeek = calendar.getTime();

        calendar.add(Calendar.DAY_OF_WEEK, 6); // Move calendar to the end of the week (Sunday)
        Date endOfWeek = calendar.getTime();

        List<Facture> allInvoices = factureRepository.findAll(); // Assuming a method to get all invoices exists

        for (Facture invoice : allInvoices) {
            Date invoiceDate = invoice.getDate();

            if (invoiceDate.after(startOfWeek) && invoiceDate.before(endOfWeek)) {
                for (FactureLigne factureLigne : invoice.getFactureLignes()) {
                    // Assuming you have a method 'getMontant()' in FactureLigne class to retrieve the amount
                    totalWeeklyRevenue += factureLigne.getPrixTot();
                }
            }
        }

        return totalWeeklyRevenue;
    }


    @Override
    public Float chiffreAffaireMax() {
        float max = 0.0f;
        List<Facture> allInvoices = factureRepository.findAll(); // Assuming a method to get all invoices exists

        for (Facture invoice : allInvoices) {
            float maxU = 0.0f;

            for (FactureLigne factureLigne : invoice.getFactureLignes()) {
                // Assuming you have a method 'getMontant()' in FactureLigne class to retrieve the amount
                maxU += factureLigne.getPrixTot();
            }

            if (maxU > max) {
                max = maxU;
            }
        }

        return max;
    }

    @Override
    public Float chiffreAffaireMin() {

        float min = Float.MAX_VALUE;
        List<Facture> allInvoices = factureRepository.findAll(); // Assuming a method to get all invoices exists

        for (Facture invoice : allInvoices) {
            float minU = 0.0f;

            for (FactureLigne factureLigne : invoice.getFactureLignes()) {
                // Assuming you have a method 'getMontant()' in FactureLigne class to retrieve the amount
                minU += factureLigne.getPrixTot();
            }

            if (minU < min) {
                min = minU;
            }
        }

        return min==Float.MAX_VALUE ? 0.0f : min;
    }
}
