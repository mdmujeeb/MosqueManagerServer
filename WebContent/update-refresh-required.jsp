<html>
	<head>
		<title>MosqueManager - Refresh Clock Page.</title>
		<script  type="text/javascript" src="js/jquery.min.js"></script>
	</head>
	<body bgcolor="black" text="white">
		<center>
		
		<%@include file="include.jsp"%>
		
		<h3><font face="Arial">Refresh Clock Page.</font></h3>
		
		<form method="POST" action="updateRefreshRequired">
			<input type="hidden" id="refreshRequired" name="refreshRequired" value="true">
			<input type="hidden" id="masjidId" name="id" value="<%=session.getAttribute("MASJID_ID")%>">
			<br/>
			<input type="button" value=" Refresh Now " onclick="updateRefreshRequired()">
		</form>
		<br/>
		<a href="update.jsp">Back</a>
		
		<script  type="text/javascript">
			function updateRefreshRequired() {

				$.post("updateRefreshRequired", { refreshRequired: $("#refreshRequired").val(), id: $("#masjidId").val() },
						function(data, status){
							alert(data);
				});
			}
		</script>
	</body>
</html>