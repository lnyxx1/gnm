import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UserMainForm {
	
	Scene scene;
	BorderPane borderPane;
	MenuBar menuBar;
	Menu menu;
	Menu account;
	MenuItem buyMenu, transactionMenu, logOffMenu; 
	
	public void init() {
		borderPane = new BorderPane();
		scene = new Scene(borderPane, 800, 600);
		menuBar = new MenuBar();

	    menu = new Menu("Menu");
	    account = new Menu("Account");

	    menuBar.getMenus().addAll(menu, account);
	    buyMenu = new MenuItem("Buy");
	    transactionMenu = new MenuItem("Transaction");
	    logOffMenu = new MenuItem("Logoff");

	    menu.getItems().addAll(buyMenu, transactionMenu);
	    account.getItems().addAll(logOffMenu);
	    
	    borderPane.setTop(menuBar);
	}
	
	public void menuButton(Stage stg) {
		buyMenu.setOnAction(e -> {
			BuyMenu buyMenu = new BuyMenu();
			buyMenu.stage(stg);
		});
		
		transactionMenu.setOnAction(e -> {
			ShowTransaction showTransaction = new ShowTransaction();
			
			showTransaction.stage(stg);
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
