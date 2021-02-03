package com.anand.library.subscription.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anand.library.subscription.entity.SubcriberBookIdPk;
import com.anand.library.subscription.entity.Subscription;
import com.anand.library.subscription.service.SubscriptionService;

@RestController
public class SubscriptionController {
	
	@Autowired
	private SubscriptionService subscriberService;
	
	@GetMapping(path = "subscriptions")
	public List<Subscription> getSubscriptions(){
		return subscriberService.getSubscriptions();
	}
	
	@PostMapping(path = "subscription")
	public Subscription postSubscription(@RequestBody SubcriberBookIdPk subcriberBookIdPk){
		return subscriberService.postSubscription(subcriberBookIdPk);
	}
	
	@PostMapping(path = "return")
	public Subscription postReturn(@RequestBody SubcriberBookIdPk subcriberBookIdPk){
		return subscriberService.postReturn(subcriberBookIdPk);
	}

}
