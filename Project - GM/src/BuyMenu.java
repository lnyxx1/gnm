import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Vector;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BuyMenu {
	
	Scene scene;
//	BorderPane borderPane;
	GridPane gridPane;
	VBox vBox, buttonBox;
	HBox hBox;
	MenuBar menuBar;
	Menu menu;
	Menu account;
	MenuItem buyMenu, transactionMenu, logOffMenu;
	
	Spinner<Integer> quantitySpnr;
	
	Button addCartBtn, removeCartBtn, orderBtn;
	
	TableView<Product> productTable;
	TableView<Cart> cartTable;

	Vector<Product> product;
	Vector<Cart> cart;
	Vector<TransactionHeader> transactionHeader;
	Vector<TransactionDetail> transactionDetail;
	
	Connect con = Connect.getInstance();
	
	public void init() {
//		borderPane = new BorderPane();
		
		vBox = new VBox();
		hBox = new HBox();
		scene = new Scene(vBox, 800, 600);
		menuBar = new MenuBar();
		
		gridPane = new GridPane();
		
		buttonBox = new VBox();
		
	    menu = new Menu("Menu");
	    account = new Menu("Account");

	    menuBar.getMenus().addAll(menu, account);
	    buyMenu = new MenuItem("Buy");
	    transactionMenu = new MenuItem("Transaction");
	    logOffMenu = new MenuItem("Logoff");

	    menu.getItems().addAll(buyMenu, transactionMenu);
	    account.getItems().addAll(logOffMenu);
	    
	    quantitySpnr = new Spinner<Integer>(1, 999, 1);
	    
	    addCartBtn = new Button("Add To Cart");
	    removeCartBtn = new Button("Remove From Cart");
	    orderBtn = new Button("Order");
	    
	    productTable = new TableView<Product>();
	    cartTable = new TableView<Cart>();
	    
	    product = new Vector<Product>();
	    cart = new Vector<Cart>();
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
		productTable.setMaxHeight(vBox.getHeight() / 2.2);
		
		refreshProduct();
	}
	
	public void cartTable() {
		TableColumn<Cart, String> IDColumn = new TableColumn<Cart, String>("ID");
		IDColumn.setCellValueFactory(new PropertyValueFactory<Cart, String>("productID"));
		IDColumn.setMinWidth(vBox.getWidth() / 5);
		
		TableColumn<Cart, Integer> NameColumn = new TableColumn<Cart, Integer>("Quantity");
		NameColumn.setCellValueFactory(new PropertyValueFactory<Cart, Integer>("quantity"));
		NameColumn.setMinWidth(vBox.getWidth() / 5);
		
		cartTable.getColumns().addAll(IDColumn, NameColumn);
		refreshCart();
	}
	
	public void arrange() {
		addCartBtn.setMaxWidth(150);
		removeCartBtn.setMaxWidth(150);
		orderBtn.setMaxWidth(150);
		
		buttonBox.getChildren().addAll(quantitySpnr, addCartBtn, removeCartBtn, orderBtn);
		buttonBox.setSpacing(7);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setPadding(new Insets(5,5,5,175));
		
		gridPane.add(cartTable, 0, 0);
		gridPane.add(buttonBox, 1, 0);
		
		vBox.getChildren().addAll(menuBar, productTable, gridPane);
//		borderPane.setTop(menuBar);
//	    borderPane.setCenter(gridPane);
	}
	
	public void menuButton(Stage stg) {
		
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
	
	public void refreshCart() {
		getCart();
		ObservableList<Cart> cartObs = FXCollections.observableArrayList(cart);
		cartTable.setItems(cartObs);
	}
	
//	public void addCart() {		
//		addCartBtn.setOnAction(e -> {
//			Alert errorAlert = new Alert(AlertType.ERROR);
//			
//			Product productItem = productTable.getSelectionModel().getSelectedItem();
//			String query = "SELECT * FROM ms_cart JOIN ms_product ON ms_product.id = ms_cart.product_id";
//			con.res = con.execQuery(query);
//			
//			try {
//				if (productItem == null) {
//					errorAlert.setHeaderText("Error");
//					errorAlert.setContentText("There is no selected product from shop");
//					errorAlert.show();
//				} else if (con.res.next()) {
//					if (con.res.getString("ms_cart.product_id").equals(productItem.getId())) {
//						errorAlert.setHeaderText("Error");
//						errorAlert.setContentText("The selected product already exists in the cart table");
//						errorAlert.show();
//					} else {
//						String productID = productItem.getId();
//						int quantity = quantitySpnr.getValue();
//						
//						String userEmail = Login.emailTxt.getText();
//						
//						String query2 = String.format("SELECT id FROM ms_customer WHERE email = '%s'", userEmail);
//
//						String custID = null;
//						con.res = con.execQuery(query2);
//						try {
//							while (con.res.next()) {
//								custID = con.res.getString("id");
//							}
//						} catch (Exception e2) {
//					
//						}
//						System.out.println(custID);
//						
//						String query3 = String.format("INSERT INTO ms_cart(cust_id, product_id, quantity) VALUES ('%s', '%s', %d)", custID, productID, quantity);
//						con.execUpdate(query3);
//						refreshCart();
//					}
//				}
//			} catch (Exception e2) {
//				// TODO: handle exception
//			}
//		});
//	}
	
//	public void addCart() {
//		addCartBtn.setOnAction(new EventHandler<ActionEvent>() {
////		addCartBtn.setOnAction(e -> {
//		
//			@Override
//			public void handle(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				Alert errorAlert = new Alert(AlertType.ERROR);
//				
//				Product productItem = productTable.getSelectionModel().getSelectedItem();
//				String query = String.format("SELECT * FROM ms_cart JOIN ms_product ON ms_cart.product_id = ms_product.id");
//				con.res = con.execQuery(query);
//				
//				if (productItem == null) {
//					errorAlert.setHeaderText("Error");
//					errorAlert.setContentText("There is no selected product from shop");
//					errorAlert.show();
//				} else
//					try {
//						if (con.res.next()) {
//							if (con.res.getString("ms_cart.product_id").equals(productItem.getId())) {
//								errorAlert.setHeaderText("Error");
//								errorAlert.setContentText("The selected product already exists in the cart table");
//								errorAlert.show();
//							} else {
//								String productID = productItem.getId();
//								
//								int quantity = quantitySpnr.getValue();
//								
//								String userEmail = Login.emailTxt.getText();
//								String query2 = String.format("SELECT id FROM ms_customer WHERE email = '%s'", userEmail);
//								String custID = null;
//								con.res = con.execQuery(query2);
//								try {
//									while (con.res.next()) {
//										custID = con.res.getString("id");
//									}
//								} catch (Exception e2) {
//									System.out.println(e2);
//								}
//								
//								String cartID = "";
//								
//								System.out.println(productID);
//								System.out.println(quantity);
//								System.out.println(custID);
//								String query3 = String.format("INSERT INTO ms_cart VALUES('%s', '%s', '%s', %d)", cartID, custID, productID, quantity);
//								con.execUpdate(query3);
//								refreshCart();
//							}
//						} 
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//			}
//		});
//	}
	
	public void addCart() {
		addCartBtn.setOnAction(e -> {
			// Alert
			Alert errorAlert = new Alert(AlertType.ERROR);
			
			// Product ID
			Product productItem = productTable.getSelectionModel().getSelectedItem();
			String productID = productItem.getId();
			
			// Customer ID
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
			
			// Quantity
			int quantity = quantitySpnr.getValue();
			
			if (productID == null) {
				errorAlert.setHeaderText("Error");
				errorAlert.setContentText("There is no selected product from shop");
				errorAlert.show();
			} 
			
			String query = String.format("SELECT * FROM ms_cart JOIN ms_product ON ms_cart.product_id = ms_product.id");
			con.res = con.execQuery(query);
			try {
				while (con.res.next()) {
					if (con.res.getString("ms_cart.product_id").equals(productItem.getId())) {
						errorAlert.setHeaderText("Error");
						errorAlert.setContentText("The selected product already exists in the cart table");
						errorAlert.show();
					}
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
			String queryInsert = String.format("INSERT INTO ms_cart VALUES(%d, '%s', '%s', %d)", 0, custID, productID, quantity);
			con.execUpdate(queryInsert);
			refreshCart();
		});
	}
	
	
	public void getCart() {
		cart.removeAllElements();
		
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
		
		String query = String.format("SELECT * FROM ms_cart JOIN ms_product ON ms_cart.product_id = ms_product.id WHERE ms_cart.cust_id = '%s'", custID);
		con.res = con.execQuery(query);
		try {
			while (con.res.next()) {
				String productID = con.res.getString("ms_product.id");
				int quantity = con.res.getInt("quantity");
				String cartID = "";
				
				cart.add(new Cart(cartID, custID, productID, quantity));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void removeCart() {
		removeCartBtn.setOnAction(e -> {
			Alert errorAlert = new Alert(AlertType.ERROR);
			
			Cart cartItem = cartTable.getSelectionModel().getSelectedItem();
			
			if (cartItem == null) {
				errorAlert.setHeaderText("Error");
				errorAlert.setContentText("There is no selected product from shop");
				errorAlert.show();
			} else {
				String productId = cartItem.getProductID();
				String query = String.format("DELETE FROM ms_cart WHERE product_id = '%s'", productId);
				con.execUpdate(query);
				refreshCart();
			}
		});
	}
	
	public void order() {
		orderBtn.setOnAction(e -> {
			Alert errorAlert = new Alert(AlertType.ERROR);
			
			Cart cartItem = cartTable.getSelectionModel().getSelectedItem();
			
			String query = String.format("SELECT * FROM ms_cart JOIN ms_product ON ms_cart.product_id = ms_product.id");
			con.res = con.execQuery(query);
			
			ObservableList<Cart> cartObs = FXCollections.observableArrayList(cart);
			
			if (cartObs.isEmpty()) {
				errorAlert.setHeaderText("Error");
				errorAlert.setContentText("Cart is empty! Please select at least 1 product");
				errorAlert.show();
			} else if (cartItem == null) {
				errorAlert.setHeaderText("Error");
				errorAlert.setContentText("Please select a product from the cart");
				errorAlert.show();
			} else
				try {
					if (con.res.next()) {
						if (con.res.getInt("ms_cart.quantity") > con.res.getInt("ms_product.stock")) {							
							errorAlert.setHeaderText("Error");
							errorAlert.setContentText("The quantity of the product in the cart is too much");
							errorAlert.show();
						} else {
							// Transaction Header
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
							
//							String query3 = String.format("SELECT * FROM transaction_header");
//							con.res = con.execQuery(query3);
//							int id = con.res.getInt("id");
//							for (int i = 0; i < id; i++) {
//								id++;
//							} 
							String transactionID = "TR001";
							
							LocalDate date = LocalDate.now();
							
							String queryTH = String.format("INSERT INTO transaction_header VALUES('%s', '%s', '%s')", transactionID, custID, date.toString());
							con.execUpdate(queryTH);
							
							String productID = cartItem.getProductID();
							String query3 = String.format("SELECT * FROM ms_product JOIN ms_cart ON ms_product.id = ms_cart.product_id WHERE ms_cart.product_id = '%s'", productID);
//							String stringQuantity = quantitySpnr.getEditor().getText();
//							int quantity = Integer.parseInt(stringQuantity);
							int quantity = quantitySpnr.getValue();
							int updateStock = 0;
							con.res = con.execQuery(query3);
							try {
								while (con.res.next()) {
									updateStock = con.res.getInt("ms_product.stock") - quantity;
								}
								
								String queryUpdtStck = String.format("UPDATE ms_product JOIN ms_cart ON ms_product.id = ms_cart.product_id SET stock = %d WHERE ms_product.id = '%s'", updateStock, productID);
								con.execUpdate(queryUpdtStck);
								refreshProduct();
								
								String queryRemoveCart = String.format("DELETE FROM ms_cart");
								con.execUpdate(queryRemoveCart);
								refreshCart();
								
							} catch (Exception e2) {
								// TODO: handle exception
							}
							
							// Transaction Detail
							String transactionDetailID = "TR001";
							String tdProductID = cartItem.getProductID();
							int tdQuantity = quantitySpnr.getValue();
							
							String queryTD = String.format("INSERT INTO transaction_detail VALUES('%s', '%s', %d)", transactionDetailID, tdProductID, tdQuantity);
							con.execUpdate(queryTD);
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		});
	}
	
	public void getTH() {
		transactionHeader.removeAllElements();
		
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
		
		String transactionID = "TR001";
		
		String query = String.format("SELECT * FROM transaction_header");
		con.res = con.execQuery(query);
		String transactionId = null;
//		String userID = null;
//		Date date;
		String date;
		try {
			while (con.res.next()) {
				transactionId = con.res.getString("id");
//				userID = con.res.getString("user_id");
				date = con.res.getString("tr_date");
				
				transactionHeader.add(new TransactionHeader(transactionId, date));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void getTD() {
		transactionDetail.removeAllElements();
		
		String transactionID = "TR001";
		
		String query = String.format("SELECT * FROM transaction_detail JOIN ms_product ON transaction_detail.product_id = ms_product.id JOIN ms_product_type ON ms_product.type_id = ms_product_type.id WHERE tr_id = '%s'", transactionID);
		con.res = con.execQuery(query);
		String productID = null;
		String productName = null;
		String productType = null;
		int quantity = 0;
		try {
			while (con.res.next()) {
				productID = con.res.getString("ms_product.product_id");
				productName = con.res.getString("ms_product.name");
				productType = con.res.getString("ms_product_type.name");
				quantity = con.res.getInt("transaction_detail.quantity");
				
				transactionDetail.add(new TransactionDetail(productID, productName, productType, quantity));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void refreshTH() {
		getTH();
		ObservableList<TransactionHeader> thObs = FXCollections.observableArrayList(transactionHeader);
//		ShowTransaction showTransaction = new ShowTransaction();
//		showTransaction.thTable.setItems(thObs);
	}
	
	public void refreshTD() {
		getTD();
		ObservableList<TransactionDetail> tdObs = FXCollections.observableArrayList(transactionDetail);
//		ShowTransaction showTransaction = new ShowTransaction();
//		showTransaction.tdTable.setItems(tdObs);
	}
	
	public void stage(Stage stg) {
		init();
		arrange();
		menuButton(stg);
		productTable();
		cartTable();
		addCart();
		removeCart();
		order();
		stg.setScene(scene);
		stg.setTitle("G&M");
		stg.show();
	}
}
