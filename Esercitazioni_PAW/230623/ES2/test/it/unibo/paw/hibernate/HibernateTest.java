package it.unibo.paw.hibernate;

import java.io.*;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import it.unibo.paw.hibernate.util.HibernateUtil;

public class HibernateTest {
    private static final String FIRSTQUERY_STR = 
        "select count(cand), conc.classeConcorso " +
        "from Commissario comm " +
        "join comm.concorso conc " +
        "join conc.candidati cand " +
        "where comm.matricola = :matrVar " +
        "group by conc.classeConcorso " +
        "order by count(cand) desc";  // ordina per numero candidati discendente

    private static final String SECONDQUERY_STR = 
        "select cand.nome, cand.cognome, count(conc) as numConcorsi " +
        "from Candidato cand " +
        "join cand.concorsi conc " + 
        "group by cand.nome, cand.cognome " +
        "having count(conc) > 1 " +
        "order by numConcorsi desc";  // ordina per numero concorsi discendente

    public static void main(String[] argv) {

        Session session = null;
        Transaction tx = null;

        try {

            HibernateUtil.dropAndCreateTables();

            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

         // Creo candidati
            // Candidato 1
            Candidato cand1 = new Candidato();
            cand1.setMatricola(1);
            cand1.setNome("beppe");
            cand1.setCognome("grillo");
            session.persist(cand1);

            // Candidato 2
            Candidato cand2 = new Candidato();
            cand2.setMatricola(2);
            cand2.setNome("fede");
            cand2.setCognome("ferro");
            session.persist(cand2);

            // Concorso 1
            Concorso conc1 = new Concorso();
            conc1.setCodiceConcorso(1);
            conc1.setClasseConcorso("classe1");
            conc1.setDescrizione("robe belle");
            conc1.getCandidati().add(cand1);
            cand1.getConcorsi().add(conc1);
            session.persist(conc1);

            // Concorso 2
            Concorso conc2 = new Concorso();
            conc2.setCodiceConcorso(2);
            conc2.setClasseConcorso("classe2");
            conc2.setDescrizione("robe brutte");
            conc2.getCandidati().add(cand1);
            conc2.getCandidati().add(cand2);
            cand1.getConcorsi().add(conc2);
            cand2.getConcorsi().add(conc2);
            session.persist(conc2);

            // Commissario 1 (matricola=1) associato al concorso 1
            Commissario comm1 = new Commissario();
            comm1.setMatricola(1);
            comm1.setNome("matteo");
            comm1.setCognome("lomba");
            comm1.setConcorso(conc1);
            session.persist(comm1);
            conc1.getCommissari().add(comm1);

            // Commissario 2 (matricola=2) associato al concorso 2
            Commissario comm2 = new Commissario();
            comm2.setMatricola(2);
            comm2.setNome("john");
            comm2.setCognome("snow");
            comm2.setConcorso(conc2);
            session.persist(comm2);
            conc2.getCommissari().add(comm2);

            tx.commit();
            session.close();

            session = HibernateUtil.getSessionFactory().openSession();

            StringBuilder firstQueryResult = new StringBuilder();
            StringBuilder secondQueryResult = new StringBuilder();

            // Prima query
            Query firstQuery = session.createQuery(FIRSTQUERY_STR);
            firstQuery.setParameter("matrVar", 1);
            List<Object[]> firstQueryRecords = firstQuery.list();
            for (Object[] row : firstQueryRecords) {
                Long count = (Long) row[0];
                String classeConcorso = (String) row[1];
                firstQueryResult.append("Classe Concorso: ").append(classeConcorso)
                                .append(" - Numero candidati: ").append(count).append("\n");
            }

            // Seconda query
            Query secondQuery = session.createQuery(SECONDQUERY_STR);
            List<Object[]> secondQueryRecords = secondQuery.list();
            for (Object[] row : secondQueryRecords) {
                String nome = (String) row[0];
                String cognome = (String) row[1];
                Long numConcorsi = (Long) row[2];
                secondQueryResult.append(nome).append(" ").append(cognome)
                                 .append(" - Concorsi iscritti: ").append(numConcorsi).append("\n");
            }

            try (PrintWriter pw = new PrintWriter(new FileWriter("Concorso.txt"))) {
                pw.println("Risultati Prima Query:");
                pw.println(firstQueryResult.toString());
                pw.println("\nRisultati Seconda Query:");
                pw.println(secondQueryResult.toString());
                pw.close();
            }

            session.close();

        } catch (Exception e1) {
            if (tx != null) {
                try {
                    tx.rollback();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            e1.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
