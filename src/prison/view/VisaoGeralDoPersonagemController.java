package prison.view;

//import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.stage.FileChooser;

//import java.io.File;

import prison.MainApp;
import prison.model.Personagem;
import prison.util.DateUtil;

public class VisaoGeralDoPersonagemController {
    @FXML
    private TableView<Personagem> personTable;
    @FXML
    private TableColumn<Personagem, String> firstNameColumn;
    @FXML
    private TableColumn<Personagem, String> lastNameColumn;

    @FXML
    private Label nomeLabel;
    @FXML
    private Label sobrenomeLabel;
    @FXML
    private Label enderecoLabel;
    @FXML
    private Label tipoDoCrimeLabel;
    @FXML
    private Label cidadeLabel;
    @FXML
    private Label dataDoCrimeLabel;

    @FXML
    private ImageView fotoView;
    
    private Image caminhoFoto;
    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public VisaoGeralDoPersonagemController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().sobrenomeProperty());
        
        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }
    
    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     * 
     * @param person the person or null
     */
    private void showPersonDetails(Personagem person) {
        if (person != null) {
            // Preenche os labels com informações do personagem
            nomeLabel.setText(person.getNome());
            sobrenomeLabel.setText(person.getSobrenome());
            enderecoLabel.setText(person.getEndereco());
            tipoDoCrimeLabel.setText(person.getTipoDeCrime());
            cidadeLabel.setText(person.getCidade());
            dataDoCrimeLabel.setText(DateUtil.format(person.getDataDoFatoCriminoso()));
            fotoView.setImage(caminhoFoto);
           // imageview.setImage(new Image("file:///"+file.getAbsolutePath()));
        } else {
            // Personagem for vazio , remove todos os textos
            nomeLabel.setText("");
            sobrenomeLabel.setText("");
            enderecoLabel.setText("");
            tipoDoCrimeLabel.setText("");
            cidadeLabel.setText("");
            dataDoCrimeLabel.setText("");
        }
    }
    
    /**
     * Deleta o personagem quando acionado o botão deletar
     */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Nenhum Selecionado");
            alert.setHeaderText("Indivíduo não selecionado");
            alert.setContentText("Selecione um individuo para visualizar o registro completo.");
            
            alert.showAndWait();
        }
    }
    
    /**
     * Quando é clicado o botão de novo é aberto uma dialog para editar detalhes de um novo personagem.
     */
    @FXML
    private void handleNewPerson() {
        Personagem tempPerson = new Personagem();
        boolean okClicked = mainApp.janelaEditarPersonagem(tempPerson);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
        }
    }

    /**
     * Quando é clicado o botão editar, é aberto uma dialog para editar as informações do personagem escolhido.
     */
    @FXML
    private void handleEditPerson() {
        Personagem selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.janelaEditarPersonagem(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Não há selecionados");
            alert.setHeaderText("Não há personagem selecionado");
            alert.setContentText("Por favor, selecione um indivíduo na tabela.");
            
            alert.showAndWait();
        }
    }
   
}