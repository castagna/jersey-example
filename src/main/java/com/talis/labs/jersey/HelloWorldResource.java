/*
 * Copyright © 2011 Talis Systems Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.talis.labs.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Providers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/hello")
public class HelloWorldResource {

	private static final Logger logger = LoggerFactory.getLogger(HelloWorldResource.class);

	@Context 
	private Providers providers;
	
	@GET 
	@Produces(MediaType.TEXT_PLAIN)
	public Response doGetPlain() {
		logger.info("GET (text/plain)");
		
		ContextResolver<Message> cr = providers.getContextResolver(Message.class, MediaType.WILDCARD_TYPE);
		Message message = cr.getContext(Message.class);
		
		return Response.status(Response.Status.OK).entity(message.getMessage() + "\n").build();
	}
	
	@GET 
	@Produces(MediaType.APPLICATION_JSON)
	public Response doGetJson() {
		logger.info("GET (application/json)");
		return Response.status(Response.Status.OK).entity("{ message: 'Hello World!' }\n").build();
	}
	
}
