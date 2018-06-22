$(document).ready(function() 
	{
	//for user session display (to know whether user is logged in/out)
	var userid=$('#userid').text();
	var editpub=$('#editpub').text();
	//if no session hide the logout link in the top
	if(userid === null || $.trim(userid) === "")
	{
		$("#logoutTab").hide();
	}
	if($.trim(userid) === 'quakecore.nz@gmail.com')//if admin, show  attribution
	{
		$("#lock-attrib").show();
		$("#attrib").show();
	}
	$('#authorsList').searchableOptionList({
	    maxHeight: '200px'
	});
	var ticked=$('#templink').text();
	//console.log(ticked);
	if(ticked === null || $.trim(ticked) === "")
	{
		
	}
	else
		{
		console.log("got inside");
		$('#authorsList option').each(function(){
			var value=$(this).val() ;
			/*console.log(value);*/
			
			if(ticked.indexOf(value) != -1)
			{
				$(this).prop('selected', true);
		    }
		});
		}
	
	var year	=	($($('#temp > div')[0]).html() != "") ? ($($('#temp > div')[0]).html()) : "";
	var fund	=	($($('#temp > div')[1]).html() != "") ? ($($('#temp > div')[1]).html()) : "";
	var status	=	($($('#temp > div')[2]).html() != "") ? ($($('#temp > div')[2]).html()) : "";
	var article	=	($($('#temp > div')[3]).html() != "") ? ($($('#temp > div')[3]).html()) : "";
	var author	=	($($('#temp > div')[4]).html() != "") ? ($($('#temp > div')[4]).html()) : "";
	var title	=	($($('#temp > div')[5]).html() != "") ? ($($('#temp > div')[5]).html()) : "";
	var venueName	=	($($('#temp > div')[6]).html() != "") ? ($($('#temp > div')[6]).html()) : "";
	var volume	=	($($('#temp > div')[7]).html() != "") ? ($($('#temp > div')[7]).html()) : "";
	var page	=	($($('#temp > div')[8]).html() != "") ? ($($('#temp > div')[8]).html()) : "";
	var location	=	($($('#temp > div')[9]).html() != "") ? ($($('#temp > div')[9]).html()) : "";
	var url	=	($($('#temp > div')[10]).html() != "") ? ($($('#temp > div')[10]).html()) : "";
	var dates	=	($($('#temp > div')[11]).html() != "") ? ($($('#temp > div')[11]).html()) : "";
	var publisher	=	($($('#temp > div')[12]).html() != "") ? ($($('#temp > div')[12]).html()) : "";
	var publicationNo	=	($($('#temp > div')[13]).html() != "") ? ($($('#temp > div')[13]).html()) : "";
	var descOutputOther= ($($('#temp > div')[14]).html() != "") ? ($($('#temp > div')[14]).html()) : "";
	var project= ($($('#temp > div')[15]).html() != "") ? ($($('#temp > div')[15]).html()) : "";
	var publishDate= ($($('#temp > div')[16]).html() != "") ? ($($('#temp > div')[16]).html()) : "";
	var lock = ($($('#temp > div')[17]).html() != "") ? ($($('#temp > div')[17]).html()) : "";
	var aff = ($($('#temp > div')[18]).html() != "") ? ($($('#temp > div')[18]).html()) : "";
	
	if($.trim(editpub) == "edit")
	{
		//console.log("GOT In EDIT validation"+year.length);
		if(publicationNo.length != 0)
			$('#editNo').val(publicationNo);
	
		
	if(year.length != 0)
		$('#year').val(year);
	
	if(fund.length != 0)
		$('#fund').val(fund);
	if(lock.length != 0)
		$('#lock').val(lock);
	if(status.length != 0)
		$('#status').val(status);
	
	if(article.length != 0)
		$('#article').val(article);
	if(aff.length != 0)
		$('#aff').val(aff);
	
	if(project.length != 0)
		$('#project').val(project);
	else
		$('#project').val("");
	
	if(author.length != 0)
		$('#author').val(author);
	else
		$('#author').val("");
	if(title.length != 0)
		$('#title').val(title);
	else
		$('#title').val("");
	if(venueName.length != 0)
		$('#venueName').val(venueName);
	else
		$('#venueName').val("");
	if(descOutputOther.length != 0)
		$('#descOutputOther').val(descOutputOther);
	else
		$('#descOutputOther').val("");
	
	if(volume.length != 0)
		$('#volume').val(volume);
	else
		$('#volume').val("");
	if(page.length != 0)
		$('#page').val(page);
	else
		$('#page').val("");
	if(location.length != 0)
		$('#location').val(location);
	else
		$('#location').val("");
	if(url.length != 0)
		$('#url').val(url);
	else
		$('#url').val("");
	
	if(dates.length != 0)
		$('#dates').val(dates);
	else
		$('#dates').val("");
	if(publisher.length != 0)
		$('#publisher').val(publisher);
	else
		$('#publisher').val("");
	if(publishDate.length != 0)
		$('#publishDate').val(publishDate);
	else
		$('#publishDate').val("");
	
    
	$('#btn-new-pub').hide();
	}
	else
		{
		$('#btn-edit-pub').hide();
		document.getElementById("author").value			=	"";  
        document.getElementById("author").focus(); 
        document.getElementById("title").value			=	""; 
    	document.getElementById("venueName").value		=	""; 
    	document.getElementById("descOutputOther").value=	""; 
    	document.getElementById("project").value		=	""; 
    	document.getElementById("volume").value			=	""; 
    	document.getElementById("page").value			=	""; 
    	document.getElementById("location").value		=	""; 
    	document.getElementById("url").value			=	""; 
    	document.getElementById("dates").value			=	""; 
    	document.getElementById("publisher").value		=	""; 
    	document.getElementById("publishDate").value	=	""; 
    	
    	
		}
    $("select").change(function(){
    	//console.log("moves HERE here");
    	var article	=	document.getElementById("article").value;
    	var status	=	document.getElementById("status").value;
    	if(article === "Quakecore report peer-reviewed"||article === "Quakecore report non-peer-reviewed")
    		{
    		$("#pubstatus").hide();
    		}
    	else
    		{
    		$("#pubstatus").show();
    		}
    	if(status === "Published" && article === "Journal")
		{
    	$("#venueDiv").show();
		$("#descDiv").hide();
		$("#volumeDiv").show();
		$("#pageDiv").show();
		$("#locationDiv").hide();
		$("#dateDiv").hide();
		$("#publisherDiv").hide();
		$("#publishDateDiv").show();
		}
		else if(status === "Published" && article === "Conference")
		{
		$("#venueDiv").show();
		$("#locationDiv").show();
		$("#descDiv").hide();
		$("#pageDiv").hide();
		$("#dateDiv").show();
		$("#volumeDiv").hide();
		$("#publisherDiv").hide();
		$("#publishDateDiv").hide();
		}
		else if(status === "Published" && article === "Book")
		{
		$("#venueDiv").hide();
		$("#pageDiv").hide();
		$("#publisherDiv").show();
		$("#descDiv").hide();
		$("#locationDiv").hide();
		$("#dateDiv").hide();
		$("#volumeDiv").hide();
		$("#publishDateDiv").hide();
		}
		else if(status === "Published" && article === "Book chapter")
		{
		$("#venueDiv").show();
		$("#pageDiv").show();
		$("#publisherDiv").show();
		$("#locationDiv").hide();
		$("#descDiv").hide();
		$("#dateDiv").hide();
		$("#volumeDiv").hide();
		$("#publishDateDiv").hide();
		}
		else if(status === "Published" && (article === "Peer-reviewed" || article === "Non peer-reviewed"))
		{
		$("#venueDiv").show();
		$("#pageDiv").hide();
		$("#dateDiv").show();
		$("#venueName").show();
		$("#descDiv").show();
		$("#publisherDiv").hide();
		$("#volumeDiv").hide();
		$("#publishDateDiv").hide();
		}
		else if((status === "Accepted" || status === "Submitted") && article === "Book" )
		{
    	$("#venueDiv").hide();
		$("#descDiv").hide();
		$("#pageDiv").hide();
		$("#volumeDiv").hide();
		$("#publishDateDiv").hide();
		$("#locationDiv").hide();
		$("#dateDiv").hide();
		$("#publisherDiv").hide();
		}
		else
		{
		$("#venueDiv").show();
		$("#descDiv").hide();
		$("#pageDiv").hide();
		$("#volumeDiv").hide();
		$("#publishDateDiv").hide();
		$("#locationDiv").hide(); 
		$("#dateDiv").hide();
		$("#publisherDiv").hide();
		}
    }).change();
		
validate=function(id)
{	
	//alert("from validate() newpublication.js "+id);
	$("#buttonid").html(id);
	
	var year			=	document.getElementById("year").value;
	var fund			=	document.getElementById("fund").value;
	var status			=	document.getElementById("status").value;
	var article			=	document.getElementById("article").value;
	var author			=	document.getElementById("author").value;
	var title			=	document.getElementById("title").value;
	var descOutputOther 	=	document.getElementById("descOutputOther").value;
	var project		=	document.getElementById("project").value;
	var venueName		=	document.getElementById("venueName").value;
	var volume 			=	document.getElementById("volume").value;
	var page			=	document.getElementById("page").value;
	var location 		=	document.getElementById("location").value;
	var url				=	document.getElementById("url").value;
	var dates			=	document.getElementById("dates").value
	var publisher		=	document.getElementById("publisher").value;
	var publishDate		=	document.getElementById("publishDate").value;
	var lock 			=   document.getElementById("lock").value;
	var aff				=   document.getElementById("aff").value;
	var authorsList=[];
	$('#authorsList option:selected').each(function(){
		authorsList[authorsList.length]=$(this).val();
	});
	
	//if admin, then authorslist should not be empty
	if($.trim(userid) === 'quakecore.nz@gmail.com' && (!isValid(authorsList)))
	{
		$('#err').html("Link authors AI/PI please");
		$('#messagebox').fadeIn().delay(2000).fadeOut();
	    $("html, body").animate({ scrollTop: 0 }, "slow");
		return false;
	}
    console.log("------------list in validate from js----------------");
    console.log(authorsList);
    
    if(!isValidInput(project))
	{
	//document.getElementById("project").value="";  
    document.getElementById("project").focus(); 
	$('#err').html("Please avoid &,< and > symbols in project names");
	$('#messagebox').fadeIn().delay(2000).fadeOut();
    $("html, body").animate({ scrollTop: 0 }, "slow");
	return false;
	}
	if(!isValid(author) || !isValidInput(author))
	{
	//document.getElementById("author").value="";  
    document.getElementById("author").focus(); 
    $('#err').html("Please enter valid names");
    if(!isValidInput(author)){
	     $('#err').html("Please avoid &,< and > symbols in author names");
	    }
    $('#messagebox').fadeIn().delay(2000).fadeOut();
    $("html, body").animate({ scrollTop: 0 }, "slow");
	return false;
	}
	if(!isValid(title) || !isValidInput(title))
	{
	//document.getElementById("title").value="";  
    document.getElementById("title").focus(); 
    $('#err').html("Please enter valid article title");
    if(!isValidInput(title)){
	     $('#err').html("Please avoid &,< and > symbols in title");
	    }
    $('#messagebox').fadeIn().delay(2000).fadeOut();
    $("html, body").animate({ scrollTop: 0 }, "slow");
	return false;
	}
	if(article === "Journal" || article === "Conference" ||  article === "Book chapter" || article === "Peer-reviewed" || article === "Non peer-reviewed" || article === "Poster" )
	{
		if(!isValid(venueName) || !isValidInput(venueName))
		{
		//document.getElementById("venueName").value="";  
	    document.getElementById("venueName").focus(); 
	    $('#err').html("Please enter valid venue details");
	    if(!isValidInput(venueName)){
		     $('#err').html("Please avoid &,< and > symbols in venue");
		    }
	    $('#messagebox').fadeIn().delay(2000).fadeOut();
	    $("html, body").animate({ scrollTop: 0 }, "slow");
		return false;
		}
		
	}
	if(status === "Published")
		{
			
			if( article === "Book" ||  article === "Book chapter" )
			{
				if(!isValid(publisher) || !isValidInput(publisher))
				{
				//document.getElementById("publisher").value="";  
			    document.getElementById("publisher").focus(); 
			    $('#err').html("Please enter valid publisher details");
			    if(!isValidInput(publisher)){
				     $('#err').html("Please avoid &,< and > symbols in publisher");
				    }
			    $('#messagebox').fadeIn().delay(2000).fadeOut();
			    $("html, body").animate({ scrollTop: 0 }, "slow");
				return false;
				}
			}
		
			if(article === "Journal" )
			{
				if(!isValid(page) || !isValidInput(page))
				{
				//document.getElementById("page").value="";  
			    document.getElementById("page").focus(); 
			    $('#err').html("Please enter valid number of pages");
			    if(!isValidInput(page)){
				     $('#err').html("Please avoid &,< and > symbols in pages");
				    }
			    $('#messagebox').fadeIn().delay(2000).fadeOut();
			    $("html, body").animate({ scrollTop: 0 }, "slow");
				return false;
				}
				if(!isValid(volume) || !isValidInput(volume))
				{
				//document.getElementById("volume").value="";  
			    document.getElementById("volume").focus(); 
			    $('#err').html("Please enter valid volume and issue numbers");
			    if(!isValidInput(volume)){
				     $('#err').html("Please avoid &,< and > symbols in volume");
				    }
			    $('#messagebox').fadeIn().delay(2000).fadeOut();
			    $("html, body").animate({ scrollTop: 0 }, "slow");
				return false;
				}
				if(!isValid(publishDate) || !isValidInput(publishDate) )
				{
				//document.getElementById("publishDate").value="";  
			    document.getElementById("publishDate").focus(); 
			    $('#err').html("Please enter the publishDate");
			    if(!isValidInput(publishDate)){
				     $('#err').html("Please avoid &,< and > symbols in publishDate");
				    }
			    $('#messagebox').fadeIn().delay(2000).fadeOut();
			    $("html, body").animate({ scrollTop: 0 }, "slow");
				return false;
				}
			}
			if(article === "Conference" )
			{
				if(!isValid(location) || !isValidInput(location))
				{
				//document.getElementById("location").value="";  
			    document.getElementById("location").focus(); 
			    $('#err').html("Please enter valid location");
			    if(!isValidInput(location)){
				     $('#err').html("Please avoid &,< and > symbols in location");
				    }
			    $('#messagebox').fadeIn().delay(2000).fadeOut();
			    $("html, body").animate({ scrollTop: 0 }, "slow");
				return false;
				}
				if(!isValid(dates) || !isValidInput(dates) )
				{
				//document.getElementById("dates").value="";  
			    document.getElementById("dates").focus(); 
			    $('#err').html("Please enter the dates");
			    if(!isValidInput(dates)){
				     $('#err').html("Please avoid &,< and > symbols in dates");
				    }
			    $('#messagebox').fadeIn().delay(2000).fadeOut();
			    $("html, body").animate({ scrollTop: 0 }, "slow");
				return false;
				}
				
			}
			if(article === "Peer-reviewed" || article === "Non peer-reviewed")
				{
				if(!isValid(descOutputOther) || !isValidInput(descOutputOther))
				{
				//document.getElementById("descOutputOther").value="";  
			    document.getElementById("descOutputOther").focus(); 
			    $('#err').html("Please enter the description of output");
			    if(!isValidInput(descOutputOther)){
			     $('#err').html("Please avoid &,< and > symbols in description");
			    }
			    $('#messagebox').fadeIn().delay(2000).fadeOut();
			    $("html, body").animate({ scrollTop: 0 }, "slow");
				return false;
				}
				
				}
			
			if(isValid(url) && !isValidUrl(url) )
			{
			//document.getElementById("url").value="";  
		    document.getElementById("url").focus(); 
		    $('#err').html("Please avoid < and > symbols in DOI");
		    $('#messagebox').fadeIn().delay(2000).fadeOut();
		    $("html, body").animate({ scrollTop: 0 }, "slow");
			return false;
			}
		}
		if ($('#venueDiv').is(":hidden"))
			$('#venueName').val('');
		if ($("#descDiv").is(":hidden"))
			$('#descOutputOther').val("");
		if ($("#pageDiv").is(":hidden"))
			$('#page').val("");
		if ($("#volumeDiv").is(":hidden"))
			$('#volume').val("");
		if ($("#publishDateDiv").is(":hidden"))
			$('#publishDate').val("");
		if ($("#locationDiv").is(":hidden"))
			$('#location').val("");
		if ($("#dateDiv").is(":hidden"))
			$('#dates').val("");
		if ($("#publisherDiv").is(":hidden"))
			$('#publisher').val("");
	return true;
}
var isValid = function(someValue) {
    if (someValue === null || $.trim(someValue) === "") 
    	return false;
    else
    	return true;
}
/*var isValidNumberOfPages = function(text) {
    var flag = true;
    var text =  /^[0-9]+$/i;
    flag = text.test(name);
    return flag;
}
var isValidAuthorNames = function(text) {
    var flag = true;
    var text =  /^[a-zA-Z,._- ]+$/i;
    flag = text.test(name);
    return flag;
}*/
var isValidInput = function(text) {
    var flag = true;
   // var namePattern =  /^[a-z]+$/i;
    if(text.indexOf('<') > -1 || text.indexOf('>') > -1 || text.indexOf('&') > -1)
    flag = false;
    return flag;
}
var isValidUrl = function(text) {
    var flag = true;
   // var namePattern =  /^[a-z]+$/i;
    if(text.indexOf('<') > -1 || text.indexOf('>') > -1)
    flag = false;
    return flag;
}


});