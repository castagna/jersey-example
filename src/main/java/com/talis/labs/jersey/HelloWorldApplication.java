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

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldApplication extends Application {

	private static final Logger logger = LoggerFactory.getLogger(HelloWorldApplication.class);
 	
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> classes = new HashSet<Class<?>>();
	
	public HelloWorldApplication() {
		logger.info("Constructing a new Sparql11HttpRdfUpdateApplication...");

		classes.add(RootResource.class);
		classes.add(HelloWorldResource.class);
		
		MessageProvider messageProvider = new MessageProvider();
		singletons.add(messageProvider);
	}
	
	@Override
	public Set<Class<?>> getClasses() {
		logger.info("HelloWorldApplication classes are {}", classes);

		return classes;
	}
	
	@Override
    public Set<Object> getSingletons() {
		logger.info("HelloWorldApplication singletons are {}", classes);

		return singletons;
    }

}
