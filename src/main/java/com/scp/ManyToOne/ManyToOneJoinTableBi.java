package com.scp.ManyToOne;

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
import org.hibernate.annotations.WhereJoinTable;

import com.scp.OneToOne.HibernateUtility;
import com.scp.OneToOne.MyException;



@Entity
@Table
class CompanyMTO_JTB {
	@Id
	// @GeneratedValue(strategy=GenerationType.TABLE)
	int id;
	String c_Name;
	@ManyToOne
	@JoinTable(name="COMP_DEPT_JTB",joinColumns=@JoinColumn(name="id"),inverseJoinColumns=@JoinColumn(name="depid"))
	DepartmentMTO_JTB department;
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
	public DepartmentMTO_JTB getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentMTO_JTB department) {
		this.department = department;
	}
	public CompanyMTO_JTB(int id, String c_Name, DepartmentMTO_JTB department) {
		super();
		this.id = id;
		this.c_Name = c_Name;
		this.department = department;
	}
	public CompanyMTO_JTB() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}

@Entity
@Table
class DepartmentMTO_JTB {
	@Id
	// @GeneratedValue(strategy=GenerationType.TABLE)
	int depid;
	String depName;
	String depPlace;
	@OneToMany(cascade = CascadeType.ALL,mappedBy="department")
	
	List<CompanyMTO_JTB> company;
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
	public List<CompanyMTO_JTB> getCompany() {
		return company;
	}
	public void setCompany(List<CompanyMTO_JTB> company) {
		this.company = company;
	}
	public DepartmentMTO_JTB(int depid, String depName, String depPlace, List<CompanyMTO_JTB> company) {
		super();
		this.depid = depid;
		this.depName = depName;
		this.depPlace = depPlace;
		this.company = company;
	}
	public DepartmentMTO_JTB() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}

public class ManyToOneJoinTableBi {

	public static void main(String[] args) throws HibernateException, MyException {
Session session=HibernateUtility.getSessionFactory().openSession();
	    
	    Transaction tx =  session.beginTransaction();
	    
	    
	    CompanyMTO_JTB c1=new CompanyMTO_JTB(21, "MANTruck",null);
	    CompanyMTO_JTB c2=new CompanyMTO_JTB(22, "TataMotors",null);
	    
	    DepartmentMTO_JTB d1=new DepartmentMTO_JTB(3, "Design", "Ground_Flour", null); 
	    List<CompanyMTO_JTB> list=new ArrayList<CompanyMTO_JTB>();
	    
	    c1.setDepartment(d1);
	    c2.setDepartment(d1);
	    
	    list.add(c1);
	    list.add(c2);
	    d1.setCompany(list);
	    
	    CompanyMTO_JTB c3=new CompanyMTO_JTB(23, "Mahindra",null);
	    CompanyMTO_JTB c4=new CompanyMTO_JTB(24, "JCB",null);
	    
	    DepartmentMTO_JTB d2=new DepartmentMTO_JTB(4, "Engine", "1st_Flour", null);
	    List<CompanyMTO_JTB> list1=new ArrayList<CompanyMTO_JTB>();
	    
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
