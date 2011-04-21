package com.talis.labs.jersey;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class MessageProvider implements ContextResolver<Message> {

	private static final Logger logger = LoggerFactory.getLogger(MessageProvider.class);
	
	@Override
	public Message getContext(Class<?> type) {
		logger.info("getContext method called.");
		
		return new Message();
	}

}
