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
class CompanyMTM_JCB {
		@Id
		int id;
		String c_Name;
		@ManyToMany(cascade=CascadeType.ALL)
		@JoinColumn(name="MTMJCBID")
		
		List<DepartmentMTM_JCB> department=new ArrayList<DepartmentMTM_JCB>();

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
		public List<DepartmentMTM_JCB> getDepartment() {
			return department;
		}

		public void setDepartment(List<DepartmentMTM_JCB> department) {
			this.department = department;
		}

		public CompanyMTM_JCB(int id, String c_Name){
			super();
			this.id = id;
			this.c_Name = c_Name;
			//this.department = department;
		}

		public CompanyMTM_JCB() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "CompanyMTM_JCB [id=" + id + ", c_Name=" + c_Name + ", department=" + department + "]";
		}
			
		
		
}

@Entity
@Table
class DepartmentMTM_JCB {
		@Id
		int depid;
		String depName;
		String depPlace;
		
		@ManyToMany(mappedBy="department")
		
		List<CompanyMTM_JCB> company=new ArrayList<CompanyMTM_JCB>();
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
		
		public List<CompanyMTM_JCB> getCompany() {
			return company;
		}
		public void setCompany(List<CompanyMTM_JCB> company) {
			this.company = company;
		}
		public DepartmentMTM_JCB(int depid, String depName, String depPlace) {
			super();
			this.depid = depid;
			this.depName = depName;
			this.depPlace = depPlace;
		}
		public DepartmentMTM_JCB() {
			super();
			// TODO Auto-generated constructor stub
		}
		@Override
		public String toString() {
			return "DepartmentMTM_JCB [depid=" + depid + ", depName=" + depName + ", depPlace=" + depPlace + "]";
		}
		
					
}



public class ManyToManyJoinColumnBi {
	
	public static void main(String[] args) throws HibernateException, MyException {
		Session session = HibernateUtility.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();
		DepartmentMTM_JCB d1 = new DepartmentMTM_JCB(11, "Design", "3rd Flour");
		DepartmentMTM_JCB d2 = new DepartmentMTM_JCB(12, "Engine", "3rd Flour");
		DepartmentMTM_JCB d3 = new DepartmentMTM_JCB(13, "Production", "4th Flour");

		CompanyMTM_JCB c1 = new CompanyMTM_JCB(1, "Mahindra");
		CompanyMTM_JCB c2 = new CompanyMTM_JCB(2, "TataMotors");
		
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
