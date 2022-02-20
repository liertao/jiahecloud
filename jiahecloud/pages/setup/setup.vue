<template>
	<view class="forget">
		<br><br>
		<view class="content">
			<!-- 主体 -->
			<view class="main">
				<wInput v-model="baseIP" type="text" placeholder="请输入IP地址" @confirm="reSetUp()"></wInput>
				<wInput v-model="basePort" type="text" placeholder="请输入端口号" @confirm="reSetUp()"></wInput>
				<wInput style="display: none;" v-model="projectName" type="text" placeholder="请输入项目名称" @confirm="reSetUp()"></wInput>
				<wButton class="wbutton" text="保 存" :rotate="isRotate" @click.native="reSetUp()"></wButton>
			</view>
		</view>
	</view>
</template>

<script>
	var _this;
	import wInput from '../../components/watch-login/watch-input.vue' //input
	import wButton from '../../components/watch-login/watch-button.vue' //button
	export default {
		data() {
			return {
				baseIP: this.$store.state.baseIP,
				basePort: this.$store.state.basePort,
				projectName: this.$store.state.projectName,
				isRotate: false
			}
		},
		components: {
			wInput,
			wButton
		},
		mounted() {
			_this = this;
		},
		methods: {
			reSetUp() {
				if (this.baseIP == "") {
					uni.showToast({
						title: "服务器IP不能为空",
						icon: "none"
					})
					return;
				}
				if (this.basePort == "") {
					uni.showToast({
						title: "服务端口不能为空",
						icon: "none"
					})
					return;
				}
				_this.isRotate = true
				setTimeout(function() {
					_this.isRotate = false;
					//调用全局方法 存储设置的IP和端口
					_this.$store.commit("setup", {
						"baseIP": _this.baseIP,
						"basePort": _this.basePort,
						"projectName": _this.projectName
					}); //调用全局登录方法
					uni.navigateBack();
				}, 1000)
			}
		}
	}
</script>

<style>
	@import url("../../components/watch-login/css/icon.css");
	@import url("../login/css/main.css");
	page{
		background-color: #FFFFFF;
	}
</style>
