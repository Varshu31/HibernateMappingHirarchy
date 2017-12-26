package com.scp.ManyToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.scp.OneToOne.HibernateUtility;
import com.scp.OneToOne.MyException;

@Entity
@Table
class CompanyMTM_JTB {
		@Id
		int id;
		String c_Name;
		@ManyToMany(cascade=CascadeType.ALL)
		@JoinColumn(name="MTMJCBID")
		
		List<DepartmentMTM_JTB> department=new ArrayList<DepartmentMTM_JTB>();

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
		public List<DepartmentMTM_JTB> getDepartment() {
			return department;
		}

		public void setDepartment(List<DepartmentMTM_JTB> department) {
			this.department = department;
		}

		public CompanyMTM_JTB(int id, String c_Name){
			super();
			this.id = id;
			this.c_Name = c_Name;
			//this.department = department;
		}

		public CompanyMTM_JTB() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "CompanyMTM_JTB [id=" + id + ", c_Name=" + c_Name + ", department=" + department + "]";
		}
			
		
		
}

@Entity
@Table
class DepartmentMTM_JTB {
		@Id
		int depid;
		String depName;
		String depPlace;
		
		@ManyToMany(mappedBy="department")
		List<CompanyMTM_JTB> company=new ArrayList<CompanyMTM_JTB>();

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

		public List<CompanyMTM_JTB> getCompany() {
			return company;
		}

		public void setCompany(List<CompanyMTM_JTB> company) {
			this.company = company;
		}

		public DepartmentMTM_JTB(int depid, String depName, String depPlace) {
			super();
			this.depid = depid;
			this.depName = depName;
			this.depPlace = depPlace;
		}

		public DepartmentMTM_JTB() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "DepartmentMTM_JTB [depid=" + depid + ", depName=" + depName + ", depPlace=" + depPlace
					+ ", company=" + company + "]";
		}
		
		
					
}



public class ManyToManyJoinTableBi {
	
	public static void main(String[] args) throws HibernateException, MyException {
		Session session = HibernateUtility.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();
		DepartmentMTM_JTB d1 = new DepartmentMTM_JTB(11, "Design", "3rd Flour");
		DepartmentMTM_JTB d2 = new DepartmentMTM_JTB(12, "Engine", "3rd Flour");
		DepartmentMTM_JTB d3 = new DepartmentMTM_JTB(13, "Production", "4th Flour");

		CompanyMTM_JTB c1 = new CompanyMTM_JTB(1, "Mahindra");
		CompanyMTM_JTB c2 = new CompanyMTM_JTB(2, "TataMotors");
		
		// company1 have multiple department c1.getDepartment().add(d1);
		 c1.getDepartment().add(d1);
		 c1.getDepartment().add(d2);
		  
		// company2 have multiple department c2.getDepartment().add(d1);
		 c2.getDepartment().add(d1); 
		 c2.getDepartment().add(d2);
		 c2.getDepartment().add(d3);
			
		session.save(c1);
		session.save(c2);
		tx.commit();

	}

}
