import java.util.Vector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ManageProduct {

	Scene scene;
	GridPane gridPane;
	VBox vBox, product1vBox, product2vBox;
	HBox buttonBox, productNameHbox, productPriceHbox, productStockHbox, productTypeHbox;
	MenuBar menuBar;
	Menu menu;
	Menu account;
	MenuItem manageProduct, manageUser, logOffMenu; 
	
	Label productNameLbl, productPriceLbl, productStockLbl, productTypeLbl;
	TextField productNameTxt, productPriceTxt;
	Spinner<Integer> stockSpnr;
	ComboBox<String> productTypeComboBox;
	
	Button insertBtn, updateBtn, removeBtn;
	
	TableView<Product> productTable;
	Vector<Product> product;
	
	Connect con = Connect.getInstance();
	
	public void init() {
		vBox = new VBox();
		scene = new Scene(vBox, 800, 600);
		
		productNameHbox = new HBox();
		productPriceHbox = new HBox();
		productStockHbox = new HBox();
		productTypeHbox = new HBox();
		product1vBox = new VBox();
		product2vBox = new VBox();
		
		gridPane = new GridPane();
		buttonBox = new HBox();
		
		menuBar = new MenuBar();

	    menu = new Menu("Menu");
	    account = new Menu("Account");

	    menuBar.getMenus().addAll(menu, account);
	    manageProduct = new MenuItem("Manage Product");
	    manageUser = new MenuItem("Manage User");
	    logOffMenu = new MenuItem("Logoff");
	    
	    productNameLbl = new Label("Product Name");
	    productPriceLbl = new Label("Product Price");
	    productStockLbl = new Label("Product Stock");
	    productTypeLbl = new Label("Product Type");
	    productNameTxt = new TextField();
	    productPriceTxt = new TextField();
	    stockSpnr = new Spinner<Integer>(1, 999, 1);
	    productTypeComboBox = new ComboBox<String>();
	    productTypeComboBox.getItems().addAll("Shirts", "Dresses", "Jackets", "Trousers", "Jeans", "Shorts", "Skirts");
	    
	    insertBtn = new Button("Insert");
	    updateBtn = new Button("Update");
	    removeBtn = new Button("Remove");
	    buttonBox.getChildren().addAll(insertBtn, updateBtn, removeBtn);
	    
	    menu.getItems().addAll(manageProduct, manageUser);
	    account.getItems().addAll(logOffMenu);
	    
	    productTable = new TableView<Product>();
	    product = new Vector<Product>();
	    
	}
	
	public void menuButton(Stage stg) {
		
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
	
	public void productTable() {
		TableColumn<Product, String> IDColumn = new TableColumn<Product, String>("ID");
		IDColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("id"));
		IDColumn.setMinWidth(vBox.getWidth() / 7);
		
		TableColumn<Product, String> NameColumn = new TableColumn<Product, String>("Product Name");
		NameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		NameColumn.setMinWidth(vBox.getWidth() / 4);
		
		TableColumn<Product, String> ProductTypeColumn = new TableColumn<Product, String>("Product Type");
		ProductTypeColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("productType"));
		ProductTypeColumn.setMinWidth(vBox.getWidth() / 4);
		
		TableColumn<Product, Integer> PriceColumn = new TableColumn<Product, Integer>("Price");
		PriceColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
		PriceColumn.setMinWidth(vBox.getWidth() / 7);
		
		TableColumn<Product, Integer> StockColumn = new TableColumn<Product, Integer>("Stock");
		StockColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
		StockColumn.setMinWidth(vBox.getWidth() / 7);
		
		productTable.getColumns().addAll(IDColumn, NameColumn, ProductTypeColumn, PriceColumn, StockColumn);
		productTable.setMaxHeight(vBox.getHeight() / 3);
		
		refreshProduct();
	}
	
	public void arrange() {
		productNameHbox.getChildren().addAll(productNameLbl, productNameTxt);
		productNameHbox.setSpacing(10);
		
		productPriceHbox.getChildren().addAll(productPriceLbl, productPriceTxt);
		productPriceHbox.setSpacing(10);
		
		productStockHbox.getChildren().addAll(productStockLbl, stockSpnr);
		productStockHbox.setSpacing(10);
		
		productTypeHbox.getChildren().addAll(productTypeLbl, productTypeComboBox);
		productTypeHbox.setSpacing(10);
		
		product1vBox.getChildren().addAll(productNameHbox, productStockHbox);
		product1vBox.setSpacing(75);
		
		product2vBox.getChildren().addAll(productPriceHbox, productTypeHbox);
		product2vBox.setSpacing(75);
		
		insertBtn.setPadding(new Insets(8, 30, 8, 30));
		updateBtn.setPadding(new Insets(8, 23, 8, 23));
		removeBtn.setPadding(new Insets(8, 20, 8, 20));
		
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setPadding(new Insets(50,10,10,10));
		buttonBox.setSpacing(20);
		
		gridPane.add(product1vBox, 0, 0);
		gridPane.add(product2vBox, 1, 0);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setPadding(new Insets(50, 10, 50, 10));
		gridPane.setVgap(10);
		gridPane.setHgap(100);
		
		vBox.getChildren().addAll(menuBar, productTable, gridPane, buttonBox);
	}
	
	public void refreshProduct() {
		getProduct();
		ObservableList<Product> productObs = FXCollections.observableArrayList(product);
		productTable.setItems(productObs);
	}
	
	public void getProduct() {
		product.removeAllElements();
		
		String query = "SELECT ms_product.id, ms_product.name, ms_product_type.name, price, stock FROM ms_product JOIN ms_product_type ON ms_product.type_id = ms_product_type.id";
		con.res = con.execQuery(query);
		
		try {
			while (con.res.next()) {
				String ID = con.res.getString("ms_product.id");
				String name = con.res.getString("ms_product.name");
				String productType = con.res.getString("ms_product_type.name");
				int price = con.res.getInt("price");
				int stock = con.res.getInt("stock");
				
				product.add(new Product(ID, name, productType, price, stock));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void button() {
		insertBtn.setOnAction(e -> {
			String productID = "PR026";
			String productName = productNameTxt.getText();
			
			String query = String.format("INSERT INTO ms_product VALUES('%s', '%s', '%s', %d, %d)");
		});
		
		updateBtn.setOnAction(e -> {
			
		});
		
		removeBtn.setOnAction(e -> {
			
		});
	}
	
	public void stage(Stage stg) {
		init();
		productTable();
		arrange();
		menuButton(stg);
		stg.setScene(scene);
		stg.setTitle("G&M");
		stg.show();
	}
}
