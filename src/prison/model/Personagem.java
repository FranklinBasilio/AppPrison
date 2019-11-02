package prison.model;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import prison.util.LocalDateAdapter;

/**
 * Model class for a Person.
 *
 */
public class Personagem {

	private final StringProperty nome;
	private final StringProperty sobrenome;
	private final StringProperty endereco;
	private final StringProperty cidade;
	private final StringProperty tipoDeCrime;
	private final ObjectProperty<LocalDate> dataDoFatoCriminoso;
	private final ImageView fotoView;
	private  String fotoString;

	/**
	 * Default constructor.
	 */
	public Personagem() {
		this(null, null);
	}

	/**
	 * Constructor with some initial data.
	 * 
	 * @param nome
	 * @param sobrenome
	 */
	public Personagem(String nome, String sobrenome) {
		this.nome = new SimpleStringProperty(nome);
		this.sobrenome = new SimpleStringProperty(sobrenome);
		this.endereco = new SimpleStringProperty("");
		//this.endereco = new SimpleStringProperty("Nome da Rua e número");
		this.tipoDeCrime = new SimpleStringProperty("");
		
		this.cidade = new SimpleStringProperty("");
		this.dataDoFatoCriminoso = new SimpleObjectProperty<LocalDate>(LocalDate.of(2018, 11, 11));
		this.fotoView = new ImageView();
		this.fotoString = new String();
	}

	//verificar essa parte da imagem/
	public ImageView getFotoView() {
		return fotoView;

	}
	public void setFotoView(Image image) {
		this.fotoView.setAccessibleHelp(null);
	}

	//ate aqui
	public String getFotoString() {
		return fotoString;
	}
	public void setFotoString(String foto) {
		this.fotoString = foto;
	}
	
public String getNome() {
		return nome.get();
	}

	public void setNome(String nome) {
		this.nome.set(nome);
	}

	public StringProperty nomeProperty() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome.get();
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome.set(sobrenome);
	}

	public StringProperty sobrenomeProperty() {
		return sobrenome;
	}

	public String getEndereco() {
		return endereco.get();
	}

	public void setEndereco(String endereco) {
		this.endereco.set(endereco);
	}

	public StringProperty enderecoProperty() {
		return endereco;
	}
	
	public String getTipoDeCrime() {
		return tipoDeCrime.get();
	}

	public void setTipoDeCrime(String tipoDeCrime) {
		this.tipoDeCrime.set(tipoDeCrime);
	}

	public StringProperty tipoDeCrimeProperty() {
		return tipoDeCrime;
	}

	public String getCidade() {
		return cidade.get();
	}

	public void setCidade(String cidade) {
		this.cidade.set(cidade);
	}

	public StringProperty cidadeProperty() {
		return cidade;
	}

	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getDataDoFatoCriminoso() {
		return dataDoFatoCriminoso.get();
	}

	public void setDataDoFatoCriminoso(LocalDate data) {
		this.dataDoFatoCriminoso.set(data);
	}

	public ObjectProperty<LocalDate> dataDoFatoCriminoso() {
		return dataDoFatoCriminoso;
	}
}