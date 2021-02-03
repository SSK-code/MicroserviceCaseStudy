package com.anand.library.subscription.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anand.library.subscription.entity.SubcriberBookIdPk;
import com.anand.library.subscription.entity.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, SubcriberBookIdPk> {

}
