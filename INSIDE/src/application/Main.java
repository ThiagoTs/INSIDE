package application;

import java.net.URL;

import Controller.PainelAdmController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;


public class Main extends Application {
	PainelAdmController p;
	@Override
	public void start(Stage principal) throws Exception{
		VBox raiz = new VBox(10); // 1
		raiz.setAlignment(Pos.CENTER); // 2
		URL arquivoFXML;
		arquivoFXML = getClass().getResource("/Visao/inicial.fxml");
		Parent fxmlParent =(Parent) FXMLLoader.load(arquivoFXML);
		Scene cena = new Scene(fxmlParent, 990, 630);
		principal.resizableProperty().setValue(false);
		principal.setTitle("Janela Principal");
		principal.setScene(cena);
		principal.show();

	}


	public static void main(String[] args) {
		launch(args);
	}
}
