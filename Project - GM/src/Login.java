import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Login extends Application{
	
	VBox vbox;
	Scene scene;
//	BorderPane borderPane;
	HBox buttonBox;
//	Pane buttonPane;
	
	GridPane gridPane;
	
	Label loginLbl, emailLbl, passwordLbl;
	static TextField emailTxt;
	PasswordField passwordTxt;
	Button signUpBtn, signInBtn;
	
	Connect con = Connect.getInstance();
	
//	Vector<UserID> userId;

	public void init() {
//		borderPane = new BorderPane();
		vbox = new VBox();
		gridPane = new GridPane();
		scene = new Scene(vbox, 300, 200);
		gridPane = new GridPane();
		
		loginLbl = new Label("Login");
		emailLbl = new Label("Email:");
		emailTxt = new TextField();
		passwordLbl = new Label("Password:");
		passwordTxt = new PasswordField();
		
		signUpBtn = new Button("Sign Up");
		signInBtn = new Button("Sign In");
		
//		buttonPane = new Pane();
		buttonBox = new HBox();
	}
	
	public void arrange() {
//		passwordTxt.set
		
		loginLbl.setFont(new Font(25));
		loginLbl.setPadding(new Insets(15,10,7,42));
		
		emailTxt.setPromptText("Your Email");
		passwordTxt.setPromptText("Your Password");
		
		gridPane.add(emailLbl, 0, 0);
		gridPane.add(emailTxt, 1, 0);
		gridPane.add(passwordLbl, 0, 1);
		gridPane.add(passwordTxt, 1, 1);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		
		buttonBox.getChildren().addAll(signUpBtn, signInBtn);
		buttonBox.setAlignment(Pos.CENTER_RIGHT);
		buttonBox.setSpacing(10);
		buttonBox.setPadding(new Insets(18, 44, 20, 7));
		
		vbox.getChildren().addAll(loginLbl, gridPane, buttonBox);
		vbox.setAlignment(Pos.TOP_LEFT);
	}
	
//	public String userEmail() {
//		String userEmail = emailTxt.getText();
//		setuId(userEmail);
//		
//		return getuId();
//	}
	
//	public void getID() {
//		String userEmail = emailTxt.getText();
//		
//		String query = String.format("SELECT id From ms_customer WHERE email = ''%s''", userEmail);
//		con.res = con.execQuery(query);
//		String idCus = "";
//		try {
//			while (con.res.next()) {
//				String idCus = con.res.getString("id");
//				
//				UserID id = new UserID(idCus);
//				id.setUserId(idCus);
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
	
	public void button(Stage arg0) {
	signInBtn.setOnAction(e -> {		
		String email = emailTxt.getText();
		String password = passwordTxt.getText();
		
		Alert errorAlert = new Alert(AlertType.ERROR);
		Alert confirmAlert = new Alert(AlertType.CONFIRMATION);

		Pattern passPattern = Pattern.compile("[aA-zZ ']+$");
		
		String query = String.format("SELECT * FROM ms_customer WHERE email = '%s' AND password = '%s'", email, password);
		ResultSet rs = con.execQuery(query);
		
//		String regex = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
//		Pattern emailPattern = Pattern.compile(regex);
//		Matcher emailMatcher = emailPattern.matcher(email);
		
		try {
			if (email.isBlank() || password.isBlank()) {
				errorAlert.setHeaderText("Error");
				errorAlert.setContentText("There is an empty field!");
				errorAlert.show();
			} else if (!email.endsWith(".com") && !email.endsWith(".id")) {
				errorAlert.setHeaderText("Error");
				errorAlert.setContentText("Invalid email format");
				errorAlert.show();
			} else if (passPattern.matcher(password).matches()) {
				errorAlert.setHeaderText("Error");
				errorAlert.setContentText("Password must be alphanumeric");
				errorAlert.show();
			} else if (password.length() < 6) {
				errorAlert.setHeaderText("Error");
				errorAlert.setContentText("Password must be between 6 - 12 characters");
				errorAlert.show();
			} else if (password.length() > 12) {
				errorAlert.setHeaderText("Error");
				errorAlert.setContentText("Password must be between 6 - 12 characters");
				errorAlert.show();
			} else if (rs.next()) {
							
				if (rs.getString("role").equalsIgnoreCase("user")) {
					UserMainForm userMain = new UserMainForm();
					userMain.stage(arg0);
				} else if (rs.getString("role").equalsIgnoreCase("admin")) {
					AdminMainForm adminMain = new AdminMainForm();
					adminMain.stage(arg0);
				}	
						
				confirmAlert.setHeaderText("Confirmation");
				confirmAlert.setContentText("Success Login");
				confirmAlert.show();
									
			} else {
					errorAlert.setHeaderText("Error");
					errorAlert.setContentText("Not Registered");
					errorAlert.show();
				}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	});
		
		signUpBtn.setOnAction(e -> {
			Register regis = new Register();
			regis.stage(arg0);
		});
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		init();
		arrange();
		button(arg0);
		arg0.setScene(scene);
		arg0.setTitle("Login Form");
		arg0.show();
	}

}
