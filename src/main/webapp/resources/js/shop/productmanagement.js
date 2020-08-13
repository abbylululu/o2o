$(function() {
	// get product list URL
	var listUrl = '/O2O/shopadmin/getproductlistbyshop?pageIndex=1&pageSize=999';
	// make product offline URL
	var statusUrl = '/O2O/shopadmin/modifyproduct';
	getList();
	/**
	 * get the product list
	 * 
	 * @returns
	 */
	function getList() {
		$.getJSON(listUrl, function(data) {
			if (data.success) {
				var productList = data.productList;
				var tempHtml = '';
				// loop over product name, priority, online/offline, edit button and preview button
				// concatenate all infos into one line
				productList.map(function(item, index) {
					var textOp = "Off Line";
					var contraryStatus = 0;
					if (item.enableStatus == 0) {
						textOp = "On Line";
						contraryStatus = 1;
					} else {
						contraryStatus = 0;
					}
					// get every product's line
					tempHtml += '' + '<div class="row row-product">'
							+ '<div class="col-33">'
							+ item.productName
							+ '</div>'
							+ '<div class="col-20">'
							+ item.point
							+ '</div>'
							+ '<div class="col-40">'
							+ '<a href="#" class="edit" data-id="'
							+ item.productId
							+ '" data-status="'
							+ item.enableStatus
							+ '">Edit</a>'
							+ '<a href="#" class="status" data-id="'
							+ item.productId
							+ '" data-status="'
							+ contraryStatus
							+ '">'
							+ textOp
							+ '</a>'
							+ '<a href="#" class="preview" data-id="'
							+ item.productId
							+ '" data-status="'
							+ item.enableStatus
							+ '">Preview</a>'
							+ '</div>'
							+ '</div>';
				});
				$('.product-wrap').html(tempHtml);
			}
		});
	}
	// bind event to a tag of product-wrap class
	$('.product-wrap')
			.on(
					'click',
					'a',
					function(e) {
						var target = $(e.currentTarget);
						if (target.hasClass('edit')) {
							window.location.href = '/O2O/shopadmin/productoperation?productId='
									+ e.currentTarget.dataset.id;
						} else if (target.hasClass('status')) {
							changeItemStatus(e.currentTarget.dataset.id,
									e.currentTarget.dataset.status);
						} else if (target.hasClass('preview')) {
							window.location.href = '/O2O/frontend/productdetail?productId='
									+ e.currentTarget.dataset.id;
						}
					});
	function changeItemStatus(id, enableStatus) {
		var product = {};
		product.productId = id;
		product.enableStatus = enableStatus;
		$.confirm('Confirm change?', function() {
			$.ajax({
				url : statusUrl,
				type : 'POST',
				data : {
					productStr : JSON.stringify(product),
					statusChange : true
				},
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$.toast('Success！');
						getList(); // refresh product list
					} else {
						$.toast('Change failed！');
					}
				}
			});
		});
	}
});