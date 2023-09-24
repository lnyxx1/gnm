import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AdminMainForm {
	
	Scene scene;
	BorderPane borderPane;
	MenuBar menuBar;
	Menu menu;
	Menu account;
	MenuItem manageProduct, manageUser, logOffMenu; 
	
	public void init() {
		borderPane = new BorderPane();
		scene = new Scene(borderPane, 800, 600);
		menuBar = new MenuBar();

	    menu = new Menu("Menu");
	    account = new Menu("Account");

	    menuBar.getMenus().addAll(menu, account);
	    manageProduct = new MenuItem("Manage Product");
	    manageUser = new MenuItem("Manage User");
	    logOffMenu = new MenuItem("Logoff");

	    menu.getItems().addAll(manageProduct, manageUser);
	    account.getItems().addAll(logOffMenu);
	    
	    borderPane.setTop(menuBar);
	}
	
	public void menuButton(Stage stg) {
		manageProduct.setOnAction(e -> {
			ManageProduct manageProduct = new ManageProduct();
			manageProduct.stage(stg);
		});
		
		manageUser.setOnAction(e -> {
			ManageUser manageUser = new ManageUser();
			manageUser.stage(stg);
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
		menuButton(stg);
		stg.setScene(scene);
		stg.setTitle("G&M");
		stg.show();
	}
}
