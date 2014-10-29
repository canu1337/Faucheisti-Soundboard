$(".sound").click(function() {
	var file = $(this).attr('id') + '.ogg';
	// C'est très très sale
    var dir = $('#dir').text();
//	var audio = new Audio('sounds/' + dir + '/' +  file);
    AndAud.playAudio('sounds/' + dir + '/' +  file);
//  audio.play();
});