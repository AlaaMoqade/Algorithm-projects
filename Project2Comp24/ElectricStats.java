package Project2Comp24;



public class ElectricStats {
    private float total;
    private float average;
    private float maximum;
    private float minimum;

    public ElectricStats(float total,float average,float maximum,float minimum) {
        this.total = 0;
        this.average = 0;
        this.maximum = Float.MIN_VALUE;
        this.minimum = Float.MAX_VALUE;
    }

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getAverage() {
		return average;
	}

	public void setAverage(float average) {
		this.average = average;
	}

	public float getMaximum() {
		return maximum;
	}

	public void setMaximum(float maximum) {
		this.maximum = maximum;
	}

	public float getMinimum() {
		return minimum;
	}

	public void setMinimum(float minimum) {
		this.minimum = minimum;
	}

	@Override
	public String toString() {
		return "ElectricStats [total=" + total + ", average=" + average + ", maximum=" + maximum + ", minimum="
				+ minimum + "]";
	}

   
    
}
