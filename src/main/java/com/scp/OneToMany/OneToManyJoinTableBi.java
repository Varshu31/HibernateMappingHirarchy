package com.scp.OneToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.scp.OneToOne.HibernateUtility;
import com.scp.OneToOne.MyException;

@Entity
@Table
class CompanyOTM_JTB {
	@Id
	// @GeneratedValue(strategy=GenerationType.TABLE)
	int id;
	String c_Name;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="company")
	
	List<DepartmentOTM_JTB> department;
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
	public List<DepartmentOTM_JTB> getDepartment() {
		return department;
	}
	public void setDepartment(List<DepartmentOTM_JTB> department) {
		this.department = department;
	}
	public CompanyOTM_JTB(int id, String c_Name, List<DepartmentOTM_JTB> list) {
		super();
		this.id = id;
		this.c_Name = c_Name;
		this.department = list;
	}
	public CompanyOTM_JTB() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CompanyOTM_JTB [id=" + id + ", c_Name=" + c_Name + ", department=" + department + "]";
	}

	
}

@Entity
@Table
class DepartmentOTM_JTB {
	@Id
	// @GeneratedValue(strategy=GenerationType.TABLE)
	int depid;
	String depName;
	String depPlace;
	@ManyToOne
	@JoinTable(name="OTMCDJTB",joinColumns=@JoinColumn(name="id"),inverseJoinColumns=@JoinColumn(name="depid"))

	CompanyOTM_JTB company;

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

	public CompanyOTM_JTB getCompany() {
		return company;
	}

	public void setCompany(CompanyOTM_JTB company) {
		this.company = company;
	}

	public DepartmentOTM_JTB(int depid, String depName, String depPlace, CompanyOTM_JTB company) {
		super();
		this.depid = depid;
		this.depName = depName;
		this.depPlace = depPlace;
		this.company = company;
	}

	public DepartmentOTM_JTB() {
		super();
		// TODO Auto-generated constructor stub
	}
}

public class OneToManyJoinTableBi {

	public static void main(String[] args) throws HibernateException, MyException {
		// TODO Auto-generated method stub
		
Session session=HibernateUtility.getSessionFactory().openSession();
	    
	    Transaction tx =  session.beginTransaction();
	    DepartmentOTM_JTB d1=new DepartmentOTM_JTB(21, "Design", "Ground_Flour", null);
	    DepartmentOTM_JTB d2=new DepartmentOTM_JTB(22,"Production", "2nd_Flour", null);
	    DepartmentOTM_JTB d3=new DepartmentOTM_JTB(23,"Engine", "3rd_Flour",null);
	    DepartmentOTM_JTB d4=new DepartmentOTM_JTB(24,"Maintainance", "4th_Flour", null);
	    CompanyOTM_JTB c1=new CompanyOTM_JTB(11, "MANTruck",null);
	    
	    List<DepartmentOTM_JTB> list=new ArrayList<DepartmentOTM_JTB>();
	    d1.setCompany(c1);
	    d2.setCompany(c1);
	    list.add(d1);
	    list.add(d2);
	    c1.setDepartment(list);
	    
	    CompanyOTM_JTB c2=new CompanyOTM_JTB(12, "TataMotors",null);
	    List<DepartmentOTM_JTB> list1=new ArrayList<DepartmentOTM_JTB>();
	    d3.setCompany(c2);
	    d4.setCompany(c2);
	    list1.add(d3);
	    list1.add(d4);
	    c2.setDepartment(list1);
	   
	      
	    session.save(c1);
	    session.save(c2);
	    

	   
	   System.out.println("Successfully completed");
	   tx.commit();

	}

}
