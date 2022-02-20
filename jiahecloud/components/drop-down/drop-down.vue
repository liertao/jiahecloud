<template>
	<view>
		<view @click.stop>
			<view :class="!viewShow?'nav':'nav-open'">
				<u-icon name="arrow-down" size="40" @click="openDown"></u-icon>
			</view>

			<view class="view-class" v-if="viewShow" style="z-index: 10071;">
				<u-row>
					<u-col :span="12">
						<u-subsection :current="curNow" :list="tabData" active-color="#606266" inactive-color="#303133" @change="changeSub"></u-subsection>
						<view v-for="(item,index) in tabData" :key="index" v-if="index === curNow">
							<u-button v-for="(item1,index1) in item.fiterList" :key="index1" class="tab-class" :type="isShow(item,item1)"
							 :ripple="true" shape="circle" size="medium" @click="chooseTab(item,index1,item1)">{{item1.title}}</u-button>
						</view>
					</u-col>
				</u-row>
				<u-row style="margin-top: 15px;">
					<u-col :span="6">
						<u-button type="error" shape="circle" @click="openDown">取消</u-button>
					</u-col>
					<u-col :span="6">
						<u-button type="primary" shape="circle" @click="tSure">确定</u-button>
					</u-col>
				</u-row>
			</view>
		</view>
		<u-mask :show="viewShow" @click="viewShow = false" :zoom="false" style="position: fixed;top: 60px;" v-if="viewShow"></u-mask>
	</view>
</template>

<script>
	export default {
		props: {
			tabData: {
				type: Array
			}
		},
		data() {
			return {
				viewShow: false,
				buttonShow: [{
					fiterKey: null,
					fiterValue: []
				}, {
					fiterKey: null,
					fiterValue: []
				}],
				curNow: 0,
			}
		},
		methods: {
			isShow(item, item1) {
				if (item.fiterKey == "departmentCodes") {
					if (this.buttonShow[0].fiterValue.indexOf(item1.value) > -1) {
						return "primary";
					} else {
						return "default";
					}
				}
				if (item.fiterKey == "eqmTypes") {
					if (this.buttonShow[1].fiterValue.indexOf(item1.value) > -1) {
						return "primary";
					} else {
						return "default";
					}
				}
			},
			openDown() {
				this.viewShow = !this.viewShow;
			},
			tSure() {
				const filterResult = "{\"" + this.buttonShow[0].fiterKey + "\":" + JSON.stringify(this.buttonShow[0].fiterValue) +
					",\"" +
					this.buttonShow[1].fiterKey + "\":" + JSON.stringify(this.buttonShow[1].fiterValue) + "}"
				console.log(filterResult);
				this.$emit("drop-sure", filterResult);
				this.viewShow = !this.viewShow;
			},
			chooseTab(item, index1, item1) {
				const value = item1.value,
					index = index1;
				this.buttonShow.forEach((items, i) => {
					if (i == 0) {
						items.fiterKey = "departmentCodes";
						if (item.fiterKey == "departmentCodes") {
							if (index == 0) {
								this.$set(item, "fiterValue", []);
							} else {
								if (items.fiterValue.indexOf(value) > -1) {
									this.$delete(item.fiterValue, items.fiterValue.indexOf(value));
								} else {
									if (value != "" || value != null) {
										this.$set(item.fiterValue, items.fiterValue.length, value);
									}
								}
							}
							items.fiterValue = item.fiterValue;
						}
					} else {
						items.fiterKey = "eqmTypes";
						if (item.fiterKey == "eqmTypes") {
							if (index == 0) {
								this.$set(item, "fiterValue", []);
							} else {
								if (items.fiterValue.indexOf(value) > -1) {
									this.$delete(item.fiterValue, items.fiterValue.indexOf(value));
								} else {
									if (value != "" || value != null) {
										this.$set(item.fiterValue, items.fiterValue.length, value);
									}
								}
							}
							items.fiterValue = item.fiterValue;
						}
					}
				})
				console.log(JSON.stringify(this.buttonShow));
			},
			changeSub(val) {
				this.curNow = val;
			}
		}
	}
</script>

<style scoped>
	.warp {
		display: flex;
		align-items: center;
		justify-content: center;
		height: 100%;
	}

	.rect {
		width: 120px;
		height: 120px;
		background-color: #fff;
	}

	.nav {
		padding: 15px;
		transition-duration: 0.3s;
	}

	.nav-open {
		padding: 15px;
		transform: rotate(180deg);
		transition-duration: 0.3s;
	}

	.view-class {
		box-shadow: 0 0 5px #c5c5c5;
		position: absolute;
		background: #FFFFFF;
		width: 100%;
	}

	.tab-class {
		margin: 15px 5px 0 0;
	}

	.view-class::after {
		content: ' ';
		height: 10px;
		display: block;
	}
</style>
