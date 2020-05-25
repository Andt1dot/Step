package com.system;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class FXMLController implements Initializable {
    
    Transaction transaction = new Transaction();
     
    @FXML
    private TextField textField;
         
    @FXML
    private AnchorPane anchorPane;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        textField.textProperty().addListener((obs, oldText, newText) -> {
          //byte transactionStatus = //transaction.paymentTravel(newText);
        
        if(newText.equals("1")){
              System.out.println("succes");
       // anchorPane.setId("succes");
        anchorPane.getStyleClass().add("succes");
          }
          else
            System.out.println("error");
                      
        });
}
}
