package com.scp.OneToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.scp.OneToOne.HibernateUtility;
import com.scp.OneToOne.MyException;




@Entity
@Table
class CompanyOTM_JTU{
	@Id
	//@GeneratedValue(strategy=GenerationType.TABLE)
	int id;
	String c_Name;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinTable(name="CDOTMJT",joinColumns=@JoinColumn(name="id"),inverseJoinColumns=@JoinColumn(name="depid"))
	List<DepartmentOTM_JTU> department;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getC_Name() {
		return c_Name;
	}
	public void setC_Name(String c_Name) {
		this.c_Name = c_Name;
	}
	public List<DepartmentOTM_JTU> getDepartment() {
		return department;
	}
	public void setDepartment(List<DepartmentOTM_JTU> department) {
		this.department = department;
	}
	public CompanyOTM_JTU(int id, String c_Name, List<DepartmentOTM_JTU> department) {
		super();
		this.id = id;
		this.c_Name = c_Name;
		this.department = department;
	}
	public CompanyOTM_JTU() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
@Entity
@Table
class DepartmentOTM_JTU {
	@Id
	//@GeneratedValue(strategy=GenerationType.TABLE)
	int depid;
	String depName;
	String depPlace;
	public int getDepid() {
		return depid;
	}
	public void setDepid(int depid) {
		this.depid = depid;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getDepPlace() {
		return depPlace;
	}
	public void setDepPlace(String depPlace) {
		this.depPlace = depPlace;
	}
	public DepartmentOTM_JTU(int depid, String depName, String depPlace) {
		super();
		this.depid = depid;
		this.depName = depName;
		this.depPlace = depPlace;
	}
	public DepartmentOTM_JTU() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Department_JTU [depid=" + depid + ", depName=" + depName + ", depPlace=" + depPlace + "]";
	}
		
	
}
public class OneToManyJoinTableUni {

	public static void main(String[] args) throws HibernateException, MyException {
		
		Session session=HibernateUtility.getSessionFactory().openSession();
	    Transaction tx =  session.beginTransaction();
		
	    DepartmentOTM_JTU d1=new DepartmentOTM_JTU(11,"Design", "First_Flour");
		DepartmentOTM_JTU d2=new DepartmentOTM_JTU(12,"Production", "2nd_Flour");
		DepartmentOTM_JTU d3=new DepartmentOTM_JTU(13,"Engine", "3rd_Flour");
		DepartmentOTM_JTU d4=new DepartmentOTM_JTU(14,"Maintainance", "4th_Flour");
	    
	    List<DepartmentOTM_JTU> list=new ArrayList<DepartmentOTM_JTU>();
	    list.add(d1);
	    list.add(d2);
	    
	    List<DepartmentOTM_JTU> list1=new ArrayList<DepartmentOTM_JTU>();
	    list1.add(d3);
	    list1.add(d4);
	   
	    CompanyOTM_JTU c1=new CompanyOTM_JTU(1, "MANTruck",list);
	    CompanyOTM_JTU c2=new CompanyOTM_JTU(2, "TataMotors",list1);
	    
	    session.save(c1);
	    session.save(c2);
	    tx.commit();

	}

}
