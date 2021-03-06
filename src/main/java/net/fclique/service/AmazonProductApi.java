/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.fclique.service;

import com.ECS.client.jax.ItemSearchRequest;
import com.ECS.client.jax.Items;
import java.io.IOException;
import net.fclique.amazon.ecs.AbstractAmazonApi;
import net.fclique.amazon.ecs.exception.RequestException;
import org.springframework.stereotype.Service;

/**
 *
 * @author devesh.bharathan
 */
@Service
public class AmazonProductApi extends AbstractAmazonApi{
    public AmazonProductApi() throws IOException {
		super();
	}
    
    public void testItemSearch() throws RequestException  {
    		ItemSearchRequest itemSearchRequest = new ItemSearchRequest();
		itemSearchRequest.setSearchIndex("Books");
		itemSearchRequest.setKeywords("Star Wars");
                itemSearchRequest.setAvailability(null);
		Items items = api.getItemSearch().call(itemSearchRequest);
                itemSearchRequest.getResponseGroup();
    }
}
