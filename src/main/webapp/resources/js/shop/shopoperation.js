/**
 * 
 */
$(function() {

	var shopId = getQueryString('shopId');
	var isEdit = shopId ? true: false; // to verify whether to register or to modify. true: modify
	var initUrl = '/O2O/shopadmin/getshopinitinfo';
	var registerShopUrl = '/O2O/shopadmin/registershop';
	var shopInfoUrl = '/O2O/shopadmin/getshopbyid?shopId=' + shopId;
	var editShopUrl = '/O2O/shopadmin/modifyshop'
	if (!isEdit) {
		getShopInitInfo();
	} else {
		getShopInfo(shopId);
	}

	function getShopInfo(shopId) {
			$.getJSON(shopInfoUrl, function(data) {
				if (data.success) {
					// if success, then fill the form data
					var shop = data.shop;
					$('#shop-name').val(shop.shopName);
					$('#shop-addr').val(shop.shopAddr);
					$('#shop-phone').val(shop.phone);
					$('#shop-desc').val(shop.shopDesc);
					// select prev shop category, shop category cannot be modified
					var shopCategory = '<option data-id="'
							+ shop.shopCategory.shopCategoryId + '" selected>'
							+ shop.shopCategory.shopCategoryName + '</option>';
					var tempAreaHtml = '';
					// initialize area list, area list can be modified
					data.areaList.map(function(item, index) {
						tempAreaHtml += '<option data-id="' + item.areaId + '">'
								+ item.areaName + '</option>';
					});
					$('#shop-category').html(shopCategory);
					// shop category is not allowed to be selected
					$('#shop-category').attr('disabled', 'disabled');
					$('#area').html(tempAreaHtml);
					// select prev shop area
					$("#area option[data-id='" + shop.area.areaId + "']").attr(
							"selected", "selected");
				}
			});
		}
	
	// get from backend shop category and area category info
	// and populate shop category and area category drop down list
	function getShopInitInfo() {
		$.getJSON(initUrl, function(data) {
			if (data.success) {
				var tempHtml = '';
				var tempAreaHtml = '';
				data.shopCategoryList.map(function(item, index) {
					tempHtml += '<option data-id="' + item.shopCategoryId
							+ '">' + item.shopCategoryName + '</option>';
				});
				data.areaList.map(function(item, index) {
					tempAreaHtml += '<option data-id="' + item.areaId + '">'
							+ item.areaName + '</option>';
				});
				$('#shop-category').html(tempHtml);
				$('#area').html(tempAreaHtml);
			}
		});
	}
	
	$('#submit').click(function() {

		var shop = {};
		// get info from form and populate the shop object
		if (isEdit) {
			shop.shopId = shopId;
		}
		shop.shopName = $('#shop-name').val();
		shop.shopAddr = $('#shop-addr').val();
		shop.phone = $('#shop-phone').val();
		shop.shopDesc = $('#shop-desc').val();
		shop.shopCategory = {
			shopCategoryId : $('#shop-category').find('option').not(function() {
				return !this.selected;
			}).data('id')
		};
		shop.area = {
			areaId : $('#area').find('option').not(function() {
				return !this.selected;
			}).data('id')
		};

		var shopImg = $('#shop-img')[0].files[0];

		var formData = new FormData();
		formData.append('shopImg', shopImg);
		formData.append('shopStr', JSON.stringify(shop));
		// append the captcha
		var verifyCodeActual = $('#j_captcha').val();
		/*console.log(verifyCodeActual);*/
		
		if (!verifyCodeActual) {
			$.toast("Please enter Captcha!");
			return;
		}
		formData.append('verifyCodeActual', verifyCodeActual);
		
		$.ajax({
			url : (isEdit ? editShopUrl: registerShopUrl),
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					$.toast('sucess！');
				} else {
					$.toast('submit failed！' + data.errMsg);
				}
				// update the captcha after submitting
				$('#captcha_img').click();
			}
		});
	});
	
})