package net.fclique.amazon.ecs.call;

import java.util.List;

import javax.xml.ws.Holder;

import com.ECS.client.jax.ItemSearch;
import com.ECS.client.jax.ItemSearchRequest;
import com.ECS.client.jax.Items;
import com.ECS.client.jax.OperationRequest;

import net.fclique.amazon.ecs.ProductAdvertisingAPI;

public class ItemSeachCall extends ItemCall<ItemSearch, ItemSearchRequest> {

	public ItemSeachCall(ProductAdvertisingAPI api) {
		super(api, ItemSearch.class);
	}

	@Override
	protected void call(ItemSearch call,
			Holder<OperationRequest> operationRequest,
			Holder<List<Items>> result) {

		api.getPort().itemSearch(call.getMarketplaceDomain(),
				call.getAWSAccessKeyId(), call.getAssociateTag(),
				call.getXMLEscaping(), call.getValidate(), call.getShared(),
				call.getRequest(), operationRequest, result);
	}

}
