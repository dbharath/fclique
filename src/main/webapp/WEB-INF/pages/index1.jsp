<html>
<head>
    <script src='scripts/jquery-1.6.1.min.js'></script>
    <script src="scripts/utils.js"></script>
    <script src="scripts/adv_search.js"></script>
	<link rel="stylesheet" href="styles/style.css" type="text/css" />
</head>
<body>
<div id="wrapper"> 

	<div id="header">
		<div id="mm_searchdiv">
			<div>
				<iframe src='http://www.flipkart.com/affiliate/displayWidget?affrid=WRID-140067812574856952' height=55 width=660 scrolling='no' frameborder=0></iframe>
			</div>
			<div id="searchdiv">				
				<div id="searchright">             
					<div id="allcategory">
					  <select id="search-select" class="search-select">
						<option value="000">All</option>
						<option value="001">Flipkart</option>
						<option value="002">Amazon</option>
						<option value="003">Ebay</option>
						<option value="008">Shopping India Times</option>
						<option value="005">Tradus</option>
						<option value="004">HomeShop18</option>
						<option value="009">Snapdeal</option>
						<option value="006">Infobeam</option>
						<option value="007">Naaptol</option>						
					  </select>					
					</div>
					<div id="searchbar">
						<input type="text" class="searchfield ui-autocomplete-input" name="searchText" id="search-box" placeholder="Search for a product" autocomplete="off" role="textbox" aria-autocomplete="list" aria-haspopup="true">
					</div>
					<div id="searchbutton">
						<input name="" type="button" value="SEARCH" id="search-button"/>
					</div>
				</div>        
			</div>
		</div>
	</div>
	<div  id="middlecontainer">
		<div class="prods-cnt" id="search-products-container">
			<div id="list" class="list "></div>
			<div id="grid" class="grid"></div>
			<div class="clear"></div>
		</div>			
	</div>		
</div>
 </body>
 </html>