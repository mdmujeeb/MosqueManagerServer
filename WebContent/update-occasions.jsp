<html>
	<head>
		<title>MosqueManager - Update Occasions.</title>
		<script  type="text/javascript" src="js/jquery.min.js"></script>
	</head>
	<body bgcolor="black" text="white">
		<center>
		
		<%@include file="include.jsp"%>
		
		<h3><font face="Arial">Update Occasions.<font></h3>
		<br>
		
		<form method="POST" action="addOccasion">
			<table border="1" cellpadding="0" cellspacing="0">
				<tr>
					<th>&nbsp;Date&nbsp;</th>
					<th>&nbsp;Description&nbsp;</th>
					<th></th>
				</tr>
			</table>
			<br/><br/>
			Select Date: <select id="date" name="date">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
					<option value="13">13</option>
					<option value="14">14</option>
					<option value="15">15</option>
					<option value="16">16</option>
					<option value="17">17</option>
					<option value="18">18</option>
					<option value="19">19</option>
					<option value="20">20</option>
					<option value="21">21</option>
					<option value="22">22</option>
					<option value="23">23</option>
					<option value="24">24</option>
					<option value="25">25</option>
					<option value="26">26</option>
					<option value="27">27</option>
					<option value="28">28</option>
					<option value="29">29</option>
					<option value="30">30</option>
					<option value="31">31</option>
			</select>&nbsp;&nbsp;
			<select id="month" name="month">
					<option value="1">January</option>
					<option value="2">February</option>
					<option value="3">March</option>
					<option value="4">April</option>
					<option value="5">May</option>
					<option value="6">June</option>
					<option value="7">July</option>
					<option value="8">August</option>
					<option value="9">September</option>
					<option value="10">October</option>
					<option value="11">November</option>
					<option value="12">December</option>
			</select>
			<br/><br/>
			Enter Description: <input type="text" id="description" name="description" size="40">
			<br/><br/><br>
			<input type="button" value=" Add " onclick="addOccasion()">
		</form>
		<br/>
		<a href="update.jsp">Back</a>

		<script  type="text/javascript">
			getOccasions();

			function getOccasions() {

				$.get("getOccasions", { id: <%=session.getAttribute("MASJID_ID")%> },
						function(data, status){
							for (var index in data) {
								var occasion = data[index];
								//var markup = "<tr><td>&nbsp;" + occasion.date + "&nbsp;</td><td>&nbsp;" + occasion.description +"&nbsp;</td><td>&nbsp;<a href='deleteOccasion?index=" + occasion.id +  "'>Delete</a>&nbsp;</td></tr>";
								var markup = "<tr><td>&nbsp;" + occasion.date + "&nbsp;</td><td>&nbsp;" + occasion.description +"&nbsp;</td><td>&nbsp;<a href='javascript:deleteOccasion(" + occasion.id +  ")'>Delete</a>&nbsp;</td></tr>";
            					$("table tbody").append(markup);
							}
				});
			}
			
			function addOccasion() {

				$.post("addOccasion", { date: $("#date").val(), month: $("#month").val(), description: $("#description").val() },
						function(data, status){
							alert(data);
							location.reload();
				});
			}
			
			function deleteOccasion(id) {

				$.get("deleteOccasion", { index: id },
						function(data, status){
							alert(data);
							location.reload();
				});
			}
		</script>
	</body>
</html>