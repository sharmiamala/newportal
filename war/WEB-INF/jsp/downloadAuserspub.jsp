<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<!-- <meta content="text/html; charset=utf-8" http-equiv="Content-Type">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"> -->
		 <link href="/lib/sol.css" rel="stylesheet">
		<link href="/css/styles.css" rel="stylesheet" type="text/css">
		<link href="/css/main.css" rel="stylesheet">
		<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
		<link href="/css/jumbotron-narrow.css" rel="stylesheet">
		
		<title>QuakeCoRE</title>
		
		<!-- <meta name="Keywords" content="QuakeCoRE, research in earthquake resilience, University of Canterbury, New Zealand, University, ">
		<meta name="Description" content="QuakeCoRE Home Page, University of Canterbury, Christchurch, New Zealand"> -->
		
		<!-- <style type="text/css">
		</style> -->
	
	</head>

	<body>
		<div id="wrapper">
				<div id="header">
				<h1>QuakeCoRE</h1>
				<h2>Centre for Earthquake Resilience</h2>
				</div>
				<div id="imagearea">
				</div>
					  
					
					   <div id="topnavbar"><button id="nav-button">Toggle Navigation</button>
								<ul>
									<li id="homeUserTab"><a href="/homeuser"><strong>Home</strong></a></li> 
									<li id="homeAdminTab"><a href="/homeAdmin"><strong>Home</strong></a></li> 
									<li id="logoutTab"><a href="/logout"><strong>Logout</strong></a></li> 
								</ul>
					  </div> 
					  
					  
			  	 <!-- <div class="topbar"></div>  --> 
				<div id="content" class="content-width">
				  	<div class="container"> 
        <div id="userid"  style="display:none;">
		${email}
	</div>
	<h3 class="form-heading">Download a user's publication details</h3>
    <div class="jumbotron">
  <form  method="post"  class="fillform" name="downloadAuserspubData" action="downloadAuserspubData">
   <div id="messagebox" class="alertbox" style="display: none; position: absolute;z-index: 2001;">
        <p>
            <span class="alert alert-danger" id="Err"></span>
        </p>
    </div>
        
        <div class="form-group">
         <label class="col-xs-4 control-label">Select user (AI/PI)</label>
	       <div class="col-xs-8 selectContainer"  id="comboMargin">
		         <select name="userList"  id="userList"  style="text-align:left">
			        <c:forEach var="person" items="${users}">
				       <c:if test="${person.properties.email!=email}"><option value="${person.properties.email}">${person.properties.firstname}&nbsp${person.properties.lastname}</option></c:if>
				    </c:forEach>
		    	</select>
		     </div>
    	 </div>
        
   <!--  <button type="submit" class="myButton" id="btn-download" onclick="return validate()">Download</button> -->
    <button type="submit" class="myButton" id="btn-download">Download</button>
    <!-- <a href="/homeuser" class="btn-lnk">Cancel</a> -->
	 </form>
	 </div>
	 </div> 
			  	 </div>	
			    
				  <div id="footer"><p><strong>QuakeCoRE | 
				  </strong><a href="mailto:quakecore@canterbury.ac.nz">quakecore@canterbury.ac.nz</a><strong> | </strong> </p>
				  </div> 
		</div>
		<script  src="/lib/jquery-min-latest.js"></script>
		<script  src="bootstrap/js/bootstrap.min.js"></script>
		<script  src="/lib/sol.js"></script>
		<script  src="/js/viewAuthorise.js"></script>
		</body>
		</html>