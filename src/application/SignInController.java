package application;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SignInController implements Initializable {

		
		@FXML
		private AnchorPane main_form;
		
	    @FXML
	    private TextField username;

	    @FXML
	    private PasswordField password;

	    @FXML
	    private Button loginBtn;

	    @FXML
	    private Button close;
	    
	    private Connection connect;
	    private PreparedStatement prepare;
	    private ResultSet result;
	    
	    private double x = 0;
	    private double y = 0;
	    
	    public void loginAdmin(){
	        
	        connect = connexion.connectDb();
	        
	        String sql = "SELECT * FROM admin WHERE username = ? and password = ?"; 
	        
	        try{
	            Alert alert;
	            
	            prepare = connect.prepareStatement(sql);
	            prepare.setString(1, username.getText());
	            prepare.setString(2, password.getText());
	            
	            result = prepare.executeQuery();
	            
	            if(username.getText().isEmpty() || password.getText().isEmpty()){
	                alert = new Alert(AlertType.ERROR);
	                alert.setTitle("Error Message");
	                alert.setHeaderText(null);
	                alert.setContentText("Please fill all blank fields");
	                alert.showAndWait();
	            }else{
	                if(result.next()){
	                	
	                	getData.username = username.getText();
	                	
	                	alert = new Alert(AlertType.INFORMATION);
	                    alert.setTitle("Information Message");
	                    alert.setHeaderText(null);
	                    alert.setContentText("Successfully Login");
	                    alert.showAndWait();
	                    
	                    loginBtn.getScene().getWindow().hide();
	                    
	                    // LINK YOUR DASHBOARD FORM : ) 
	                    Parent root = FXMLLoader.load(getClass().getResource("/FXML/Home.FXML"));
	                    Stage stage = new Stage();
	                    Scene scene = new Scene(root);
	                    
	                    root.setOnMousePressed((MouseEvent event) ->{
	                        x = event.getSceneX();
	                        y = event.getSceneY();
	                    });
	                    
	                    root.setOnMouseDragged((MouseEvent event) ->{
	                        stage.setX(event.getScreenX() - x);
	                        stage.setY(event.getScreenY() - y);
	                    });
	                    
	                    stage.initStyle(StageStyle.TRANSPARENT);
	                    
	                    stage.setScene(scene);
	                    stage.show();
	                    
	                }else{ // IF WRONG USERNAME OR PASSWORD
	                    alert = new Alert(AlertType.ERROR);
	                    alert.setTitle("Error Message");
	                    alert.setHeaderText(null);
	                    alert.setContentText("Wrong Username/Password");
	                    alert.showAndWait();
	                }
	            }
	            
	        }catch(Exception e){e.printStackTrace();}
	        
	    }
	    
	    
	    
	    public void close(){
	        System.exit(0);
	    }

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
		}
	    
	    

}

