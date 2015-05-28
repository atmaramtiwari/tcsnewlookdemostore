var productId = new Array();
var checked = 0;
$("div.compare_chk input[type='checkbox']").change(function(){
	var sel = $(this).attr("data-unique-itemid");
		if($(this).is(":checked")){
			checked = checked+1; 
			productId = $(this).attr("data-product-id");
			var imageUrl = $("ul.product-listing li#"+sel+" a.thumb img").attr("src");
			var productName =  $("ul.product-listing li#"+sel+" a.name").text().trim();
			var productLink =  $("ul.product-listing li#"+sel+" a.name").attr("href");
			var productId = $(this).attr("data-product-id");
			if(checked > 0){
				$("div.compare_cart_wrapper").show();
				$("div.compare_cart_wrapper ").append('<div class="compare_items" data-remove-div="'+sel+'">'+
				'<div class="compare_item">'+
					'<div class="thumb_holder">'+
						'<img src="'+imageUrl+'" alt="'+productName+'" title="'+productName+'"/>'+
					'</div>'+
					'<a class="pdct_des" href="'+productLink+'">'+productName+'</a>'+
					'<a href="javascript:void(0);" class="link remove_compare_article" data-remove-id="'+sel+'"> X</a>'+
					'<input type="hidden" name="productId" value="'+productId+'" />'+
				'</div>'
				);
			}
		}
		else{
			$("div.compare_cart_wrapper div[data-remove-div='"+sel+"']").remove();
			
		}
	});

$(document).on('click','a.remove_compare_article',function(){
	var removeThis = $(this).attr('data-remove-id'); 
	$(this).closest("div.compare_item").remove();
	$("div.compare_chk input[type='checkbox'][data-unique-itemid='"+removeThis+"']").prop("checked",false);
});

$(document).ready(function(){
	$('.compare_btn').click(function(){
		var productIdArray = [];
		$("div.compare_items").each(function(){
			var productID = $(this).find("input[type='hidden'][name='productId']").val();
			productIdArray.push(productID);
		});
		window.location.href = "/newlookstorefront/en/productcompare?productId="+productIdArray;
	});
});
