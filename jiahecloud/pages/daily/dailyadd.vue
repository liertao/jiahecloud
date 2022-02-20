<template>
	<view>
		<view class="wrap">
			<u-form :model="model" :rules="rules" ref="uForm" :errorType="errorType">
				<u-form-item class="from-item-height" label-width="150" :label-position="labelPosition" label="项目" prop="projectName">
					<u-input @click="choseproject" :border="border" placeholder="请选择项目" v-model="model.projectName" type="text"></u-input>
				</u-form-item>
				<u-form-item class="from-item-height" label-width="150" :label-position="labelPosition" label="成本项" prop="costName">
					<u-input @click="chosecosttype" type="text" :border="border" placeholder="请选择成本项" v-model="model.costName" />
				</u-form-item>
				<u-form-item class="from-item-height" label-width="150" :label-position="labelPosition" label="工时" prop="costHours">
					<u-input type="number" :border="border" placeholder="请输入工时" v-model="model.costHours" />
				</u-form-item>
				<u-form-item class="from-item-height" label-width="150" :label-position="labelPosition" label="单价" prop="unitCost">
					<u-input type="number" disabled :border="border" placeholder="请输入单价" v-model="model.unitCost" />
				</u-form-item>
				<u-form-item class="from-item-height" label-width="150" :label-position="labelPosition" label="金额" prop="total">
					<u-input disabled type="number" :border="border" placeholder="请输入金额" v-model="total" />
				</u-form-item>
				<u-form-item class="from-item-height" label-width="150" :label-position="labelPosition" label="姓名" prop="createName">
					<u-input :border="border" disabled placeholder="请输入姓名" v-model="model.createName" type="text"></u-input>
				</u-form-item>
				<u-form-item class="from-item-height" :label-position="labelPosition" label="工作日期" prop="workDate" label-width="150">
					<u-calendar v-model="show" :mode="mode1" @change="changeMake"></u-calendar>
					<u-input :border="border" placeholder="请输入工作日期" v-model="model.workDate" type="text" style="float: left;width: 90%;"></u-input>
					<u-icon name="calendar" color="#19be6b" size="44" @click="show = true" style="float: left;width: 8%;"></u-icon>
				</u-form-item>
				<u-form-item class="from-item-height" :label-position="labelPosition" label="工作地点" prop="workAdd" label-width="150">
					<u-input :border="border" type="select" :select-open="selectShow" v-model="model.workAdd" placeholder="请选择日期"
					 @click="selectShow = true"></u-input>
				</u-form-item>
				<u-form-item label-width="150" :label-position="labelPosition" label="费用说明" prop="comments">
					<u-input :border="border" placeholder="请输入费用说明" v-model="model.comments" type="textarea"></u-input>
				</u-form-item>
			</u-form>
			<u-button type="success" :loading="loading" @click="submit">提交</u-button>
			<u-select mode="single-column" :list="selectList" v-model="selectShow" @confirm="selectConfirm"></u-select>
		</view>
	</view>
</template>

