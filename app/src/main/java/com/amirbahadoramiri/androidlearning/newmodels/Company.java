package com.amirbahadoramiri.androidlearning.newmodels;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "company")
public class Company{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private List<WarehouseItem> warehouse;
    private List<KalaItem> kala;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWarehouse(List<WarehouseItem> warehouse){
		this.warehouse = warehouse;
	}

	public List<WarehouseItem> getWarehouse(){
		return warehouse;
	}

    public void setKala(List<KalaItem> kala){
        this.kala = kala;
    }

    public List<KalaItem> getKala(){
        return kala;
    }

}