package com.tcs.application.resolver;

import com.tcs.application.Subscriber;
import com.tcs.application.SubscriptionEvent;

public class DepedencyResolver extends AbstractResolver implements Subscriber {

	public String getResolverNamespace() {
		return "dependency";
	}
	
	public Object resolve(String uri) {
		parse(uri);
		return null;
	}

	public static void main(String args[]) {
		new DepedencyResolver().resolve("com:res:global://resource/Resourcename");
	}

	@Override
	public void onSubscriptionEvent(SubscriptionEvent event) throws Exception {
	}

}
