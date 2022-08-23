<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Altas Autom�ticas</title>
<!-- CSS for Bootstrap -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<!-- JQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


<script type="text/javascript">
	$(function() {
		$("#b1").click(
				function(e) {
					e.preventDefault();
					//Boton enviar
					$(this).prop('disabled', true);

					var form = document.forms[0];
					var formData = new FormData(form);

					var ajaxReq = $.ajax({
						url : '/uploadfile1',
						type : 'POST',
						data : formData,
						cache : false,
						contentType : false,
						processData : false,
						xhr : function() {

							var xhr = $.ajaxSettings.xhr();

							xhr.upload.onprogress = function(event) {

							};
							return xhr;
						},
						beforeSend : function(xhr) {

							$('#alertMsg').text('');

						}
					});

					// Mensaje exitoso de archivo subido
					ajaxReq.done(function(msg) {
						$('#alertMsg').text(msg);

					});

					// Mensaje fallido de archivo subido
					ajaxReq.fail(function(jqXHR) {
						$('#alertMsg').text(
								jqXHR.responseText + '(' + jqXHR.status + ' - '
										+ jqXHR.statusText + ')');

					});
				});
	});

	function sentData() {

		var data = $("#order").val();

		location.href = "/download?order=" + data;
	}
</script>





<script type="text/javascript">
	$(function() {
		$("#b2").click(
				function(e) {
					e.preventDefault();
					//Boton enviar
					$(this).prop('disabled', true);

					var form = document.forms[1];
					var formData = new FormData(form);

					// Mensaje exitoso de archivo subido
					var ajaxReq = $.ajax({
						url : '/uploadfile2',
						type : 'POST',
						data : formData,
						cache : false,
						contentType : false,
						processData : false,
						xhr : function() {
							//Get XmlHttpRequest object
							var xhr = $.ajaxSettings.xhr();
							//Set controller events
							xhr.upload.onprogress = function(event) {

							};
							return xhr;
						},
						beforeSend : function(xhr) {
							//Mensaje de alerta
							$('#alertMsg1').text('');

						}
					});

					// Mensaje exitoso de archivo subido
					ajaxReq.done(function(msg) {
						$('#alertMsg1').text(msg);

					});

					// Mensaje fallido de archivo subido
					ajaxReq.fail(function(jqXHR) {
						$('#alertMsg1').text(
								jqXHR.responseText + '(' + jqXHR.status + ' - '
										+ jqXHR.statusText + ')');

					});
				});
	});
</script>






</head>
<body>
	<div class="container">
		<h2>Altas Autom�ticas - Soporte y An�lisis</h2>
		<hr>




		<form action="/uploadfile1" method="post"
			enctype="multipart/form-data">
			<div class="form-group">
				<label>ALTAS OSDE</label> <input type="file" name="file">
			</div>
			<div class="form-group">
				<button class="btn btn-primary" type="submit" id="b1">Cargar
					archivo</button>
				<div id="alertMsg" style="color: black; font-size: 18px;"></div>
			</div>
		</form>
		<br />



		<form action="/uploadfile2" method="post"
			enctype="multipart/form-data">
			<div class="form-group">
				<label>ALTAS PRUEBA (futura)</label> <input type="file" name="file">
			</div>
			<div class="form-group">
				<button class="btn btn-primary" type="submit" id="b2">Cargar
					archivo</button>

				<div id="alertMsg1" style="color: black; font-size: 18px;"></div>

			</div>
		</form>

		<!--  		  
		<label>Set Order</label>
		<div>
			<textarea rows="5" cols="50" id="order"></textarea>
		</div>
	-->
		<br> <br> <br> <a href="javascript:sentData()">Descargar archivo</button>
	</div>
</body>
</html>