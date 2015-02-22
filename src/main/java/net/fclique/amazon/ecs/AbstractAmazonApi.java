/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.fclique.amazon.ecs;

import com.ECS.client.jax.AWSECommerceService;
import java.io.IOException;
import java.util.Properties;
import net.fclique.amazon.ecs.configuration.PropertiesConfiguration;

/**
 *
 * @author devesh.bharathan
 */
abstract public class AbstractAmazonApi {
    
    	protected PropertiesConfiguration configuration;

	protected ProductAdvertisingAPI api;

	public AbstractAmazonApi() throws IOException {
		Properties properties = new Properties();
		properties.load(getClass().getResourceAsStream("/amazon.properties"));
		configuration = new PropertiesConfiguration(properties);

		api = new ProductAdvertisingAPI(configuration,
				new AWSECommerceService().getAWSECommerceServicePortIN());
	}
    
}
