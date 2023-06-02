package flights.ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.*;

import java.io.IOException;

import flights.controller.*;
import flights.persistence.*;
import flights.model.*;

public class MainPane extends Application 
{
	public void start(Stage stage)
	{
		stage.setTitle("Flights");
		
		//creo i tre pannelli di cui si comporrà la mia scena.
		FlowPane mainPanel = new FlowPane();
		VBox leftPanel = new VBox();
		FlightScheduleListPanel rightPanel = new FlightScheduleListPanel();
		
		//faccio in modo che il pannello sx e dx siano posti correttamente una affianco a l'altro.
		mainPanel.getChildren().addAll(leftPanel, rightPanel);
		
		//creo le tre etichette che userò nel programma.
		Label departureAirportLab = new Label("Departure Airport");
		Label arrivalAirportLab = new Label("Arrival Airport");
		Label departureDateLab = new Label("Departure Date");
		
		//creo i componenti grafici che userò nel programma.
		ComboBox<Airport> selectDepAirport = new ComboBox<Airport>();
		ComboBox<Airport> selectArrAirport = new ComboBox<Airport>();	
		DatePicker selectDepDate = new DatePicker();
		Button findMatch = new Button("Find");
		
		//attacco i componenti creati ai pannelli corrispondenti.
		leftPanel.getChildren().addAll(departureAirportLab, selectDepAirport, 
				arrivalAirportLab, selectArrAirport, departureDateLab, selectDepDate, findMatch);
		
		//creo un'istanza del controllore e riempo le due liste a tendina con i dati del model.
		DataManager dataManager = new DataManager(new MyCitiesReader(), 
				new MyAircraftsReader(), new MyFlightScheduleReader());
		try
		{
			dataManager.readAll();
		}
		catch(IOException | BadFileFormatException e)
		{
			
		}
		Controller controller = new MyController(dataManager);
		selectDepAirport.setItems(FXCollections.observableArrayList(controller.getSortedAirports()));
		selectArrAirport.setItems(FXCollections.observableArrayList(controller.getSortedAirports()));
				
		//alla premuta del pulsante vengono calcolati e mostrati i voli.
		findMatch.setOnAction(event -> 
		rightPanel.setFlightSchedules(controller.searchFlights(selectDepAirport.getSelectionModel().getSelectedItem(), 
				selectArrAirport.getSelectionModel().getSelectedItem(), selectDepDate.getValue())));
		
		Scene mainScene = new Scene(mainPanel);
		
		stage.setScene(mainScene);
		
		stage.show();
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}
