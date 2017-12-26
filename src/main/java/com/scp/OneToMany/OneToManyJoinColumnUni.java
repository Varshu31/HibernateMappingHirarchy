package com.scp.OneToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.scp.OneToOne.HibernateUtility;
import com.scp.OneToOne.MyException;


@Entity
@Table
class CompanyOTM_JCU{
	@Id
	//@GeneratedValue(strategy=GenerationType.TABLE)
	int id;
	String c_Name;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="DeptOTM")
	List<DepartmentOTM_JCU> department;
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
	public List<DepartmentOTM_JCU> getDepartment() {
		return department;
	}
	public void setDepartment(List<DepartmentOTM_JCU> department) {
		this.department = department;
	}
	public CompanyOTM_JCU(int id, String c_Name, List<DepartmentOTM_JCU> department) {
		super();
		this.id = id;
		this.c_Name = c_Name;
		this.department = department;
	}
	public CompanyOTM_JCU() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Company_JCU [id=" + id + ", c_Name=" + c_Name + ", department=" + department + "]";
	}
	
	

}
@Entity
@Table
class DepartmentOTM_JCU {
	@Id
	//@GeneratedValue(strategy=GenerationType.TABLE)
	int depid;
	String depName;
	String depPlace;
	public DepartmentOTM_JCU(int depid,String depName, String depPlace) {
		super();
		this.depid = depid;
		this.depName = depName;
		this.depPlace = depPlace;
	}
	public DepartmentOTM_JCU() {
		super();
		// TODO Auto-generated constructor stub
	}
		
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
	public void setDepPlace( String depPlace) {
		this.depPlace = depPlace;
	}
	@Override
	public String toString() {
		return "DepartmentOTM_JCU [depid=" + depid + ", depName=" + depName + ", depPlace=" + depPlace + "]";
	}
	
	
}

public class OneToManyJoinColumnUni {
	
	public static void main(String[] args) throws HibernateException, MyException {
	Session session=HibernateUtility.getSessionFactory().openSession();
    
    Transaction tx =  session.beginTransaction();
    DepartmentOTM_JCU d1=new DepartmentOTM_JCU(11,"Design", "First_Flour");
    DepartmentOTM_JCU d2=new DepartmentOTM_JCU(12,"Production", "2nd_Flour");
    DepartmentOTM_JCU d3=new DepartmentOTM_JCU(13,"Engine", "3rd_Flour");
    DepartmentOTM_JCU d4=new DepartmentOTM_JCU(14,"Maintainance", "4th_Flour");
    
    List<DepartmentOTM_JCU> list=new ArrayList<DepartmentOTM_JCU>();
    list.add(d1);
    list.add(d2);
    
    List<DepartmentOTM_JCU> list1=new ArrayList<DepartmentOTM_JCU>();
    list1.add(d3);
    list1.add(d4);
   
    CompanyOTM_JCU c1=new CompanyOTM_JCU(1, "MANTruck",list);
    CompanyOTM_JCU c2=new CompanyOTM_JCU(2, "TataMotors",list1);
    
    session.save(c1);
    session.save(c2);
   
   
    System.out.println("Successfully completed");
   tx.commit();
	}
}
