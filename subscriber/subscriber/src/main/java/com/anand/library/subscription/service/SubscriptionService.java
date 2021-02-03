package com.anand.library.subscription.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.anand.library.subscription.entity.SubcriberBookIdPk;
import com.anand.library.subscription.entity.Subscription;
import com.anand.library.subscription.repository.SubscriptionRepository;

@Service
public class SubscriptionService {

	@Autowired
	SubscriptionRepository subscriberRepository;
	
	public Subscription postSubscription(SubcriberBookIdPk subcriberBookIdPk) {
		Optional<Subscription> existingSubscription = subscriberRepository
				.findById(subcriberBookIdPk);
		if (existingSubscription.isPresent() && existingSubscription.get().getDateReturned() == null)
			throw new ResourceNotFoundException(existingSubscription.get().getSubcriberBookIdPk().getSubscriberName()
					+ " has already subscription for " + existingSubscription.get().getSubcriberBookIdPk().getBookId());
		Subscription subscription = new Subscription();
		subscription.setDateSubscribed(LocalDate.now());
		subscription.setDateReturned(null);
		subscription.setSubcriberBookIdPk(subcriberBookIdPk);
		return subscriberRepository.save(subscription);
	}
	
	public Subscription postReturn(SubcriberBookIdPk subcriberBookIdPk) {
		Optional<Subscription> existingSubscription = subscriberRepository
				.findById(subcriberBookIdPk);
		if (!existingSubscription.isPresent() || existingSubscription.get().getDateReturned() != null)
			throw new ResourceNotFoundException(subcriberBookIdPk.getSubscriberName()
					+ " has no subscription for " + subcriberBookIdPk.getBookId());
		existingSubscription.get().setDateReturned(LocalDate.now());
		return subscriberRepository.save(existingSubscription.get());
	}
	
	public List<Subscription> getSubscriptions(){
		return subscriberRepository.findAll();
	}
}
