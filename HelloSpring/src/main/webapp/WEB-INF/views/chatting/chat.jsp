<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅창</title>

<script src="${pageContext.request.contextPath }/resources/js/jquery-3.6.1.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/chatting.js"></script>
</head>
<body>
	<h3>spring chatting</h3>
	<div id="chattingcontainer"></div>
	<input id="msg"><button id="sendBtn">전송</button>
	
	<script>
	   const websocket = new WebSocket("ws://localhost:8080/spring/chatting");
	      
	      websocket.onopen=(data)=>{
	         console.log(data);
	         websocket.send(JSON.stringify(new Message("open",'${loginMember.userId}',"","","","")));
	         //여기서 보내면 cahttingServer 의 handleTextMessage 얘가 받아서 처리         
	         
	         
	      }
	      websocket.onmessage=(response)=>{
	    	  console.log(response)
	    	  const msg=JSON.parse(response.data);
	    	  console.log(msg);
	    	  switch(msg.type){
	    	  	case "system" : addMsgSystem(msg);break;
	    	  	case "msg" : printMsg('${loginMember.userId}',msg);break;
	    	  	
	    	  }
	      }
	      
	      $("#sendBtn").click(e=>{
	    	  const msg=$("#msg").val();
	    	  const sendData=new Message("msg",'${loginMember.userId}',"",msg,""); //swtich문에서 파싱하넉 보고 해야함
	    	  websocket.send(JSON.stringify(sendData));
	      })
	      
	      class Message{
	    	  constructor(type,sender,receiver,msg,room){
	    		  this.type=type;
	    		  this.sender=sender;
	    		  this.receiver=receiver;
	    		  this.msg=msg;
	    		  this.room=room;
	    	  }
	      }
		
	</script>
	
	
</body>
</html>