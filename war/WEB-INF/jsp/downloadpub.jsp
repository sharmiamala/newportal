<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<!-- <meta content="text/html; charset=utf-8" http-equiv="Content-Type">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"> -->
		 
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
	<h3 class="form-heading">Download publication Details</h3>
    <div class="jumbotron">
  <form  method="post"  class="fillform" name="downloadPubData" action="downloadPubData">
   <div id="messagebox" class="alertbox" style="display: none; position: absolute;z-index: 2001;">
        <p>
            <span class="alert alert-danger" id="Err"></span>
        </p>
    </div>
        <div class="form-group">
      		<label class="col-xs-4 control-label">Select to download</label>
	        <div class="col-xs-8 selectContainer"  id="comboMargin">
	            <select class="form-control" name="pub" id="pub">
	            	<c:when test="${email=='quakecore.nz@gmail.com'}"><option value="my" ${email=='quakecore.nz@gmail.com' ? 'disabled' : ''}>My Publications</option></c:when>
	            	<option value="all">All Publications</option>
	                <option value="Flagship 1">Flagship 1 Publications</option>
	                <option value="Flagship 2">Flagship 2 Publications</option>
	                <option value="Flagship 3">Flagship 3 Publications</option>
	                 <option value="Flagship 4">Flagship 4 Publications</option>
	                <option value="Flagship 5">Flagship 5 Publications</option>
	                <option value="Special project 1">Special Project 1 Publications</option>
	                <option value="Technology platform 1">Technology Platform 1 Publications</option>
	                <option value="Technology platform 2">Technology Platform 2 Publications</option>
	                <option value="Technology platform 3">Technology Platform 3 Publications</option>
	                <option value="Technology platform 4">Technology Platform 4 Publications</option>
	                <option value="Other">Other</option>
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
		<script  src="/js/viewAuthorise.js"></script>
		</body>
		</html>