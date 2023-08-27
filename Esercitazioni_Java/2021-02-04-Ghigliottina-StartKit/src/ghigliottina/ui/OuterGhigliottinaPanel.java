package ghigliottina.ui;

import ghigliottina.model.Ghigliottina;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;

public class OuterGhigliottinaPanel extends BorderPane {
 
	private GhigliottinaPanel gPanel;
	private TextField txtRispostaUtente, txtRispostaEsatta;
	private Label rightLabel, leftLabel;
	private Button svela;
	private String rispostaEsatta;
	private Controller controller;
	private Ghigliottina gh;
	private int montepremi;
	
	public OuterGhigliottinaPanel(int montepremi, Controller controller) {
		this.controller=controller;
		this.montepremi=montepremi;
		
		gh = controller.sorteggiaGhigliottina();
		rispostaEsatta = gh.getRispostaEsatta();
		montepremi = (int) Math.random() * 2500;
		
		setupGhigliottinaPanel();
		
		leftLabel = new Label("La parola segreta:");
		rightLabel = new Label("La tua risposta:");
		PasswordField secretWord = new PasswordField();
		secretWord.setPrefColumnCount(25);
		secretWord.setEditable(false);
		secretWord.setText(rispostaEsatta);
		TextField playerAnswer = new PasswordField();
		playerAnswer.setPrefColumnCount(25);
		svela = new Button("SVELA");
		svela.setOnAction(e -> this.myHandle(e, playerAnswer, secretWord));
		
		HBox revealBox = new HBox();
		revealBox.setAlignment(Pos.CENTER);
		revealBox.setStyle("-fx-background-color: blue;");
		
		revealBox.getChildren().addAll(rightLabel, leftLabel, secretWord, playerAnswer, svela);
		
		this.setTop(revealBox);
	}
	
	private void myHandle(ActionEvent event, TextField playerAnswer, PasswordField secretWord)
	{
		if (playerAnswer.getCharacters().toString().equals(secretWord.getCharacters().toString()))
			OuterGhigliottinaPanel.info("Risultato", "Hai vinto!", "Montepremi: soldi");
		else
			OuterGhigliottinaPanel.info("Risultato", "Hai perso!", "Soldi persi: soldi");
	}
	
	private void setupGhigliottinaPanel() {
		// initial setup
		gh = controller.sorteggiaGhigliottina();
		this.rispostaEsatta=gh.getRispostaEsatta();
		gPanel = new GhigliottinaPanel(montepremi, gh.getTerne());
		this.setBottom(gPanel);
	}
	
	private void reset() {
		setupGhigliottinaPanel();
		txtRispostaUtente.setText("");
		txtRispostaEsatta.setText("");
	}
	

	// COMPLETARE: gestione evento pressione pulsante SVELA


	public static void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
	
	public static void info(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}

}
