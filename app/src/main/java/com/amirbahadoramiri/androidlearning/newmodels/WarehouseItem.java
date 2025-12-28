package com.amirbahadoramiri.androidlearning.newmodels;

import androidx.room.PrimaryKey;

import java.util.List;

public class WarehouseItem {
    @PrimaryKey(autoGenerate = true)
	private int wareid;
    private String name;
    private List<MojodiItem> mojodi;

    public WarehouseItem(String name) {
        this.name = name;
    }

    public void setMojodi(List<MojodiItem> mojodi){
		this.mojodi = mojodi;
	}

	public List<MojodiItem> getMojodi(){
		return mojodi;
	}

	public void setWareid(int wareid){
		this.wareid = wareid;
	}

	public int getWareid(){
		return wareid;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}
}