package prison.view;

//import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
//import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import prison.model.Personagem;

/**
 * The controller for the statistics view.
 * 
 */
public class EstatisticasDeArtigoController {

    @FXML private BarChart<String, Integer> barChart;
    @FXML private CategoryAxis xAxis;
    
    private ObservableList<String> artigoNomes = FXCollections.observableArrayList(); // artigos penais

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        String[] artigos = {"Homicídio","Estelionato","Furto", "Roubo"};  //cria um vetor com o nome dos artigos especificados;
       // int[] art = {121,171,155,157};
    
        artigoNomes.addAll(Arrays.asList(artigos));//converte os artigos em uma lista e add no ObservableList
        xAxis.setCategories(artigoNomes); // nomes dos artigos no eixo dos X
    }
public void setPersonData(List<Personagem> persons) {
	int[] contadorCrime = new int[20];
	for(Personagem p : persons) {
		
	}
	XYChart.Series<String, Integer> series = new XYChart.Series<>();
	
	barChart.getData().add(series);
	
}
  
}