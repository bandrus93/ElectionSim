package com.innotech.votingsim.models;

public class Population {
	private Long totalPop;
	private Long radLeft;
	private Long modLeft;
	private Long center;
	private Long modRight;
	private Long radRight;
	
	public Population() {
		
	}

	public Long getTotalPop() {
		return totalPop;
	}

	public void setTotalPop(Long totalPop) {
		this.totalPop = totalPop;
	}

	public Long getRadLeft() {
		return radLeft;
	}

	public void setRadLeft(Double segment) {
		this.radLeft = Math.round(totalPop * segment);
	}

	public Long getModLeft() {
		return modLeft;
	}

	public void setModLeft(Double segment) {
		this.modLeft = Math.round(totalPop * segment);
	}

	public Long getCenter() {
		return center;
	}

	public void setCenter(Double segment) {
		this.center = Math.round(totalPop * segment);
	}

	public Long getModRight() {
		return modRight;
	}

	public void setModRight(Double segment) {
		this.modRight = Math.round(totalPop * segment);
	}

	public Long getRadRight() {
		return radRight;
	}

	public void setRadRight(Double segment) {
		this.radRight = Math.round(totalPop * segment);
	}
	
	public boolean checkSum() {
		if(this.totalPop == null || this.radLeft == null || this.modLeft == null || this.center == null || this.modRight == null || this.radRight == null) {
			return false;
		}
		return true;
	}
}
