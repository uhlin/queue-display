package com.uhlin;

public class Patient {
	private Integer nr;
	private Integer pos;


	public Patient(Integer nr, Integer pos) {
		this.nr = nr;
		this.pos = pos;
	}


	public Integer getNr() {
		return nr;
	}
	public void setNr(Integer nr) {
		this.nr = nr;
	}


	public Integer getPos() {
		return pos;
	}
	public void setPos(Integer pos) {
		this.pos = pos;
	}
}
