package dad.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisa extends Application {
	private Alert alert ;
	private TextField origenText,destinoText;
	private ComboBox<String> origen,destino;
	private Button changeButton;
	Divisa euro = new Divisa("Euro", 1.0);
	Divisa libra = new Divisa("Libra", 0.8873);
	Divisa dolar = new Divisa("Dolar", 1.2007);
	Divisa yen = new Divisa("Yen", 133.59);
	
	public void start(Stage primaryStage) throws Exception {
		
		origenText = new TextField("0");
		origenText.maxWidth(20);
		
		destinoText = new TextField("0");
		destinoText.maxWidth(20);
		
		origen = new ComboBox<String>();
		origen.getItems().addAll("Euro","Libra","Dolar","Yen");
		origen.setValue("Euro");
		
		destino = new ComboBox<String>();
		destino.getItems().addAll("Euro","Libra","Dolar","Yen");
		destino.setValue("Libra");
		
		changeButton = new Button("Acceder");
		changeButton.setDefaultButton(true);
		changeButton.setOnAction(e -> onLoginButtonAction(e));
		
		HBox origenBox = new HBox(5,origenText,origen);
		origenBox.setAlignment(Pos.CENTER);
		
		HBox destinoBox = new HBox(5,destinoText,destino);
		destinoBox.setAlignment(Pos.CENTER);
		
		VBox root = new VBox(5,origenBox,destinoBox,changeButton);
		root.setAlignment(Pos.CENTER);
		Scene scene = new Scene(root,320,200);
		
		primaryStage.setTitle("Cambiar Divisa");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		alert = new Alert(AlertType.ERROR);
		alert.setTitle("AdivinApp");
		alert.setHeaderText("ERROR");
		
	}
	
	
	private void onLoginButtonAction(ActionEvent e) {
		
		Divisa origen1 = null;
		Divisa destino1 = null;
		try {
			switch(origen.getValue()){
				case "Euro": {
					origen1 = euro;
					break;
				}
				case "Libra": {
					origen1 = libra;
					break;
				}
				case "Dolar": {
					origen1 = dolar;
					break;
				}
				case "Yen": {
					origen1 = yen;
					break;
				}
			}
			switch(destino.getValue()){
				case "Euro": {
					destino1 = euro;
					break;
				}
				case "Libra": {
					destino1 = libra;
					break;
				}
				case "Dolar": {
					destino1 = dolar;
					break;
				}
				case "Yen": {
					destino1 = yen;
					break;
				}
			}	
			destinoText.setText(Math.abs(Divisa.fromTo(origen1, destino1,Double.parseDouble(origenText.getText())))+"");
		} catch (NumberFormatException e1) {
			alert.setContentText("Numero no bueno");
			alert.showAndWait();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	
	
}
