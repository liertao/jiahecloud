<template>
	<view>
		<!-- <u-swiper :list="swiperList" height="350" ></u-swiper> -->
		<swiper class="swiper" indicator-dots="true" autoplay="true" interval="2000" duration="500">
			<swiper-item v-for="(item, index) in swiperList" :key="index">
				<image class="simage" :src="item.image"></image>
			</swiper-item>
		</swiper>
		<view style="margin: 10rpx 20rpx 0 20rpx;border: 1px solid #F6F7F8;" class="shadow">
			<view class="info" v-for="(item, index) in list" :key="index">
				<text>&nbsp;·&nbsp;{{item.content}}</text>
			</view>
		</view>
		<view v-for="(item,i) in falist" :key="i">
			<u-card class="shadow" :title=item.title :sub-title=item.subTitle :thumb="thumb">
				<view v-for="(iitem, j) in item.contentlist" :key="j" slot="body">
					<view class="u-body-item u-flex u-row-between u-p-b-0">
						<view class="u-body-item-title u-line-2">{{iitem.name}}</view>
						<u-icon name="arrow-right" size="28"></u-icon>
					</view>
				</view>
			</u-card>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				list:[{"content":"您有一条日志信息未填写"},{"content":"项目信息汇报"}],
				falist:[
					{
						title: '公告通知',
						subTitle: '2020-09-07',
						contentlist:[
							{name:"2020年国庆节放假通知"},
							{name:"考勤明细通知"},
							{name:"项目管理制度通知"}
						],
						thumb: 'http://pic2.sc.chinaz.com/Files/pic/pic9/202002/hpic2119_s.jpg',
					},
					{
						title: '项目进度信息',
						subTitle: '2020-09-07',
						contentlist:[
							{name:"159项目进度通知"},
							{name:"706项目进度通知"},
							{name:"202项目进度通知"}
						],
						thumb: 'http://pic2.sc.chinaz.com/Files/pic/pic9/202002/hpic2119_s.jpg',
					},
				],
				title: '公告通知',
				subTitle: '2020-09-07',
				thumb: 'http://pic2.sc.chinaz.com/Files/pic/pic9/202002/hpic2119_s.jpg',
				tabData: [{}, {}], //传入数据
				gridList: [{
					"name": '设备总数',
					"icon": 'none'
				}, {
					"name": '在用总数',
					"icon": 'none'
				}, {
					"name": '维修总数',
					"icon": 'none'
				}, {
					"name": '封存总数',
					"icon": 'none'
				}, {
					"name": '报废总数',
					"icon": 'none'
				}],
				swiperList: [{
						// image: '../../static/swipe/sb3.jpg',
						image: '../../static/swipe/sb3.jpg',
						title: ''
					},
					{
						// image: '../../static/swipe/sb2.jpg',
						image: '../../static/swipe/sb2.jpg',
						title: ''
					},
					{
						// image: '../../static/swipe/sb1.jpg',
						image: '../../static/swipe/sb1.jpg',
						title: ''
					}
				],
				disabled: false,
				show: false,
				options: [],
				filterResult: '',
				menuList: [{
						'title': '使用部门',
						'detailTitle': '请选则使用部门（可多选）',
						'isMutiple': true,
						'key': 'departmentCodes',
						'detailList': [{
							'title': '不限',
							'value': ''
						}]

					},
					{
						'title': '设备分类',
						'key': 'eqmTypes',
						'isMutiple': true,
						'detailTitle': '请选择设备分类（可多选）',
						'detailList': [{
							'title': '不限',
							'value': ''
						}]

					}
				]
			};
		},
		onShow() {
		},
		methods: {
		}
	};
</script>

<style lang="scss" scoped>
image {
	width: 120rpx;
	flex: 0 0 120rpx;
	height: 120rpx;
	margin-right: 20rpx;
	border-radius: 12rpx;
}
.simage {
	width: 750rpx;
	height: 100%;
	mode: scaleToFill
}
.title {
	text-align: left;
	font-size: 28rpx;
	color: $u-content-color;
	margin-top: 20rpx;
}
.info{
	font-size: 30upx;
	color: #8F8F94;
	font-weight: bold;
	background-color: #FFFFFF;
}
</style>
