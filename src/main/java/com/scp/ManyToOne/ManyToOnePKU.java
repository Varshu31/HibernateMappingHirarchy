package com.scp.ManyToOne;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.scp.OneToOne.HibernateUtility;
import com.scp.OneToOne.MyException;

@Entity
@Table
class CompanyMTO_PKU{
	@Id
	//@GeneratedValue(strategy=GenerationType.TABLE)
	int id;
	String c_Name;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	DepartmentMTO_PKU department;

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

	public DepartmentMTO_PKU getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentMTO_PKU department) {
		this.department = department;
	}

	public CompanyMTO_PKU(int id, String c_Name, DepartmentMTO_PKU department) {
		super();
		this.id = id;
		this.c_Name = c_Name;
		this.department = department;
	}

	public CompanyMTO_PKU() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CompanyMTO_JTU [id=" + id + ", c_Name=" + c_Name + ", department=" + department + "]";
	}
	
	
}

@Entity
@Table
class DepartmentMTO_PKU {
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
	public DepartmentMTO_PKU(int depid, String depName, String depPlace) {
		super();
		this.depid = depid;
		this.depName = depName;
		this.depPlace = depPlace;
	}
	public DepartmentMTO_PKU() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "DepartmentMTO_JTU [depid=" + depid + ", depName=" + depName + ", depPlace=" + depPlace + "]";
	}
	
	
}


public class ManyToOnePKU {

	public static void main(String[] args) throws HibernateException, MyException {
		Session session=HibernateUtility.getSessionFactory().openSession();
	    
	    Transaction tx =  session.beginTransaction();
	    DepartmentMTO_PKU d1=new DepartmentMTO_PKU(11,"Design", "First_Flour");

	    CompanyMTO_PKU c1=new CompanyMTO_PKU(1, "MANTruck",d1);
	    CompanyMTO_PKU c2=new CompanyMTO_PKU(2, "TataMotors",d1);
	    
	    session.save(c1);
	    session.save(c2);
	    tx.commit();

	}

}
