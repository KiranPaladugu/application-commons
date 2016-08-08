package com.tcs.application.resolver;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractResolver {
	
	abstract public String getResolverNamespace();

	abstract public Object resolve(String uri);

	protected Object parse(String uri) {
		String[] tokens = uri.split("\\:");
		String resourceName = tokens[tokens.length - 1];
		String nameSpcace = "";
		List<String> resolvers = new LinkedList<>();
		for (int i = tokens.length - 2; i >= 0; i--) {
			String token = tokens[i];
			if (i == tokens.length - 2)
				nameSpcace = token;
			else
				nameSpcace = token + ":" + nameSpcace;
			resolvers.add(token);
		}
		Collections.reverse(resolvers);
		System.out.println("Resolvers are : " + resolvers);
		System.out.println("Namespace is     | " + nameSpcace);
		System.out.println("Resource name is | " + resourceName);
		return resourceName;
	}

}
