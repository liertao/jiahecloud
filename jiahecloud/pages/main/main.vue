<template>
	<uni-grid :column="3" :show-border="false"  :square="true" :highlight="true" @change="openView">
		<view class="maintitle">设备台账管理</view>
		<uni-grid-item :index=1>
			<uni-icons class="mainicon" type="contact" size="30"></uni-icons>
			<text class="text">设备台账查询</text>
		</uni-grid-item>
		<view class="maintitle">设备维修管理</view>
		<uni-grid-item :index=2>
			<uni-icons class="mainicon" type="contact" size="30"></uni-icons>
			<text class="text">设备故障登记</text>
		</uni-grid-item>
		<uni-grid-item :index=3>
			<uni-icons class="mainicon" type="contact" size="30"></uni-icons>
			<text class="text">设备维修处理</text>
		</uni-grid-item>
		<uni-grid-item :index=4>
			<uni-icons class="mainicon" type="contact" size="30"></uni-icons>
			<text class="text">设备维修记录</text>
		</uni-grid-item>
		<view class="maintitle">设备保养润滑管理</view>
		<uni-grid-item :index=5>
			<uni-icons class="mainicon" type="contact" size="30"></uni-icons>
			<text class="text">设备保养实施</text>
		</uni-grid-item>
		<uni-grid-item :index=6>
			<uni-icons class="mainicon" type="contact" size="30"></uni-icons>
			<text class="text">设备润滑</text>
		</uni-grid-item>
		<view class="maintitle">设备安全管理</view>
		<uni-grid-item :index=7>
			<uni-icons class="mainicon" type="contact" size="30"></uni-icons>
			<text class="text">设备巡检</text>
		</uni-grid-item>
		<uni-grid-item :index=8>
			<uni-icons class="mainicon" type="contact" size="30"></uni-icons>
			<text class="text">设备盘点</text>
		</uni-grid-item>
	</uni-grid>
</template>

<script>
	import uniGrid from "@/components/uni-grid/uni-grid.vue"
	import uniGridItem from "@/components/uni-grid-item/uni-grid-item.vue"
	import uniIcons from "@/components/uni-icons/uni-icons.vue"
	import uniCard from '@/components/uni-card/uni-card.vue'
	export default {
		components: {uniGrid,uniGridItem,uniIcons,uniCard},
		data() {
			return {
				
			}
		},
		methods: {
			openView(e){
				console.log(12);
				if(e.detail.index==1){//打开库存盘点
					uni.navigateTo({
					    url: '../account/account'
					});
				}
				if(e.detail.index==2){//打开故障登记
					uni.navigateTo({
					    url: '../maintain/faultregister'
					});
				}
				if(e.detail.index==3){//打开故障处理
					uni.navigateTo({
					    url: '../maintain/faulthandle'
					});
				}
				if(e.detail.index==4){//打开设备维修记录
					uni.navigateTo({
					    url: '../maintain/repaire'
					});
				}
				if(e.detail.index==5){//打开设备保养实施
					uni.navigateTo({
					    url: '../maintain/maintain'
					});
				}
				if(e.detail.index==6){//打开设备润滑实施
					uni.navigateTo({
					    url: '../maintain/lubrication'
					});
				}
				if(e.detail.index==7){//打开设备巡检实施
					uni.navigateTo({
					    url: '../maintain/checkout'
					});
				}
				if(e.detail.index==8){//打开库存盘点
					uni.navigateTo({
					    url: '../maintain/store'
					});
				}
				// console.log(e.detail.index);
				// console.log(window.localStorage.getItem("logId"));
				// window.localStorage.setItem("logId", "liertao");
				// console.log(window.localStorage.getItem("logId"));
				// window.localStorage.clear("logId");
				// console.log(window.localStorage.getItem("logId"));
			}
		}
	}
</script>

<style>
.text{
	color: #999999;
	text-align: center;
	font-weight: bold;
}
.mainicon{
	padding-top: 40upx;
}
.maintitle{
	display: block;
	width: 750upx;
	padding-top: 5upx;
	padding-left: 3upx;
	border-left: 2px solid black;
	border-bottom: 1px solid #ccc;
	font-size: 34upx;
	font-family: "微软雅黑";
}
</style>
