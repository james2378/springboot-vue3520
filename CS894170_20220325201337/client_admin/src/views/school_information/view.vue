<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','school_name') || $check_field('add','school_name') || $check_field('set','school_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学校名称" prop="school_name">
					<el-input id="school_name" v-model="form['school_name']" placeholder="请输入学校名称"
							  v-if="user_group === '管理员' || (form['school_information_id'] && $check_field('set','school_name')) || (!form['school_information_id'] && $check_field('add','school_name'))" :disabled="disabledObj['school_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','school_name')">{{form['school_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','school_ranking') || $check_field('add','school_ranking') || $check_field('set','school_ranking')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学校排名" prop="school_ranking">
					<el-input id="school_ranking" v-model="form['school_ranking']" placeholder="请输入学校排名"
							  v-if="user_group === '管理员' || (form['school_information_id'] && $check_field('set','school_ranking')) || (!form['school_information_id'] && $check_field('add','school_ranking'))" :disabled="disabledObj['school_ranking_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','school_ranking')">{{form['school_ranking']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','city') || $check_field('add','city') || $check_field('set','city')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="所属城市" prop="city">
					<el-select id="city" v-model="form['city']"
						v-if="user_group === '管理员' || (form['school_information_id'] && $check_field('set','city')) || (!form['school_information_id'] && $check_field('add','city'))">
						<el-option v-for="o in list_city" :key="o['city']" :label="o['city']"
							:value="o['city']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','city')">{{form['city']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','founding_year_') || $check_field('add','founding_year_') || $check_field('set','founding_year_')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="建校时间" prop="founding_year_">
					<el-input id="founding_year_" v-model="form['founding_year_']" placeholder="请输入建校时间"
							  v-if="user_group === '管理员' || (form['school_information_id'] && $check_field('set','founding_year_')) || (!form['school_information_id'] && $check_field('add','founding_year_'))" :disabled="disabledObj['founding_year__isDisabled']"></el-input>
					<div v-else-if="$check_field('get','founding_year_')">{{form['founding_year_']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','school_pictures') || $check_field('add','school_pictures') || $check_field('set','school_pictures')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学校图片" prop="school_pictures">
					<el-upload :disabled="disabledObj['school_pictures_isDisabled']" id="school_pictures" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_school_pictures"
						:show-file-list="false" v-if="user_group === '管理员' || (form['school_information_id'] && $check_field('set','school_pictures')) || (!form['school_information_id'] && $check_field('add','school_pictures'))">
						<img v-if="form['school_pictures']" :src="$fullUrl(form['school_pictures'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','school_pictures')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['school_pictures'])" :preview-src-list="[$fullUrl(form['school_pictures'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','enrollment_score_line') || $check_field('add','enrollment_score_line') || $check_field('set','enrollment_score_line')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="招生分数线" prop="enrollment_score_line">
					<el-input id="enrollment_score_line" v-model="form['enrollment_score_line']" placeholder="请输入招生分数线"
							  v-if="user_group === '管理员' || (form['school_information_id'] && $check_field('set','enrollment_score_line')) || (!form['school_information_id'] && $check_field('add','enrollment_score_line'))" :disabled="disabledObj['enrollment_score_line_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','enrollment_score_line')">{{form['enrollment_score_line']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','enrollment_time') || $check_field('add','enrollment_time') || $check_field('set','enrollment_time')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="招生时间" prop="enrollment_time">
					<el-input id="enrollment_time" v-model="form['enrollment_time']" placeholder="请输入招生时间"
							  v-if="user_group === '管理员' || (form['school_information_id'] && $check_field('set','enrollment_time')) || (!form['school_information_id'] && $check_field('add','enrollment_time'))" :disabled="disabledObj['enrollment_time_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','enrollment_time')">{{form['enrollment_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','consultation_telephone') || $check_field('add','consultation_telephone') || $check_field('set','consultation_telephone')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="咨询电话" prop="consultation_telephone">
					<el-input id="consultation_telephone" v-model="form['consultation_telephone']" placeholder="请输入咨询电话"
							  v-if="user_group === '管理员' || (form['school_information_id'] && $check_field('set','consultation_telephone')) || (!form['school_information_id'] && $check_field('add','consultation_telephone'))" :disabled="disabledObj['consultation_telephone_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','consultation_telephone')">{{form['consultation_telephone']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','school_address') || $check_field('add','school_address') || $check_field('set','school_address')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学校地址" prop="school_address">
					<el-input type="textarea" id="school_address" v-model="form['school_address']" placeholder="请输入学校地址"
						v-if="user_group === '管理员' || (form['school_information_id'] && $check_field('set','school_address')) || (!form['school_information_id'] && $check_field('add','school_address'))" :disabled="disabledObj['school_address_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','school_address')">{{form['school_address']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','school_introduction') || $check_field('add','school_introduction') || $check_field('set','school_introduction')" :xs="24" :sm="24" :lg="24">
				<el-form-item label="学校介绍" prop="school_introduction">
					<quill-editor v-model.number="form['school_introduction']"
						v-if="user_group === '管理员' || (form['school_information_id'] && $check_field('set','school_introduction')) || (!form['school_information_id'] && $check_field('add','school_introduction')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','school_introduction')" v-html="form['school_introduction']"></div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "school_information_id",
				url_add: "~/api/school_information/add?",
				url_set: "~/api/school_information/set?",
				url_get_obj: "~/api/school_information/get_obj?",
				url_upload: "~/api/school_information/upload?",

				query: {
					"school_information_id": 0,
				},

				form: {
					"school_name":'', // 学校名称
					"school_ranking":'', // 学校排名
					"city":'', // 所属城市
					"founding_year_":'', // 建校时间
					"school_pictures":'', // 学校图片
					"enrollment_score_line":'', // 招生分数线
					"enrollment_time":'', // 招生时间
					"consultation_telephone":'', // 咨询电话
					"school_address":'', // 学校地址
					"school_introduction":'', // 学校介绍
					"school_information_id": 0, // ID

				},
				disabledObj:{
					"school_name_isDisabled": false,
					"school_ranking_isDisabled": false,
					"city_isDisabled": false,
					"founding_year__isDisabled": false,
					"school_pictures_isDisabled": false,
					"enrollment_score_line_isDisabled": false,
					"enrollment_time_isDisabled": false,
					"consultation_telephone_isDisabled": false,
					"school_address_isDisabled": false,
					"school_introduction_isDisabled": false,
				},
				//所属城市选项列表
				list_city: [],

			}
		},
		methods: {
			/**
			 * 获取所属城市列表
			 */
			async get_list_city() {
				var json = await this.$get("~/api/city_classification/get_list?");
				if(json.result && json.result.list){
					this.list_city = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			/**
			 * 上传学校图片
			 * @param {Object} param图片参数
			 */
			upload_school_pictures(param){
				this.uploadFile(param.file, "school_pictures");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/school_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/school_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/school_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/school_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/school_information/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
			this.get_list_city();
		}
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
