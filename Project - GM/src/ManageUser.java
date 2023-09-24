import java.sql.Date;
import java.time.LocalDate;
import java.util.Vector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManageUser {
	
	Scene scene;
	BorderPane borderPane;
	VBox vBox;
	MenuBar menuBar;
	Menu menu;
	Menu account;
	MenuItem manageProduct, manageUser, logOffMenu; 
	
	TableView<User> userTable;
	Vector<User> user;
	
	Connect con = Connect.getInstance();
	
	public void init() {
//		borderPane = new BorderPane();
		vBox = new VBox();
		scene = new Scene(vBox, 800, 600);
		menuBar = new MenuBar();

	    menu = new Menu("Menu");
	    account = new Menu("Account");

	    menuBar.getMenus().addAll(menu, account);
	    manageProduct = new MenuItem("Manage Product");
	    manageUser = new MenuItem("Manage User");
	    logOffMenu = new MenuItem("Logoff");

	    menu.getItems().addAll(manageProduct, manageUser);
	    account.getItems().addAll(logOffMenu);
	    
	    userTable = new TableView<User>();
	    user = new Vector<User>();
	    
//	    borderPane.setTop(menuBar);
//	    borderPane.setCenter(userTable);
	}
	
	public void userTable() {
		TableColumn<User, String> IDColumn = new TableColumn<User, String>("ID");
		IDColumn.setCellValueFactory(new PropertyValueFactory<User, String>("userID"));
		IDColumn.setMinWidth(vBox.getWidth() / 10);
		
		TableColumn<User, String> UserNameColumn = new TableColumn<User, String>("UserName");
		UserNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		UserNameColumn.setMinWidth(vBox.getWidth() / 8);
		
		TableColumn<User, String> UserPhoneColumn = new TableColumn<User, String>("UserPhone");
		UserPhoneColumn.setCellValueFactory(new PropertyValueFactory<User, String>("phone"));
		UserPhoneColumn.setMinWidth(vBox.getWidth() / 8);
		
		TableColumn<User, String> UserAddressColumn = new TableColumn<User, String>("UserAddress");
		UserAddressColumn.setCellValueFactory(new PropertyValueFactory<User, String>("address"));
		UserAddressColumn.setMinWidth(vBox.getWidth() / 8);
		
		TableColumn<User, String> UserEmailColumn = new TableColumn<User, String>("UserEmail");
		UserEmailColumn.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
		UserEmailColumn.setMinWidth(vBox.getWidth() / 8);
		
		TableColumn<User, String> UserPasswordColumn = new TableColumn<User, String>("UserPassword");
		UserPasswordColumn.setCellValueFactory(new PropertyValueFactory<User, String>("password"));
		UserPasswordColumn.setMinWidth(vBox.getWidth() / 8);
		
		TableColumn<User, String> UserGenderColumn = new TableColumn<User, String>("UserGender");
		UserGenderColumn.setCellValueFactory(new PropertyValueFactory<User, String>("gender"));
		UserGenderColumn.setMinWidth(vBox.getWidth() / 8);

		TableColumn<User, Date> UserDOBColumn = new TableColumn<User, Date>("UserDOB");
		UserDOBColumn.setCellValueFactory(new PropertyValueFactory<User, Date>("dob"));
		UserDOBColumn.setMinWidth(vBox.getWidth() / 8);
		
		TableColumn<User, String> UserRoleColumn = new TableColumn<User, String>("UserRole");
		UserRoleColumn.setCellValueFactory(new PropertyValueFactory<User, String>("role"));
		UserRoleColumn.setMinWidth(vBox.getWidth() / 8);
		
		userTable.getColumns().addAll(IDColumn, UserNameColumn, UserPhoneColumn, UserAddressColumn, UserEmailColumn, UserPasswordColumn, UserGenderColumn, UserDOBColumn, UserRoleColumn);
		userTable.setMaxHeight(vBox.getMaxHeight() / 2.5);
		
		refreshUser();
	}
	
	public void refreshUser() {
		getUser();
		ObservableList<User> userObs = FXCollections.observableArrayList(user);
		userTable.setItems(userObs);
	}
	
	public void getUser() {
		user.removeAllElements();
		
		String userEmail = Login.emailTxt.getText();
		String query2 = String.format("SELECT id FROM ms_customer WHERE email = '%s'", userEmail);
		String custID = null;
		con.res = con.execQuery(query2);
		try {
			while (con.res.next()) {
				custID = con.res.getString("id");
			}
		} catch (Exception e2) {
			// TODO: handle exception
		}
		
		String query = String.format("SELECT * FROM ms_customer");
		con.res = con.execQuery(query);
		try {
			while (con.res.next()) {
				String id = con.res.getString("id");
				String name = con.res.getString("name");
				String phone = con.res.getString("phone");
				String address = con.res.getString("address");
				String email = con.res.getString("email");
				String password = con.res.getString("password");
				String gender = con.res.getString("gender");
				Date dob = con.res.getDate("dob");
				String role = con.res.getString("role");
				
				user.add(new User(id, name, phone, address, email, password, dob, gender, role));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void arrange() {
		vBox.getChildren().addAll(menuBar, userTable);
	}
	
	public void menuButton(Stage stg) {
		manageProduct.setOnAction(e -> {
			ManageProduct manageProduct = new ManageProduct();
			manageProduct.stage(stg);
		});
		
		manageUser.setOnAction(e -> {
			
		});
		
		logOffMenu.setOnAction(e -> {
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
		userTable();
		arrange();
		menuButton(stg);
		stg.setScene(scene);
		stg.setTitle("G&M");
		stg.show();
	}
	
}
