<html>
	<head>
		<title>MosqueManager - Update Namaz Times.</title>
		<script  type="text/javascript" src="js/jquery.min.js"></script>
	</head>
	<body bgcolor="black" text="white">
		<center>
		
	<%@include file = "include.jsp"%>
		
		<h3><font face="Arial">Update Namaz Times.<font></h3>
		<br>
		
		<form method="POST" action="updateNamazTimes">
			Select Time: <select id="name" name="name" onchange="getNamazTime()">
					<option value="FAJR">FAJR</option>
					<!-- <option value="ISHRAQ">ISHRAQ</option> -->
					<option value="ZOHOR">ZOHOR</option>
					<option value="ASR">ASR</option>
					<!-- <option value="MAGRIB">MAGRIB</option> -->
					<option value="ISHA">ISHA</option>
					<option value="JUMA">JUMA</option>
			</select>
			<br/><br/>
			Enter time: <input id="time" type="text" name="time" size="10">
			<br/><br/><br/>
			<input type="button" value=" Update " onclick="updateNamazTime()">
		</form>
		<br/>
		<a href="update.jsp">Back</a>

		<script  type="text/javascript">
			getNamazTime();

			function getNamazTime() {

				$.get("getNamazTimes", { id: <%=session.getAttribute("MASJID_ID")%> },
						function(data, status){
							var timestr = data[$("#name option:selected" ).text()];
							$("input#time").val(timestr);
				});
			}
			
			function updateNamazTime() {

				$.post("updateNamazTimes", { name: $("#name").val(), time: $("#time").val() },
						function(data, status){
							alert(data);
				});
			}
		</script>
	</body>
</html>