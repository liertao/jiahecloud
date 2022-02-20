<template>
	<view>
		<view class="uni-padding-wrap">
			<view class="page-section swiper">
				<view class="page-section-spacing">
					<swiper class="swiper" :indicator-dots="indicatorDots" :autoplay="autoplay" :interval="interval" :duration="duration">
						<swiper-item>
							<view class="swiper-item uni-bg-red">
								<image class="swipe" src="../../static/swipe/sb3.jpg" mode="scaleToFill"></image>
							</view>
						</swiper-item>
						<swiper-item>
							<view class="swiper-item uni-bg-green">
								<image class="swipe" src="../../static/swipe/sb2.jpg" mode="scaleToFill"></image>
							</view>
						</swiper-item>
						<swiper-item>
							<view class="swiper-item uni-bg-blue">
								<image class="swipe" src="../../static/swipe/sb1.jpg" mode="scaleToFill"></image>
							</view>
						</swiper-item>
					</swiper>
				</view>
			</view>
		</view>
		<view class="pad"></view>
		<!-- 功能块 -->
		<uni-grid :column="4" :show-border="false"  :square="true" :highlight="true" @change="openView">
			<uni-grid-item :index=1>
				<uni-icons class="mainicon" type="contact" size="30"></uni-icons>
				<text class="text">设备台账查询</text>
			</uni-grid-item>
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
			<uni-grid-item :index=5>
				<uni-icons class="mainicon" type="contact" size="30"></uni-icons>
				<text class="text">设备保养实施</text>
			</uni-grid-item>
			<uni-grid-item :index=6>
				<uni-icons class="mainicon" type="contact" size="30"></uni-icons>
				<text class="text">设备润滑</text>
			</uni-grid-item>
			<uni-grid-item :index=7>
				<uni-icons class="mainicon" type="contact" size="30"></uni-icons>
				<text class="text">设备巡检</text>
			</uni-grid-item>
			<uni-grid-item :index=8>
				<uni-icons class="mainicon" type="contact" size="30"></uni-icons>
				<text class="text">设备盘点</text>
			</uni-grid-item>
		</uni-grid>
	</view>
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
				background: ['color1', 'color2', 'color3'],
				indicatorDots: true,
				autoplay: true,
				interval: 3000,
				duration: 1000,
				hasLogin: this.$store.state.hasLogin
			}
		},
		methods: {
			openView(e){
				//如果没有登录，返回登录界面
				if(!this.hasLogin){
					uni.redirectTo({
					    url: '../login/login'
					});
					return
				}
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
			}
		}
	}
</script>

<style>
.swipe{
	width: 750upx;
	height: 400upx;
}
.text{
	color: #8F8F94;
	font-size: 20upx;
	text-align: center;
	font-family: "微软雅黑";
}
.pad{
	padding-top: 40upx;
}
</style>
