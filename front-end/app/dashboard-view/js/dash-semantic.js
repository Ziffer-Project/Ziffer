$(document).ready(function() {

	function animation() {
		$('.category-info').transition('fade');

		$('.dropdown').dropdown({
			on: 'hover',
			transition: 'scale'
		});
	}

	setTimeout(animation, 300);

});