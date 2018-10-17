package study.spring.hellospring.model;

public class Professor {
	private int profno;
	private String pname;
	private String userid; 
	private String position;
	private int sal;
	private String hiredate;
	private int comm;
	private int deptno;
	
	public int getProfno() {
		return profno;
	}
	public void setProfno(int profno) {
		this.profno = profno;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getSal() {
		return sal;
	}
	public void setSal(int sal) {
		this.sal = sal;
	}
	public String getHiredate() {
		return hiredate;
	}
	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}
	public int getComm() {
		return comm;
	}
	public void setComm(int comm) {
		this.comm = comm;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	
	@Override
	public String toString() {
		return "Professor [profno=" + profno + ", pname=" + pname + ", userid=" + userid + ", position=" + position
				+ ", sal=" + sal + ", hiredate=" + hiredate + ", comm=" + comm + ", deptno=" + deptno + "]";
	}
}
