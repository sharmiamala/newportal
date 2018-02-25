package myproject.practice.com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

//import javax.jdo.PersistenceManager;
//import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ui.ModelMap;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.hash.Hashing;
import org.json.JSONException;
import org.json.JSONObject;

public class UserService {
	
	public static String  getUserData(String emailId,HttpServletRequest request,HttpServletResponse response,ModelMap model,HttpSession session) throws Exception
	{ 
		
		//System.out.println("in getUserData");
		String nextpage = "editprofile";
		if(session.getAttribute("email").equals(Constants.adminEmailId))
			nextpage="userActivation";
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("User");
		query.setFilter(new Query.FilterPredicate("email", FilterOperator.EQUAL, emailId));
		PreparedQuery pq = datastore.prepare(query);
		try {
			List<Entity> results = pq.asList(FetchOptions.Builder.withDefaults());
			System.out.println(results.get(0).getKey().getName() +" =ID/Name");
			if (!results.isEmpty()) {
				//System.out.println(results.get(0).getCountry().toString());
				
				request.setAttribute("active", results.get(0).getProperty("active").toString());
				request.setAttribute("newUserAcc", results.get(0).getProperty("email").toString());
				request.setAttribute("firstName", results.get(0).getProperty("firstname").toString());
				request.setAttribute("lastName", results.get(0).getProperty("lastname").toString());
				request.setAttribute("position", results.get(0).getProperty("position").toString());
				request.setAttribute("organisation", results.get(0).getProperty("organisation").toString());
				request.setAttribute("category", results.get(0).getProperty("category").toString());
				request.setAttribute("gender", results.get(0).getProperty("gender").toString());
				request.setAttribute("country", results.get(0).getProperty("country").toString());
				request.setAttribute("ethnicity", results.get(0).getProperty("ethnicity").toString());
				request.setAttribute("iwi", results.get(0).getProperty("iwi").toString());
				request.setAttribute("comments", results.get(0).getProperty("comments").toString());
				request.setAttribute("orcId", results.get(0).getProperty("orcId").toString());
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return nextpage;
			
	}
	
public static String activateUser(String useremail,String userStatus,ModelMap model,HttpSession session)
{System.out.println("in activateUser "+userStatus);
String nextpage="expiry";
	if(session.getAttribute("email").equals(Constants.adminEmailId))
	{
	if(!Strings.isNullOrEmpty(useremail))
	{
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("User");
		query.setFilter(new Query.FilterPredicate("email", FilterOperator.EQUAL, useremail));
		PreparedQuery pq = datastore.prepare(query);
		try {
			List<Entity> results = pq.asList(FetchOptions.Builder.withDefaults());
			System.out.println("result size "+results.size());
			System.out.println(results.get(0).getProperty("organisation").toString());
			if (!results.isEmpty()) {
				System.out.println(results.get(0).getProperty("active"));
				if(userStatus.equals("false"))
					results.get(0).setProperty("active",true);
				else
					results.get(0).setProperty("active",false);
				nextpage="userStatusChanged";
				
		        datastore.put(results.get(0));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	}
	return nextpage;
}


	public static String  getUserSubscriptions(String emailId,HttpServletRequest request,HttpServletResponse response,ModelMap model,HttpSession session) throws Exception
	{
		String subslist = "";
		String nextpage = "mailinglist";
		
		System.out.println("getUserSubscriptions");
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("User");
		query.setFilter(new Query.FilterPredicate("email", FilterOperator.EQUAL, emailId));
		PreparedQuery pq = datastore.prepare(query);
		try {
			List<Entity> results = pq.asList(FetchOptions.Builder.withDefaults());
		
			System.out.println(results.size());
			if (!results.isEmpty()) {
				System.out.println(results.get(0).getProperty("email"));

				if (Strings.isNullOrEmpty(results.get(0).getProperty("mailLists").toString())) {
					System.out.println("Its NUll here...");
				}

				else {
					subslist = results.get(0).getProperty("mailLists").toString();
					if (!Strings.isNullOrEmpty(subslist)) {
						System.out.println(subslist + " contents sublist");
						request.setAttribute("subslist", subslist);

					} else {
						System.out.println(" empty sublist");
						request.setAttribute("subslist", null);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
						e.printStackTrace();
		} 
		
		return nextpage;
			
	}
	public static void sendNewPubDeatilsToUser(String receiverEmail,String number,String aticleTitle)
	{
		//System.out.println("came to /sendNewPubDeatilsToUser");
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
		Email mm = (Email) context.getBean("Email");
		if (!(Strings.isNullOrEmpty(number)) && !(Strings.isNullOrEmpty(aticleTitle)) ) {
			StringBuilder bodyText = new StringBuilder();
			bodyText.append("Hello,"+"\r\n\n ");
			
			bodyText.append("Your QuakeCoRE publication number for your submission entitled '" +aticleTitle+  "' is "+number+".\r\n\n");
			bodyText.append("Add the following statement in your acknowledgement – "+"\r\n\n");
			bodyText.append("'This project was (partially) supported by QuakeCoRE, a New Zealand Tertiary Education Commission-funded Centre.This is QuakeCoRE publication number "+number+"'.\r\n\n");
			bodyText.append("Please forward this message to your co-authors."+"\r\n\n");
			bodyText.append("Thank you,"+"\r\n");
			bodyText.append("The QuakeCoRE Team");
			mm.sendMail(Constants.adminEmailId,receiverEmail, "Your new QuakeCoRE publication number",
					bodyText.toString() );
		}
	}
	
	public static String hashPassword(String password)
	{
		if (Strings.isNullOrEmpty(password))
			return null;
		else
			return Hashing.sha256().newHasher().putString(password.trim(), Charsets.UTF_8).hash().toString();
	}
	
	public static int randInt( int max) {

		Random rand = new Random();
		int randomNum = rand.nextInt(max);
		// //System.out.println(randomNum);
		return randomNum;
	}
	
	public static String randString() {
		String randomStr=new String();
	    String alphabets= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	  
	   int max=26;
	   for (int i=0;i<4;i++)
		{
		  
			randomStr=randomStr+alphabets.charAt(randInt(max) );
		}
	   randomStr=randomStr+randInt(10)+randInt(10);
	 //  //System.out.println(randomStr);
	   
	    return randomStr;
	}
	
	//.instance().hasher(UUID.randomUUID().toString());

	public String generateKey(User user) {
		if (user == null)	
		return null;
		return Hashing.sha1().newHasher().putString(user.getEmail(), Charsets.UTF_8).hash().toString();

	}

}
