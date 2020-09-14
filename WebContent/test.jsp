<!DOCTYPE html>
<html>
	<head>
		<title>Test page</title>
		<script type="text/javascript" src="js/jquery.min.js">
		</script>
	</head>
	<body>
		<audio id="buzzer" src="bismillah.wav"></audio>
		<input type="button" value=" Play " onclick="javascript:playAudio()">
		<script type="text/javascript" >
			function playAudio() {
				$('#buzzer').get(0).play();
			}
		</script>
	</body>
</html>
