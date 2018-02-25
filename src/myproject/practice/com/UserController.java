package myproject.practice.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletOutputStream;

//import javax.jdo.PersistenceManager;
//import javax.jdo.Query;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.common.base.Strings;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;



@Controller
public class UserController {


	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getHomePage(ModelMap model) {
		//System.out.println("home :) ");
		return "home";

	}

	@RequestMapping(value = "/narrow", method = RequestMethod.GET)
	public String getnarrowJumboPage(ModelMap model) {
		//System.out.println("narrow :) ");
		return "narrowJumbo";

	}

	@RequestMapping(value = "/sample", method = RequestMethod.GET)
	public String sample(ModelMap model) {
		//System.out.println("sample :) ");
		return "sample";

	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String getSignUpPage(ModelMap model) {
		System.out.println("got here for /signup");
		return "signup";

	}

	@RequestMapping(value = "/done", method = RequestMethod.GET)
	public String savedOrmodified(ModelMap model) {
		//System.out.println("came to /done");
		return "thankyou";
	}

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public String selectMailingList(ModelMap model) {
		//System.out.println("came to /select");
		return "select";
	}

	@RequestMapping(value = "/userStatusChanged", method = RequestMethod.GET)
	public String userStatusChangedPage(ModelMap model) {
		//System.out.println("came to /userStatusChanged");
		return "userStatusChanged";
	}
	
	@RequestMapping(value = "/itemDetailsChanged", method = RequestMethod.GET)
	public String pubvisibilityChangedPage(ModelMap model) {
		//System.out.println("came to /itemDetailsChanged");
		return "itemDetailsChanged";
	}
	
	@RequestMapping(value = "/exusersignup", method = RequestMethod.GET)
	public String getExUserSignUpPage(ModelMap model) {
		//System.out.println("got here for /exusersignup");
		return "exusersignup";

	}

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public String getForgotPasswordPage(ModelMap model) {
		//System.out.println("got here for /forgotpassword");
		return "forgotpassword";

	}
	//Admin related pages-start
	@RequestMapping(value = "/homeAdmin", method = RequestMethod.GET)
	public String homeAdminPage(ModelMap model,HttpSession session) {
		//System.out.println("got here for /homeAdmin");
		String page="expiry";
		if(session == null || session.getAttribute("email") == null)
			return page;
		else
		{
		if(session.getAttribute("email").equals(Constants.adminEmailId) )
			return "homeAdmin";
		else
			return "invalidrequest";
			}

	}
	@RequestMapping(value = "/getmailSubscriptions", method = RequestMethod.GET)
	public String getmailSubscriptions(ModelMap model,HttpSession session) {
		//System.out.println("got here for /getmailSubscriptions");
		
			String page="expiry";
			if(session == null || session.getAttribute("email") == null)
				return page;
			else
			{
			if(session.getAttribute("email").equals(Constants.adminEmailId) )
				return "getmailSubscriptions";
			else
				return "invalidrequest";
				}
		
	}
	
	@RequestMapping(value = "/showbanner", method = RequestMethod.GET)
	public String getshowbannerPage(ModelMap model,HttpSession session) {
		//System.out.println("got here for /showbanner ");
		String page="expiry";
		if(session == null || session.getAttribute("email") == null)
			return page;
		else
		{
		if(session.getAttribute("email").equals(Constants.adminEmailId) )
		return "showbanner";
		else
		return "invalidrequest";
		}

	}
	@RequestMapping(value = "/sent", method = RequestMethod.GET)
	public String sent(ModelMap model,HttpSession session) {
		//System.out.println("got here for /sent");
		String page="expiry";
		if(session == null || session.getAttribute("email") == null)
			return page;
		else
		{
		if(session.getAttribute("email").equals(Constants.adminEmailId) )
			return "sent";
		else
			return "invalidrequest";
		}

	}
	@RequestMapping(value = "/banner", method = RequestMethod.GET)
	public String banner(ModelMap model,HttpSession session) {
		//System.out.println("got here for /banner");
		String page="expiry";
		if(session == null || session.getAttribute("email") == null)
			return page;
		else
		{
		if(session.getAttribute("email").equals(Constants.adminEmailId) )
			return "banner";
		else
			return "invalidrequest";
		}

	}
	@RequestMapping(value = "/downloadUserPubData", method = RequestMethod.GET)
	public ModelAndView getUserPubData(ModelMap model,HttpSession session,HttpServletRequest request,HttpServletResponse response){
		System.out.println("getUserPubData");
		
		if(session == null || session.getAttribute("email") == null)
			return new ModelAndView( "expiry");
		else
		{
			if(session.getAttribute("email").equals(Constants.adminEmailId) )
			{
		response.setContentType("application/csv");   
		response.setHeader("content-disposition","attachment;filename =userpublications.csv"); 
		ServletOutputStream writer;
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("UserPublication");
		PreparedQuery pq = datastore.prepare(query);
		try {
			List<Entity> userpubs = pq.asList(FetchOptions.Builder.withDefaults());
			
	writer = response.getOutputStream();
    System.out.println("downloading userpub contents to csv");

    writer.print("email,publications");
    writer.println();            
    if (!userpubs.isEmpty()) {
    for(int i=0;i<userpubs.size();i++)
    {
	    try {
	    	writer.print("\""+userpubs.get(i).getKey().getName()+"\",");writer.print("\""+userpubs.get(i).getProperty("publicationList")+"\",");
            writer.println();   
    	} 
    catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                
 }
}
        writer.flush();
        writer.close();
} 
catch (IOException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}   
return null;

	}
else
	return new ModelAndView( "invalidrequest");
	}
}
	    
	@RequestMapping(value = "/downloadUserData", method = RequestMethod.GET)
	public ModelAndView downloadUserCSV(ModelMap model,HttpSession session,HttpServletRequest request,HttpServletResponse response){
		System.out.println("downloadUserData");
		
		if(session == null || session.getAttribute("email") == null)
			return new ModelAndView( "expiry");
		else
		{
			if(session.getAttribute("email").equals(Constants.adminEmailId) )
			{
		response.setContentType("application/csv");   
		response.setHeader("content-disposition","attachment;filename =user.csv"); 
		ServletOutputStream writer;
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("User");
		PreparedQuery pq = datastore.prepare(query);
		try {
			List<Entity> users = pq.asList(FetchOptions.Builder.withDefaults());
			
	writer = response.getOutputStream();
    System.out.println("downloading user contents to csv");

    writer.print("email,firstname,lastname,position,organisation,orcId,active,category,maillists,signupdate,lastlogindate,gender,ethnicity,country,iwi,comments,");
    writer.println();            
    if (!users.isEmpty()) {
    for(int i=0;i<users.size();i++)
    {
	    try {
				writer.print("\""+users.get(i).getProperty("email")+"\",");writer.print("\""+users.get(i).getProperty("firstname")+"\",");
				writer.print("\""+users.get(i).getProperty("lastname")+"\",");writer.print("\""+users.get(i).getProperty("position")+"\",");
				writer.print("\""+users.get(i).getProperty("organisation")+"\",");writer.print("\""+users.get(i).getProperty("orcId")+"\",");
				writer.print("\""+users.get(i).getProperty("active")+"\",");writer.print("\""+users.get(i).getProperty("category")+"\",");
				writer.print("\""+users.get(i).getProperty("mailLists")+"\",");writer.print("\""+users.get(i).getProperty("signupdate")+"\",");
				writer.print("\""+users.get(i).getProperty("lastlogindate")+"\",");writer.print("\""+users.get(i).getProperty("gender")+"\",");
				writer.print("\""+users.get(i).getProperty("ethnicity")+"\",");writer.print("\""+users.get(i).getProperty("country")+"\",");
				writer.print("\""+users.get(i).getProperty("iwi")+"\",");writer.print("\""+users.get(i).getProperty("comments")+"\",");
	            writer.println();   
	    	} 
	    catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	                
	 }
    }
	        writer.flush();
	        writer.close();
	} 
	catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}   
	return null;
	
		}
	else
		return new ModelAndView( "invalidrequest");
		}
	}
	@RequestMapping(value = "/downloadPubData", method = RequestMethod.GET)
	public ModelAndView downloadPubCSV(ModelMap model,HttpSession session,HttpServletRequest request,HttpServletResponse response){
		System.out.println("downloadPubData");
		
		if(session == null || session.getAttribute("email") == null)
			return new ModelAndView( "expiry");
		else
		{
			if(session.getAttribute("email").equals(Constants.adminEmailId) )
			{
						
				//response.setContentType("application/csv");  
						
				response.setHeader("content-disposition","attachment;filename =pub.csv"); 
				
				
				
				PrintWriter writer;
				try {
					writer = response.getWriter();
				
				
				
				//ServletOutputStream writer;
				
				DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
				Query query = new Query("Publication");
				PreparedQuery pq = datastore.prepare(query);
				
					List<Entity> users = pq.asList(FetchOptions.Builder.withDefaults());
					
			//writer = response.getOutputStream();
		    System.out.println("downloading pub contents to csv");
		
		    writer.print("publicationId,pubIdStr4digit,isVisible,year,fund,status,article,author,title,venueName,descOutputOther,page,location,url,volume,publishDate,publisher,project,firstEnteredDate,lastModifiedDate,");
		    writer.println();            
		    if (!users.isEmpty()) {
		    for(int i=0;i<users.size();i++)
		    {
			    
						writer.print("\""+users.get(i).getProperty("publicationId")+"\",");writer.print("\""+users.get(i).getProperty("pubIdStr4digit")+"\",");
						writer.print("\""+users.get(i).getProperty("isVisible")+"\",");writer.print("\""+users.get(i).getProperty("year")+"\",");
						writer.print("\""+users.get(i).getProperty("fund")+"\",");writer.print("\""+users.get(i).getProperty("status")+"\",");
						writer.print("\""+users.get(i).getProperty("article")+"\",");writer.print("\""+users.get(i).getProperty("author")+"\",");
						writer.print("\""+users.get(i).getProperty("title")+"\",");writer.print("\""+users.get(i).getProperty("venueName")+"\",");
						writer.print("\""+users.get(i).getProperty("descOutputOther")+"\",");writer.print("\""+users.get(i).getProperty("page")+"\",");
						writer.print("\""+users.get(i).getProperty("location")+"\",");writer.print("\""+users.get(i).getProperty("url")+"\",");
						writer.print("\""+users.get(i).getProperty("volume")+"\",");writer.print("\""+users.get(i).getProperty("publishDate")+"\",");
						writer.print("\""+users.get(i).getProperty("publisher")+"\",");writer.print("\""+users.get(i).getProperty("project")+"\",");
						writer.print("\""+users.get(i).getProperty("firstEnteredDate")+"\",");writer.print("\""+users.get(i).getProperty("lastModifiedDate")+"\",");
			            writer.println();   
			    	
			                
			 }
		    }
			        writer.flush();
			        writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	} 
			else
				return new ModelAndView( "invalidrequest");
	return null;
	
		
		}
		
	
		}
	
	//Admin related pages-end
	
	@RequestMapping(value = "/changed", method = RequestMethod.POST)
	public String postchanged(ModelMap model) {
		//System.out.println("got here for /changed POST");
		return "thankyou";

	}

	@RequestMapping(value = "/thankyou", method = RequestMethod.GET)
	public String thankyou(ModelMap model) {
		//System.out.println("got here for /thankyou " + model);
		return "thankyou";

	}

	@RequestMapping(value = "/newpublication", method = RequestMethod.GET)
	public ModelAndView editPublicationPage(@RequestParam(value = "id", required = false) String pubid,
			ModelMap model,HttpSession session) {
		//System.out.println("got here for EDIT /newpublication GET");
		 if(session == null || session.getAttribute("email") == null)
			 return new ModelAndView( "expiry");
			else
			{
				if(session.getAttribute("email").equals(Constants.adminEmailId))
					return new ModelAndView("adminInvalidrequest");
				else
				{
					if (pubid != null && !pubid.isEmpty())
						model.addAttribute("singlePub", pubid);

					return new ModelAndView("newpublication");
				}
			}

	}

	
	@RequestMapping(value = "/rechangepassword", method = RequestMethod.GET)
	public String getreChangePasswordPage(ModelMap model) {
		//System.out.println("got here for /rechangepassword");
		return "rechangepassword";

	}

	

	@RequestMapping(value = "/contactlist", method = RequestMethod.POST)
	public String contactlistAdminPage(ModelMap model) {
		//System.out.println("got here for /contactlist");
		return "contactlist";

	}
	@RequestMapping(value = "/newsignup", method = RequestMethod.POST)
	public String newsignupPage(ModelMap model) {
		System.out.println("got here for /newsignup POST ");
		return "newsignup";

	}
	//Start:user related pages
	@RequestMapping(value = "/homeuser", method = RequestMethod.POST)
	public String homeUserPage(ModelMap model,HttpSession session) {
		//System.out.println("got here for /homeuser  POST");
		 if(session == null || session.getAttribute("email") == null)
				return "expiry";
			else
			{
				if(session.getAttribute("email").equals(Constants.adminEmailId))
				return "adminInvalidrequest";
				else
					return "homeuser";
			}

	}

	@RequestMapping(value = "/homeuser", method = RequestMethod.GET)
	public String gethomeUserPage(ModelMap model,HttpSession session) {
		//System.out.println("got here for /gethomeuser GET");
		 if(session == null || session.getAttribute("email") == null)
				return "expiry";
			else
			{
				if(session.getAttribute("email").equals(Constants.adminEmailId))
				return "adminInvalidrequest";
				else
					return "homeuser";
			}

	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.GET)
	public String getChangePasswordPage(ModelMap model,HttpSession session) {
		//System.out.println("got here for /changepassword " + model);
		if((session != null && session.getAttribute("email") != null )&& !(session.getAttribute("email").equals(Constants.adminEmailId)))
			return "changepassword";
			else if(session == null || session.getAttribute("email") == null)
				return "expiry";
			else
				return "adminInvalidrequest";
	}

	@RequestMapping(value = "/userActivation", method = RequestMethod.GET)
	public String getuserActivationPage(ModelMap model) {
		//System.out.println("got here for /userActivation ");
		return "userActivation";

	}
	
	@RequestMapping(value = "/invalidrequest", method = RequestMethod.GET)
	public String invalidrequest(ModelMap model) {
		//System.out.println("/invalidrequest");
		return "invalidrequest";

	}
	@RequestMapping(value = "/adminInvalidrequest", method = RequestMethod.GET)
	public String adminInvalidrequest(ModelMap model) {
		//System.out.println("/adminInvalidrequest");
		return "adminInvalidrequest";

	}
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String postHomePage(ModelMap model) {
		//System.out.println("home :) POST");
		return "home";

	}
	@RequestMapping(value = "/banner", method = RequestMethod.POST)
	public String getshowbannerPage(ModelMap model,HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		//System.out.println("got here for /banner POST");
		//System.out.println(request.getParameter("msg"));
		String displayMsg=request.getParameter("msg");
		
		String page="expiry";
		if(session != null && session.getAttribute("email") != null) {
			String currentSessionEmail=session.getAttribute("email").toString();
			if(currentSessionEmail.equals(Constants.adminEmailId))
			{
		        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
				Query query = new Query("Admin");
				query.setFilter(new Query.FilterPredicate("email", FilterOperator.EQUAL, currentSessionEmail));
				PreparedQuery pq = datastore.prepare(query);
				
				
				try {
					Entity adminuser = pq.asSingleEntity();
		            if (adminuser != null) {
		                     
		            	   	 adminuser.setProperty("msg",displayMsg);
		                     page="banner";
		                     datastore.put(adminuser);
		               }
		        } finally {
		               
		        }
			}
			else
				return "invalidrequest";
		}
		return page;

	}
	@RequestMapping(value = "/activated", method = RequestMethod.GET)
	public String getuseractivatedPage(ModelMap model,HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		System.out.println("got here for /activated ");
		String page="expiry";
		if (session != null && session.getAttribute("email") != null) {
			if(!(session.getAttribute("email").equals(Constants.adminEmailId)))
				return "invalidrequest";
		//System.out.println(request.getParameter("useremail"));
		String useremail=request.getParameter("useremail");
		String activeStatus=request.getParameter("activeStatus");
		page=UserService.activateUser(useremail,activeStatus,model,session);
		if(page.equals("userStatusChanged"))
		{
			String url=request.getScheme() + "://"	+ request.getServerName() + request.getContextPath();
			ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");

			Email mm = (Email) context.getBean("Email");
			if(activeStatus.equals("false"))
			{

				StringBuilder bodyText = new StringBuilder();
				
				bodyText.append("Hello,"+"\r\n\n ");
				bodyText.append("Thank you for signing up to the QuakeCoRE user portal, your profile is now active.You can log onto the portal using the following "+url+"\r\n\n ");
				
				bodyText.append("After you have logged in, please take a moment to review your mailing list subscription options and confirm that your profile information is correct"+"\r\n\n");
				
				bodyText.append("Thank you,"+"\r\n ");
				bodyText.append("The QuakeCoRE Team");
				mm.sendMail(Constants.adminEmailId, useremail, "Account Activated for QuakeCoRE User Portal",
						bodyText.toString());
			}
			/*else
				mm.sendMail(Constants.adminEmailId, useremail, "Account Deactivated for QuakeCoRE User Portal",
						"Sorry, your account has been deactivated.");*/
			
		}
		
		}
		return page;
	}
	@RequestMapping(value = "/changeVisibility", method = RequestMethod.GET)
	public String getchangeVisibilityPage(ModelMap model,HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		//System.out.println("got here for /changeVisibility ");
		String page="expiry";
		if (session != null && session.getAttribute("email") != null) {
			if(!(session.getAttribute("email").equals(Constants.adminEmailId)))
				return "invalidrequest";
		//System.out.println(request.getParameter("useremail"));
		String pubId=request.getParameter("pubno");
		
		page=PublicationService.publicationVisibility(pubId,model,session);
		}
		return page;
	}
	@RequestMapping(value = "/correct", method = RequestMethod.GET)
	public ModelAndView correctPublicationPage(ModelMap model,HttpServletRequest request, HttpServletResponse response,HttpSession session) {
		//System.out.println("got here for /changeVisibility ");
		
		if (session != null && session.getAttribute("email") != null) {
			if(!(session.getAttribute("email").equals(Constants.adminEmailId)))
				return new ModelAndView ("invalidrequest");
			//System.out.println(request.getParameter("useremail"));
			String pubId=request.getParameter("pubno");
			int no=Integer.parseInt(pubId);
			try {
				return getPublication(Constants.adminEmailId, request, response, model,no);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new ModelAndView ("expiry");
		
	}
	
	@RequestMapping(value = "/changed", method = RequestMethod.GET)
	public String changed(ModelMap model) {
		//System.out.println("got here for /changed");
		return "thankyou";

	}

	

	@RequestMapping(value = "/editthisAbstract", method = RequestMethod.POST)
	public String geteditAbstractPage(@RequestParam(value = "absId", required = false) String absId,
			HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) {

		//System.out.println("got here for /editthisAbstract" + absId);
		// get the details of an abstract from this absId, then set the abstract
		// details in request attribute ->to front jsp to display

		return "abstractSubmission";

	}

	@RequestMapping(value = "/popup", method = RequestMethod.GET)
	public String getPopup(ModelMap model) {
		//System.out.println("got here for /popup");
		return "popup";

	}

	@RequestMapping(value = "/abstractSubmitted", method = RequestMethod.GET)
	public String getabstractSubmissionPage(ModelMap model) {
		//System.out.println("got here for /abstractSubmitted");
		return "abstractSubmitted";

	}

	@RequestMapping(value = "/abstractSubmission", method = RequestMethod.GET)
	public String abstractSubmission(ModelMap model) {
		//System.out.println("got here for GET /abstractSubmission");
		return "abstractSubmission";

	}

	@RequestMapping(value = "/confirmpopup", method = RequestMethod.GET)
	public String getconfirmPopup(ModelMap model) {
		//System.out.println("got here for /confirmpopup");
		return "confirmpopup";

	}
	//this is not needed
	/*@RequestMapping(value = "/abstractSubmission", method = RequestMethod.POST)
	public ModelAndView abstractSubmissionSave(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, ModelMap model) {
		//System.out.println("got here for POST  /abstractSubmission");
		String emailId = "";
		int absNo = 0;
		if (session != null && session.getAttribute("email") != null) {
			emailId = session.getAttribute("email").toString();
		} else
			return new ModelAndView("expiry");
		String year = request.getParameter("year");
		String category = request.getParameter("category");
		String abstractDesc = request.getParameter("abstractDescription");
		String author = request.getParameter("author");
		String title = request.getParameter("title");
		String project = request.getParameter("project");
		String url = request.getParameter("url");

		//System.out.println(year + "-- " + category);
		//System.out.println(abstractDesc + "-- " + author);
		//System.out.println(title + "-- " + project + " --- " + url);

		try {
			absNo = AbstractService.getAbstractNo(emailId, request, response, model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Proposal abs = new Proposal();
		String uuid = UUID.randomUUID().toString();
		abs.setKey(uuid);
		abs.setAbstractId(absNo);
		abs.setYear(year);
		abs.setCategory(category);
		abs.setAbstractDescription(abstractDesc);
		abs.setAuthor(author);
		abs.setTitle(title);
		abs.setProject(project);
		abs.setUrl(url);

		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(abs);
			//System.out.println(" ****************  " + absNo);
			PublicationService.saveNewUserAbstract(emailId, absNo);
		} finally {
			pm.close();
		}

		request.setAttribute("absNo", absNo);
		return new ModelAndView("abstractSubmitted");

	}*/

	
	//CHECK IT
	// for viewing all QuakeCoRE publications which has visibility true
	@RequestMapping(value = "/viewpublication", method = RequestMethod.GET)
	public String getviewpublicationsPage(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap model) {
		//System.out.println("got here for GET /viewpublication " + model.toString());
		String emailId = "";
		if (session != null && session.getAttribute("email") != null) {
			emailId = session.getAttribute("email").toString();
		} else
			return "expiry";
		Boolean visible=true;
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Query query1 =new Query("Publication");
		query1.addSort("publicationId", SortDirection.DESCENDING);
		//Query query1 = new Query("Publication").setFilter(new Query.FilterPredicate("isVisible", FilterOperator.EQUAL, visible)).addSort("publicationId",Query.SortDirection.ASCENDING);
		PreparedQuery pq = datastore.prepare(query1);
		System.out.println("Query : "+query1);
		try {
			List<Entity> results = pq.asList(FetchOptions.Builder.withDefaults());//CHECK IT 
			if (results.isEmpty()) {
				model.addAttribute("allPublicationList", null);
			} else {
				model.addAttribute("allPublicationList", results);
			}

		}  catch (Exception e) {
			
			e.printStackTrace();
		}

		return "viewpublication";

	}
	// for viewing all QuakeCoRE members who are active (active=true)
		@RequestMapping(value = "/viewpeople", method = RequestMethod.GET)
		public String getviewpeoplePage(HttpServletRequest request, HttpServletResponse response, HttpSession session,
				ModelMap model) {
			System.out.println("got here for GET /viewpeople " + model.toString());
			String emailId = "";
			if (session != null && session.getAttribute("email") != null) {
				emailId = session.getAttribute("email").toString();
			} else
				return "expiry";
			Boolean isActiveMember=true;
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Query query = new Query("User").addSort("firstname", Query.SortDirection.ASCENDING);
			query.setFilter(new Query.FilterPredicate("active", FilterOperator.EQUAL, isActiveMember));
			PreparedQuery pq = datastore.prepare(query);
			System.out.println("Query : "+query);
			
			try {
				List<Entity> results = pq.asList(FetchOptions.Builder.withDefaults());//CHECK IT
				System.out.println("result size " + results.size()+results.get(0).getProperty("firstname"));
				if (results.isEmpty()) {
					model.addAttribute("peopleList", null);
				} else {
					//System.out.println("putting peopleList"+results);
					model.addAttribute("peopleList", results);
				}

			} catch (Exception e) {
				
				e.printStackTrace();
			}

			return "viewpeople";

		}
		@RequestMapping(value = "/correctPublication", method = RequestMethod.GET)
		public  String getAllPubforAdmin(HttpServletRequest request, HttpServletResponse response,
				ModelMap model,HttpSession session )
		{//System.out.println("/correctPublication");
			String emailId = "";
			if (session != null && session.getAttribute("email") != null) {
				emailId = session.getAttribute("email").toString();
				if(!(emailId.equals(Constants.adminEmailId)))
					return "invalidrequest";
			} else
				return "expiry";
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Query query = new Query("Publication").addSort("publicationId", Query.SortDirection.ASCENDING);
			PreparedQuery pq = datastore.prepare(query);
			
			try {
				List<Entity> results = pq.asList(FetchOptions.Builder.withDefaults());//CHECK IT
			
				//System.out.println("result size " + results.size());
				if (results.isEmpty()) {
					model.addAttribute("allPubsList", null);
				} else {
					model.addAttribute("allPubsList", results);
				}

			} catch (Exception e) {
				
				e.printStackTrace();
			}

			return "correctPublication";
		}
	@RequestMapping(value = "/selectpublication", method = RequestMethod.GET)
	public  String getAllPubsforAdmin(HttpServletRequest request, HttpServletResponse response,
			ModelMap model,HttpSession session )
	{//System.out.println("/selectpublication");
		String emailId = "";
		if (session != null && session.getAttribute("email") != null) {
			emailId = session.getAttribute("email").toString();
			if(!(emailId.equals(Constants.adminEmailId)))
				return "invalidrequest";
		} else
			return "expiry";
		Boolean visible=true;
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("Publication").addSort("publicationId", Query.SortDirection.ASCENDING);
		query.setFilter(new Query.FilterPredicate("isVisible", FilterOperator.EQUAL, visible));
		PreparedQuery pq = datastore.prepare(query);
		
		try {
			List<Entity> results = pq.asList(FetchOptions.Builder.withDefaults());//CHECK IT
			//System.out.println("result size " + results.size());
			if (results.isEmpty()) {
				model.addAttribute("allPubsList", null);
			} else {
				model.addAttribute("allPubsList", results);
			}

		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return "selectpublication";
	}
	
	public boolean containsPubId(List<Integer> user_pubList, Integer publicationId) {
		
		System.out.println(user_pubList+" user_pubList | publicationId "+publicationId);
		String pubid = publicationId.toString();
	    for (int i=0;i<user_pubList.size();i++) {
	    	System.out.println("pubs in order "+user_pubList.get(i));
	    	String userpub = user_pubList.get(i)+"";
	    	
	        if ( pubid.equals(userpub)) { 
	        	System.out.println("inside for true");
	        	return true;
	        }
	    }
	    return false;
	}
	
	public ModelAndView getPublication(String emailId, HttpServletRequest request, HttpServletResponse response,
			ModelMap model, Integer publicationId) throws Exception {
		
		if(!emailId.equals(Constants.adminEmailId))//if edited by user than admin, this check is needed
		{
		List<Integer> user_pubList=getUserPublicationList(emailId);
			if(!containsPubId(user_pubList,publicationId))
			{
				System.out.println(user_pubList+" =user_pubList does not have publicationId= "+publicationId);
				
				return new ModelAndView("expiry");
			}
		}
		String nextpage = "newpublication";
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		List<Entity> results = new ArrayList<Entity> ();
		Query query = new Query("Publication");
		query.setFilter(new Query.FilterPredicate("publicationId", FilterOperator.EQUAL, publicationId));
		PreparedQuery pq = datastore.prepare(query);
		
		try {
			results = pq.asList(FetchOptions.Builder.withDefaults());//CHECK IT
			if (!results.isEmpty()) {
				//System.out.println(results.get(0).getAuthor());

				request.setAttribute("editPub", "edit");
				request.setAttribute("year", results.get(0).getProperty("year"));
				request.setAttribute("fund", results.get(0).getProperty("fund"));
				request.setAttribute("status", results.get(0).getProperty("status"));
				request.setAttribute("article", results.get(0).getProperty("article"));
				request.setAttribute("author", results.get(0).getProperty("author"));
				request.setAttribute("title", results.get(0).getProperty("title"));
				request.setAttribute("venueName", results.get(0).getProperty("venueName"));
				request.setAttribute("volume", results.get(0).getProperty("volume"));
				request.setAttribute("page", results.get(0).getProperty("page"));
				request.setAttribute("location", results.get(0).getProperty("location"));
				request.setAttribute("url", results.get(0).getProperty("url"));
				request.setAttribute("publishDate", results.get(0).getProperty("publishDate"));
				request.setAttribute("publisher", results.get(0).getProperty("publisher"));
				request.setAttribute("publicationNo", publicationId);
				request.setAttribute("descOutputOther", results.get(0).getProperty("descOutputOther"));
				request.setAttribute("project", results.get(0).getProperty("project"));
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		//System.out.println("IT DOES");
		return new ModelAndView(nextpage);
	}

	@RequestMapping(value = "/newpublication", method = RequestMethod.POST)
	public ModelAndView getPublicationId(@RequestParam(value = "id", required = false) String pub,
			HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) {
		
		String buttonValue = request.getParameter("actionBtn");

		
		String pubNo = request.getParameter("editNo");
		//System.out.println(pubNo + " *** POST *** " + buttonValue);
		if (buttonValue != null) {
			//System.out.println(" *** ONE *** ");
			//To get publication details based on button value 
			if (session != null && session.getAttribute("email") != null) {
				if(session.getAttribute("email").equals(Constants.adminEmailId))
					return new ModelAndView("adminInvalidrequest");
					
				String emailId = session.getAttribute("email").toString();
				Integer id = Integer.parseInt(buttonValue);
				try {

					return getPublication(emailId, request, response, model, id);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				return new ModelAndView("expiry");
			}
		} else if (buttonValue == null && !(pubNo.isEmpty())) {//displays the page with a particular publications's  details for editing
			//System.out.println(" *** TWO *** ");
			int no = Integer.parseInt(pubNo);
			//System.out.println(" *** TWO *** no " + no);
			
			if (session != null && session.getAttribute("email") != null) {
				/*if(session.getAttribute("email").equals(Constants.adminEmailId))//Admin can edit as well, so blocked this
					return new ModelAndView("adminInvalidrequest");*/
				
				 DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
					Query query = new Query("Publication");
					query.setFilter(new Query.FilterPredicate("publicationId", FilterOperator.EQUAL, no));
					PreparedQuery pq = datastore.prepare(query);
					
			try {
				List<Entity> results = pq.asList(FetchOptions.Builder.withDefaults());
				//System.out.println("result size " + results.size());
				if (results.isEmpty()) {
					//System.out.println("no records found" + no);

				} else {
					//System.out.println("to save the edited pubdetails");
					String year = request.getParameter("year");
					String fund = request.getParameter("fund");
					String status = request.getParameter("status");
					String article = request.getParameter("article");
					String author = request.getParameter("author");
					String title = request.getParameter("title");
					String venueName = request.getParameter("venueName");
					String descOutputOther= request.getParameter("descOutputOther");
					String volume = request.getParameter("volume");
					String page = request.getParameter("page");
					String location = request.getParameter("location");
					String url = request.getParameter("url");
					String dates = request.getParameter("dates");
					String publisher = request.getParameter("publisher");
					String project = request.getParameter("project");
					
					try {
						results.get(0).setProperty("lastModifiedDate",new Date());
						results.get(0).setProperty("year",year);
						results.get(0).setProperty("fund",fund);
						results.get(0).setProperty("status",status);
						results.get(0).setProperty("article",article);
						results.get(0).setProperty("author",author.toUpperCase());
						results.get(0).setProperty("title",title.substring(0, 1).toUpperCase()+title.substring(1));
						if (!Strings.isNullOrEmpty(project))
							results.get(0).setProperty("project",project);
						else
							results.get(0).setProperty("project","");
						if (!Strings.isNullOrEmpty(venueName))
							results.get(0).setProperty("venueName",venueName.substring(0, 1).toUpperCase()+venueName.substring(1));
						else
							results.get(0).setProperty("venueName","");
						if (!Strings.isNullOrEmpty(descOutputOther))
							results.get(0).setProperty("descOutputOther",descOutputOther);
						else
							results.get(0).setProperty("descOutputOther","");
						if (!Strings.isNullOrEmpty(volume))
							results.get(0).setProperty("volume",volume);
						else
							results.get(0).setProperty("volume","");
						if (!Strings.isNullOrEmpty(page))
							results.get(0).setProperty("page",page);
						else
							results.get(0).setProperty("page","");
						if (!Strings.isNullOrEmpty(location))
							results.get(0).setProperty("location",location);
						else
							results.get(0).setProperty("location","");
						if (!Strings.isNullOrEmpty(url))
							results.get(0).setProperty("url",url);
						else
							results.get(0).setProperty("url","");
						if (!Strings.isNullOrEmpty(dates))
							results.get(0).setProperty("dates",dates);
						else
							results.get(0).setProperty("dates","");
						if (!Strings.isNullOrEmpty(publisher))
							results.get(0).setProperty("publisher",publisher);
						else
							results.get(0).setProperty("publisher","");
						datastore.put(results.get(0));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(session.getAttribute("email").equals(Constants.adminEmailId))//Admin can edit as well, so blocked this
						return new ModelAndView("itemDetailsChanged");
					return new ModelAndView("changed");
				}

			} finally {
				
			}
			}else {
				return new ModelAndView("expiry");
			}
		} else if (buttonValue == null && pubNo.isEmpty())//To sava a new publication

		{
			//System.out.println(" *** THREE *** ");
			if (session != null && session.getAttribute("email") != null) {
				if(session.getAttribute("email").equals(Constants.adminEmailId))
					return new ModelAndView("adminInvalidrequest");
				String year = request.getParameter("year");
				String fund = request.getParameter("fund");
				String status = request.getParameter("status");
				String article = request.getParameter("article");
				String author = request.getParameter("author");
				String title = request.getParameter("title");
				String venueName = request.getParameter("venueName");
				String descOutputOther= request.getParameter("descOutputOther");
				String volume = request.getParameter("volume");
				String page = request.getParameter("page");
				String location = request.getParameter("location");
				String url = request.getParameter("url");
				String dates = request.getParameter("dates");
				String publisher = request.getParameter("publisher");
				String project = request.getParameter("project");
				JSONObject pub_detailsJson = new JSONObject();
				try {
					pub_detailsJson.put("year", year);
					pub_detailsJson.put("fund", fund);
					pub_detailsJson.put("status", status);
					pub_detailsJson.put("article", article);System.out.println("************************article name while putting to JSON "+article);
					pub_detailsJson.put("author", author);
					pub_detailsJson.put("title", title);
					pub_detailsJson.put("venueName", venueName);
					pub_detailsJson.put("descOutputOther", descOutputOther);
					pub_detailsJson.put("volume", volume);
					pub_detailsJson.put("page", page);
					pub_detailsJson.put("location", location);
					pub_detailsJson.put("url", url);
					pub_detailsJson.put("dates", dates);
					pub_detailsJson.put("publisher", publisher);
					pub_detailsJson.put("project", project);
					String emailAddress = session.getAttribute("email").toString();
					
						try {
							return PublicationService.getPublicationNo(pub_detailsJson,emailAddress, request, response, model,session);//it gets saved and gets a publication No
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 
			}else {
				return new ModelAndView("expiry");
			}

			

		} else {
			//System.out.println(" *** FOUR *** ");
		}
		return new ModelAndView("changed");

	}

	
	

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, ModelMap model) {
		//System.out.println("got here for /logout");
		HttpSession session = request.getSession(false);
		if (session != null) {
			
			session.removeAttribute("email");
			session.removeAttribute("name");
			session.removeAttribute("userKey");
			session.invalidate();
		}
		return "logout";

	}
	@RequestMapping(value = "/expiry", method = RequestMethod.GET)
	public String expiry(ModelMap model,HttpServletRequest request) {
		//System.out.println("got here for /expiry");
		HttpSession session = request.getSession(false);
		if (session != null) {
			
			session.removeAttribute("email");
			session.removeAttribute("name");
			session.removeAttribute("userKey");
			session.invalidate();
		}
		return "expiry";

	}

	@RequestMapping(value = "/getpasswordchanged", method = RequestMethod.POST)
	public ModelAndView getpasswordchanged(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, ModelMap model) {

		if (session != null && session.getAttribute("email") != null) {
			String oldPassword = request.getParameter("password");
			String newPassword = request.getParameter("newpassword");
			String emailId = session.getAttribute("email").toString();
			String isMatched = checkpLoginDetails(emailId, oldPassword, request, response, session).toString();
			//System.out.println("1.matched got in " + isMatched);
			if (isMatched.equals("userMatched")) {
				//System.out.println(emailId + " 2.matched got in " + newPassword);
				saveTempEntry(emailId, newPassword);
			} else if (isMatched.equals("userMismatched")) {
				//System.out.println(emailId + " 3.wrong password  " + newPassword);

				return new ModelAndView("rechangepassword");
			}
		} else {
			return new ModelAndView("expiry");
		}

		return new ModelAndView("thankyou");

	}

	@RequestMapping(value = "/editprofile", method = RequestMethod.GET)
	public String editprofilePage(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {

		String page = "expiry";

		if (session != null && session.getAttribute("email") != null) {
			//System.out.println("got here for /editprofile GET");
			if(session.getAttribute("email").equals(Constants.adminEmailId))
				return "adminInvalidrequest";
			String email = session.getAttribute("email").toString();

			try {
				page = UserService.getUserData(email, request, response, model,session);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return page;
	}
	@RequestMapping(value = "/activate", method = RequestMethod.GET)
	public String activate(HttpServletRequest request, HttpServletResponse response, HttpSession session,ModelMap model) {
		String newuserAcc=request.getParameter("acc");
		//System.out.println("MAILID "+newuserAcc);
		
		String page = "expiry";

		if (session != null && session.getAttribute("email") != null && session.getAttribute("email").equals(Constants.adminEmailId)) {
			//System.out.println("got here for /activate GET");
			
			try {
				page = UserService.getUserData(newuserAcc, request, response, model,session);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return page;
		//return "userActivation";
	}
	@RequestMapping(value = "/updatePublication", method = RequestMethod.GET)
	public String updateThisPublication(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String number = request.getParameter("actionBtn");
		//System.out.println(" /updatePublication " + number);
		String page = "newpublication";
		/*
		 * if (session != null && session.getAttribute("email") != null) {
		 * String emailId=session.getAttribute("email").toString(); Integer
		 * id=Integer.parseInt(number); try {
		 * page=PublicationService.getPublication(emailId,request,response,model
		 * ,id); } catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } }
		 */

		return page;

	}

	@RequestMapping(value = "/mailinglist", method = RequestMethod.GET)
	public String mailingListPage(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		String page = "expiry";
		if (session != null && session.getAttribute("email") != null) {
			//System.out.println("got here for /mailingList");
			String email = session.getAttribute("email").toString();
			if (email.equalsIgnoreCase(Constants.adminEmailId)) 
				return "adminInvalidrequest";
			try {
				page = UserService.getUserSubscriptions(email, request, response, model,session);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return page;
	}

	@RequestMapping(value = "/checklogin", method = RequestMethod.POST)
	public @ResponseBody String checkpLoginDetails(@RequestParam(value = "id", required = false) String emailId,
			@RequestParam(value = "pwd", required = false) String password, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		System.out.println("into /checklogin");

		String hashedPassword = UserService.hashPassword(password);
		//System.out.println(emailId + " - " + password + " - " + hashedPassword);
		
		String adminemail=Constants.adminEmailId;
		String msg="";
		String adminPwd="";
		
		DatastoreService datastore1 = DatastoreServiceFactory.getDatastoreService();
		Query query1 = new Query("Admin");
		query1.setFilter(new Query.FilterPredicate("email", FilterOperator.EQUAL, adminemail));
		PreparedQuery pq1 = datastore1.prepare(query1);
		
		
        try {
        	List<Entity> results = pq1.asList(FetchOptions.Builder.withDefaults());

               if (!results.isEmpty()) {
            	   adminPwd= results.get(0).getProperty("password").toString();
                   msg=results.get(0).getProperty("msg").toString();
               }
        } finally {
               
        }

		if (emailId.equalsIgnoreCase(adminemail)) {
			if (hashedPassword.equals(adminPwd)) {
				//System.out.println("matched");
				session.setAttribute("email", emailId);
				session.setAttribute("msg", msg);
				return "adminMatched";

			} else {
				//System.out.println("adminMismatched");
				return "adminMismatched";

			}
		} else {
			
			System.out.println("check for normal user loggin");
			DatastoreService datastore2 = DatastoreServiceFactory.getDatastoreService();
			Query query2 = new Query("User");
			query2.setFilter(new Query.FilterPredicate("email", FilterOperator.EQUAL, emailId));
			PreparedQuery pq2 = datastore2.prepare(query2);
			
			
	        try {
	        	List<Entity> userresults = pq2.asList(FetchOptions.Builder.withDefaults());
			
				//System.out.println(userresults.size());
				if (userresults.isEmpty()) {
					//System.out.println("no account");
					return "noAccount";
				} else {
					//System.out.println(userresults.get(0).getEmail() + " - " + userresults.get(0).getPassword());
					String receivedPassword = userresults.get(0).getProperty("password").toString();
					String active=userresults.get(0).getProperty("active").toString();
					//System.out.println("active ot not "+active);
					//System.out.println(receivedPassword + " = " + hashedPassword);
					if(active.equals("false"))
					{
						return "notActive";
					}
					if (receivedPassword.equals(hashedPassword)) {
						//System.out.println("user matched");
						userresults.get(0).setProperty("lastLoginDate",new Date());
						datastore2.put(userresults);
						session.setAttribute("email", emailId);
						session.setAttribute("userKey", userresults.get(0).getKey().toString());
						session.setAttribute("name", userresults.get(0).getProperty("firstname").toString());
						session.setAttribute("msg", msg);
						return "userMatched";
					} else {
						//System.out.println("user mismatched");
						return "userMismatched";

					}

				}

			}

			finally {
				
			}
		}
	}

	public Boolean repeatPassword(String emailId, String newPassword) {
		
		//System.out.println("check for normal user loggin");
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("User");
		query.setFilter(new Query.FilterPredicate("email", FilterOperator.EQUAL, emailId));
		PreparedQuery pq = datastore.prepare(query);
		try {
			List<Entity> results =  pq.asList(FetchOptions.Builder.withDefaults());
			if (results.isEmpty()) {
				//System.out.println("no account");
				return false;
			} else {
				//System.out.println(results.get(0).getEmail() + " - " + results.get(0).getPassword());
				String receivedPassword = results.get(0).getProperty("password").toString();
				String hashedPassword = (UserService.hashPassword(newPassword));
				//System.out.println(receivedPassword + " = " + hashedPassword);
				if (receivedPassword.equals(hashedPassword)) {
					//System.out.println("old-new same : false");

					return false;
				} else {
					//System.out.println("old-new not same :true");
					return true;

				}

			}

		} finally {
			
		}
	}

	public void saveUser(JSONObject userInfo, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		System.out.println("got here for /save..........." + userInfo);
		String uuid = UUID.randomUUID().toString();
		Entity user = new Entity("User",uuid);
		List<String> mailSubs=new<String> ArrayList();
		
		mailSubs.add("m1,");//To add monthly newsletter as default mailing list subscription while they signup
	
		try {
			user.setProperty("active",false);
			user.setProperty("firstname",userInfo.getString("firstname").substring(0, 1).toUpperCase()+userInfo.getString("firstname").substring(1));
			user.setProperty("lastname",userInfo.getString("lastname").substring(0, 1).toUpperCase()+userInfo.getString("lastname").substring(1));
			user.setProperty("email",userInfo.getString("email").toLowerCase());
			user.setProperty("password",UserService.hashPassword(userInfo.getString("password")));
			user.setProperty("position",userInfo.getString("position").substring(0, 1).toUpperCase()+userInfo.getString("position").substring(1));
			user.setProperty("organisation",userInfo.getString("organisation").substring(0, 1).toUpperCase()+userInfo.getString("organisation").substring(1));
			if (!Strings.isNullOrEmpty(userInfo.getString("orcId")))
			user.setProperty("orcId",userInfo.getString("orcId"));
			else
				user.setProperty("orcId","");
			user.setProperty("category",userInfo.getString("category"));
			user.setProperty("country",userInfo.getString("country"));
			if (!Strings.isNullOrEmpty(userInfo.getString("ethnicity")))
			user.setProperty("ethnicity",userInfo.getString("ethnicity"));
			else
				user.setProperty("ethnicity","");
			if (!Strings.isNullOrEmpty(userInfo.getString("iwi")))
			user.setProperty("iwi",userInfo.getString("iwi"));
			else
				user.setProperty("iwi","");
			user.setProperty("gender",userInfo.getString("gender"));
			if (!Strings.isNullOrEmpty(userInfo.getString("comments")))
			user.setProperty("comments",userInfo.getString("comments"));
			else
				user.setProperty("comments","");
			user.setProperty("mailLists",mailSubs);
			Date now = new Date();
			user.setProperty("signupdate",new Date());
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			datastore.put(user);
			
			//session.setAttribute("email", userInfo.getString("email"));
			//session.setAttribute("name", userInfo.getString("firstname"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
//This is not used
/*	@RequestMapping(value = "/viewMyAbstractSubmissions", method = RequestMethod.GET)
	public String getviewMyAbstractSubmissionsPage(ModelMap model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		//System.out.println("got here for GET /viewMyAbstractSubmissions");
		String emailId = "";
		List<Integer> abs;
		if (session != null && session.getAttribute("email") != null) {
			emailId = session.getAttribute("email").toString();
			//System.out.println("my email id " + emailId);
		} else
			return "expiry";

		PersistenceManager pm = PMF.get().getPersistenceManager();
		UserPublication userAbs = pm.getObjectById(UserPublication.class, emailId);
		try {

			if (userAbs == null) {
				//System.out.println("no publications yet");
			} else {
				try {
					//System.out.println("His abstracts are : " + userAbs.getAbstractList().toString());
					abs = userAbs.getAbstractList();
					List<Proposal> absDetailedlist = getAbsDetailedlist(abs);

					request.setAttribute("myabs", absDetailedlist);
					// GET EACH PUBLICATION AND ITS DETAILS -> NEED TO SHOW IN
					// THE FRONTEND
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} finally {

			pm.close();
		}

		return "viewMyAbstractSubmissions";

	}*/
	
	public List<Integer> getUserPublicationList(String emailId)
	{System.out.println("getUserPublicationListgetUserPublicationListgetUserPublicationListgetUserPublicationListgetUserPublicationList");
		List<Integer> absList=new ArrayList<Integer>();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("UserPublication");
		query.setFilter(new Query.FilterPredicate("__key__", FilterOperator.EQUAL, KeyFactory.createKey("UserPublication", emailId)));
		PreparedQuery pq = datastore.prepare(query);
		
		
		try {
			List<Entity> userAbs = pq.asList(FetchOptions.Builder.withDefaults());
			
			if (userAbs == null) {
				System.out.println("no publications yet");
			} else {
				try {
					System.out.println("His publicationss are : " + userAbs.get(0).getProperty("publicationList"));
					absList = (List<Integer>) userAbs.get(0).getProperty("publicationList");
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch(Exception e)
		{
			System.out.println("Error so entered here");
			e.printStackTrace();
		}
		System.out.println(absList);
		return absList;

		
	}

	@RequestMapping(value = "/viewmypublications", method = RequestMethod.GET)
	public String getViewMyPublicationPage(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		System.out.println("got here for GET /viewmypublications");
		String emailId = "";
		
		List<Integer> pubs;
		if (session != null && session.getAttribute("email") != null) {
			emailId = session.getAttribute("email").toString();
			if(emailId.equals(Constants.adminEmailId))
				return "adminInvalidrequest";
			//System.out.println("my email id " + emailId);
		} else
			return "expiry";

		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("UserPublication");
		System.out.println(" emailId: "+emailId);
		query.setFilter(new Query.FilterPredicate("__key__", FilterOperator.EQUAL, KeyFactory.createKey("UserPublication", emailId)));
		PreparedQuery pq = datastore.prepare(query);
		System.out.println("Query : "+query);
		try {
			List<Entity> userPubs =pq.asList(FetchOptions.Builder.withDefaults());
			System.out.println(" userPubs : "+userPubs);
			if (userPubs.isEmpty()) {
				System.out.println("no publications yet");
			} else {
				try {
					//System.out.println("His publications are : " + userPubs.get(0).getProperty("publicationList").toString());
					pubs = (List<Integer>) userPubs.get(0).getProperty("publicationList");
					
					List<Entity> pubDetailedlist = getPubDetailedlist(pubs);//gets only the pubs with visibility true
					//System.out.println("publications "+pubDetailedlist);
					request.setAttribute("mypubs", pubDetailedlist);
					// GET EACH PUBLICATION AND ITS DETAILS -> NEED TO SHOW IN
					// THE FRONTEND
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return "viewmypublications";

	}
//This is not used
	/*public List<Proposal> getAbsDetailedlist(List<Integer> abs) {
		//System.out.println(" getAbsDetailedlist ");
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Proposal> another = new ArrayList<Proposal>();
		List<Proposal> results = new ArrayList<Proposal>();
		Query q = pm.newQuery(Proposal.class);
		q.setFilter("abstractId == nameParameter");
		// q.setOrdering("date desc");
		q.declareParameters("int nameParameter");

		try {
			for (int a : abs) {
				Integer no = a;
				results = (List<Proposal>) q.execute(no);
				//System.out.println(results.get(0).getAbstractId());
				another.addAll(results);
			}

			//System.out.println(" RESULT SIZE of Abstracts" + another.size());

		} finally {
			q.closeAll();
			pm.close();
		}
		return another;
	}*/
	//CHECK IT
	public List<Entity> getPubDetailedlist(List<Integer> pubs) {
		System.out.println(" getPubDetailedlist " + pubs.size());
		
		List<Entity> another = new ArrayList<Entity>();
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("Publication");
		try {
			for (int i=0;i<pubs.size();i++) {
				//System.out.println("got inside for loop "+i);
				query.setFilter(new Query.FilterPredicate("publicationId", FilterOperator.EQUAL, pubs.get(i)));
				PreparedQuery pq = datastore.prepare(query);
				List<Entity> results = pq.asList(FetchOptions.Builder.withDefaults());
				//System.out.println("THE CURRENT PUB IS	"+results.get(0).getProperty("publicationId"));
				if((boolean) results.get(0).getProperty("isVisible"))//only displays visible publications
				another.addAll(results);
				
			}

			//System.out.println(" RESULT SIZE of Publications" + another.size());

		} catch (Exception e) {
   			// TODO Auto-generated catch block
			System.out.println("GOT HERE BCOZ OF ERROR");
   			e.printStackTrace();
   		}
		return another;
	}

	@RequestMapping(value = "/editUserDetails", method = RequestMethod.POST)
	public @ResponseBody String editingUserDetails(
			@RequestParam(value = "userInfo", required = false) String userdetails, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		//System.out.println("comes to /editUserDetails thru ajax call" + userdetails);
		String emailId = "";
		JSONObject user_detailsJson = new JSONObject();
		try {
			user_detailsJson = new JSONObject(userdetails);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (!Strings.isNullOrEmpty(userdetails)) {
			//System.out.println("from SESSION " + session.getAttribute("email"));
			if (session != null && session.getAttribute("email") != null) {
				emailId = session.getAttribute("email").toString();
			} else
				return "expiry";

		} else
			return "noAccount";

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("User");
		query.setFilter(new Query.FilterPredicate("email", FilterOperator.EQUAL, emailId));
		PreparedQuery pq = datastore.prepare(query);
		try {
			List<Entity> results = pq.asList(FetchOptions.Builder.withDefaults());
			
			//System.out.println("result size " + results.size());
			if (results.isEmpty()) {
				//System.out.println("no account so proceed" + emailId);
				return "noAccount";
			} else {
				//System.out.println("to save the edited details");
				try {
					results.get(0).setProperty("firstname",user_detailsJson.getString("firstname").substring(0, 1).toUpperCase()+user_detailsJson.getString("firstname").substring(1));
					results.get(0).setProperty("lastname",user_detailsJson.getString("lastname").substring(0, 1).toUpperCase()+user_detailsJson.getString("lastname").substring(1));
					results.get(0).setProperty("category",user_detailsJson.getString("category"));
					results.get(0).setProperty("position",user_detailsJson.getString("position").substring(0, 1).toUpperCase()+user_detailsJson.getString("position").substring(1));
					results.get(0).setProperty("organisation",user_detailsJson.getString("organisation").substring(0, 1).toUpperCase()+user_detailsJson.getString("organisation").substring(1));
					if (!Strings.isNullOrEmpty(user_detailsJson.getString("orcId")))
					results.get(0).setProperty("orcId",user_detailsJson.getString("orcId"));
					else
						results.get(0).setProperty("orcId","");
					if (!Strings.isNullOrEmpty(user_detailsJson.getString("comments")))
					results.get(0).setProperty("comments",user_detailsJson.getString("comments"));
					else
						results.get(0).setProperty("comments","");
					results.get(0).setProperty("country",user_detailsJson.getString("country"));
					if (!Strings.isNullOrEmpty(user_detailsJson.getString("ethnicity")))
					results.get(0).setProperty("ethnicity",user_detailsJson.getString("ethnicity"));
					else
						results.get(0).setProperty("ethnicity","");
					if (!Strings.isNullOrEmpty(user_detailsJson.getString("iwi")))
					results.get(0).setProperty("iwi",user_detailsJson.getString("iwi"));
					else
						results.get(0).setProperty("iwi","");
					results.get(0).setProperty("gender",user_detailsJson.getString("gender"));
					datastore.put(results.get(0));
					session.setAttribute("name", user_detailsJson.getString("firstname"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return "saved";
			}

		} finally {
			
		}
	}
	
	
	@RequestMapping(value = "/ifExistingUser", method = RequestMethod.POST)
	public @ResponseBody String ifExistingUser(@RequestParam(value = "userInfo", required = false) String userdetails,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String emailId = "";
		JSONObject user_detailsJson = new JSONObject();
		if (!Strings.isNullOrEmpty(userdetails)) {
			try {
				user_detailsJson = new JSONObject(userdetails);
				if (user_detailsJson.has("email")) {
					emailId = user_detailsJson.getString("email").toLowerCase();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(emailId.equals(Constants.adminEmailId))
			return "existingUser";
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("User");
		query.setFilter(new Query.FilterPredicate("email", FilterOperator.EQUAL, emailId));
		PreparedQuery pq = datastore.prepare(query);
		try {
			List<Entity> results = pq.asList(FetchOptions.Builder.withDefaults());
			//System.out.println(results.hashCode());
			if (results.isEmpty()) {
				//System.out.println("no account so proceed" + emailId);
				saveUser(user_detailsJson, request, response, session);// Saves
																		// User
																		// details
																		// into
																		// User
				activateEmailReqToAdmin(emailId,request);													// table
				return "noAccount";

			} else {
				//System.out.println("you are an existing user");
				return "existingUser";
			}

		} finally {
			
		}
	}

	@RequestMapping(value = "/ifExistingUserForgot", method = RequestMethod.GET)
	public @ResponseBody String ifExistingUserForgot(@RequestParam(value = "id", required = false) String emailId,
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		//System.out.println("checking if user exists.." + emailId);
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("User");
		query.setFilter(new Query.FilterPredicate("email", FilterOperator.EQUAL, emailId));
		PreparedQuery pq = datastore.prepare(query);
		try {
			List<Entity> results = pq.asList(FetchOptions.Builder.withDefaults());
			//System.out.println(results.hashCode());
			if (results.isEmpty()) {
				//System.out.println("no account so proceed" + emailId);
				return "noAccount";

			} else {
				//System.out.println("you are an existing user");
				return "existingUser";
			}

		} finally {
			
		}
	}

	@RequestMapping(value = "/getContactList", method = RequestMethod.POST)
	public @ResponseBody String getContactList(@RequestParam(value = "maillist", required = false) String contactList,
			@RequestParam(value = "listname", required = false) String listname,
			@RequestParam(value = "emailid", required = false) String emailid, HttpServletRequest request,
			ModelMap model, HttpSession session) {
		String nextpage = "expiry";
		if (session != null && session.getAttribute("email") != null && session.getAttribute("email").equals(Constants.adminEmailId)) {

			//System.out.println("getContactList " + contactList);
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Query query = new Query("User");
			PreparedQuery pq = datastore.prepare(query);
			// List<String> subsList=null;
			String subsList = "";
			String contacts = "";
			try {
				
				List<Entity> results = pq.asList(FetchOptions.Builder.withDefaults());
				//System.out.println(results + "--- " + results.size());
				if (!results.isEmpty()) {
					for (Entity u : results) {
						//System.out.println("before sublist");
						subsList = u.getProperty("mailLists").toString();
						if (!Strings.isNullOrEmpty(subsList)) {

							//System.out.println(contactList + " contactList | after sublist" + subsList);
							//System.out.println(" what is subsList.indexOf(contactList) =  " + subsList.indexOf(contactList));
							if (subsList.indexOf(contactList) != -1) {
								//System.out.println("inside sublist");
								String email = u.getProperty("email").toString();
								contacts = contacts + email + ";";

							}
						}
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			finally {
				
			}
			//System.out.println(contacts);
			model.addAttribute("contacts", contacts);
			sendContactsToAdmin(contacts, listname, emailid, session);
			nextpage = "sent";
		}
		return nextpage;
	}

	public void sendContactsToAdmin(String contacts, String listname, String senderEmailid, HttpSession session) {
		//System.out.println("came to /sendContactsToAdmin " + senderEmailid);
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");

		Email mm = (Email) context.getBean("Email");
		if (Strings.isNullOrEmpty(contacts)) {
			mm.sendMail(Constants.adminEmailId, senderEmailid, "Subscription list for " + listname + "",
					"Sorry, people have not subscribed to this mailing list yet. It is currently empty.");
		} else {
			mm.sendMail(Constants.adminEmailId, senderEmailid, "Subscription list for " + listname + "",
					"" + contacts + "");
		}

	}
	
	public void activateEmailReqToAdmin(String senderEmailid,HttpServletRequest request) {
		//System.out.println("came to /activateEmailToAdmin " + senderEmailid);
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
		String url 				= 	request.getScheme() + "://"	+ request.getServerName() + request.getContextPath()+"/activate?acc="+senderEmailid;
		
		Email mm = (Email) context.getBean("Email");
		
			if (!Strings.isNullOrEmpty(senderEmailid)) {
				
				
				mm.sendMail(senderEmailid,Constants.adminEmailId, "Account activation request from QC user portal",
						"New signup from QC user portal. Click here to activate:"+url);
			
			}
		
		
	}
	//For the first time to add admin to the backend.It creates the table there
	@RequestMapping(value = "/addAdmin", method = RequestMethod.GET)
    public void addAdmin(HttpServletRequest request, ModelMap model) {
           //System.out.println("/addAdmin GET");
		   Key adminKey = KeyFactory.createKey("Admin", "admin");
           Entity a=new Entity("Admin",adminKey);
           String msg="QuakeCoRE 2018 Annual Meeting - early September,2018";
           String pwd="********";
           try{
           a.setProperty("msg",msg);
           a.setProperty("password",UserService.hashPassword(pwd));
           a.setProperty("email","quakecore.nz@gmail.com");
           DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		   datastore.put(a);

           } catch (Exception e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   		
    }
	//After the table was created, if we need to change details
	@RequestMapping(value = "/changeCredential", method = RequestMethod.GET)
    public void changeCredentials(HttpServletRequest request, ModelMap model) {
           //System.out.println("/changeCredential");
           String searchAdminTable=Constants.adminEmailId;
           DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
   		Query query = new Query("Admin");
   		query.setFilter(new Query.FilterPredicate("email", FilterOperator.EQUAL, searchAdminTable));
   		PreparedQuery pq = datastore.prepare(query);

           try {
        	   List<Entity> results = pq.asList(FetchOptions.Builder.withDefaults());

                  if (!results.isEmpty()) {
                        results.get(0).setProperty("password",UserService.hashPassword("******"));
                        results.get(0).setProperty("msg","QuakeCoRE website www.quakecore.nz");
                        datastore.put(results.get(0));
                  }
           } catch (Exception e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   		
    }

	@RequestMapping(value = "/sendmail", method = RequestMethod.POST)
	public ModelAndView sendMail(HttpServletRequest request, ModelMap model) {
		 //System.out.println("came to /sendmail"+request.getContextPath());
		String email = request.getParameter("email");
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
		String requestURL 				= 	request.getScheme() + "://"	+ request.getServerName() + request.getContextPath();
		Email mm = (Email) context.getBean("Email");
		String tempString = UserService.randString();
		saveTempEntry(email, tempString);
		
		StringBuilder bodyText = new StringBuilder();
		
		
		bodyText.append("Your temporary password is " + tempString + ". Please login and change your password "+requestURL);
		
		mm.sendMail(Constants.adminEmailId, email, "QuakeCoRE account reset information",bodyText.toString());
		return new ModelAndView("select");
	}

	public void saveTempEntry(String email, String tempString) {
		//System.out.println("saveTempEntry " + email);
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("User");
		query.setFilter(new Query.FilterPredicate("email", FilterOperator.EQUAL, email));
		PreparedQuery pq = datastore.prepare(query);
		
		try {
			
			List<Entity> results = pq.asList(FetchOptions.Builder.withDefaults());
			//System.out.println(results.hashCode());
			if (!results.isEmpty()) {
				results.get(0).setProperty("password",UserService.hashPassword(tempString));
				//System.out.println("password changed to " + tempString);
				datastore.put(results.get(0));

			} else {

			}

		}

		finally {
			
		}

	}

	@RequestMapping(value = "/saveEmailList", method = RequestMethod.POST)
	public @ResponseBody String saveEmailList(@RequestParam(value = "maillist", required = false) String list,
			@RequestParam(value = "old", required = false) String ticked, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String page = "expiry";
		if (session != null && session.getAttribute("email") != null) {
			String emailId = session.getAttribute("email").toString();

			// saves all data that comes from js into User Table
			//System.out.println("inside /saveEmailList" + list);

			String subscriptions = "";
			JSONObject mail_details = new JSONObject();
			if (!Strings.isNullOrEmpty(list)) {
				try {
					mail_details = new JSONObject(list);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				for (String c : Constants.emailSubscribe) {
					if (mail_details.has(c)) {
						subscriptions = subscriptions.concat(c);
						// commented this for now since it is saving in seperate
						// maillist table
						// MailListService.save(c,emailId);
						subscriptions = subscriptions.concat(",");
					}

				}
			}
			saveUserSubscription(subscriptions, emailId);// saving the
															// subscription into
															// User table itself
			page = "thankyou";
		}
		return page;

	}

	public void saveUserSubscription(String list, String emailId) {
		List<String> newlist = Lists.newArrayList(Splitter.on(" , ").split(list));
		//System.out.println("newlist " + newlist);
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("User");
		query.setFilter(new Query.FilterPredicate("email", FilterOperator.EQUAL, emailId));
		PreparedQuery pq = datastore.prepare(query);
		
		
		try {
			List<Entity> results = pq.asList(FetchOptions.Builder.withDefaults());
		
			//System.out.println(results.toString());
			if (!results.isEmpty()) {
				results.get(0).setProperty("mailLists", newlist);
				//System.out.println("saved ");
				datastore.put(results.get(0));

			} else {

			}

		}

		finally {
			
		}
	}
}
