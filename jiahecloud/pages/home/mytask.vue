<template>
	<view>
		<view style="margin: 200rpx 0 0 0;text-align: center;" v-if="gridBTNList.length===0?true:false">
			<u-icon name="account-fill" size="40" color="#a3a3a3"></u-icon>
			<text style="font-size: 40upx;color: #c9c9c9;font-weight: bold;width: 100%;">您没有任何功能操作权限\n请联系系统管理员</text>
		</view>
		<u-grid :col="4" :border="false">
			<u-grid-item v-for="(item,index) in gridBTNList" :key="index" @click="gridCclick(item)">
				<!-- <u-icon :name="item.icon" :size="46"></u-icon> -->
				<image :src=item.img class="image"></image>
				<view class="grid-text">{{item.name}}</view>
			</u-grid-item>
		</u-grid>
	</view>
</template>

<script>
	export default {
		components: {
		},
		data() {
			return {
				keyword: '',
				tabData: [{}, {}], //传入数据
				//非计划功能列表
				gridBTNList: [{
					"name": '日志填写',
					"img" : '../../static/images/logo.png',
					"url": '../daily/dailycalendar'
				},{
					"name": '待办任务',
					"img" : '../../static/images/logo.png',
					"url": '../daily/ssss',
				},{
					"name": '项目履历',
					"img" : '../../static/images/logo.png',
					"url": '../daily/ssss'
				},{
					"name": 'CodeReview',
					"img" : '../../static/images/logo.png',
					"url": '../daily/ssss'
				},{
					"name": '培训视频列表',
					"img" : '../../static/images/logo.png',
					"url": '../daily/ssss'
				}
				// ,{
				// 	"name": '码拖',
				// 	"img" : '../../static/images/logo.png',
				// 	"url": '../maintain/repaire'
				// },
				],
				current: 0,
				list: [],
				disabled: false,
				btnWidth: 160,
				show: false,
				themeColor: '#000000',
				filterResult: ''
			};
		},
		//加载用户按钮权限，权限来源后台数组
		// onShow() {
		// 	this.gridBTNList = this.$store.state.auths;
		// },
		methods: {
			gridCclick(item) {
				// 如果没有登录，返回登录界面
				if (!uni.getStorageSync('userInfo')) {
					// uni.redirectTo({
					// 	url: '../login/login'
					// });
					uni.navigateTo({
						url:'../login/login'
					})
					return
				}
				if(item.name=='待办任务'||item.name=='项目履历'||item.name=='CodeReview'||item.name=='培训视频列表'){
					uni.showToast({
						title: '待实现',
						icon: "none"
					})
					return
				}
				uni.navigateTo({
					url: item.url
				});
			}
		}
	};
</script>

<style lang="scss" scoped>
.item {
	display: flex;
	padding: 20rpx;
}
image {
	width: 100rpx;
	flex: 0 0 100rpx;
	height: 100rpx;
	border-radius: 12rpx;
}
.grid-text{
	text-align: center;
	font-size: 32upx;
}
page{
	background-color: #FFFFFF;
}
</style>
