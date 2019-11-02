package prison.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of persons. This is used for saving the
 * list of persons to XML.
 * 
 * 
 */
@XmlRootElement(name = "persons")
public class PersonListWrapper {

    private List<Personagem> persons;

    @XmlElement(name = "person")
    public List<Personagem> getPersons() {
        return persons;
    }

    public void setPersons(List<Personagem> persons) {
        this.persons = persons;
    }
}