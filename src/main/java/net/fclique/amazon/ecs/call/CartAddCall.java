package net.fclique.amazon.ecs.call;

import java.util.List;

import javax.xml.ws.Holder;

import com.ECS.client.jax.Cart;
import com.ECS.client.jax.CartAdd;
import com.ECS.client.jax.CartAddRequest;
import com.ECS.client.jax.OperationRequest;

import net.fclique.amazon.ecs.ProductAdvertisingAPI;

public class CartAddCall extends CartCall<CartAdd, CartAddRequest> {

	public CartAddCall(ProductAdvertisingAPI api) {
		super(api, CartAdd.class, CartAddRequest.class);
	}

	@Override
	protected void call(CartAdd call,
			Holder<OperationRequest> operationRequest, Holder<List<Cart>> result) {

		api.getPort().cartAdd(call.getMarketplaceDomain(),
				call.getAWSAccessKeyId(), call.getAssociateTag(),
				call.getXMLEscaping(), call.getValidate(), call.getShared(),
				call.getRequest(), operationRequest, result);
	}

}
