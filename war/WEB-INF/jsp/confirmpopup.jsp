
<div class="container">
  <!-- Modal -->
  <div class="modal fade" id="confirmModal" data-backdrop="static" role="dialog">
    <div class="modal-dialog">
    <div id="userid"  style="display:none;">
				${email}
			</div>
			<div id="editpub"  style="display:none;">
				${editPub}
			</div>
      <!-- Modal content-->
      <div class="modal-content">
       
        <div class="modal-body">
          <p>Please confirm that the intended publication was not given a QuakeCoRE number before </p>
        </div>
        <div class="modal-footer">
       <a href="viewpublication" role="button" class="btn btn-default">No</a>
          <button type="button" class="btn btn-lg" data-dismiss="modal">Yes</button>
        </div>
      </div>
      
    </div>
  </div>
  
</div>

</body>

<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <script  src="/lib/jquery-min-latest.js"></script>
	<script  src="bootstrap/js/bootstrap.min.js"></script>
	<script  src="/js/confirmpopup.js"></script>
<!-- <script  src="/js/newpublication.js"></script> -->
</html>

