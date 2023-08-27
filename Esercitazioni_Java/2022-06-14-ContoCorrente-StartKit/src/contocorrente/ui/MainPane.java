package contocorrente.ui;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import contocorrente.controller.Controller;
import contocorrente.model.Movimento;
import contocorrente.model.Tipologia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainPane extends BorderPane {
	
	private Controller controller;
	private ListView<Movimento> listView;
	private TextField txtSaldoCorrente, txtDataCorrente, txtTotAccrediti, txtTotAddebiti;
	private PieChart chart;
	private LocalDate dataCorrente;
	private DateTimeFormatter dateFormatter;
	private NumberFormat currencyFormatter;
	
	public MainPane(Controller controller) {
		this.controller=controller;
		// *** DA COMPLETARE - PARTE 1 ***
		// *** configurazione iniziale dei formattatori di valuta e data e della data corrente
		// ***
		//
		// ----- top box -----
		HBox topBox = new HBox(10);
		topBox.setPrefWidth(400);
		topBox.getChildren().addAll(new Label("Conto corrente n° " + this.controller.ccId()) );
		topBox.getChildren().addAll(new Label("Intestatario: " + this.controller.ccIntestatario()) );
		this.setTop(topBox);		
		// ----- left box -----
		listView = new ListView<>();
		listView.setItems(FXCollections.observableArrayList(this.controller.movimenti()));		
		listView.setPrefWidth(500);
		listView.setPrefHeight(listView.getItems().size()*24+ 2);
		this.setLeft(listView);	
		// *** DA COMPLETARE - PARTE 2 ***
		//	l’aggancio alla ListView dell’opportuno listener incapsulato nel metodo ausiliario myHandler
		// ***
		//
		// ----- right box -----
		VBox rightBox = new VBox(10);
		rightBox.setPrefWidth(300);
			//---
			txtDataCorrente = new TextField();
			txtDataCorrente.setEditable(false);
			txtDataCorrente.setMaxWidth(70);
			//---
			txtSaldoCorrente = new TextField();
			txtSaldoCorrente.setEditable(false);
			txtSaldoCorrente.setMaxWidth(150);
			txtSaldoCorrente.setAlignment(Pos.CENTER_RIGHT);
			//---
			txtTotAccrediti = new TextField();
			txtTotAccrediti.setEditable(false);
			txtTotAccrediti.setMaxWidth(100);
			txtTotAccrediti.setAlignment(Pos.CENTER_RIGHT);
			//---
			txtTotAddebiti = new TextField();
			txtTotAddebiti.setEditable(false);
			txtTotAddebiti.setMaxWidth(100);
			txtTotAddebiti.setAlignment(Pos.CENTER_RIGHT);
			//---
			HBox miniBoxData = new HBox(10);
			txtDataCorrente.setText(dateFormatter.format(dataCorrente));
			miniBoxData.getChildren().addAll(new Label("Dati al"), txtDataCorrente);
			rightBox.getChildren().addAll(miniBoxData);
			//---		
			HBox miniBoxAccrediti = new HBox(10);
			miniBoxAccrediti.getChildren().addAll(new Label("Totale accrediti"), txtTotAccrediti);
			rightBox.getChildren().addAll(miniBoxAccrediti);
			//---		
			HBox miniBoxAddebiti = new HBox(10);
			miniBoxAddebiti.getChildren().addAll(new Label("Totale addebiti"), txtTotAddebiti);
			rightBox.getChildren().addAll(miniBoxAddebiti);
			//---
			HBox miniBoxSaldo = new HBox(10);
			miniBoxSaldo.getChildren().addAll(new Label("Saldo"), txtSaldoCorrente);
			rightBox.getChildren().addAll(miniBoxSaldo);
			//---		
			double totaleAddebiti  = controller.getTotale(dataCorrente, Tipologia.ADDEBITO);
			double totaleAccrediti = controller.getTotale(dataCorrente, Tipologia.ACCREDITO);
			chart = makePieChart(totaleAccrediti,totaleAddebiti);
			rightBox.getChildren().addAll(chart);
		this.setRight(rightBox);
	}

	private void myHandler(Movimento newMov) {
		// *** DA FARE - PARTE 3 ***
		// logica di gestione dell’evento
		// - recuperare dal movimento selezionato la data contabile da utilizzare e impostarla nell’apposito campo di testo, 
		//   opportunamente formattata
		// - utilizzare tale data per recuperare dal controller i vari dati (saldo, totale accrediti e addebiti) e mostrarli, 
		//   opportunamente formattati, negli appositi campi di testo
		// - aggiornare il grafico a torta, ricalcolandolo coi nuovi totali accrediti e addebiti appena ottenuti
	}

	private PieChart makePieChart(double totaleAccrediti, double totaleAddebiti) {
		// DA FARE - PARTE 4
		// costruzione del grafico a torta, di dimensioni 250x250
		return null; // (fake)
	}
	
}
