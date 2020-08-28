$(function() {
	var loading = false;
	// maximum item count for pagination
	var maxItems = 999;
	// maximum item per page
	var pageSize = 3;
	
	var listUrl = '/O2O/frontend/listshops';
	var searchDivUrl = '/O2O/frontend/listshopspageinfo';

	var pageNum = 1;
	var parentId = getQueryString('parentId');
	var selectedParent = false;
	if (parentId){
		selectedParent = true;
	}
	var areaId = '';
	var shopCategoryId = '';
	var shopName = '';

	getSearchDivData();
	addItems(pageSize, pageNum);
	/**
	 * get shop category list and area list
	 * 
	 * @returns
	 */
	function getSearchDivData() {
		var url = searchDivUrl + '?' + 'parentId=' + parentId;
		$
				.getJSON(
						url,
						function(data) {
							if (data.success) {
								var shopCategoryList = data.shopCategoryList;
								var html = '';
								html += '<a href="#" class="button" data-category-id=""> All Shop Category </a>';
								shopCategoryList
										.map(function(item, index) {
											html += '<a href="#" class="button" data-category-id='
													+ item.shopCategoryId
													+ '>'
													+ item.shopCategoryName
													+ '</a>';
										});
								$('#shoplist-search-div').html(html);
								var selectOptions = '<option value=""> All Area </option>';
								var areaList = data.areaList;
								areaList.map(function(item, index) {
									selectOptions += '<option value="'
											+ item.areaId + '">'
											+ item.areaName + '</option>';
								});
								$('#area-search').html(selectOptions);
							}
						});
	}

	/**
	 * get shop list info
	 * 
	 * @param pageSize
	 * @param pageIndex
	 * @returns
	 */
	function addItems(pageSize, pageIndex) {
		var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
				+ pageSize + '&parentId=' + parentId + '&areaId=' + areaId
				+ '&shopCategoryId=' + shopCategoryId + '&shopName=' + shopName;
		loading = true;

		$.getJSON(url, function(data) {
			if (data.success) {
				maxItems = data.count;
				var html = '';
				data.shopList.map(function(item, index) {
					html += '' + '<div class="card" data-shop-id="'
							+ item.shopId + '">' + '<div class="card-header">'
							+ item.shopName + '</div>'
							+ '<div class="card-content">'
							+ '<div class="list-block media-list">' + '<ul>'
							+ '<li class="item-content">'
							+ '<div class="item-media">' + '<img src="'
							+ item.shopImg + '" width="44">' + '</div>'
							+ '<div class="item-inner">'
							+ '<div class="item-subtitle">' + item.shopDesc
							+ '</div>' + '</div>' + '</li>' + '</ul>'
							+ '</div>' + '</div>' + '<div class="card-footer">'
							+ '<p class="color-gray">'
							+ new Date(item.lastEditTime).Format("yyyy-MM-dd")
							+ 'Update</p>' + '<span>View</span>' + '</div>'
							+ '</div>';
				});

				$('.list-div').append(html);
				
				// for infinite scroll
				var total = $('.list-div .card').length;
				if (total >= maxItems) {
					$('.infinite-scroll-preloader').hide();
                    return;
				} else {
					$('.infinite-scroll-preloader').show();
				}
				pageNum += 1;
				loading = false;
				$.refreshScroller();
			}
		});
	}

	$(document).on('infinite', '.infinite-scroll-bottom', function() {
		if (loading)
			return;
		addItems(pageSize, pageNum);
	});

	// click view button, and enter shop detail page
	$('.shop-list').on('click', '.card', function(e) {
		var shopId = e.currentTarget.dataset.shopId;
		window.location.href = '/O2O/frontend/shopdetail?shopId=' + shopId;
	});

	// new shop category selection
	$('#shoplist-search-div').on(
			'click',
			'.button',
			function(e) {
				if (parentId && selectedParent) {
					shopCategoryId = e.target.dataset.categoryId;
					// remove the prev search condition
					if ($(e.target).hasClass('button-fill')) {
						$(e.target).removeClass('button-fill');
						shopCategoryId = '';
					} else {
						$(e.target).addClass('button-fill').siblings()
								.removeClass('button-fill');
					}
					// empty shop list of prev search
					$('.list-div').empty();
					// reset pagination
					pageNum = 1;
					addItems(pageSize, pageNum);
				} else {// if parent id is not empty, search based on parent category
					parentId = e.target.dataset.categoryId;
					if ($(e.target).hasClass('button-fill')) {
						$(e.target).removeClass('button-fill');
						parentId = '';
					} else {
						$(e.target).addClass('button-fill').siblings()
								.removeClass('button-fill');
					}
					// empty shop list of prev search
					$('.list-div').empty();
					// reset pagination
					pageNum = 1;
					addItems(pageSize, pageNum);
				}

			});

	// shop name search condition change
	$('#search').on('change', function(e) {
		shopName = e.target.value;
		$('.list-div').empty();
		pageNum = 1;
		addItems(pageSize, pageNum);
	});

	// area search condition change
	$('#area-search').on('change', function() {
		areaId = $('#area-search').val();
		$('.list-div').empty();
		pageNum = 1;
		addItems(pageSize, pageNum);
	});

	// open side bar
	$('#me').click(function() {
		$.openPanel('#panel-right-demo');
	});

	// refresh page
	$.init();
});
