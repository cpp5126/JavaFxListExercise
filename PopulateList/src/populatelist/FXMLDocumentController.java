/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package populatelist;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Phil O'Connell <pxo4@psu.edu>
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private AnchorPane btnAddPerson;
    @FXML
    private Button button;
    @FXML
    private ListView<String> lvPeople;
    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfLastName;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        Person newPerson = new Person();
        newPerson.setFirstName(tfFirstName.getText());
        newPerson.setLastName(tfLastName.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handleBtnAddPersonClicked(MouseEvent event) {
        // Build up full name
        String fullName = tfFirstName.getText() + " "
                + tfLastName.getText();
        // Debugging to be removed later
        System.out.println(fullName);
        // Debugging to be removed later
        lvPeople.getItems().add(fullName);

        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("PopulateListPU");

        PersonJpaController jpaPerson
                = new PersonJpaController(emf);

        try {
            Person newPerson = null;
            jpaPerson.create(newPerson);
        } catch (Exception ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(
                    Level.SEVERE, null, ex
            );
            

            
        }
    }
    
    


    public void SyncPeopleListView() {

        // Items inside the list
        ObservableList<String> items = lvPeople.getItems();

        // Clear out the list
        items.clear();

    }

    // Add each person to the list
    for (Person p : people

    
        ) {
      String fullName = p.getFirstName() + " " + p.getLastName();
        lvPeople.getItems().add(fullName);
    }

}