<script>
	let that = this;
	export default {
		data() {
			const currentDate = this.getDate({
				format: true
			})
			return {
				show: false,
				mode: 'selector',
				mode1: 'date',
				defaultTime: '2019-12-11 20:15:35',
				params: {
					year: true,
					month: true,
					day: true,
					hour: true,
					minute: true,
					second: true,
					province: true,
					city: true,
					area: true,
					timestamp: true
				},
				model: {
					projectNo:'',
					projectName:'',
					costType:'1',
					costName:'开发费',
					costHours:8,
					unitCost: 200,
					// createName: this.$store.state.userInfo.logName,
					createName: uni.getStorageSync("userInfo").logName,
					comments:'',
					workAdd: '公司',
					workDate: currentDate,
					eqmNum: '',
					exceptions: '',
					applyPerson: '',
					applyPersonName: '',
					date: currentDate, //系统当前时间
				},
				selectList: [{
					value: '公司',
					label: '公司'
				},{
					value: '项目单位',
					label: '项目单位'
				},{
					value: '其他',
					label: '其他'
				}],
				rules: {
					projectName: [{
						required: true,
						message: '请填写项目名称',
						trigger: 'blur'
					}],
					costHours: [{
						required: true,
						message: '请填写工时'
					}],
					workAdd: [{
						required: true,
						message: '请选择工作地点',
						trigger: 'change'
					}],
					comments: [{
						required: true,
						message: '请填写费用明细',
						trigger: 'change'
					}]
				},
				border: false,
				selectShow: false,
				labelPosition: 'left',
				errorType: ['toast'],
				loading: false
			}
		},
		computed: {
			total() {
				return this.model.costHours*this.model.unitCost;
			}
		},
		onShow() {
			let _this = this;
			uni.$on("handClickXXX", res => {
				//项目选择
				if(res.projectName!="" && res.projectNo!="" && res.projectName!=undefined && res.projectNo!=undefined){
					_this.model.projectName = res.projectName;
					_this.model.projectNo = res.projectNo;
				}else if(res.costType!="" && res.costName!="" && res.costType!=undefined && res.costName!=undefined){
					_this.model.costType = res.costType;
					_this.model.costName = res.costName;
				}
			    // 清除监听
			    uni.$off('handClickXXX');
			});
		},
		onLoad: function(option) {
			//如果日期大于今天 改为今天
			if(option.workDate != undefined){
				if(option.workDate.replace(/-/g,"") - this.model.date.replace(/-/g,"")>0){
					this.model.workDate = this.model.date;
				}else{
					this.model.workDate = option.workDate;
				}
			}
		},
		onReady() {
			this.$refs.uForm.setRules(this.rules);
		},
		methods: {
			scan() {
				// 调起条码扫描
				uni.scanCode({
					scanType: ['barCode'],
					success: function(res) {
						this.model.eqmNum = res.result;
						// console.log('条码类型：' + res.scanType);
						// console.log('条码内容：' + res.result);
					}
				});
			},
			//选择工作日期
			changeMake(e) {
				this.model.workDate = e.result;
			},
			getDate() {
				const date = new Date();
				let year = date.getFullYear();
				let month = date.getMonth() + 1;
				let day = date.getDate();
				month = month > 9 ? month : '0' + month;;
				day = day > 9 ? day : '0' + day;
				return `${year}-${month}-${day}`;
			},
			submit() {
				let _this = this;
				_this.loading = true;
				this.$refs.uForm.validate(valid => {
					if (valid) {
						console.log('验证通过');
						//发送ajax
						uni.request({
							url: uni.getStorageSync('basePath') + '/daily/add',
							// url: this.$store.state.basePath + '/daily/add',
							data: {
								projectNo: _this.model.projectNo,
								costType: _this.model.costType,
								costHours: _this.model.costHours,
								createUser: uni.getStorageSync("userInfo").logId,
								createDate: _this.model.date,
								workDate:_this.model.workDate,
								workAdd:_this.model.workAdd,
								unitCost:_this.model.unitCost,
								comments:_this.model.comments
							},
							success: (res) => {
								if (!res.data.success) { //如果失败提示信息
									uni.showToast({
										title: res.data.msg,
										icon: "none"
									})
									_this.loading = false;
									return;
								}
								uni.showToast({
									title: '保存成功',
									icon: "none"
								})
								_this.loading = false;
								uni.navigateBack();
								return;
							},
							fail: (e) => {
								_this.loading = false;
								console.log(e.errMsg);
							}
						});
					} else {
						_this.loading = false;
						console.log('验证失败');
					}
				});
			},
			// 选择工作地点
			selectConfirm(e) {
				this.model.workAdd = '';
				e.map((val, index) => {
					this.model.workAdd = val.value;
				})
			},
			//选则项目
			choseproject(){
				uni.navigateTo({
					url: '../helpinput/projecthelpinput',
					animationType: "slide-in-bottom",
					animationDuration: 300
				});
			},
			//选则费用类型
			chosecosttype(){
				uni.navigateTo({
					url: '../helpinput/costtypehelpinput',
					animationType: "slide-in-bottom",
					animationDuration: 300
				});
			},
			//返回按钮
			back() {
				uni.navigateBack();
			}
		}
	}
</script>

<style scoped lang="scss">
.wrap {
	padding: 30rpx;
}
page{
	background-color: #FFFFFF;
}
</style>
