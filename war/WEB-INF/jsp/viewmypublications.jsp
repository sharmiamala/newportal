<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		 
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
									<li id="homeTab"><a href="/homeuser"><strong>Home</strong></a></li> 
									<li id="logoutTab"><a href="/logout"><strong>Logout</strong></a></li> 
								</ul>
					  </div> 
					
			  	 <!-- <div class="topbar"></div>  --> 
				<div id="content" class="content-width">
				  	<div class="container">
				        	<div id="userid"  style="display:none;">
								${email}
							</div>
							<%-- <div id="pubs"  style="display:none;">
								${mypubs}
							</div> --%>
							<div id="singlepub"  style="display:none;">
								${singlePub}
							</div>
							<div id="buttonid"  style="display:none;">
								
							</div>
							<div id="refresh"  class="well well-sm" style="display:block;">
								Contact <a href="mailto:quakecore@canterbury.ac.nz">admin</a> to remove any item.
							</div>
      				</div>
      <!--  <span id="myPublications" class="hide"> -->
      <h3 class="form-heading">View my publications</h3>
        <div class="jumbotron">
        <form  method="post" name="formLogin" action="newpublication">
       <!--  <span style="white-space: nowrap"> -->
          <div style="white-space: nowrap" align="center">
          <c:choose>
           <c:when test="${empty mypubs}"><c:out value="Sorry, currently no publications to update" /></c:when> 
			
      		 <c:otherwise>
		        <table  border="1" cellpadding="5">
		            
		            <tr id="rowFont">
		                <th>ID</th>
		                <th>Details</th>
						<th>Affiliation</th>
						<th>Funding</th>
						<th>Article</th>
						<th>Status</th>
						<th>Year</th>
						<th>AI/PI</th>
						<th>Action</th>
						
						
		            </tr>
					
		            <c:forEach var="pub" items="${mypubs}">
		                 <tr id="rowFont">
							 
							<%--  <c:set var="pubStatus" scope="application" value="${pub.status}"/> --%>
							 
							 <td><c:if test="${not empty pub.properties.pubIdStr4digit}"><c:out value="${pub.properties.pubIdStr4digit}" /></c:if></td> 
		                   <td> 
							<c:if test="${not empty pub.properties.author}"><c:out value="${pub.properties.author}"/>'</c:if> 
							<c:if test="${not empty pub.properties.title}"><c:out value="${pub.properties.title}"/>',</c:if> 
							<c:if test="${not empty pub.properties.venueName}"><i><c:out value="${pub.properties.venueName}"/></i><c:if test = "${pub.properties.status == 'Published'}">,</c:if></c:if>
							<c:if test="${not empty pub.properties.location}"><c:out value="${pub.properties.location}"/>.</c:if>
							<c:if test="${not empty pub.properties.publisher}"><c:out value="${pub.properties.publisher}"/>.</c:if>
							<c:if test="${not empty pub.properties.volume}"><b><c:out value="${pub.properties.volume}"/></b>:</c:if>
							<c:if test="${not empty pub.properties.page}"><c:out value="${pub.properties.page}"/></c:if>
							<c:if test="${not empty pub.properties.url}">,<c:out value="${pub.properties.url}"/></c:if>
							<c:if test="${not empty pub.properties.publishDate}">,<c:out value="${pub.properties.publishDate}"/>.</c:if>
							</td>
							<td><c:if test="${not empty pub.properties.aff}"><c:out value="${pub.properties.aff}"/></c:if></td>
							<td><c:if test="${not empty pub.properties.fund}"><c:out value="${pub.properties.fund}"/></c:if> </td>
							<td><c:if test="${not empty pub.properties.article}"><c:out value="${pub.properties.article}"/></c:if> </td>
							<td><c:if test="${not empty pub.properties.status && pub.properties.article != 'qcorereport non-peer-reviewed' && pub.properties.article != 'qcorereport peer-reviewed'}"><c:out value="${pub.properties.status}" /></c:if> </td>
							<td><c:if test="${not empty pub.properties.year}"><c:out value="${pub.properties.year}" /></c:if></td>
							<td><c:if test="${not empty pub.properties.authorsList}"><c:set var = "string1" value = "${pub.properties.authorsList}"/>
      <c:set var = "string2" value = "${fn:replace(string1, ']', '')}" /> <c:out value="${fn:replace(string2, '[', '')}" /></c:if></td> 
							<td><c:if test="${pub.properties.lock!='locked'}"><button type="submit" class="myButton" name="actionBtn" value="<c:out value="${pub.properties.publicationId}" />">Update</button ></c:if></td>
							
		                </tr>
		            </c:forEach>
		
		        </table>
		</c:otherwise>
		</c:choose>
		    </div>
		<!-- </span> -->
		
		</form>
<div id="pagination">
    <c:url value="/viewmypublications" var="prev">
        <c:param name="page" value="${page-1}"/>
    </c:url>
    <c:if test="${page > 1}">
        <a href="<c:out value="${prev}" />" class="pn prev">Prev</a>
    </c:if>

    <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
        <c:choose>
            <c:when test="${page == i.index}">
                <span>${i.index}</span>
            </c:when>
            <c:otherwise>
                <c:url value="/viewmypublications" var="url">
                    <c:param name="page" value="${i.index}"/>
                </c:url>
                <a href='<c:out value="${url}" />'>${i.index}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:url value="/viewmypublications" var="next">
        <c:param name="page" value="${page + 1}"/>
    </c:url>
    <c:if test="${page + 1 <= maxPages}">
        <a href='<c:out value="${next}" />' class="pn next">Next</a>
    </c:if>
</div>
		</div>
		
		<!-- </span> -->
	 </div>  
	 
				  <div id="footer"><p><strong>QuakeCoRE | 
				  </strong><a href="mailto:quakecore@canterbury.ac.nz">quakecore@canterbury.ac.nz</a><strong> | </strong> </p>
				  </div> 
		</div>
		<%--  <span id="editPublication" class="hide">
		<%@ include file="newpublication.jsp" %>
	</span>  --%>
		 
		<script  src="/lib/jquery-min-latest.js"></script>
		<script  src="bootstrap/js/bootstrap.min.js"></script>
		 <!-- <script  src="/js/viewmypublication.js"></script> -->
		</body>
		</html>