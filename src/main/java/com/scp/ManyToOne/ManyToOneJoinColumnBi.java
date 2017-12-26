package com.scp.ManyToOne;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
class CompanyMTO_JCB {
	@Id
	// @GeneratedValue(strategy=GenerationType.TABLE)
	int id;
	String c_Name;
	@ManyToOne
	@JoinColumn(name="DeptCompMTO_JCB")
	DepartmentMTO_JCB department;
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
	public DepartmentMTO_JCB getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentMTO_JCB department) {
		this.department = department;
	}
	public CompanyMTO_JCB(int id, String c_Name, DepartmentMTO_JCB department) {
		super();
		this.id = id;
		this.c_Name = c_Name;
		this.department = department;
	}
	public CompanyMTO_JCB() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}

@Entity
@Table
class DepartmentMTO_JCB {
	@Id
	// @GeneratedValue(strategy=GenerationType.TABLE)
	int depid;
	String depName;
	String depPlace;
	@OneToMany(cascade = CascadeType.ALL, mappedBy="department")
	
	List<CompanyMTO_JCB> company;
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
	public List<CompanyMTO_JCB> getCompany() {
		return company;
	}
	public void setCompany(List<CompanyMTO_JCB> company) {
		this.company = company;
	}
	public DepartmentMTO_JCB(int depid, String depName, String depPlace, List<CompanyMTO_JCB> company) {
		super();
		this.depid = depid;
		this.depName = depName;
		this.depPlace = depPlace;
		this.company = company;
	}
	public DepartmentMTO_JCB() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}

public class ManyToOneJoinColumnBi {

	public static void main(String[] args) throws HibernateException, MyException {
		Session session=HibernateUtility.getSessionFactory().openSession();
	    
	    Transaction tx =  session.beginTransaction();
	    
	    
	    CompanyMTO_JCB c1=new CompanyMTO_JCB(11, "MANTruck",null);
	    CompanyMTO_JCB c2=new CompanyMTO_JCB(12, "TataMotors",null);
	    
	    DepartmentMTO_JCB d1=new DepartmentMTO_JCB(1, "Design", "Ground_Flour", null); 
	    List<CompanyMTO_JCB> list=new ArrayList<CompanyMTO_JCB>();
	    
	    c1.setDepartment(d1);
	    c2.setDepartment(d1);
	    
	    list.add(c1);
	    list.add(c2);
	    d1.setCompany(list);
	    
	    CompanyMTO_JCB c3=new CompanyMTO_JCB(13, "Mahindra",null);
	    CompanyMTO_JCB c4=new CompanyMTO_JCB(14, "JCB",null);
	    
	    DepartmentMTO_JCB d2=new DepartmentMTO_JCB(2, "Engine", "1st_Flour", null);
	    List<CompanyMTO_JCB> list1=new ArrayList<CompanyMTO_JCB>();
	    
	    c3.setDepartment(d2);
	    c4.setDepartment(d2);
	    
	    list1.add(c3);
	    list1.add(c4);
	    d2.setCompany(list1);
	    
	    session.save(d1);
	    session.save(d2);
	    tx.commit();
	}

}
