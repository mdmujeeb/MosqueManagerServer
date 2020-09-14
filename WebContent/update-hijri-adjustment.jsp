<html>
	<head>
		<title>Mosque Manager - Update Hijri Adjustment.</title>
		<script  type="text/javascript" src="js/jquery.min.js"></script>
	</head>
	<body bgcolor="black" text="white">
		<center>
		
		<%@include file="include.jsp"%>
		
		<h3><font face="Arial">Update Hijri Adjustment.<font></h3>
		
		<form method="POST" action="updateHijriAdjustment">
			<br/><br/>
			Enter Hijri Adjustment in Days: <input id="adjustment" type="text" name="adjustment" size="10">
			<br/><br/>
			<input type="button" value=" Update " onclick="updateHijriAdjustment()">
		</form>
		<br/>
		<a href="update.jsp">Back</a>

		<script  type="text/javascript">
					getHijriAdjustment();

					function getHijriAdjustment() {

						$.get("getNamazTimes", { id: <%=session.getAttribute("MASJID_ID")%> },
								function(data, status){
									$("input#adjustment").val(data['HIJRI_ADJUSTMENT']);
						});
					}
					
					function updateHijriAdjustment() {

						$.post("updateHijriAdjustment", { adjustment: $("#adjustment").val() },
								function(data, status){
									alert(data);
						});
					}
		</script>
		
		</center>
	</body>
</html>