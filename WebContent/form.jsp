<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Snippet - Bootsnipp.com</title>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet">
<link href="/css/style-form.css" rel="stylesheet">
</head>
<body style="">

	<div class="container">

		<form role="form" style="width: 400px; margin: 0 auto;">
			<h1>Contact us</h1>

			<div class="required-field-block">
				<input type="text" placeholder="Name" class="form-control">
				<div class="required-icon" data-original-title="" title="">
					<div class="text">*</div>
				</div>
			</div>

			<div class="required-field-block">
				<input type="text" placeholder="Email" class="form-control">
				<div class="required-icon" data-original-title="" title="">
					<div class="text">*</div>
				</div>
			</div>

			<input type="text" placeholder="Phone" class="form-control">

			<div class="required-field-block">
				<textarea rows="3" class="form-control" placeholder="Message"></textarea>
				<div class="required-icon" data-original-title="" title="">
					<div class="text">*</div>
				</div>
			</div>

			<button class="btn btn-primary">Send</button>
		</form>
	</div>

	<script type="text/javascript" src="//code.jquery.com/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$('.required-icon').tooltip({
				placement : 'left',
				title : 'Required field'
			});
		});
	</script>

</body>
</html>