<template>
	<view class="u-demo">
		<!-- <u-sticky :offset-top="offsetTop" :enable="enable" @fixed="fixed">
			<view class="sticky">
				<uni-nav-bar background-color="#ffffff" left-icon="back" left-text="" right-text="新增" title="日志列表查询" @clickLeft="back"
				 @clickRight="add"></uni-nav-bar>
			</view>
		</u-sticky> -->
		<u-button type="success" @click="dailyAdd">填写日志</u-button>
		<u-back-top :scroll-top="scrollTop"></u-back-top>
		<u-card class="shadow" margin="10rpx" padding="16" title-color="#63da56" title-size="30" sub-title-size="28" :title="item.projectName" :sub-title="item.workDate" :thumb="thumb" v-for="(item, idx) in list" :key="idx" @click="cellclick(idx)" :index="index">
			<view class="" slot="body" style="font-size: 30upx;color: #808080;">
				<u-row gutter="16">
					<u-col span="6">
						<view >{{item.costName}}:{{item.costHours}}小时</view>
					</u-col>
					<u-col span="6">
						<view style="">总金额:{{item.total}}</view>
					</u-col>
				</u-row>
				<u-row gutter="16">
					<u-col span="6">
						<view style="">工作地点:{{item.workAdd}}</view>
					</u-col>
					<u-col span="6">
						<view style="">审核结果:{{item.checkResult}}</view>
					</u-col>
				</u-row>
				<u-gap height="1" bg-color="#f1f1f1"></u-gap>
				<u-row gutter="16">
					<u-col span="12">
						<view style="font-weight: bold;">工作内容:</view>
					</u-col>
				</u-row>
				<u-gap height="1" bg-color="#f1f1f1"></u-gap>
				<u-row gutter="16">
					<u-col span="12">
						<view style="">{{item.comments}}</view>
					</u-col>
				</u-row>
				<!-- <u-gap v-if="item.id===delId?true:false" height="1" bg-color="#f1f1f1"></u-gap> -->
				<u-row gutter="16" v-if="item.id===delId?true:false" style="padding-top: 10upx;">
					<u-col span="12">
						<u-button style="float: right;font-size: 30upx;" size="mini" type="error" @click="delDaily(idx, item.id)">删除</u-button>
					</u-col>
				</u-row>
			</view>
		</u-card>
		<view style="font-size: 36upx;color: #808080;text-align: center;">{{loadMoreText}}</view>
	</view>
