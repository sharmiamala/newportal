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
		 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	 <div id="templink" name="templink" style="display:none;">
				${authorsList}
	</div>
	
	<div id="userid"  style="display:none;">
				${email}
			</div>
			<div id="editpub"  style="display:none;">
				${editPub}
			</div>
			<div id="buttonid"  style="display:none;">
				
			</div>
		<div id="wrapper">
		
			<div id="temp" style="display:none;">
			<div>${year}</div>
			<div>${fund}</div>
		 	<div>${status}</div>
		 	<div>${article}</div>
		 	<div>${author}</div>
		 	<div>${title}</div>
		    <div>${venueName}</div>
		    <div>${volume}</div>
		    <div>${page}</div>
		    <div>${location}</div>
		 	<div>${url}</div>
		    <div>${dates}</div>
		    <div>${publisher}</div>
		    <div>${publicationNo}</div>
		     <div>${descOutputOther}</div>
		     <div>${project}</div>
		     <div>${publishDate}</div>
		     <div>${lock}</div>
		     <div>${aff}</div>
		     
		     
		</div>

				<div id="header">
				<h1>QuakeCoRE</h1>
				<h2>Centre for Earthquake Resilience</h2>
				</div>
				<div id="imagearea">
				</div>
					  
					 <div id="topnavbar"><button id="nav-button">Toggle Navigation</button>
								<ul>
									<li id="homeTab"><a href="/homeuser"><strong>Home</strong></a></li> 
									<li id="logoutTab"><a href="/logout"><strong>Logout</strong></a></li> 
								</ul>
					  </div> 
					  
			  	 <!-- <div class="topbar"></div>  --> 
				<div id="content" class="content-width">
				  	<div class="container">
        
      <h3 class="form-heading">My Publication</h3>
        <div class="jumbotron">
  <form  method="post"  class="fillform" name="formLogin" action="newpublication">

  <h5>Please only enter publications that are a direct result of QuakeCoRE funding; this could include project funding from and RfP Project or Coordinated Project or scholarship funding.</h5>
  <br><br> <div id="messagebox" class="alertbox" style="display: none; position: absolute;z-index: 2001;">
        <p>
            <span class="alert alert-danger" id="err"></span>
            
        </p>
    </div>
    <br><br><br>
    <div class="form-group" >
      		<input type="hidden" class="form-control" name="editNo" id="editNo">
    	 </div>
    	 
    	 <!-- copy from here for editPublication -->
		 <div class="form-group">
      		<label class="col-xs-4 control-label">Year</label>
        <div class="col-xs-5 selectContainer" id="comboMargin">
            <select class="form-control selectwidthauto" name="year" id="year">
                <option value="2016">2016</option>
                <option value="2017">2017</option>
                <option value="2018">2018</option>
                <option value="2019">2019</option>
                <option value="2020">2020</option>
            </select>
        </div>
        </div>
         <div class="form-group" id="lock-attrib" style="display:none;">
        <label class="col-xs-4 control-label">Lock</label>
        <div class="col-xs-5 selectContainer" id="comboMargin">
            <select class="form-control selectwidthauto" name="lock" id="lock">
                <option value="unlocked">Unlock</option>
                <option value="locked">Lock</option>
            </select>
            </div>
         </div>
        <div class="form-group" id="attrib" style="display:none;">
        <label class="col-xs-4 control-label">Attribution</label>
        <div class="col-xs-5 selectContainer" id="comboMargin">
            <select class="form-control selectwidthauto" name="fund" id="fund">
                <option value="Direct">Direct</option>
                <option value="Aligned">Aligned</option>
            </select>
            </div>
         </div>
    	 <!-- <a href="#" class="col-xs-1" data-toggle="tooltip" data-placement="right" ><span class="glyphicon glyphicon-question-sign"></span></a> -->
    	 
    	  <div class="form-group">
      		<label class="col-xs-4 control-label">Type</label>
	        <div class="col-xs-8 selectContainer"  id="comboMargin"><!-- if you change/add the value names in this dropdown list,change in js files accordingly -->
	            <select class="form-control" name="article" id="article">
	                <option value="Journal">Journal Publication (peer-reviewed)</option>
	                <option value="Conference">Conference Proceeding (peer-reviewed)</option>
	                <option value="Book">Book (peer-reviewed)</option>
	                 <option value="Book chapter">Book Chapter (peer-reviewed)</option>
	                <option value="Peer-reviewed">Other (peer-reviewed)</option>
	                <option value="Non peer-reviewed">Other (non peer-reviewed)</option>
	                <option value="Quakecore report peer-reviewed">QuakeCoRE Report (peer-reviewed)</option>
	                <option value="Quakecore report non-peer-reviewed">QuakeCoRE Report (non peer-reviewed)</option>
	                <option value="Poster">Poster</option>
	            </select>
	        </div>
    	 </div>
    	 
    	  <div class="form-group" id="pubstatus" style="display:none;">
      		<label class="col-xs-4 control-label">Status</label>
	        <div class="col-xs-5 selectContainer selectwidthauto" id="comboMargin"><!-- if you change/add the value names in this dropdown list,change in js files accordingly -->
	            <select class="form-control" name="status" id="status">
	                <option value="Submitted">Submitted</option>
	                <option value="Accepted">Accepted</option>
	                <option value="Published">Published</option>
	            </select>
	        </div>
         </div>
         
          <div class="form-group">
      		<label class="col-xs-4 control-label">Primary Affiliation</label>
	        <div class="col-xs-8 selectContainer"  id="comboMargin">
	            <select class="form-control" name="aff" id="aff">
	                <option value="Flagship 1">Flagship 1</option>
	                <option value="Flagship 2">Flagship 2</option>
	                <option value="Flagship 3">Flagship 3</option>
	                 <option value="Flagship 4">Flagship 4</option>
	                <option value="Flagship 5">Flagship 5</option>
	                <option value="Special project 1">Special Project 1</option>
	                <option value="Technology platform 1">Technology Platform 1</option>
	                <option value="Technology platform 2">Technology Platform 2</option>
	                <option value="Technology platform 3">Technology Platform 3</option>
	                <option value="Technology platform 4">Technology Platform 4</option>
	                <option value="Other">Other</option>
	            </select>
	        </div>
    	 </div>
    	 
    	<div class="form-group">
      		<input type="text" class="form-control" name="project" id="project" placeholder="Project Id (e.g: 16002)">
    	 </div>
    	  <div class="form-group">
      		<input type="text" class="form-control" name="author" id="author" placeholder="Authors (e.g: MOORE B., KANE A.C., AND LI J.)">
    	 </div>
    	 
		 <div class="form-group" >
      		<input type="text" class="form-control" name="title" id="title" placeholder="Article Title">
    	 </div>
    	 <div id="optional">
		 <div class="form-group" style="display:none;" id="venueDiv"> 
      		<input type="text" class="form-control" name="venueName" id="venueName" placeholder="Journal / Conference / Workshop / Book Title">
    	 </div>
    	 
    	  <div class="form-group" style="display:none;" id="descDiv"> 
      		<input type="text" class="form-control" name="descOutputOther" id="descOutputOther" placeholder="Description of output (e.g: Technical report)">
    	 </div> 
    	
		 <div class="form-group" style="display:none;" id="pageDiv">
      		<input type="text" class="form-control" name="page" id="page"  placeholder="Number of pages">
    	 </div>
    	 <div class="form-group" style="display:none;" id="volumeDiv">
      		<input type="text" class="form-control" name="volume" id="volume" placeholder="Volume,issue no, if available (e.g: 14(2))">
    	 </div>
    	 
    	 <div class="form-group" style="display:none;" id="locationDiv">
      		<input type="text" class="form-control" name="location" id="location" placeholder="Location, if available (e.g: Berlin,Germany)">
    	 </div>
    	 
		 <div class="form-group"  id="urlDiv">
      		<input type="text" class="form-control" name="url" id="url" placeholder="URL/DOI/EID/PubMedID, if available">
    	 </div>
    	 
    	 <div class="form-group" style="display:none;" id="dateDiv">
      		<input type="text" class="form-control" name="dates" id="dates" placeholder="Dates (e.g: 7-13 May 2016)">
    	 </div>
    	 
    	  <div class="form-group" style="display:none;" id="publisherDiv">
      		<input type="text" class="form-control" name="publisher" id="publisher" placeholder="Publisher">
    	 </div>
    	  <div class="form-group" style="display:none;" id="publishDateDiv">
      		<input type="text" class="form-control" name="publishDate" id="publishDate" placeholder="Published Date">
    	 </div>
			</div>
			<br>
			 <!-- <div class="form-group"> -->
         <label class="col-xs-4 control-label">Link Authors (AI/PI)</label>
	       <div class="col-xs-8 selectContainer"  id="comboMargin">
		         <select name="authorsList"  multiple id="authorsList"  style="text-align:left">
			        <c:forEach var="person" items="${users}">
				       <c:if test="${person.properties.email!=email}"><option value="${person.properties.email}">${person.properties.firstname}&nbsp${person.properties.lastname}</option></c:if>
				    </c:forEach>
		    	</select>
		     </div>
    	 <!-- </div> --><br>
			
		<button type="submit" class="myButton" name="submitBtn" id="btn-new-pub" onclick="return validate(id)">Submit</button>
		<button type="submit" class="myButton" name="submitBtn" id="btn-edit-pub" onclick="return validate(id)">Submit</button>
		<a href="/homeuser" class="btn-lnk">Cancel</a>
	 </form>
	</div>
	 </div>
			  	 </div>	
			    
				  <div id="footer"><p><strong>QuakeCoRE | 
				  </strong><a href="mailto:quakecore@canterbury.ac.nz">quakecore@canterbury.ac.nz</a><strong> | </strong> </p>
				  </div> 
		</div>
		 	  <%@include file="confirmpopup.jsp" %> 
		
		<script  src="/lib/jquery-min-latest.js"></script>
		<script  src="bootstrap/js/bootstrap.min.js"></script>
		<script  src="/lib/sol.js"></script>
		<script  src="/js/newpublication.js"></script>
		</body>
		</html>