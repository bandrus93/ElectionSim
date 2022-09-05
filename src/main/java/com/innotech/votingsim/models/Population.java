package com.innotech.votingsim.models;

public class Population {
	private Long totalPop = 0L;
	private Long radLeft = 0L;
	private Long modLeft = 0L;
	private Long center = 0L;
	private Long modRight = 0L;
	private Long radRight = 0L;
	
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

	public boolean checkNull() {
		if (totalPop == 0) {
			return true;
		} else if (radLeft == 0) {
			return true;
		} else if (modLeft == 0) {
			return true;
		} else if (center == 0) {
			return true;
		} else if (modRight == 0) {
			return true;
		} else return radRight == 0;
	}

}
