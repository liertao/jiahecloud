<template>
	<view class="content">
		<u-sticky :offset-top="offsetTop" :enable="enable" @fixed="fixed">
			<view class="sticky">
				<sl-filter :themeColor="themeColor" :menuList="menuList" @result="result"></sl-filter>
			</view>
		</u-sticky>
		<!-- 功能块 -->
		<uni-grid :column="5" :show-border="false"  :square="true" :highlight="true" @change="openView">
			<uni-grid-item :index=1>
				<text class="text">设备总数</text>
				<text class="text">{{eqmsCount}}台</text>
			</uni-grid-item>
			<uni-grid-item :index=2>
				<text class="text">在用</text>
				<text class="text">{{zyEqmsCount}}台</text>
			</uni-grid-item>
			<uni-grid-item :index=3>
				<text class="text">维修</text>
				<text class="text">{{wxEqmsCount}}台</text>
			</uni-grid-item>
			<uni-grid-item :index=4>
				<text class="text">封存</text>
				<text class="text">{{fcEqmsCount}}台</text>
			</uni-grid-item>
			<uni-grid-item :index=5>
				<text class="text">报废</text>
				<text class="text">{{bfEqmsCount}}台</text>
			</uni-grid-item>
			<uni-grid-item :index=6>
				<text class="text">待处理故障</text>
				<text class="text">{{faultCount}}项</text>
			</uni-grid-item>
			<uni-grid-item :index=7>
				<text class="text">到期保养</text>
				<text class="text">{{byCount}}台</text>
			</uni-grid-item>
			<uni-grid-item :index=8>
				<text class="text">到期润滑</text>
				<text class="text">{{rhCount}}台</text>
			</uni-grid-item>
			<uni-grid-item :index=9>
				<text class="text">到期巡检</text>
				<text class="text">{{xjCount}}台</text>
			</uni-grid-item>
			<uni-grid-item :index=10>
				<text class="text">到期检定</text>
				<text class="text">{{jdCount}}台</text>
			</uni-grid-item>
		</uni-grid>
		<!-- 分割线 -->
		<view class="u-demo-area u-flex u-row-center">
			<u-line :border-style="borderStyle" color="red" :color="color" :length="length" :direction="direction" :hair-line="hairLine"></u-line>
		</view>
		<view class="u-demo-wrap">
			<view class="u-demo-area">
				<u-toast ref="uToast"></u-toast>
				<u-swipe-action
					bg-color="rgb(250, 250, 250)"
					@open="open"
					:disabled="disabled"
					:index="index"
					v-for="(item, index) in list"
					:key="item.id"
					:show="item.show"
					@click="click"
					:btn-width="btnWidth"
					@close="close"
					:options="options"
					@content-click="contentClick"
				>
					<view class="item u-border-bottom">
						<image mode="aspectFill" style="size: 100%;" :src="item.images" />
						<!-- 此层wrap在此为必写的，否则可能会出现标题定位错误 -->
						<view class="title-wrap">
							<text class="title u-line-2">{{ item.title }}</text>
						</view>
					</view>
				</u-swipe-action>
			</view>
		</view>
	</view>
</template>

