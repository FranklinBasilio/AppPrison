package prison.view;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import java.io.File;
import prison.MainApp;
import prison.model.Personagem;
import prison.util.DateUtil;


public class EditarPersonagemController {
	 private MainApp mainApp; //verificar
    @FXML
    private TextField nomeField;
    @FXML
    private TextField sobrenomeField;
    @FXML
    private TextField enderecoField;
    @FXML
    private TextField tipoDoCrimeField;
    @FXML
    private TextField cidadeField;
    @FXML
    private TextField dataDoCrimeField;
    
    @FXML
    private ImageView fotoView;
    
    private Stage dialogStage;
    private Personagem person;
    private boolean okClicked = false;
    private String caminhoFoto;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        
        // Set the dialog icon.
        this.dialogStage.getIcons().add(new Image("file:resources/images/Prisioner.png"));
    }

    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setPerson(Personagem person) {
        this.person = person;

        nomeField.setText(person.getNome());
        sobrenomeField.setText(person.getSobrenome());
        enderecoField.setText(person.getEndereco());
        tipoDoCrimeField.setText(""+person.getTipoDeCrime());
        cidadeField.setText(person.getCidade());
        dataDoCrimeField.setText(DateUtil.format(person.getDataDoFatoCriminoso()));
        dataDoCrimeField.setPromptText("dd.mm.yyyy");
        fotoView.setId("file:resources/images/semfoto.png");
    }
 public boolean isOkClicked() {
        return okClicked;
    }

  @FXML
    private void handleOk() {
        if (isInputValid()) {
            person.setNome(nomeField.getText());
            person.setSobrenome(sobrenomeField.getText());
            person.setEndereco(enderecoField.getText());
           // person.setTipoDeCrime(tipoDoCrimeField.);
            person.setTipoDeCrime(tipoDoCrimeField.getText());
            person.setCidade(cidadeField.getText());
            person.setDataDoFatoCriminoso(DateUtil.parse(dataDoCrimeField.getText()));
            person.setFotoView(fotoView.getImage());
            person.setFotoString(caminhoFoto);
            okClicked = true;
            dialogStage.close();
        }
    }
  @FXML
    private void handleCancel() {
        dialogStage.close();
    }
private boolean isInputValid() {
        String errorMessage = "";

        if (nomeField.getText() == null || nomeField.getText().length() == 0) {
            errorMessage += "Nome vazio!\n"; 
        }
        if (sobrenomeField.getText() == null || sobrenomeField.getText().length() == 0) {
            errorMessage += "Sobrenome vazio!\n"; 
        }
        if (enderecoField.getText() == null || enderecoField.getText().length() == 0) {
            errorMessage += "Endereço vazio!\n"; 
        }

        if (tipoDoCrimeField.getText() == null || tipoDoCrimeField.getText().length() == 0) {
            errorMessage += "Tipo de crime vazio\n"; 
            // criar regra para ser digitado apenas o tipo de crime desejado.
            
        }else {
            try {
                Integer.parseInt(tipoDoCrimeField.getText());
            	} catch (NumberFormatException e) {
                errorMessage += "valor não inteiro!\n"; 
            	} 
        }
       if (cidadeField.getText() == null || cidadeField.getText().length() == 0) {
            errorMessage += "Não há nome de cidade!\n"; 
        }

        if (dataDoCrimeField.getText() == null || dataDoCrimeField.getText().length() == 0) {
            errorMessage += "Data inválida!\n";
        } else {
            if (!DateUtil.validDate(dataDoCrimeField.getText())) {
                errorMessage += "Data em formato inválido. Use o formato dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Células inválidas");
            alert.setHeaderText("Dados inseridos incorretamente");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
        
    }
// para carregar uma foto do computador  - 
    @FXML
    private void handleLoad(ActionEvent evt) {
    	
    	FileChooser f = new FileChooser();
    	f.getExtensionFilters().add(new ExtensionFilter("JPEG, PNG","*.jpg","*.jpeg","*.png"));
    	File file = f.showOpenDialog(new Stage());
    	if(file != null) {
    		fotoView.setImage(new Image("file:///"+file.getAbsolutePath()));
    		caminhoFoto = file.getAbsolutePath();
    		
    		 
    	}
    	
    }
}