package it.unibo.paw.hibernate.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory = initHibernateUtil();

	private static final String CREATE_CANDIDATI = "CREATE TABLE candidati(" +
			"	CandidatoId INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
			"	matricola varchar(50)," +
			"	nome varchar(50)," +
			"	cognome varchar(50)" +
			")";
	private static final String CREATE_CONCORSI = "CREATE TABLE concorsi(" +
			"	ConcorsoId INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
			"	codiceConcorso int NOT NULL UNIQUE," +
			"	classeConcorso varchar(255)," +
			"	descrizione varchar(255)" +
			")";

	private static final String CREATE_CONCORSI_CANDIDATI = "CREATE TABLE concorsi_candidati(" +
			"	CandidatoId INT NOT NULL," +
			"	ConcorsoId INT NOT NULL," +
			"	PRIMARY KEY(CandidatoId, ConcorsoId)," +
			"	FOREIGN KEY (CandidatoId) REFERENCES candidati(CandidatoId)," +
			"	FOREIGN KEY (ConcorsoId) REFERENCES concorsi(ConcorsoId)" +
			")";
	private static final String CREATE_COMMISSARI = "CREATE TABLE commissari(" +
			"	CommissarioId INT NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY," +
			"	matricola varchar(50)," +
			"	nome varchar(50)," +
			"	cognome varchar(50)," +
			"	concorso int," +
			"	FOREIGN KEY (concorso) REFERENCES concorsi(ConcorsoId)" +
			")";

	private static SessionFactory initHibernateUtil() {
		try {
			return new Configuration().configure().buildSessionFactory();
		} catch (HibernateException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}

	public static void dropAndCreateTables() {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
					
			try {
				session.createSQLQuery("DROP TABLE candidati").executeUpdate();
				session.createSQLQuery("DROP TABLE concorsi_candidati").executeUpdate();
				session.createSQLQuery("DROP TABLE concorsi").executeUpdate();
				session.createSQLQuery("DROP TABLE commissari").executeUpdate();
			} catch (HibernateException e) {
				System.out.println("dropTable(): failed to drop tables " + e.getMessage());
			}
			session.createSQLQuery(CREATE_CANDIDATI).executeUpdate();
			session.createSQLQuery(CREATE_CONCORSI).executeUpdate();
			session.createSQLQuery(CREATE_CONCORSI_CANDIDATI).executeUpdate();
			session.createSQLQuery(CREATE_COMMISSARI).executeUpdate();
			
			tx.commit();
		} finally {
			session.close();
		}
	}
}
