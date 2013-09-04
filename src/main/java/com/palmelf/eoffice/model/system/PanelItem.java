package com.palmelf.eoffice.model.system;

public class PanelItem {
	private String panelId;
	private int column;
	private int row;

	public String getPanelId() {
		return this.panelId;
	}

	public void setPanelId(String panelId) {
		this.panelId = panelId;
	}

	public int getColumn() {
		return this.column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return this.row;
	}

	public void setRow(int row) {
		this.row = row;
	}
}
