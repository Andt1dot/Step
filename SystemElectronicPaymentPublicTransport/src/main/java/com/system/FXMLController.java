package com.system;

import com.system.model.Customer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;


public class FXMLController implements Initializable {
    
    Customer customer =  new Customer();    
     
    @FXML
    private TextField textField;
                 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
 
          // if transaction succes -> show image green 
               // else if no money -> show image red
                     // else -> no change image
   

        textField.textProperty().addListener((obs, oldText, newText) -> {
          byte transactionStatus = customer.paymentTravel(newText);
        
            switch (transactionStatus) {
                case 0:
                //.........    
                    break;
                case 1:
                //........    
                    break;
                default:
                    throw new AssertionError();
            }
          
     
        });


}
}
