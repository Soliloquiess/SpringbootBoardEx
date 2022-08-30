package aa.bb.cc;


public class DTO {
	private String ename;
	private String job;
	private Integer sal;
	private int avg;
	public DTO() {
		// TODO Auto-generated constructor stub
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	
	public void setAvg(int avg){
		this.avg = avg;
	}
	public int getAvg(){
		return avg;
	}
	public Integer getSal() {
		return sal;
	}
	public void setSal(Integer sal) {
		this.sal = sal;
	}
	public DTO(String ename, String job, Integer sal) {
		super();
		this.ename = ename;
		this.job = job;
		this.sal = sal;
	}
	
	
	

	
	
}
	