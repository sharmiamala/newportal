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
		
		<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-35589834-2', 'auto');
  ga('send', 'pageview');

</script>
	
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
									<!-- <li id="homeTab"><a href="/"><strong>Home</strong></a></li>  -->
									<li id="logoutTab"><a href="/logout"><strong>Logout</strong></a></li> 
								</ul>
					  </div> 
					   
			  	 <!-- <div class="topbar"></div> --> 
				<div id="content" class="content-width">
				<div id="msg"  class="panel panel-info" style="display:block;">
						${msg}
						</div>
				  	<div class="container">  
				        <div id="userid"  style="display:none;">
						${email}
						</div>
	 <h3 class="form-heading">Welcome ${name}!</h3>
		<div class="jumbotron">
				<form  method="post"  class="homeuserform" name="formUserSelection" action="">
					 <div id="messagebox" class="alertbox" style="display: none; position: absolute;z-index: 2001;">
				        <p>
				            <span class="alert alert-success" id="Err"></span>
				        </p>
				     </div>
			   		 <!--  <br>
			         <ul class="nav nav-pills"> 
							  <a href="editprofile" class="btn  btn-default btn-block" role="button" >Edit profile</a>
							  <a href="changepassword" class="btn btn-default btn-block" role="button" >Change password</a>
							  <a href="mailinglist" class="btn  btn-default btn-block" role="button">Mailing list subscription</a>
							  <a href="newpublication" class="btn  btn-default btn-block" role="button">Get QuakeCoRE publication number</a>
							   <a href="viewmypublications" class="btn  btn-default btn-block" role="button">Update publication details</a>
							   <a href="viewpeople" class="btn  btn-default btn-block" role="button">QuakeCoRE community</a>
							  <a href="viewpublication" class="btn  btn-default btn-block" role="button">QuakeCoRE publications</a>
							  <a href="http://www.quakecore.nz/logo/" target="_blank" class="btn  btn-default btn-block" role="button">Get QuakeCoRE logo & templates</a>
							   <a href="https://wiki.canterbury.ac.nz/display/QuakeCore/QuakeCoRE%3A+The+Centre+for+Earthquake+Resilience+Home" target="_blank" class="btn  btn-default btn-block" role="button">QuakeCoRE wikipages</a>
							  <a href="http://www.quakecore.nz/wp-content/uploads/2017/03/QuakeCoRE_No_Cost_Extension_Application_-2016.doc" class="btn  btn-default btn-block" role="button">No cost extension application</a>
							  <a href="http://www.quakecore.nz/wp-content/uploads/2017/03/QuakeCoRE_RfP_Variation_Application_-2016.doc" class="btn  btn-default btn-block" role="button">Variation application</a>
							 <br><br> -->
							   <!--  <a href="abstractSubmission" class="btn btn-round" role="button">Submit abstract</a> 
							   <a href="viewMyAbstractSubmissions" class="btn btn-round" role="button">View my abstract submissions</a> -->
					 <!--  </ul>   -->
<div class="panel-group" id="accordion">
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">Portal Profile</a>
        </h4>
      </div>
      <div id="collapse1" class="panel-collapse collapse">
        <div class="panel-body"><a href="editprofile">Edit profile</a></div>
        <div class="panel-body"><a href="changepassword">Change password</a></div>
      </div>
    </div>
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">QuakeCoRE Publications</a>
        </h4>
      </div>
      <div id="collapse2" class="panel-collapse collapse">
        <div class="panel-body"><a href="newpublication">Get QuakeCoRE publication number</a></div>
        <div class="panel-body"><a href="viewmypublications">View/Update my publication details</a></div>
        <div class="panel-body"><a href="viewpublication">View all QuakeCoRE publications</a></div>
        <div class="panel-body"><a href="downloadpub">Download Publication Details</a></div>
      </div>
    </div>
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse3">Investigator Tools</a>
        </h4>
      </div>
      <div id="collapse3" class="panel-collapse collapse">
        <div class="panel-body"> <a href="http://www.quakecore.nz/logo/" target="_blank">Get QuakeCoRE logo & templates</a></div>
        <div class="panel-body"> <a href="https://wiki.canterbury.ac.nz/display/QuakeCore/QuakeCoRE%3A+The+Centre+for+Earthquake+Resilience+Home" target="_blank">QuakeCoRE wikipages</a></div>
      </div>
    </div>
    <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse4">Contracting</a>
        </h4>
      </div>
      <div id="collapse4" class="panel-collapse collapse">
        <div class="panel-body"><a href="http://www.quakecore.nz/wp-content/uploads/2017/03/QuakeCoRE_No_Cost_Extension_Application_-2016.doc">No cost extension application</a></div>
		<div class="panel-body"><a href="http://www.quakecore.nz/wp-content/uploads/2017/03/QuakeCoRE_RfP_Variation_Application_-2016.doc">Variation application</a></div>
      </div>
    </div>
  </div> 
							   
			  
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
		<script  src="/js/session.js"></script>
	</body>
</html>