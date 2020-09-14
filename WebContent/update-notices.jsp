<html>
	<head>
		<title>MosqueManager - Update Notices.</title>
		<script  type="text/javascript" src="js/jquery.min.js"></script>
	</head>
	<body bgcolor="black" text="white">
		<center>
		
		<%@include file="include.jsp"%>
		
		<h3><font face="Arial">Update Notices.<font></h3>
		
		<form method="POST" action="updateNotices">
			<textarea id="notices" name="notices" rows="10" cols="50"></textarea>
			<br/><br/>
			<input type="button" value=" Update " onclick="updateNotices()">
		</form>
		<br/>
		<a href="update.jsp">Back</a>

		<script  type="text/javascript">
			getCurrentData();

			function getCurrentData() {

				$.get("getNotices", { id: <%=session.getAttribute("MASJID_ID")%> },
						function(data, status){
							var string = '';
							var count;
							for(count=0; count<data.length; count++) {
								string = string + data[count] + '\n';
							}
							$("textarea#notices").val(string);
				});
			}
			
			function updateNotices() {

				$.post("updateNotices", { notices: $("#notices").val() },
						function(data, status){
							alert(data);
				});
			}
		</script>
	</body>
</html>