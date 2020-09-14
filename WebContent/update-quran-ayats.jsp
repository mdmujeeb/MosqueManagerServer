<html>
	<head>
		<title>MosqueManager - Update Quran Ayats.</title>
		<script  type="text/javascript" src="js/jquery.min.js"></script>
	</head>
	<body bgcolor="black" text="white">
		<center>
		
		<%@include file="include.jsp"%>
		
		<h3><font face="Arial">Update Quran Ayats.<font></h3>
		
		<form method="POST" action="updateQuranAyats">
			<textarea id="ayats" name="ayats" rows="10" cols="50"></textarea>
			<br/><br/>
			<input type="button" value=" Update " onclick="updateQuranAyats()">
		</form>
		<br/>
		<a href="update.jsp">Back</a>

		<script  type="text/javascript">
			getCurrentData();

			function getCurrentData() {

				$.get("getQuranAyats", null,
						function(data, status){
							var string = '';
							var count;
							for(count=0; count<data.length; count++) {
								string = string + data[count] + '\n';
							}
							$("textarea#ayats").val(string);
				});
			}
			
			function updateQuranAyats() {

				$.post("updateQuranAyats", { ayats: $("#ayats").val() },
						function(data, status){
							alert(data);
				});
			}
		</script>
	</body>
</html>