package prison;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import prison.model.Personagem;
import prison.model.PersonListWrapper;
import prison.view.EstatisticaPorMesController;
import prison.view.EstatisticasDeArtigoController;
import prison.view.EditarPersonagemController;
import prison.view.VisaoGeralDoPersonagemController;
import prison.view.RootModeloController;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootModelo;
   
    private ObservableList<Personagem> personData = FXCollections.observableArrayList();

    /**
     * Construtor
     */
    public MainApp() {
        // Add some sample data
        personData.add(new Personagem("João", "Mete Peia"));
        personData.add(new Personagem("Cícero", "Santos"));
        personData.add(new Personagem("João", "da Silva"));
        personData.add(new Personagem("Antonio", "Bastos"));
        personData.add(new Personagem("Francisco", "Santos da Silva"));
        personData.add(new Personagem("Luiz", "Silva"));
        personData.add(new Personagem("Maria", "Lima"));
        personData.add(new Personagem("Sergio", "Maia"));
        personData.add(new Personagem("Antonio", "Palloci"));
    }

    /**
     * Returns the data as an observable list of Persons. 
     * @return
     */
    public ObservableList<Personagem> getPersonData() {
        return personData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Prisson");
        
        // Set the application icon.
        this.primaryStage.getIcons().add(new Image("resources/images/prisoner.png"));
        /* teste de carregamento de imagem*/
        
        initRootLayout();

        showPersonOverview();
    }

    /**
     * Initializes the root layout and tries to load the last opened
     * person file.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootModelo.fxml"));
                           
            rootModelo = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootModelo);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootModeloController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Try to load last opened person file.
        File file = getPersonFilePath();
        if (file != null) {
            carregarDadosDoPersonagem(file);
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/VisaoGeralDoPersonagem.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootModelo.setCenter(personOverview);

            // Give the controller access to the main app.
            VisaoGeralDoPersonagemController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     * 
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean janelaEditarPersonagem(Personagem person) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/EditarPersonagem.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Infrator");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            EditarPersonagemController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);
            
            // Set the dialog icon.
            dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Opens a dialog to show birthday statistics.
     */
    public void exibirEstatiscaPorMes() {
        try {
            // Load the fxml file and create a new stage for the popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/EstatisticaPorMes.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Estatística por mês");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            // Set the dialog icon.
            dialogStage.getIcons().add(new Image("resources/images/calendar.png"));
            // Set the persons into the controller.
            EstatisticaPorMesController controller = loader.getController();
            controller.setPersonData(personData);
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Opens a dialog to show artigos statistics.
     */
    public void exibirEstatisticasDeArtigo() {
    	try {
	    	FXMLLoader loader = new FXMLLoader();
	    	loader.setLocation(MainApp.class.getResource("view/EstatisticasDeArtigo.fxml"));
	    	AnchorPane page = (AnchorPane) loader.load();
	    	Stage dialogStage = new Stage();
	    	dialogStage.setTitle("Estatística por tipo de Crime");
	    	dialogStage.initModality(Modality.WINDOW_MODAL);
	    	dialogStage.initOwner(primaryStage);
	    	Scene scene = new Scene(page);
	    	dialogStage.setScene(scene);
	    	dialogStage.getIcons().add(new Image("resources/images/prison.png"));
	    	EstatisticasDeArtigoController controller = loader.getController();
	    	controller.setPersonData(personData);
	    	dialogStage.show();
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("Prisson - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("Prisson");
        }
    }
    
    /**
     * Loads person data from the specified file. The current person data will
     * be replaced.
     * 
     * @param file
     */
    public void carregarDadosDoPersonagem(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

            personData.clear();
            personData.addAll(wrapper.getPersons());

            // Save the file path to the registry.
            setPersonFilePath(file);

        } catch (Exception e) { // catches ANY exception
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Error");
        	alert.setHeaderText("Could not load data");
        	alert.setContentText("Could not load data from file:\n" + file.getPath());
        	
        	alert.showAndWait();
        }
    }
  public void salvarDadosDoPersonagem(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            PersonListWrapper wrapper = new PersonListWrapper();
            wrapper.setPersons(personData);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

            // Save the file path to the registry.
            setPersonFilePath(file);
        } catch (Exception e) { // catches ANY exception
        	Alert alert = new Alert(AlertType.ERROR);
        	alert.setTitle("Error");
        	alert.setHeaderText("Could not save data");
        	alert.setContentText("Could not save data to file:\n" + file.getPath());
        	
        	alert.showAndWait();
        }
    }
  public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}