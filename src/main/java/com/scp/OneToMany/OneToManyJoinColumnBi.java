package com.scp.OneToMany;

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
	class CompanyOTM_JCB {
		@Id
		// @GeneratedValue(strategy=GenerationType.TABLE)
		int id;
		String c_Name;
		
		@OneToMany(cascade = CascadeType.ALL, mappedBy="company")
		//@JoinColumn(name="Comp_Dept_JCB")
		List<DepartmentOTM_JCB> department;
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
		public List<DepartmentOTM_JCB> getDepartment() {
			return department;
		}
		public void setDepartment(List<DepartmentOTM_JCB> department) {
			this.department = department;
		}
		public CompanyOTM_JCB(int id, String c_Name, List<DepartmentOTM_JCB> list) {
			super();
			this.id = id;
			this.c_Name = c_Name;
			this.department = list;
		}
		public CompanyOTM_JCB() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "CompanyOTM_JCB [id=" + id + ", c_Name=" + c_Name + ", department=" + department + "]";
		}

		
	}

@Entity
@Table
class DepartmentOTM_JCB {
		@Id
		// @GeneratedValue(strategy=GenerationType.TABLE)
		int depid;
		String depName;
		String depPlace;
		@ManyToOne
		@JoinColumn(name="Dept_Comp_JCB")
		CompanyOTM_JCB company;

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

		public CompanyOTM_JCB getCompany() {
			return company;
		}

		public void setCompany(CompanyOTM_JCB company) {
			this.company = company;
		}

		public DepartmentOTM_JCB(int depid, String depName, String depPlace, CompanyOTM_JCB company) {
			super();
			this.depid = depid;
			this.depName = depName;
			this.depPlace = depPlace;
			this.company = company;
		}

		public DepartmentOTM_JCB() {
			super();
			// TODO Auto-generated constructor stub
		}
	}
		
public class OneToManyJoinColumnBi {

	public static void main(String[] args) throws HibernateException, MyException {
		// TODO Auto-generated method stub
		Session session=HibernateUtility.getSessionFactory().openSession();
	    
	    Transaction tx =  session.beginTransaction();
	    DepartmentOTM_JCB d1=new DepartmentOTM_JCB(31, "Design", "Ground_Flour", null);
	    DepartmentOTM_JCB d2=new DepartmentOTM_JCB(32,"Production", "2nd_Flour", null);
	    DepartmentOTM_JCB d3=new DepartmentOTM_JCB(33,"Engine", "3rd_Flour",null);
	    DepartmentOTM_JCB d4=new DepartmentOTM_JCB(34,"Maintainance", "4th_Flour", null);
	    CompanyOTM_JCB c1=new CompanyOTM_JCB(3, "MANTruck",null);
	    
	    List<DepartmentOTM_JCB> list=new ArrayList<DepartmentOTM_JCB>();
	    d1.setCompany(c1);
	    d2.setCompany(c1);
	    list.add(d1);
	    list.add(d2);
	    c1.setDepartment(list);
	    
	    CompanyOTM_JCB c2=new CompanyOTM_JCB(12, "TataMotors",null);
	    List<DepartmentOTM_JCB> list1=new ArrayList<DepartmentOTM_JCB>();
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
	