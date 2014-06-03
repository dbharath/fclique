(function(window, $) {
  var AdvanceSearchWindow = function() {  
    // send message to background
    this.sendMessage = function(action, data) {
      if (typeof data == 'undefined') data = {};
      this._port.postMessage({action: action, data: data});
    };

   
  };

  AdvanceSearchWindow.prototype = {    
    _renderProduct: function(product) {
		var siteNameKey = product.siteName;
		if(siteNameKey == 'FLIPKART'){
			siteName = "Flipkart";
		}
		if(siteNameKey == 'AMAZON'){
			siteName = "Amazon";
		}
		if(siteNameKey == 'SHOPPINGINDIATIMES'){
			siteName = "Shopping India Times";
		}
		if(siteNameKey == 'TRADUS'){
			siteName = "Tradus";	
		}
		if(siteNameKey == 'SNAPDEAL'){
			siteName = "Snapdeal";			
		}
		if(siteNameKey == 'EBAY'){
			siteName = "Ebay";			
		}
		if(siteNameKey == 'HOMESHOP18'){
			siteName = "HomeShop18.com";	
		}
		if(siteNameKey == 'INFOBEAM'){
			siteName = "Infibeam";	
		}if(siteNameKey == 'NAAPTOL'){
			siteName = "Naaptol";	
		}		
	
		var productHolder = '<div class="product-grid fade">';							
			productHolder += '<div class="product-pic"><a href="'+product.productLink+'" target="_blank"><img src="'+product.imageUrl+'" title="product-name" /></a>';
			productHolder += '<p><a href="#">'+product.name+'</a></p></div>';
			productHolder += '<div class="product-info">';
			productHolder += '<div class="product-info-cust"><a href="'+product.productLink+'" target="_blank">'+siteName+'</a></div>';
			productHolder += '<div class="product-info-price"><a href="'+product.productLink+'" target="_blank">Rs. '+product.price+'</a>';
			productHolder += '</div><div class="clear"> </div></div></div>';

	    return productHolder;
    },
	_addAjaxLoader :function (){		
		if(jQuery(".pleaseWait").length==0){jQuery("body").append("<div class='pleaseWait'></div>");}
			jQuery(".pleaseWait").show();
	},	
	_removeAjaxLoader: function (){
		if(jQuery(".pleaseWait").length>0)jQuery(".pleaseWait").hide();
	},
    _searchProducts: function(){
		var $AdvanceSearchWindow = this;    
		var search_value = $("#search-box").val();
		var lkgrURL = "";
		
		var search_select = "000";//$("#search-select").val();
		if(search_select === "000"){
			lkgrURL = 'product/search?q='+search_value+'&page=1';
		}else {
			lkgrURL = 'getPriceBySearch1?q='+search_value+'&siteCode='+search_select+'&maxProductToFetch=50';
		}
		$('#search-products-container').empty();
		$AdvanceSearchWindow._addAjaxLoader();
		fetchRecords(lkgrURL, function(response) {
			$AdvanceSearchWindow._removeAjaxLoader();			
			$AdvanceSearchWindow._renderProductsList(response, search_select);
		});
		
    },
	
    initEvents: function() {
      var $AdvanceSearchWindow = this;   
      $('#search-box').keydown(function(event) {
        if (event.keyCode == 13) {
            $AdvanceSearchWindow._searchProducts();
         }
      });   
	  $("#search-button").click(function(){			
			$AdvanceSearchWindow._searchProducts();
	  });	  
    },

    _renderProductsList: function(data, siteCode) {
		$AdvanceSearchWindow = this;		
		var noOfProducts = 20;
		if(siteCode == "000"){
			noOfProducts = 1
		}
		$('#search-products-container').empty();
		jQuery.each(data, function (key, value) {
			var siteName = "";
			var siteNameKey = value.siteName;
			var products = value.products;
			if(siteNameKey == 'FlipkartResponse'){
				siteName = "Flipkart";
			}
			if(siteNameKey == 'AmazonResponse'){
				siteName = "Amazon";
			}
			if(siteNameKey == 'ShoppingIndiaTimesResponse'){
				siteName = "Shopping India Times";
			}
			if(siteNameKey == 'TradusResponse'){
				siteName = "Tradus";	
			}
			if(siteNameKey == 'SnapdealResponse'){
				siteName = "Snapdeal";			
			}
			if(siteNameKey == 'EbayResponse'){
				siteName = "Ebay";			
			}
			if(siteNameKey == 'HomeShop18Response'){
				siteName = "HomeShop18.com";	
			}
			if(siteNameKey == 'InfibeamResponse'){
				siteName = "Infibeam";	
			}if(siteNameKey == 'NaaptolResponse'){
				siteName = "Naaptol";	
			}		
			if(products != null && products.length > 0){
				//var product = products[0];
				var count = 0;
				jQuery.each(products, function (key, product) {
					if(count>noOfProducts){
						
					}else {
						var productHolder = $AdvanceSearchWindow._renderProduct(product);
						$('#search-products-container').append(productHolder);	
					}
					count++;					 
				});
					
			}
		});
		$('#search-products-container').append('<div class="clear"> </div>');	
    }
  };

  $(function() {
    var Opened = new AdvanceSearchWindow();    
		Opened.initEvents();    
  });
})(window, jQuery);