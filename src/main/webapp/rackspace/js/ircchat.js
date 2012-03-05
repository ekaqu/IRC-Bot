/*
 * #%L
 * irc-bot
 * %%
 * Copyright (C) 2012 WAMAD
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
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
