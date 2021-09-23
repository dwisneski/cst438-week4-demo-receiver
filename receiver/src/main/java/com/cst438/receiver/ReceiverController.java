package com.cst438.receiver;

import java.util.Date;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiverController {

	@PostMapping("/message")
	public MessageDTO httpReceive(@RequestBody MessageDTO msg) {
		System.out.println("Received http message: "+msg);
		msg.date_time= new Date();
		msg.msg_no = msg.msg_no+1;
		msg.text="Thanks for the message!";
		return msg;
	}
	
	@RabbitListener(queues = "example_queue")
	@Transactional
	public void receive(MessageDTO msg ) {
		System.out.println("Received Rabbit message: "+msg);
	}
}
