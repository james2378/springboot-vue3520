<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','student_information') || $check_field('add','student_information') || $check_field('set','student_information')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="学生信息" prop="student_information">
						<el-select v-if="user_group === '管理员' || (form['recommended_information_id'] && $check_field('set','student_information')) || (!form['recommended_information_id'] && $check_field('add','student_information'))" id="student_information" v-model="form['student_information']" :disabled="disabledObj['student_information_isDisabled']">
							<el-option v-for="o in list_user_student_information" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','student_information')" id="student_information" v-model="form['student_information']" :disabled="true">
							<el-option v-for="o in list_user_student_information" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','first_recommendation') || $check_field('add','first_recommendation') || $check_field('set','first_recommendation')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="第一推荐" prop="first_recommendation">
					<el-input type="textarea" id="first_recommendation" v-model="form['first_recommendation']" placeholder="请输入第一推荐"
						v-if="user_group === '管理员' || (form['recommended_information_id'] && $check_field('set','first_recommendation')) || (!form['recommended_information_id'] && $check_field('add','first_recommendation'))" :disabled="disabledObj['first_recommendation_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','first_recommendation')">{{form['first_recommendation']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','second_recommendation') || $check_field('add','second_recommendation') || $check_field('set','second_recommendation')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="第二推荐" prop="second_recommendation">
					<el-input type="textarea" id="second_recommendation" v-model="form['second_recommendation']" placeholder="请输入第二推荐"
						v-if="user_group === '管理员' || (form['recommended_information_id'] && $check_field('set','second_recommendation')) || (!form['recommended_information_id'] && $check_field('add','second_recommendation'))" :disabled="disabledObj['second_recommendation_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','second_recommendation')">{{form['second_recommendation']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','third_recommendation') || $check_field('add','third_recommendation') || $check_field('set','third_recommendation')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="第三推荐" prop="third_recommendation">
					<el-input type="textarea" id="third_recommendation" v-model="form['third_recommendation']" placeholder="请输入第三推荐"
						v-if="user_group === '管理员' || (form['recommended_information_id'] && $check_field('set','third_recommendation')) || (!form['recommended_information_id'] && $check_field('add','third_recommendation'))" :disabled="disabledObj['third_recommendation_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','third_recommendation')">{{form['third_recommendation']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','remark_information') || $check_field('add','remark_information') || $check_field('set','remark_information')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="备注信息" prop="remark_information">
					<el-input type="textarea" id="remark_information" v-model="form['remark_information']" placeholder="请输入备注信息"
						v-if="user_group === '管理员' || (form['recommended_information_id'] && $check_field('set','remark_information')) || (!form['recommended_information_id'] && $check_field('add','remark_information'))" :disabled="disabledObj['remark_information_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','remark_information')">{{form['remark_information']}}</div>
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
				field: "recommended_information_id",
				url_add: "~/api/recommended_information/add?",
				url_set: "~/api/recommended_information/set?",
				url_get_obj: "~/api/recommended_information/get_obj?",
				url_upload: "~/api/recommended_information/upload?",

				query: {
					"recommended_information_id": 0,
				},

				form: {
					"student_information": 0, // 学生信息
					"first_recommendation":'', // 第一推荐
					"second_recommendation":'', // 第二推荐
					"third_recommendation":'', // 第三推荐
					"remark_information":'', // 备注信息
					"recommended_information_id": 0, // ID

				},
				disabledObj:{
					"student_information_isDisabled": false,
					"first_recommendation_isDisabled": false,
					"second_recommendation_isDisabled": false,
					"third_recommendation_isDisabled": false,
					"remark_information_isDisabled": false,
				},
				// 用户列表
				list_user_student_information: [],

			}
		},
		methods: {
			/**
			 * 获取学生用户用户列表
			 */
			async get_list_user_student_information() {
                // if(this.user_group !== "管理员" && this.form["student_information"] === 0) {
                //     this.form["student_information"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=学生用户");
                if(json.result && json.result.list){
                    this.list_user_student_information = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			get_user_student_information(id){
				var obj = this.list_user_student_information.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "学生信息":
							if(param["student_information"] > 0){
								param["student_information"] = this.user.user_id;
							}
							break;
					}
				}
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
					bl = this.$check_action('/recommended_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/recommended_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/recommended_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/recommended_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/recommended_information/view','get');
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
			this.get_list_user_student_information();
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