</template>
<script>
	import uniNavBar from "@/components/uni-nav-bar/uni-nav-bar.vue"
	export default {
		components: {
			uniNavBar
		},
		data() {
			return {
				thumb: uni.getStorageSync('basePath')+'/daib.png',
				show: true,
				index:0,
				scrollTop: 200,
				offsetTop: 200,
				enable: true,
				icon: 'setting',
				arrow: true,
				num: 10, //数据加载条数，用户下拉刷新
				loadMoreText: "加载中...",
				showLoadMore: false,
				continueFlag: true, //是否继续请求后台
				scrollTop: 0, //返回底部监听
				filterResult: '', //主界面传来的查询条件，字符串
				list: [
					// {
					// 	label: '报修人：let，处理状态：需要处理',
					// 	title: '机床设备001',
					// 	exceptionHandle: '1',
					// 	eqmNum: '',
					// 	id: 0,
					// }
				],
				delId:0
			}
		},
		onPageScroll(e) {
			this.scrollTop = e.scrollTop;
		},
		//加载界面传来的参数
		onLoad: function() {
		},
		onPageScroll(e) {
			this.scrollTop = e.scrollTop;
		},
		//每次页面显示加载数据
		onShow() {
			//发送ajax
			let _this = this;
			// return false;
			uni.request({
				url: uni.getStorageSync('basePath') + '/daily/query',
				// url: _this.$store.state.basePath + '/daily/query',
				data: {
					num: _this.num,
					logId: uni.getStorageSync('userInfo').logId
				},
				method: 'GET',
				header: {
					'Access-Control-Allow-Origin': '*',  
					'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',  
					//#ifdef APP-PLUS ||  MP-WEIXIN  
					'cookie': _this.$store.state.cookie
					//#endif  
				},
				success: (res) => {
					if (!res.data.success) { //如果失败提示信息
						uni.showToast({
							title: res.data.msg,
							icon: "none"
						})
						return
					}
					_this.list = res.data.root;
				},
				fail: (e) => {
					console.log(e.errMsg);
				}
			});
		},
		onReachBottom() {
			console.log("onReachBottom");
			if (!this.continueFlag) {
				this.loadMoreText = "没有更多数据了!"
				return;
			}
			this.showLoadMore = true;
			setTimeout(() => {
				this.setListData();
			}, 200);
		},
		onPullDownRefresh() {
			console.log('onPullDownRefresh');
		},
		methods: {
			offsetTopChange(index) {
				this.offsetTop = index == 0 ? 0 : index == 1 ? 0 : 0;
				uni.pageScrollTo({
					scrollTop: 0,
					duration: 0
				})
			},
			fixed() {
				// this.$refs.uToast.show({
				// 	type: 'warning',
				// 	title: '触发吸顶'
				// })
			},
			//返回按钮
			back() {
				uni.navigateBack();
			},
			//打开新增界面
			add() {
				uni.navigateTo({
					url: '../maintain/faultregister'
				});
			},
			setListData() {
				let data = [];
				this.num += 10;
				//发送ajax
				let _this = this;
				uni.request({
					url: uni.getStorageSync('basePath') +
						'/daily/query',
					// url: _this.$store.state.basePath + 
					// 	'/daily/query',
					data: {
						num: _this.num,
						logId: uni.getStorageSync('userInfo').logId
					},
					method: 'GET',
					header: {
						'Access-Control-Allow-Origin': '*',  
						'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',  
						//#ifdef APP-PLUS ||  MP-WEIXIN  
						'cookie': _this.$store.state.cookie
						//#endif  
					},
					success: (res) => {
						if (!res.data.success) { //如果失败提示信息
							uni.showToast({
								title: res.data.msg,
								icon: "none"
							})
						}
						data = res.data.root;
						_this.list = _this.list.concat(data);
						_this.continueFlag = res.data.continueFlag;
						if (!_this.continueFlag) {
							_this.loadMoreText = "没有更多数据了!"
							return;
						}
						uni.stopPullDownRefresh();
					},
					fail: (e) => {
						console.log(e.errMsg);
					}
				});
			},
			//删除未审核的记录
			delDaily(idx, id){
				//发送ajax
				let _this = this;
				uni.request({
					url: uni.getStorageSync('basePath') +
						'/daily/delete',
					// url: _this.$store.state.basePath + 
					// 	'/daily/delete',
					data: {
						id: id
					},
					success: (res) => {
						if (!res.data.success) { //如果失败提示信息
							uni.showToast({
								title: res.data.msg,
								icon: "none"
							})
							return
						}
						_this.list.splice(idx,1);
						uni.showToast({
							title: res.data.msg,
							icon: "none"
						})
					},
					fail: (e) => {
						console.log(e.errMsg);
					}
				});
			},
			//点击标题显示删除按钮
			cellclick(index){
				this.delId = this.list[index].id;
			},
			//打开日志填写界面
			dailyAdd(){
				this.num = 10;
				uni.navigateTo({
					url:'./dailyadd',
					animationType: 'slide-in-bottom',
					animationDuration: 300
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
// 吸顶样式
.u-demo-area {
	overflow: hidden;
}
.u-config-wrap {
	height: 200vh;
}
.u-demo-title {
	margin-bottom: 10rpx;
}
.sticky {
	background-color: $u-type-primary;
	color: #fff;
	// padding: 24rpx;
	margin: auto;
	font-size: 28rpx;
	line-height: 1;
	border-radius: 1px;
}
</style>
