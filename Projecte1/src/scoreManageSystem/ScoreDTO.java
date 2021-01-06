package scoreManageSystem;

import java.io.Serializable;
import java.text.DecimalFormat;

public class ScoreDTO implements Comparable<ScoreDTO>, Serializable{
	private String num; 
	private String name; 
	private int kor; 
	private int eng; 
	private int math; 
	private int pro;
	private int co;
	private int tot; 
	private double avg;
	
//	public ScoreDTO(String num, String name, int kor, int eng, int math, int tot, double avg) {
//		this.num = num;
//		this.name = name; 
//		this.kor = kor;
//		this.eng = eng;
//		this.math = math;
//		this.tot = tot;
//		this.avg = avg;
//		
//	}
	// getter & setter
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getEng() {
		return eng;
	}
	public void setEng(int eng) {
		this.eng = eng;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	
	public int getPro() {
		return pro;
	}
	public void setPro(int pro) {
		this.pro = pro;
	}
	
	public int getCo() {
		return co;
	}
	public void setCo(int co) {
		this.co = co;
	}
	

	
	public int getTot() {
		return tot;
	}
	public void setTot(int tot) {
		this.tot = tot;
	}
	public String getAvg() {
		return new DecimalFormat("0.###").format(avg);
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	
	public void calc() {
		tot = kor + eng + math + pro + co;
		avg = tot/5;
	}

	@Override
	public int compareTo(ScoreDTO dto) {
		return this.tot > dto.tot ? 1 : -1;
	}
}