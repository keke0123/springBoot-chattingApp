var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/handler');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        //console.log("connectBtn clicked");
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/all', function (greeting) {
        	//console.log("greeting : "+greeting);        	
            showGreeting(JSON.parse(greeting.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
	//console.log("sendMsg!!! : "+$("#message").val());
    stompClient.send("/app/all", {}, JSON.stringify({
    	'name': $("#name").val(),
    	'message':$("#message").val(),
    	'isMe':false
    }));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>id : "+message.name+"<br/>message : " + message.message + "</td></tr>");
}

$(function () {
	console.log("submitBtn clicked");
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});