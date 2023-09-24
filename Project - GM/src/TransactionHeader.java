import java.sql.Date;

public class TransactionHeader {
	
	private String transactionID;
//	private String userID;
	private String transactionDate;
	
	public TransactionHeader(String transactionID, String transactionDate) {
		this.transactionID = transactionID;
		this.transactionDate = transactionDate;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	
	
//	public TransactionHeader(String transactionID, String userID, Date transactionDate) {
//		this.transactionID = transactionID;
//		this.userID = userID;
//		this.transactionDate = transactionDate;
//	}
//
//	public String getTransactionID() {
//		return transactionID;
//	}
//
//	public void setTransactionID(String transactionID) {
//		this.transactionID = transactionID;
//	}
//
//	public String getUserID() {
//		return userID;
//	}
//
//	public void setUserID(String userID) {
//		this.userID = userID;
//	}
//
//	public Date getTransactionDate() {
//		return transactionDate;
//	}
//
//	public void setTransactionDate(Date transactionDate) {
//		this.transactionDate = transactionDate;
//	}
		
	
}
