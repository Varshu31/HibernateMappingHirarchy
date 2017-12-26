package com.scp.ManyToOne;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.scp.OneToOne.HibernateUtility;
import com.scp.OneToOne.MyException;



@Entity
@Table
class CompanyMTO_PKB {
	@Id
	// @GeneratedValue(strategy=GenerationType.TABLE)
	int id;
	String c_Name;
	
	@ManyToOne
	@PrimaryKeyJoinColumn
	DepartmentMTO_PKB department;

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

	public DepartmentMTO_PKB getDepartment() {
		return department;
	}

	public CompanyMTO_PKB() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompanyMTO_PKB(int id, String c_Name, DepartmentMTO_PKB department) {
		super();
		this.id = id;
		this.c_Name = c_Name;
		this.department = department;
	}

	public void setDepartment(DepartmentMTO_PKB department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "CompanyMTO_PKB [id=" + id + ", c_Name=" + c_Name + ", department=" + department + "]";
	}
	
	

	
}

@Entity
@Table
class DepartmentMTO_PKB {
	@Id
	// @GeneratedValue(strategy=GenerationType.TABLE)
	int depid;
	String depName;
	String depPlace;
	@OneToMany(cascade=CascadeType.ALL)
	List<CompanyMTO_PKB> company;
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
	public List<CompanyMTO_PKB> getCompany() {
		return company;
	}
	public void setCompany(List<CompanyMTO_PKB> company) {
		this.company = company;
	}
	public DepartmentMTO_PKB(int depid, String depName, String depPlace, List<CompanyMTO_PKB> company) {
		super();
		this.depid = depid;
		this.depName = depName;
		this.depPlace = depPlace;
		this.company = company;
	}
	public DepartmentMTO_PKB() {
		super();
		// TODO Auto-generated constructor stub
	}

}

public class ManyToOnePKBI {

	public static void main(String[] args) throws HibernateException, MyException {
		Session session=HibernateUtility.getSessionFactory().openSession();
	                                                                                                                 
	    Transaction tx =  session.beginTransaction();
	    
	    
	    CompanyMTO_PKB c1=new CompanyMTO_PKB(21, "MANTruck",null);
	    CompanyMTO_PKB c2=new CompanyMTO_PKB(22, "TataMotors",null);
	    
	    DepartmentMTO_PKB d1=new DepartmentMTO_PKB(3, "Design", "Ground_Flour", null); 
	    List<CompanyMTO_PKB> list=new ArrayList<CompanyMTO_PKB>();
	    
	    c1.setDepartment(d1);
	    c2.setDepartment(d1);
	    
	    list.add(c1);
	    list.add(c2);
	    d1.setCompany(list);
	    
	    CompanyMTO_PKB c3=new CompanyMTO_PKB(23, "Mahindra",null);
	    CompanyMTO_PKB c4=new CompanyMTO_PKB(24, "JCB",null);
	    
	    DepartmentMTO_PKB d2=new DepartmentMTO_PKB(4, "Engine", "1st_Flour", null);
	    List<CompanyMTO_PKB> list1=new ArrayList<CompanyMTO_PKB>();
	    
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


 