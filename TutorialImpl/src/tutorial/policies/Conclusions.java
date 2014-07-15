package tutorial.policies;

public class Conclusions {

	private boolean know;
	private String status, measure;
	
	
	public boolean isKnow() {
		return know;
	}
	public void setKnow(boolean know) {
		this.know = know;
	}
	
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Conclusions [know=" + know + ", status=" + status
				+ ", measure=" + measure + "]";
	}

}
