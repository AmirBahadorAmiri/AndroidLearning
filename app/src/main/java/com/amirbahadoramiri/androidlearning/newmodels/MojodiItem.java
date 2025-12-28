package com.amirbahadoramiri.androidlearning.newmodels;

import androidx.room.PrimaryKey;

public class MojodiItem{
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int kalaId;
    private String kalaName;
    private int tedad;

    public MojodiItem() {}

    public MojodiItem(int kalaId, String kalaName, int tedad) {
        this.kalaId = kalaId;
        this.kalaName = kalaName;
        this.tedad = tedad;
    }

    public void setTedad(int tedad){
		this.tedad = tedad;
	}

	public int getTedad(){
		return tedad;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setKalaName(String kalaName){
		this.kalaName = kalaName;
	}

	public String getKalaName(){
		return kalaName;
	}

	public void setKalaId(int kalaId){
		this.kalaId = kalaId;
	}

	public int getKalaId(){
		return kalaId;
	}
}
