package prison.view;

import java.net.URL;
import java.util.ResourceBundle;

//import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class LoginController implements Initializable{
	@FXML 	  private AnchorPane paneLogin;
	
	@FXML     private TextField txNome;
    @FXML     private PasswordField txSenha;
    @FXML     private Button btOk;
    @FXML     private Button btCancelar;
    
    
    /**
     * Inicializando os controles
     * */
    public void initialize(URL url, ResourceBundle rb) {
    	
    	btOk.setOnMouseClicked((MouseEvent e)->{
    		
    	});
    	
    	btCancelar.setOnMouseClicked((MouseEvent e)->{
    		//cancelar operação
    		paneLogin.setVisible(false);
    	});
    	
    }
    
    
    
    
}

















