package net.pcheck.amazon.ecs.call;

import com.ECS.client.jax.Cart;

import net.pcheck.amazon.ecs.InterfaceDecorator;
import net.pcheck.amazon.ecs.ProductAdvertisingAPI;

public abstract class CartCall<CallType, RequestType> extends
		ApiCall<CallType, RequestType, Cart> {

	private Class<RequestType> requestClass;

	public CartCall(ProductAdvertisingAPI api, Class<CallType> callClass,
			Class<RequestType> requestClass) {
		super(api, callClass);

		this.requestClass = requestClass;
	}

	public RequestType buildRequest(Cart cart) {
		try {
			RequestType request = requestClass.newInstance();
			CartRequest cartRequest = InterfaceDecorator.getProxy(request,
					CartRequest.class);
			cartRequest.setHMAC(cart.getHMAC());
			cartRequest.setCartId(cart.getCartId());
			return request;

		} catch (InstantiationException e) {
			throw new RuntimeException(e);

		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);

		}
	}

}
