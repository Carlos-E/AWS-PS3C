$(document).on('click', 'ul.nav li.parent > a.nav-link', function() {
	$(this).find('span').html('<i class="fa fa-caret-up"></i>');
});

$(document).on('click', 'ul.nav li.parent > a.nav-link[aria-expanded*="true"]', function() {
	$(this).find('span').html('<i class="fa fa-caret-down"></i>');
});

$("#menu-toggle").click(function(e) {
	e.preventDefault();
	$("#wrapper").toggleClass("toggled");
});

$(document).on("click", ".close", function() {
	$(this).parent().hide();
});
