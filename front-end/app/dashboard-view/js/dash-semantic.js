$(document).ready(function() {

	function animation() {
		$('.dropdown').dropdown({
			on: 'hover',
			transition: 'scale'
		});

		//$( ".ui.checkbox" ).checkbox();
	}

	animation();

	function categoryAnimation() {
		$('.category-info').transition('fade');
	}

	setTimeout(categoryAnimation, 1000);

});