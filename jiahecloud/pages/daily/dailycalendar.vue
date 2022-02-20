<template>
	<view class="content">
		<view v-if="!jzShow" style="text-align: center;">加载中...</view>
		<view v-if="loginShow" style="text-align: center;">请退出，重新登录...</view>
		<imt-calendar v-if="show" :selected="data" :chosed="cdata" @loadDetail="loadDetailByDate"></imt-calendar>
		<!-- <view>{{tardate}}</view> -->
		<u-card class="shadow" margin="30rpx" padding="16" title-color="#63da56" title-size="30" sub-title-size="28" :title="item.projectName" :sub-title="item.workDate" :thumb="thumb" v-for="(item, idx) in list" :key="idx" @click="cellclick(idx)" :index="index">
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
		<view v-if="list.length>0?false:true">
			<u-button v-if="show" :custom-style="customStyle" @click="dailyAdd">填写日志</u-button>
			<u-button v-if="show" :custom-style="customStyle" @click="dailylist">日志列表</u-button>
		</view>
	</view>
</template>
<script>
	import imtCalendar from 'components/imt-calendar/imt-calendar'
		export default {
			props: {
				tardate: {
					type: String,
					default: ''
				}
			},
			data() {
				return {
					customStyle: {
						marginLeft: '40rpx', // 注意驼峰命名，并且值必须用引号包括，因为这是对象
						marginTop: '3rpx',
						marginRight: '40rpx', // 注意驼峰命名，并且值必须用引号包括，因为这是对象
						color: 'white',
						background: '#19be6b'
					},
					thumb: uni.getStorageSync('basePath')+'/daib.png',
					show: false,
					jzShow: false,
					loginShow: false,
					tarDate: '',
					data:[],
					cdata:[],
					list: []//单天日志信息
				}
			},
			components: {
				imtCalendar
			},
			//每次显示页面加载一次 日志日历填写列表
			onShow() {
				let _this = this;
				_this.show = false;
				_this.jzShow = false;
				_this.loginShow = false;
				uni.request({
					// url: _this.$store.state.basePath + '/daily/dailyCalendar',
					url: uni.getStorageSync('basePath') + '/daily/dailyCalendar',
					data: {
						// logId: _this.$store.state.userInfo.logId,
						logId: uni.getStorageSync('phoneData')
					},
					success:function(res){
						_this.data = res.data.data;
						_this.cdata = res.data.cdata;
						if(_this.data == undefined){
							_this.loginShow = true;//如果登录失效，打开重新登录，关闭数据加载中
							_this.jzShow = true;
						}else{
							if(_this.data.length>0){
								_this.show = true;
								_this.jzShow = true;
							}
						}
					},
					fail: (e) => {
						console.log(e.errMsg);
					}
				});
			},
			methods: {
				//根据单个日期加载单天的日志记录
				loadDetailByDate(val){
					this.tarDate = val;
					let _this = this;
					uni.request({
						url: uni.getStorageSync('basePath') + '/daily/queryDailyByDate',
						data: {
							logId: uni.getStorageSync('userInfo').logId,
							workDate: val
						},
						success: (res) => {
							if(_this.list.length>0){
								_this.list = _this.list.pop();
							}
							if (!res.data.success) { //如果失败提示信息
								uni.showToast({
									title: res.data.msg,
									icon: "none"
								})
								return;
							}
							_this.list = res.data.root;
						},
						fail: (e) => {
							console.log(e.errMsg);
						}
					});
				},
				//打开日志填写界面
				dailyAdd(){
					uni.navigateTo({
						url:'./dailyadd?workDate='+this.tarDate,
						animationType: 'slide-in-bottom',
						animationDuration: 300
					})
				},
				dailylist(){
					uni.navigateTo({
						url:'./dailylist',
						animationType: 'slide-in-bottom',
						animationDuration: 300
					})
				}
			}
		}
</script>
<style lang="scss" scoped>
page{
	background: #f2f2f2;
}
.content{
	padding-top: 30upx;
}
</style>
