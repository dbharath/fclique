package net.fclique.amazon.ecs.call;

import java.util.List;

import javax.xml.ws.Holder;

import com.ECS.client.jax.ItemLookup;
import com.ECS.client.jax.ItemLookupRequest;
import com.ECS.client.jax.Items;
import com.ECS.client.jax.OperationRequest;

import net.fclique.amazon.ecs.ProductAdvertisingAPI;

public class ItemLookupCall extends ItemCall<ItemLookup, ItemLookupRequest> {

	public ItemLookupCall(ProductAdvertisingAPI api) {
		super(api, ItemLookup.class);
	}

	@Override
	protected void call(ItemLookup call,
			Holder<OperationRequest> operationRequest,
			Holder<List<Items>> result) {

		api.getPort().itemLookup(call.getMarketplaceDomain(),
				call.getAWSAccessKeyId(), call.getAssociateTag(),
				call.getXMLEscaping(), call.getValidate(), call.getShared(),
				call.getRequest(), operationRequest, result);
	}

}
