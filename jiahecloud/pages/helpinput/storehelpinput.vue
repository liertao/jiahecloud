<template>
	<view>
		<view class="example-body">
			<u-navbar>
				<view class="search-wrap" v-if="true">
					<!-- 如果使用u-search组件，必须要给v-model绑定一个变量 -->
					<u-search v-model="keyword" placeholder="请输入查询库房信息" :show-action="true" height="56" :action-style="{color: '#fff'}" @change="input" style="width: 100%;"></u-search>
				</view>
			</u-navbar>
			<u-swipe-action btn-width="120" :show="item.show" :index="index" v-for="(item, index) in list" :key="index" @click="click"
			 :options="options" @content-click="storeok">
				<u-cell-item style="padding-bottom: 0;" :border-bottom="false" :arrow="false">
					<view style="padding-top: 0;padding-bottom: 2px;" class="item u-border-bottom">
						<!-- 此层wrap在此为必写的，否则可能会出现标题定位错误 -->
						<view class="title-wrap" style="text-align: left;line-height: 150%;">
							<text class="title">{{ item.title }}</text>
						</view>
					</view>
				</u-cell-item>
			</u-swipe-action>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				keyword: '',
				list: [],
				disabled: false,
				btnWidth: 30,
				show: false,
				options: []
			}
		},
		methods: {
			search(res) {
				uni.showToast({
					title: '搜索：' + res.value,
					icon: 'none'
				})
				console.log('search123123');
			},
			//列表选中返回
			storeok(index){
				uni.$emit("handClickStore",{
					stockingLocation: this.list[index].stockingLocation,
					stockingName: this.list[index].stockingName,
				});
				//然后返回
				uni.navigateBack();
			},
			//返回按钮
			back(){
				uni.navigateBack();
			},
			input(res) {
				console.log(1);
				this.keyword = res;
				if (this.keyword == '') {
					return
				}
				this.list = [];
				let _this = this;
				//发送ajax
				uni.request({
					url: uni.getStorageSync('basePath') + '/model/app/helpinput/StoreHelpInput.Find.loadStore.action',
					data: {
						filter: this.keyword
					},
					header: {
						'custom-header': 'hello' //自定义请求头信息
					},
					success: (res) => {
						if (res.data.success) {
							_this.list = eval('(' + res.data.root + ')');
						} else {
							uni.showToast({
								title: res.data.errMsg,
								icon: "none"
							})
						}
					},
					fail: (e) => {
						console.log(e.errMsg);
					}
				});
			}
		},
		onBackPress() {
			// #ifdef APP-PLUS
			plus.key.hideSoftKeybord();
			// #endif
		}
	}
</script>

<style scoped lang="scss">
	// 滑动删除样式
	.item {
		display: flex;
		padding: 20rpx;
	}

	image {
		width: 120rpx;
		flex: 0 0 120rpx;
		height: 120rpx;
		margin-right: 20rpx;
		border-radius: 12rpx;
	}

	.title {
		text-align: left;
		font-size: 28rpx;
		color: $u-content-color;
		margin-top: 20rpx;
	}
</style>
