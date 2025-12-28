package com.amirbahadoramiri.androidlearning.newmodels;

import androidx.room.PrimaryKey;

public class KalaItem{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    public KalaItem(String name) {
        this.name = name;
    }

    public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}
}
