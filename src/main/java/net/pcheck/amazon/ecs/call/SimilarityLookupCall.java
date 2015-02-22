package net.pcheck.amazon.ecs.call;

import java.util.List;

import javax.xml.ws.Holder;

import com.ECS.client.jax.Items;
import com.ECS.client.jax.OperationRequest;
import com.ECS.client.jax.SimilarityLookup;
import com.ECS.client.jax.SimilarityLookupRequest;

import net.pcheck.amazon.ecs.ProductAdvertisingAPI;

public class SimilarityLookupCall extends
		ItemCall<SimilarityLookup, SimilarityLookupRequest> {

	public SimilarityLookupCall(ProductAdvertisingAPI api) {
		super(api, SimilarityLookup.class);
	}

	@Override
	protected void call(SimilarityLookup call,
			Holder<OperationRequest> operationRequest,
			Holder<List<Items>> result) {

		api.getPort().similarityLookup(call.getMarketplaceDomain(),
				call.getAWSAccessKeyId(), call.getAssociateTag(),
				call.getXMLEscaping(), call.getValidate(), call.getShared(),
				call.getRequest(), operationRequest, result);
	}

}