<script>
	import slFilter from '@/components/sl-filter/sl-filter.vue';
	import uniGrid from "@/components/uni-grid/uni-grid.vue";
	import uniGridItem from "@/components/uni-grid-item/uni-grid-item.vue";
	import uniIcons from "@/components/uni-icons/uni-icons.vue";
	import uniCard from '@/components/uni-card/uni-card.vue';
	export default {
		components: {
			slFilter,
			uniGrid,
			uniGridItem,
			uniIcons,
			uniCard
		},
		data() {
			return {
				hasLogin: this.$store.state.hasLogin,
				eqmsCount: 1,
				zyEqmsCount: 0,
				wxEqmsCount: 0,
				fcEqmsCount: 0,
				bfEqmsCount: 0,
				faultCount: 0,
				byCount:0,
				rhCount: 0,
				xjCount: 0,
				jdCount: 0,
				offsetTop: 0,
				enable: true,
				direction: 'row',
				hairLine: true,
				length: '100%',
				color: this.$u.color['primary'],
				borderStyle: 'solid',
				list1: [
					{
						id: 1,
						title: '长安回望绣成堆，山顶千门次第开，一骑红尘妃子笑，无人知是荔枝来',
						images: '../../static/images/sb-green.png',
						show: false
					}
				],
				list: [],
				disabled: false,
				btnWidth: 120,
				show: false,
				themeColor:"#000000",
				options: [
					{
						text: '故障处理',
						style: {
							backgroundColor: '#007aff'
						}
					},
					{
						text: '保养',
						style: {
							backgroundColor: '#dd524d'
						}
					},
					{
						text: '润滑',
						style: {
							backgroundColor: '#007aff'
						}
					},
					{
						text: '巡检',
						style: {
							backgroundColor: '#dd524d'
						}
					}
				],
				filterResult: '',
				menuList: [{
						'title': '使用部门',
						'detailTitle': '请选则使用部门（可多选）',
						'isMutiple': true,
						'key': 'departmentCodes',
						'detailList': [
							{
								'title': '机加车间',
								'value': '3201'
							}
						]

					},
					{
						'title': '设备分类',
						'key': 'eqmTypes',
						'isMutiple': true,
						'detailTitle': '请选择设备分类（可多选）',
						'detailList': [
							{
								'title': '化工设备',
								'value': 'hg'
							}
						]

					}
				]
			}
		},
		onLoad() {
			// setTimeout(() => {
			// 	this.list = this.list1;
			// }, 0)
		},
		//用户加载条件
		onReady(){
			//发送ajax
			let _this = this;
			uni.request({
			    url: 'http://'+this.$store.state.baseIP+':'+this.$store.state.basePort+'/mes4.0/model/app/main/Main.Find.loadMainData.action',
			    data: {},
			    header: {
			        'Content-Type': 'application/json' //自定义请求头信息
			    },
			    success: (res) => {
					let obj;
					if(typeof res.data=="object"){
						obj = res.data;
					}else{
						obj = eval('('+res.data+')');
					} 
					if(!obj.success){//如果失败提示信息
						uni.showToast({
							title: obj.msg,
							icon: "none"
						})
					} 
					//填充查询条件
					_this.menuList[0].detailList = eval('('+res.data.deptList+')');
					_this.menuList[1].detailList = eval('('+res.data.eqmTypeList+')');
			    },
				fail: (e) => {
					console.log(e.errMsg);
				}
			});
		},
		onShow() {
			//发送ajax
			let _this = this;
			uni.request({
			    url: 'http://'+this.$store.state.baseIP+':'+this.$store.state.basePort+'/mes4.0/model/app/main/Main.Find.loadMainData.action',
			    data: {
					filterResult: this.filterResult
				},
			    header: {
			        'Content-Type': 'application/json' //自定义请求头信息
			    },
			    success: (res) => {
					let obj;
					if(typeof res.data=="object"){
						obj = res.data;
					}else{
						obj = eval('('+res.data+')');
					} 
					if(!obj.success){//如果失败提示信息
						uni.showToast({
							title: obj.msg,
							icon: "none"
						})
					}
					//否则修改值
					_this.eqmsCount = res.data.eqmsCount;
					_this.zyEqmsCount = res.data.zyEqmsCount;
					_this.wxEqmsCount = res.data.wxEqmsCount;
					_this.fcEqmsCount = res.data.fcEqmsCount;
					_this.bfEqmsCount = res.data.bfEqmsCount;
					_this.faultCount = res.data.faultCount;
					_this.byCount = res.data.byCount;
					_this.rhCount = res.data.rhCount;
					_this.xjCount = res.data.xjCount;
					_this.jdCount = res.data.jdCount;
					_this.list = eval('('+res.data.root+')');
			    },
				fail: (e) => {
					console.log(e.errMsg);
				}
			});
		},
		methods: {
			result(val) {
				let _this = this;
				console.log('filter_result:' + JSON.stringify(val));
				// alert(JSON.stringify(val));
				this.filterResult = JSON.stringify(val, null, 2);
				uni.request({
				    url: 'http://'+this.$store.state.baseIP+':'+this.$store.state.basePort+'/mes4.0/model/app/main/Main.Find.loadMainData.action',
				    data: {
				        filterResult: this.filterResult
				    },
				    header: {
				        'Content-Type': 'application/json' //自定义请求头信息
				    },
				    success: (res) => {
						let obj;
						if(typeof res.data=="object"){
							obj = res.data;
						}else{
							obj = eval('('+res.data+')');
						} 
						if(!obj.success){
							uni.showToast({
								title: obj.msg,
								icon: "none"
							})
						}
						//否则修改值
						_this.eqmsCount = res.data.eqmsCount;
						_this.zyEqmsCount = res.data.zyEqmsCount;
						_this.wxEqmsCount = res.data.wxEqmsCount;
						_this.fcEqmsCount = res.data.fcEqmsCount;
						_this.bfEqmsCount = res.data.bfEqmsCount;
						_this.faultCount = res.data.faultCount;
						_this.byCount = res.data.byCount;
						_this.rhCount = res.data.rhCount;
						_this.xjCount = res.data.xjCount;
						_this.jdCount = res.data.jdCount;
						_this.list = eval('('+res.data.root+')');
				    },
					fail: (e) => {
						console.log(e.errMsg);
					}
				});
			},
			isSwiperChange(index) {
				this.isSwiper = index == 0 ? true : false;
			},
			borderChange(index) {
				this.border = index == 0 ? true : false;
			},
			colChange(index) {
				this.col = index == 0 ? 3 : 4;
			},
			click(index, index1) {
				if(index1==0){//打开故障列表
					uni.navigateTo({
					    url: '../list/faultlist?filterResult='+this.filterResult+'&eqmNum='+this.list[index].eqmNum
					});
				}else if(index1==1){//打开保养列表
					// uni.navigateTo({
					//     url: '../list/lubricationlist?filterResult='+this.filterResult
					// });
				}else if(index1==2){//打开润滑列表
					uni.navigateTo({
					    url: '../list/lubricationlist?filterResult='+this.filterResult+'&eqmNum='+this.list[index].eqmNum
					});
				}else if(index1==3){//打开巡检列表
					uni.navigateTo({
					    url: '../list/checkoutlist?filterResult='+this.filterResult+'&eqmNum='+this.list[index].eqmNum
					});
				}
				// if(index1 == 1) {
				// 	this.list.splice(index, 1);
				// 	this.$u.toast(`删除了第${index}个cell`);
				// } else {
				// 	this.list[index].show = false;
				// 	this.$u.toast(`收藏成功`);
				// }
			},
			change(e) {
				this.current = e.detail.current;
			},
			// 针对单个grid-item的事件
			itemClick(index) {
				console.log(index);
			},
			offsetTopChange(index) {
				this.offsetTop = index == 0 ? 0 : index == 1 ? 120 : 200;
				uni.pageScrollTo({
					scrollTop: 0,
					duration: 0
				})
			},
			enableChange(index) {
				this.enable = index == 0 ? true : false;
			},
			fixed() {
				// this.$refs.uToast.show({
				// 	type: 'warning',
				// 	title: '触发吸顶'
				// })
			},
			openView(e){
				// 如果没有登录，返回登录界面
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
				if(e.detail.index==6){//待处理故障列表
					uni.navigateTo({
					    url: '../list/faultlist?filterResult='+this.filterResult
					});
				}
				if(e.detail.index==8){//打开润滑按钮
					uni.navigateTo({
					    url: '../list/lubricationlist?filterResult='+this.filterResult
					});
				}
				if(e.detail.index==9){//打开设备巡检实施
					uni.navigateTo({
					    url: '../list/checkoutlist?filterResult='+this.filterResult
					});
				}
			}
		}
	}
</script>

<style scoped lang="scss">
.text{
	color: #8F8F94;
	font-size: 25upx;
	text-align: center;
	font-family: "微软雅黑";
	padding-top: 16upx;
}
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
// 吸顶样式
.u-demo-area {
	overflow: hidden;
}

.u-config-wrap {
	height: 100vh;
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
