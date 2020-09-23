$(function() {
	var url = '/O2O/frontend/listmainpageinfo';
	$.getJSON(url, function(data) {
		if (data.success) {
			var shopCategoryList = data.shopCategoryList;
			var categoryHtml = '';
			// construct first-level shop category
			shopCategoryList.map(function(item, index) {
				categoryHtml += ''
						+ '<div class="col-50 shop-classify" data-category='
						+ item.shopCategoryId + '>' + '<div class="word">'
						+ '<p class="shop-title">' + item.shopCategoryName
						+ '</p>' + '<p class="shop-desc">'
						+ item.shopCategoryDesc + '</p>' + '</div>'
						+ '<div class="shop-classify-img-warp">'
						+ '<img class="shop-img" src="' + item.shopCategoryImg
						+ '">' + '</div>' + '</div>';
			});

			$('.row').html(categoryHtml);
		}
	});

	// sidebar
	$('#me').click(function() {
		$.openPanel('#panel-right-demo');
	});

	$('.row').on('click', '.shop-classify', function(e) {
		var shopCategoryId = e.currentTarget.dataset.category;
		var newUrl = '/O2O/frontend/shoplist?parentId=' + shopCategoryId;
		window.location.href = newUrl;
	});

});
