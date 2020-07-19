/**
 * 
 */
$(function() {

	var initUrl = '/O2O/shopadmin/getshopinitinfo';
	var registerShopUrl = '/O2O/shopadmin/registershop';
	alert(initUrl);
	getShopInitInfo();

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
			url : registerShopUrl,
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