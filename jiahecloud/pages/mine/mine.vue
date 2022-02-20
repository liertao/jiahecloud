<template>
	<view class="container-class">
		<view class="wrap">
			<u-card border-radius="45" :border="false" :head-border-bottom="false">
				<view slot="head">
					<u-cell-group :border="false" :body-style="{'padding':'0 12px 0 12px'}">
						<u-cell-item :title=htitle :center="true" :border-bottom="false" hover-class="none" :arrow="false"
						 :title-style="{'font-size': '35rpx','padding-left':'30rpx'}">
							<u-avatar slot="icon" :src="src" size="large"></u-avatar>
						</u-cell-item>
					</u-cell-group>
				</view>
			</u-card>
		</view>
		<view style="position: relative;top: 30%;">
			<u-cell-group :border="false" class="bottom-class">
				<u-cell-item v-for="(item,index) in cellList" :key="index" :icon="item.icon" :title="item.title"></u-cell-item>
			</u-cell-group>
			<u-row justify="center" class="bottom-class">
				<u-col :span="12">
					<u-button type="primary" :ripple="true" @click="open">退出登录</u-button>
				</u-col>
			</u-row>
		</view>
		<u-modal v-model="show" content="是否退出登录？" @confirm="signout" :show-cancel-button="true"></u-modal>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				src: 'http://pic2.sc.chinaz.com/Files/pic/pic9/202002/hpic2119_s.jpg',
				text: '无头像',
				htitle: '请登录',
				scrollTop: 0,
				show: false,
				cellList: [{
					icon: "account",
					title: "个人资料"
				}, {
					icon: "info-circle",
					title: "问题反馈"
				}, {
					icon: "download",
					title: "版本更新"
				}, {
					icon: "server-man",
					title: "联系我们"
				}]
			}
		},
		onPageScroll(e) {
			this.scrollTop = e.scrollTop;
		},
		onShow(){
			let curEmloyeeName = uni.getStorageSync("userInfo").logName;
			if(curEmloyeeName){
				this.htitle = "欢迎您，" + curEmloyeeName;
			}else{
				this.htitle = "请登录";
			}
		},
		// computed: {
		// 	userInfo() {
		// 		debugger;
		// 		let curEmloyeeName = uni.getStorageSync("userInfo").logName;
		// 		if (curEmloyeeName) {
		// 			return "欢迎您，" + curEmloyeeName;
		// 		} else {
		// 			return "请登录";
		// 		}
		// 	}
		// },
		methods: {
			open() {
				this.show = true;
			},
			signout() {
				// #ifdef APP-PLUS  
				plus.runtime.quit();  
				// #endif
				// console.log("退出登录");
				this.$store.commit("logout");
				// uni.redirectTo({
				// 	url: '../home/mytask'
				// });
				uni.switchTab({
					url:'../home/home'
				})
			}
		}
	}
</script>

<style>
	page {
		height: 100%;
	}

	.container-class {
		height: 100%;
		background-color: #efefef;
	}

	.wrap {
		width: 100%;
		height: 20%;
		position: absolute;
		border-bottom-right-radius: 30%;
		border-bottom-left-radius: 30%;
		background-color: #5aa9e1;
	}

	.u-card {
		position: relative;
		box-shadow: 0 0 5px #c5c5c5;
	}

	.u-body-item {
		font-size: 32rpx;
		color: #333;
	}

	.bottom-class {
		padding-top: 15px;
	}
</style>
