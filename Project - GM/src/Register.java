import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Register{
	
	VBox vbox, genderBox;
	GridPane gridPane;
	
	Scene scene;
	Label registerLbl, userIDLbl, usernameLbl, phoneLbl, addressLbl, emaiLbl, passwordLbl, dobLbl, genderLbl;
	TextField userIDTxt, usernameTxt, phoneTxt, addressTxt, emailTxt;
	PasswordField passwordTxt;
	DatePicker dob;
	RadioButton maleBtn, femaleBtn;
	ToggleGroup genderGroup;
	
	Button signInBtn, signUpBtn;
	
	HBox buttonBox;
	
	private Connect con = Connect.getInstance();
	
	public void init() {
		vbox = new VBox();
		scene = new Scene(vbox, 350, 425);
		gridPane = new GridPane();
		
		registerLbl = new Label("Register");
		userIDLbl = new Label("User ID:");
		userIDTxt = new TextField();
		usernameLbl = new Label("Username:");
		usernameTxt = new TextField();
		phoneLbl = new Label("Phone:");
		phoneTxt = new TextField();
		addressLbl = new Label("Address:");
		addressTxt = new TextField();
		emaiLbl = new Label("Email:");
		emailTxt = new TextField();
		passwordLbl = new Label("Password:");
		passwordTxt = new PasswordField();
		dobLbl = new Label("Date Of Birth:");
		dob = new DatePicker();
		genderLbl = new Label("Gender:");
		
		maleBtn = new RadioButton("Male");
		femaleBtn = new RadioButton("Female");
		genderBox = new VBox(maleBtn, femaleBtn);
		genderGroup = new ToggleGroup();
		
		signInBtn = new Button("Sign In");
		signUpBtn = new Button("Sign Up");
		buttonBox = new HBox();
	}
	
	public void arrange() {
		registerLbl.setFont(new Font(25));
		registerLbl.setPadding(new Insets(20,10,7,44));
		
		genderGroup.getToggles().addAll(maleBtn, femaleBtn);
		genderBox.setSpacing(5);
				
		buttonBox.getChildren().addAll(signInBtn, signUpBtn);
		buttonBox.setSpacing(8);
		buttonBox.setAlignment(Pos.CENTER_RIGHT);
		buttonBox.setPadding(new Insets(20,44,10,10));
		
		userIDTxt.setMaxWidth(150);
		usernameTxt.setMaxWidth(150);
		phoneTxt.setMaxWidth(150);
		addressTxt.setMaxWidth(150);
		emailTxt.setMaxWidth(150);
		passwordTxt.setMaxWidth(150);
		
		userIDTxt.setPromptText("Your ID");
		usernameTxt.setPromptText("Your Username");
		phoneTxt.setPromptText("Input Phone Number");
		addressTxt.setPromptText("Your Address");
		emailTxt.setPromptText("Input Email");
		passwordTxt.setPromptText("Your Password");
		dob.setPromptText("Your Date Of Birth");
		
		gridPane.add(userIDLbl, 0, 0);
		gridPane.add(userIDTxt, 1, 0);
		gridPane.add(usernameLbl, 0, 1);
		gridPane.add(usernameTxt, 1, 1);
		gridPane.add(phoneLbl, 0, 2);
		gridPane.add(phoneTxt, 1, 2);
		gridPane.add(addressLbl, 0, 3);
		gridPane.add(addressTxt, 1, 3);
		gridPane.add(emaiLbl, 0, 4);
		gridPane.add(emailTxt, 1, 4);
		gridPane.add(passwordLbl, 0, 5);
		gridPane.add(passwordTxt, 1, 5);
		gridPane.add(dobLbl, 0, 6);
		gridPane.add(dob, 1, 6);
		gridPane.add(genderLbl, 0, 7);
		gridPane.add(genderBox, 1, 7);
		
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		
		vbox.getChildren().addAll(registerLbl, gridPane, buttonBox);
	}
	
	public void button(Stage stg) {
		signUpBtn.setOnAction(e -> {
			String id = userIDTxt.getText();
			String username = usernameTxt.getText();
			String phone = phoneTxt.getText();
			String address = addressTxt.getText();
			String email = emailTxt.getText();
			String password = passwordTxt.getText();
			LocalDate date = LocalDate.now();
			String gender = "";
			String role = "user";
			if (maleBtn.isSelected()) {
				gender = maleBtn.getText();
			} else if (femaleBtn.isSelected()) {
				gender = femaleBtn.getText();
			}
			
			Pattern passPattern = Pattern.compile("[aA-zZ ']+$");
			
			Alert errorAlert = new Alert(AlertType.ERROR);
			Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
			
			//validate empty field
			if (id.isBlank() || username.isBlank() || phone.isBlank() || address.isBlank() || email.isBlank() || password.isBlank()) {
				errorAlert.setHeaderText("Error");
				errorAlert.setContentText("There is an empty field!");
				errorAlert.show();
			} else if (username.length() < 5) {// && usernameTxt.getText().length() > 30) {
				errorAlert.setHeaderText("Error");
				errorAlert.setContentText("Username must be between 5 - 30 characters");
				errorAlert.show();
			} else if (username.length() > 30) {
				errorAlert.setHeaderText("Error");
				errorAlert.setContentText("Username must be between 5 - 30 characters");
				errorAlert.show();
			} else if (phone.length() < 10) {
				errorAlert.setHeaderText("Error");
				errorAlert.setContentText("Phone must be between 10 - 13 characters");
				errorAlert.show();
			} else if (phone.length() > 13) {
				errorAlert.setHeaderText("Error");
				errorAlert.setContentText("Phone must be between 10 - 13 characters");
				errorAlert.show();
			} else if (!phone.matches("\\d*")) {
				errorAlert.setHeaderText("Error");
				errorAlert.setContentText("Phone must be a number");
				errorAlert.show();
			} else if (!address.endsWith("Street")) {
				errorAlert.setHeaderText("Error");
				errorAlert.setContentText("Address must end with 'Street'");
				errorAlert.show();
			} else if (!email.endsWith(".com") && !email.endsWith(".id")) {
				errorAlert.setHeaderText("Error");
				errorAlert.setContentText("Invalid email format");
				errorAlert.show();
			} else if (password.length() < 6) {
				errorAlert.setHeaderText("Error");
				errorAlert.setContentText("Password must be between 6 - 12 characters");
				errorAlert.show();
			} else if (password.length() > 12) {
				errorAlert.setHeaderText("Error");
				errorAlert.setContentText("Password must be between 6 - 12 characters");
				errorAlert.show();
			} else if (passPattern.matcher(password).matches()) {
				errorAlert.setHeaderText("Error");
				errorAlert.setContentText("Password must be alphanumeric");
				errorAlert.show();
			} else if (dob.getValue() == null) {
				errorAlert.setHeaderText("Error");
				errorAlert.setContentText("Date Of Birth must be filled in");
				errorAlert.show();
			} else if (dob.getValue().equals(date)) {
				errorAlert.setHeaderText("Error");
				errorAlert.setContentText("Date of birth must be before today");
				errorAlert.show();
			} else if (gender.isBlank()) {
				errorAlert.setHeaderText("Error");
				errorAlert.setContentText("Gender must be selected, either 'Male' or 'Female'");
				errorAlert.show();
			}
			
			String query = String.format("INSERT INTO ms_customer VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')", id, username, phone, address, email, password, gender, dob.getValue().toString(), "user");
			con.execUpdate(query);
			
			confirmAlert.setHeaderText("Confirmation");
			confirmAlert.setContentText("Success Login");
			confirmAlert.show();
		});
		
		signInBtn.setOnMouseClicked(e -> {
			Login login = new Login();
			try {
				login.start(stg);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	}
	
	public void stage(Stage stg) {
		init();
		arrange();
		button(stg);
		stg.setScene(scene);
		stg.setTitle("Register Form");
		stg.show();
	}
}
