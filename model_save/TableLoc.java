package model;

import java.io.Serializable;

public class TableLoc implements Serializable{

	private int tableId;
	private String tableName;
	private int maxCapacity;

	public TableLoc() {
		super();
	}

	public int getTableId() {
		return tableId;
	}
	public String getTableName() {
		return tableName;
	}
	public int getMaxCapacity() {
		return maxCapacity;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
}
