<template>
	<view class="login">
		<view class="content">
			<!-- 头部logo -->
			<!-- <view class="header" @click="setUp()"> -->
			<view class="header">
				<image :src="logoImage"></image>
			</view>
			<!-- 系统名称 -->
			<view class="system">
				<span>嘉禾云办公</span>
			</view>

			<!-- 主体表单 -->
			<view class="main">
				<!-- <u-input border="1" :v-model="phoneData" type="text" placeholder="用户名" @confirm="startLogin"></u-input>
				<u-input border="1" :v-model="passData" type="password" placeholder="密码" @confirm="startLogin"></u-input> -->
				<input class="uni-input" v-model="phoneData" type="text" placeholder="用户名" @confirm="startLogin" ></input>
				<view>&nbsp;</view>
				<input class="uni-input" v-model="passData" type="password" placeholder="密码" @confirm="startLogin" ></input>
			</view>
			<!-- <wButton class="wbutton" text="登 录" :rotate="isRotate" @click.native="startLogin()"></wButton> -->
			<button type="primary" class="wbutton" text="登 录" :rotate="isRotate" @click="startLogin()">登 录</button >
		</view>
	</view>
</template>

<script>
	import wInput from '@/components/watch-login/watch-input.vue' //input
	import wButton from '@/components/watch-login/watch-button.vue' //button

	export default {
		data() {
			return {
				//logo图片 base64
				logoImage: '../../static/images/logo.png',
				phoneData: '', //用户/电话
				passData: '', //密码
				isRotate: false, //是否加载旋转
			};
		},
		components: {
			wInput,
			wButton,
		},
		mounted() {
			let that = this;
			const phoneData = uni.getStorageSync('phoneData');
			const passData = uni.getStorageSync('passData');
			if(phoneData && passData){
				that.phoneData = phoneData;
				that.passData = passData;
			}
		},
		methods: {
			isLogin() {
				//判断缓存中是否登录过，直接登录
				// try {
				// 	const value = uni.getStorageSync('setUserData');
				// 	if (value) {
				// 		//有登录信息
				// 		console.log("已登录用户：",value);
				// 		_this.$store.dispatch("setUserData",value); //存入状态
				// 		uni.reLaunch({
				// 			url: '../../../pages/index',
				// 		});
				// 	}
				// } catch (e) {
				// 	// error
				// }
			},
			setUp() {
				uni.navigateTo({
					url: '../setup/setup'
				});
			},
			startLogin() {
				//登录
				let _this = this;
				if (_this.isRotate) {
					//判断是否加载中，避免重复点击请求
					return false;
				}
				console.log('结果='+(_this.phoneData));
				if (_this.phoneData == "") {
					uni.showToast({
						icon: 'none',
						title: '用户名不能为空'
					});
					return;
				}
				if (_this.passData == "") {
					uni.showToast({
						title: "密码不能为空",
						icon: "none"
					})
					return;
				}
				//判断如果uni.getStorageSync('basePath')为空，取store中的值存入
				if(uni.getStorageSync('basePath')==null || uni.getStorageSync('basePath')==""){
					uni.setStorage({
						key: "basePath",
						data: _this.$store.state.basePath
					})
				}
				_this.isRotate = true;
				//发送ajax
				uni.request({
					url: uni.getStorageSync('basePath')+'/login/checklogin', //仅为示例，并非真实接口地址。
					// url: _this.$store.state.basePath +
					// 	'/login/checklogin', //仅为示例，并非真实接口地址。
					data: {
						logId: _this.phoneData,
						logPwd: _this.passData
					},
					header: {
						'Content-Type': 'application/json' //自定义请求头信息
					},
					success: (res) => {
						let obj;
						if (typeof res.data == "object") {
							obj = res.data;
						} else {
							obj = eval('(' + res.data + ')');
						}
						if (!obj.success) {
							uni.showToast({
								title: obj.msg,
								icon: "none"
							})
							_this.isRotate = false;
						} else {
							setTimeout(function() {
								_this.isRotate = false;
								_this.toMain(res);
							}, 1000)
						}
					},
					fail: (e) => {
						// _this.setUp();
						uni.showToast({
							title: "后台服务异常",
							icon: "none"
						})
						_this.isRotate = false;
					}
				});
			},
			toMain(obj) {
				// this.login(obj.data.root.userParams);
				this.$store.commit("login", obj.data.root); //调用全局登录方法
				this.$store.commit("saveAuths", {'phoneData':this.phoneData,'passData':this.passData,'auths':obj.data.auths, 'cookie':obj.header['Set-Cookie']}); //调用全局保存权限方法
				// this.$store.commit("saveAuths", obj.data.auths, obj.data.sessionID); //调用全局保存权限方法
				// console.log('ffffff='+obj.data.sessionID)
				// uni.switchTab({
				// 	url: '../home/home'
				// });
				uni.navigateBack();
			},
		}
	}
</script>

<style>
	@import url("@/components/watch-login/css/icon.css");
	@import url("./css/main.css");
	page{
		background-color: #FFFFFF;
	}
	.uni-input{
		height: 50upx;
		border-bottom: 1px solid #ccc;
		font-size: 40upx;
		border-radius:5px;
		padding-top: 20upx;
	}
	.wbutton{
		width: 90%;
	}
</style>
