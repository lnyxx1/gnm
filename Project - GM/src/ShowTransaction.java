import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ShowTransaction {
	
	Scene scene;
	BorderPane borderPane;
	GridPane gridPane;
//	VBox vBox, tdBox;
	VBox tdBox;
	HBox hBox, priceBox;
	
	MenuBar menuBar;
	Menu menu;
	Menu account;
	MenuItem buyMenu, transactionMenu, logOffMenu;
	
	Label priceLbl;
	TextField priceTxt;
	
	TableView<TransactionHeader> thTable;
	TableView<TransactionDetail> tdTable;
	
	public void init() {
//		vBox = new VBox();
		borderPane = new BorderPane();
		gridPane = new GridPane();
		hBox = new HBox();
		tdBox = new VBox();
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
		
		priceLbl = new Label("Total Price");
		priceTxt = new TextField();
		
		thTable = new TableView<TransactionHeader>();
		tdTable = new TableView<TransactionDetail>();
	}
	
	public void transactionHeaderTable() {
		TableColumn<TransactionHeader, String> IDColumn = new TableColumn<TransactionHeader, String>("ID");
		IDColumn.setCellValueFactory(new PropertyValueFactory<TransactionHeader, String>("transactionID"));
		IDColumn.setMinWidth(75);
		
		TableColumn<TransactionHeader, String> DateColumn = new TableColumn<TransactionHeader, String>("Transaction Date");
		DateColumn.setCellValueFactory(new PropertyValueFactory<TransactionHeader, String>("date"));
		DateColumn.setMinWidth(125);
		
		thTable.getColumns().addAll(IDColumn, DateColumn);
//		thTable.setMaxWidth(200);
		
//		BuyMenu buyMenu = new BuyMenu();
//		buyMenu.refreshTH();
	}
	
	public void transactionDetailTable() {
		TableColumn<TransactionDetail, String> IDColumn = new TableColumn<TransactionDetail, String>("ID");
		IDColumn.setCellValueFactory(new PropertyValueFactory<TransactionDetail, String>("productID"));
		IDColumn.setMinWidth(100);
		
		TableColumn<TransactionDetail, String> NameColumn = new TableColumn<TransactionDetail, String>("Product Name");
		NameColumn.setCellValueFactory(new PropertyValueFactory<TransactionDetail, String>("ProductName"));
		NameColumn.setMinWidth(200);
		
		TableColumn<TransactionDetail, String> ProductTypeColumn = new TableColumn<TransactionDetail, String>("Product Type");
		ProductTypeColumn.setCellValueFactory(new PropertyValueFactory<TransactionDetail, String>("productType"));
		ProductTypeColumn.setMinWidth(200);
		
		TableColumn<TransactionDetail, Integer> QuantityColumn = new TableColumn<TransactionDetail, Integer>("Quantity");
		QuantityColumn.setCellValueFactory(new PropertyValueFactory<TransactionDetail, Integer>("quantity"));
		QuantityColumn.setMinWidth(100);
		
		tdTable.getColumns().addAll(IDColumn, NameColumn, ProductTypeColumn, QuantityColumn);
		tdTable.setMaxHeight(borderPane.getHeight() / 4);
		
//		BuyMenu buyMenu = new BuyMenu();
//		buyMenu.refreshTD();
	}
	
	public void arrange() {
		
		priceBox.getChildren().addAll(priceLbl, priceTxt);
		priceBox.setAlignment(Pos.CENTER);
		priceBox.setSpacing(5);

		tdBox.getChildren().addAll(tdTable, priceBox);
//		tdBox.setAlignment(Pos.CENTER_RIGHT);
		
//		hBox.getChildren().addAll(thTable, tdBox);
//		hBox.getChildren().add(tdBox);
		
		gridPane.add(thTable, 0, 0);
		gridPane.add(tdBox, 1, 0);

		borderPane.setTop(menuBar);
		borderPane.setCenter(gridPane);
//		vBox.getChildren().addAll(menuBar, hBox);
	}
	
	public void stage(Stage stg) {
		init();
		transactionHeaderTable();
		transactionDetailTable();
		arrange();
		stg.setScene(scene);
		stg.setTitle("G&M");
		stg.show();
	}
}
