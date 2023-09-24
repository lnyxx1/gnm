
public class TransactionDetail {
	
	private String tdProductID;
	private String tdProductName;
	private String tdProductTypeName;
	private int tdQuantity;
	
	public TransactionDetail(String tdProductID, String tdProductName, String tdProductTypeName, int tdQuantity) {
		this.tdProductID = tdProductID;
		this.tdProductName = tdProductName;
		this.tdProductTypeName = tdProductTypeName;
		this.tdQuantity = tdQuantity;
	}

	public String getTdProductID() {
		return tdProductID;
	}

	public void setTdProductID(String tdProductID) {
		this.tdProductID = tdProductID;
	}

	public String getTdProductName() {
		return tdProductName;
	}

	public void setTdProductName(String tdProductName) {
		this.tdProductName = tdProductName;
	}

	public String getTdProductTypeName() {
		return tdProductTypeName;
	}

	public void setTdProductTypeName(String tdProductTypeName) {
		this.tdProductTypeName = tdProductTypeName;
	}

	public int getTdQuantity() {
		return tdQuantity;
	}

	public void setTdQuantity(int tdQuantity) {
		this.tdQuantity = tdQuantity;
	}
	
//	public TransactionDetail(String tdID, String tdProductID, int tdQuantity) {
//		this.tdID = tdID;
//		this.tdProductID = tdProductID;
//		this.tdQuantity = tdQuantity;
//	}
//
//	public String getTdID() {
//		return tdID;
//	}
//
//	public void setTdID(String tdID) {
//		this.tdID = tdID;
//	}
//
//	public String getTdProductID() {
//		return tdProductID;
//	}
//
//	public void setTdProductID(String tdProductID) {
//		this.tdProductID = tdProductID;
//	}
//
//	public int getTdQuantity() {
//		return tdQuantity;
//	}
//
//	public void setTdQuantity(int tdQuantity) {
//		this.tdQuantity = tdQuantity;
//	}
}
