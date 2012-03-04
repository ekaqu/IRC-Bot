$(document).ready(function(){
      $('#textarea').load('content.txt');

      $('#chatform').submit(function(event) {
         /* stop form from submitting normally */
        event.preventDefault();

        /* get some values from elements on the page: */
        var $form = $( this ),
        term1 = $form.find( 'input[name="name"]' ).val(),
        term2 = $form.find( 'input[name="content"]' ).val(),
        url = $form.attr( 'action' );

        /*var s = term2.split(" ");
        var s2 = "";

        for(i = 1; i < s.length; i++){
          s2 += " " +  s[i];
        }*/

        var data = {
          name: term1,
          say: term2
        };

        var request = JSON.stringify(data);
        /*var name = JSON.stringify(s[0]);
        var msg = JSON.stringify(s2);
         */
        $("#chat-msg").append(data);


        /* Send the data using post and put the results in a div */
        $.post( url, data,
          function(data) {
					            alert(data);
            var obj = jQuery.parseJSON(data);
            var msg = obj.name + ": " + obj.say + "\n";
            $("#chat-msg").append(msg);
				});

      });


    });