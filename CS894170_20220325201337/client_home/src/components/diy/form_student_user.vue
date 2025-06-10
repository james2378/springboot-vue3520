<template>
	<div>
		<b-form-group v-if="$check_register_field('add','full_name','/student_user/view')" id="input-group-0" label="姓名" label-for="input-0">
			<b-form-input id="input-0" v-model="form['full_name']" type="text" placeholder="姓名" trim></b-form-input>
		</b-form-group>
		<b-form-group v-if="$check_register_field('add','gender','/student_user/view')" id="input-group-1" label="性别" label-for="input-1">
			<b-form-select id="input-1" v-model="form['gender']" :options="getDropList(list_gender)"></b-form-select>
		</b-form-group>
		<b-form-group v-if="$check_register_field('add','test_score','/student_user/view')" id="input-group-2" label="考试分数" label-for="input-2">
			<b-form-input id="input-2" v-model="form['test_score']" type="text" placeholder="考试分数" trim></b-form-input>
		</b-form-group>
		<b-form-group v-if="$check_register_field('add','japanese_level','/student_user/view')" id="input-group-3" label="日语等级" label-for="input-3">
			<b-form-input id="input-3" v-model="form['japanese_level']" type="text" placeholder="日语等级" trim></b-form-input>
		</b-form-group>
		<b-form-group v-if="$check_register_field('add','toeic_score','/student_user/view')" id="input-group-4" label="托业分数" label-for="input-4">
			<b-form-input id="input-4" v-model="form['toeic_score']" type="text" placeholder="托业分数" trim></b-form-input>
		</b-form-group>
		<b-form-group v-if="$check_register_field('add','intended_major','/student_user/view')" id="input-group-5" label="意向专业" label-for="input-5">
			<b-form-input id="input-5" v-model="form['intended_major']" type="text" placeholder="意向专业" trim></b-form-input>
		</b-form-group>
	</div>
</template>

<script>
	import mixin from "@/mixins/component.js";

	export default {
		mixins: [mixin],
		model: {
			prop: "form",
			event: "change"
		},
		props: {
			form: {
				type: Object,
				default: () => {
					return {

					}
				}
			}
		},
		data(){
			return {
				url_upload: "~/api/student_user/upload?",
						list_gender: "男,女",
			}
		},
		methods: {
			// 下拉数据列表转换
			getDropList(e){
				let resultArr = [];
				if(e){
					let arr = e.split(",");
					arr.forEach(item => {
						resultArr.push({
							value: item,
							text: item
						})
					});
				}
				return resultArr;
			},
			/**
			 * 上传图片
			 * @param {Object} file 文件对象
			 * @param {key} 保存键名
			 */
			uploadFile(file, key = "img") {
				var _this = this;
				var form = new FormData() // FormData 对象
				form.append('file', file[0]) // 文件对象
				this.$upload(this.url_upload, form, function(json) {
					if (json.result) {
						// _this.form[key] = json.result.url;
						_this.$delete(_this.form,key);
						_this.$set(_this.form,key,json.result.url);
						console.log(_this.form[key])
					} else {
						_this.$toast('上传失败！');
					}
				});
			},
		}
	}
</script>

<style>
</style>
