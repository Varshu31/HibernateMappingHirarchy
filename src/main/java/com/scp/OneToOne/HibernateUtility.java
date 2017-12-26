package com.scp.OneToOne;


	import org.hibernate.SessionFactory;
	import org.hibernate.cfg.Configuration;

	public class HibernateUtility {
		public static SessionFactory sessionFactory=null;
		
		public static SessionFactory getSessionFactory() throws MyException
		{
			try{
		if(sessionFactory==null)
		{
			Configuration configuration = new Configuration();
			sessionFactory = configuration.configure().buildSessionFactory();
		}	
			}catch(Exception e){
				e.printStackTrace();
				//throw new MyException("Exception in configuration");
			}
		return sessionFactory;
	}

		


}
