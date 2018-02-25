package myproject.practice.com;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

//import javax.jdo.PersistenceManager;
//import javax.jdo.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.common.base.Strings;
import org.json.JSONException;
import org.json.JSONObject;

public class PublicationService {
	public static synchronized ModelAndView  getPublicationNo(JSONObject pubJson,String emailId,HttpServletRequest request,HttpServletResponse response,ModelMap model,HttpSession session) throws Exception
	{
		int publicationNo=0;String number="";
		if (session != null && session.getAttribute("email") != null && emailId.equals(session.getAttribute("email"))) {
		//querying the total number of publications from Publication Table
		//System.out.println("getPublicationNo in PublicationService");
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Query query = new Query("Publication");
			PreparedQuery q = datastore.prepare(query);
		  
		
		try {
			List<Entity> results = q.asList(FetchOptions.Builder.withDefaults());
			number=String.format("%1$04d",(results.size()+1));
			publicationNo=Integer.parseInt(number);
			if (results.isEmpty()) {
				model.addAttribute("allPublicationList", null);
			} else {
				model.addAttribute("allPublicationList", results);
			}

		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(publicationNo > 0)
		{
			String aticleTitle=pubJson.get("title").toString();
		//System.out.println(pubJson.get("year") + " -- " + pubJson.get("fund")+ "-- " + pubJson.get("project"));
		//System.out.println(pubJson.get("venueName") + "-- " +pubJson.get("status")+ "-- " + pubJson.get("descOutputOther"));
		//System.out.println(pubJson.get("volume") + "-- " + pubJson.get("article"));
		//System.out.println(pubJson.get("page")+ "-- " + pubJson.get("title"));
		//System.out.println(pubJson.get("location") + "-- " + pubJson.get("author"));
		//System.out.println(pubJson.get("url") + "-- " + pubJson.get("dates") + " -- " + pubJson.get("publisher"));
		if (!Strings.isNullOrEmpty(pubJson.get("title").toString()) && !Strings.isNullOrEmpty(pubJson.get("author").toString())) {
			String uuid = UUID.randomUUID().toString();
			Entity newpub = new Entity("Publication",uuid);
			newpub.setProperty("firstEnteredDate",new Date());
			newpub.setProperty("lastModifiedDate",new Date());
			newpub.setProperty("publicationId",publicationNo);
			newpub.setProperty("pubIdStr4digit",number);
			newpub.setProperty("isVisible",true);
			newpub.setProperty("year",pubJson.get("year").toString());
			newpub.setProperty("fund",pubJson.get("fund").toString());
			newpub.setProperty("status",pubJson.get("status").toString());
			newpub.setProperty("article",pubJson.get("article").toString());System.out.println("************************article name while putting to DB "+pubJson.get("article").toString());
			newpub.setProperty("author",pubJson.get("author").toString().toUpperCase());
			newpub.setProperty("title",pubJson.get("title").toString().substring(0, 1).toUpperCase()+pubJson.get("title").toString().substring(1));
			if (!Strings.isNullOrEmpty(pubJson.get("project").toString()))
				newpub.setProperty("project",pubJson.get("project").toString());
			else
				newpub.setProperty("project","");
			if (!Strings.isNullOrEmpty(pubJson.get("venueName").toString()))
				newpub.setProperty("venueName",pubJson.get("venueName").toString().substring(0, 1).toUpperCase()+pubJson.get("venueName").toString().substring(1));
			else
				newpub.setProperty("venueName","");
			if (!Strings.isNullOrEmpty(pubJson.get("descOutputOther").toString()))
				newpub.setProperty("descOutputOther",pubJson.get("descOutputOther").toString());
			else
				newpub.setProperty("descOutputOther","");
			if (!Strings.isNullOrEmpty(pubJson.get("volume").toString()))
				newpub.setProperty("volume",pubJson.get("volume").toString());
			else
				newpub.setProperty("volume","");
			if (!Strings.isNullOrEmpty(pubJson.get("page").toString()))
				newpub.setProperty("page",pubJson.get("page").toString());
			else
				newpub.setProperty("page","");
			if (!Strings.isNullOrEmpty(pubJson.get("location").toString()))
				newpub.setProperty("location",pubJson.get("location").toString());
			else
				newpub.setProperty("location","");
			if (!Strings.isNullOrEmpty(pubJson.get("url").toString()))
				newpub.setProperty("url",pubJson.get("url").toString());
			else
				newpub.setProperty("url","");
			if (!Strings.isNullOrEmpty(pubJson.get("dates").toString()))
				newpub.setProperty("dates",pubJson.get("dates").toString());
			else
				newpub.setProperty("dates","");
			if (!Strings.isNullOrEmpty(pubJson.get("publisher").toString()))
				newpub.setProperty("publisher",pubJson.get("publisher").toString());
			else
				newpub.setProperty("publisher","");
			
			try {
				DatastoreService datastore1 = DatastoreServiceFactory.getDatastoreService();
				datastore1.put(newpub);
				saveNewUserPublication(emailId, publicationNo);// stores
																// into
																// the
																// UserPublication
																// table
				//System.out.println(" ****************  " + number);
				request.setAttribute("pubNo", number);
				
				//To send email to user with publication number
				UserService.sendNewPubDeatilsToUser(emailId,number,aticleTitle);
				
				//return new ModelAndView("changed");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		}
		else
		{
			if(session.getAttribute("email").equals(Constants.adminEmailId))
				return new ModelAndView("adminInvalidrequest");
			else
				return new ModelAndView("expiry");
		}
		return new ModelAndView("changed");
		
	}
	//to change the visibility of a publication by admin only
	public static String publicationVisibility(String pubId,ModelMap model,HttpSession session)
	{//System.out.println("in publicationVisibility "+pubId);
	String nextpage="expiry";
	if (session != null && session.getAttribute("email") != null && session.getAttribute("email").equals(Constants.adminEmailId))
		{
		if(!Strings.isNullOrEmpty(pubId))
		{
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Query query = new Query("Publication");
			query.setFilter(new Query.FilterPredicate("publicationId", FilterOperator.EQUAL, pubId));
			PreparedQuery pq = datastore.prepare(query);
			
			
			try {
				List<Entity> results = pq.asList(FetchOptions.Builder.withDefaults());
			
				
				if (!results.isEmpty()) {
					//System.out.println(results.get(0).getIsVisible());
					Boolean pubStatus=(Boolean) results.get(0).getProperty("isVisible");
					if(pubStatus)
						results.get(0).setProperty("isVisible",false);
					else
						results.get(0).setProperty("isVisible",true);
					nextpage="itemDetailsChanged";
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
	public static void saveNewUserPublication(String email, int displayNo) {
		System.out.println("coming to save a publication");

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("UserPublication");
		query.setFilter(new Query.FilterPredicate("__key__", FilterOperator.EQUAL, KeyFactory.createKey("UserPublication", email)));
		PreparedQuery pq = datastore.prepare(query);
		
		
		
		try {
			List<Entity> results = pq.asList(FetchOptions.Builder.withDefaults());
			List<Integer> newlist = new ArrayList<Integer>();
			System.out.println(results);
			if (!results.isEmpty()) {

				newlist = (List<Integer>) results.get(0).getProperty("publicationList");
				System.out.println("while retrieved: "+newlist);
				newlist.add(displayNo);
				System.out.println("after adding: "+newlist);
				results.get(0).setProperty("publicationList",newlist);
				datastore.put(results.get(0));

			} else {

				Entity userpub = new Entity("UserPublication",email);
				newlist.add(displayNo);
				userpub.setProperty("publicationList",newlist);
				datastore.put(userpub);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	//THIS IS NOT USED
	/*public static void saveNewUserAbstract(String email, int absNo) {
		//System.out.println("coming to save an abstract in saveUserAbstract()");

		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(UserPublication.class);
		q.setFilter("key == emailParameter");
		q.declareParameters("String emailParameter");

		try {
			List<UserPublication> results = (List<UserPublication>) q.execute(email);
			List<Integer> newlist = new ArrayList<Integer>();
			//System.out.println(results.hashCode());
			if (!results.isEmpty()) {

				newlist = results.get(0).getAbstractList();
				newlist.add(absNo);
				results.get(0).setAbstractList(newlist);

			} else {

				UserPublication userpub = new UserPublication();

				newlist.add(absNo);

				userpub.setKey(email);
				userpub.setAbstractList(newlist);
				pm.makePersistent(userpub);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			q.closeAll();
			pm.close();
		}

	}
	*/
	public static String  updatePublicationDetails(String emailId,int publicationId,HttpServletRequest request,HttpServletResponse response,ModelMap model) throws Exception
	{ 
		System.out.println("in updatePublicationDetails");
		HashMap<String, String> hash = new HashMap<>();
		String nextpage="newpublication";
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query("Publication");
		query.setFilter(new Query.FilterPredicate("publicationId", FilterOperator.EQUAL, publicationId));
		PreparedQuery pq = datastore.prepare(query);
		
		
		try {
			List<Entity> results = pq.asList(FetchOptions.Builder.withDefaults());
			//System.out.println(results.get(0).getKey());
			if (!results.isEmpty()) {
				
					request.setAttribute("pub",results);
					//request.setAttribute("lastName",results.get(0).getLastname().toString());
					
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
						e.printStackTrace();
		}
		
		return nextpage;
			
	}
	
	

}
