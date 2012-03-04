$(document).ready( function() {
  $('#textarea').load('content.txt');
  $('#chatform').submit( function(event) {
    /* stop form from submitting normally */
    event.preventDefault();

    /* get some values from elements on the page: */
    var $form = $( this ),
      term1 = $form.find( 'input[name="name"]' ).val(),
      term2 = $form.find( 'input[name="content"]' ).val(),
      url = $form.attr( 'action' );

    $("#chat-msg").append(term1 + ": " + term2 + "\n");

    /* Send the data using post and put the results in a div */
    $.post( url, {name: term1, say: term2}, function(data) {
      var obj = jQuery.parseJSON(data);
      for (var i in obj) {
        var chat = obj[i],
          nick = chat.user.nick,
          message = chat.message;
        var msg = nick + ": " + message + "\n";
        $("#chat-msg").append(msg);
      }
    });
  });
});
