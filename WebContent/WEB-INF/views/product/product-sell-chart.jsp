<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>

<script type="text/javascript">
	//원형 차트
	google.charts.load('current', {
		'packages' : [ 'corechart' ]
	});
	google.charts.setOnLoadCallback(drawChart);

	function drawChart() {
		var receiptListStr = "${receiptList}";
		var formattedStr = receiptListStr.replace(/ProductCountDto\(([^)]+)\)/g, function(match, group) {
			var parts = group.split(', ').map(function(part) {
				var keyValue = part.split('=');
				return '"' + keyValue[0].trim() + '": "' + keyValue[1].trim() + '"';
			});
			return '{' + parts.join(', ') + '}';
		});
		var receiptList = JSON.parse('[' + formattedStr + ']');
		var mostSoldProduct = '';
		var highestCount = 0;
		var chartData = [ [ 'Product Name', 'Count' ] ];

		for (var i = 0; i < receiptList[0].length; i++) {
			var product = receiptList[0][i];
			var count = parseInt(product.count);
			chartData.push([ product.productName, parseInt(product.count) ]);

			if (count > highestCount) {
				highestCount = count;
				mostSoldProduct = product.productName;
			}
		}
		document.getElementById('mostSell').innerText = mostSoldProduct;
		var data = google.visualization.arrayToDataTable(chartData);

		var chart = new google.visualization.PieChart(document.getElementById('piechart'));
		
		var options = {
				curveType : 'function',
				legend : {
					position : 'bottom'
				},
				animation:{
					startup: 'true',
					duration: 1000,
					easing: 'inAndOut'
				}
			};
		chart.draw(data,options);
	}
</script>

<script type="text/javascript">
//선형 그래프
	google.charts.load('current', {
		'packages' : [ 'corechart' ]
	});
	google.charts.setOnLoadCallback(drawLineChart);

	function drawLineChart() {

		var receiptListStr = "${listEachYear}";
		var formattedStr = receiptListStr.replace(/ProductMoneyEachYearDto\(([^)]+)\)/g, function(match, group) {
			var parts = group.split(', ').map(function(part) {
				var keyValue = part.split('=');
				return '"' + keyValue[0].trim() + '": "' + keyValue[1].trim() + '"';
			});
			return '{' + parts.join(', ') + '}';
		});


		var receiptList = JSON.parse('[' + formattedStr + ']');

		var data = google.visualization.arrayToDataTable([ [ '연도', '총 매출' ] , ['start', 0]]);
		console.log(receiptList[0])
		for (var i = 0; i < receiptList[0].length; i++) {
			var product = receiptList[0][i];
			data.addRow([ product.year, parseInt(product.money) ]);
		}
		var options = {
			title : '연도별 수익률 그래프',
			curveType : 'function',
			legend : {
				position : 'bottom'
			},
			animation:{
				startup: 'true',
				duration: 1000,
				easing: 'inAndOut'
			}
		};

		var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

		chart.draw(data, options);
	}
</script>
</head>
<body
	style="height: 100%; margin: 0; display: flex; flex-direction: column;">
	<div style="flex: 1; display: flex; overflow: hidden;">
		<%@include file="/WEB-INF/views/common/sidebar.jsp"%>
		<div class="d-flex justify-content-center"
			style="flex: 1; padding: 1rem; overflow: auto; margin: 50px 0 100px 0">

			<div class="d-flex"
				style="flex-direction: column; position: relative">
				<h1 class="page-title fw-bolder" style="margin-bottom: 20px;">판매
					차트</h1>

				<div class="outer-box"
					style="padding: 30px; width: 90%; border-radius: 15px; height: 600px">
					<div class="d-flex"
						style="width: 100%; justify-content: space-between; padding-bottom: 80px">

						<div style="display: flex; align-items: center; margin: 100px">
							<div>
								<h2 class="fw-bolder" style="white-space: nowrap;">가장 많이
									판매된 제품</h2>
								<span class="text-warning fw-bold" id="mostSell"
									style="font-size: 1.5rem;"></span>
								<hr>
								<span class="fw-bold" id="mostSell"
									style="font-size: 1.5rem; white-space: nowrap;"> 총 판매
									수익률 : ${totalMoney} 원 </span>
							</div>
						</div>

						<!-- 파이 차트 -->
						<div id="piechart" style="width: 700px;"></div>
					</div>
					<div id="curve_chart" style="width: 100%; height: 500px"></div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
