$(document)
	.ready(function() {
		$('.category-info').transition('fade');

		$('.dropdown').dropdown({
			on: 'hover',
    		transition: 'scale'
    	});
	})
;