<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="学校资讯"
				source_table="article"
			  ></list_result_search>
				<!-- 论坛搜索结果 -->
			  <list_result_search
				:list="result_forum"
				title="交流中心"
				source_table="forum"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student_user/list', 'get')"
				:list="result_student_user_full_name"
				title="学生用户姓名"
				source_table="student_user"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/student_user/list', 'get')"
				:list="result_student_user_gender"
				title="学生用户性别"
				source_table="student_user"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/school_information/list', 'get')"
				:list="result_school_information_school_name"
				title="学校信息学校名称"
				source_table="school_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/school_information/list', 'get')"
				:list="result_school_information_city"
				title="学校信息所属城市"
				source_table="school_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/city_classification/list', 'get')"
				:list="result_city_classification_city"
				title="城市分类所属城市"
				source_table="city_classification"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
	  "result_forum": [],
			"result_student_user_full_name":[],
			"result_student_user_gender":[],
			"result_school_information_school_name":[],
			"result_school_information_city":[],
			"result_city_classification_city":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取交流中心
	 */
	get_forum() {
	  this.$get("~/api/forum/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_forum = json.result.list;
		}
	  });
	},
	/**
	 * 获取full_name
	 */
	get_student_user_full_name(){
		this.$get("~/api/student_user/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_user_full_name = json.result.list;
			result_student_user_full_name.map(o => o.title = o['full_name'])
	  			this.result_student_user_full_name = result_student_user_full_name
		 	}
		});
	},
	/**
	 * 获取gender
	 */
	get_student_user_gender(){
		this.$get("~/api/student_user/get_list?like=0", { page: 1, size: 10, "gender": this.query.word }, (json) => {
		  if (json.result) {
			var result_student_user_gender = json.result.list;
			result_student_user_gender.map(o => o.title = o['gender'])
	  			this.result_student_user_gender = result_student_user_gender
		 	}
		});
	},
	/**
	 * 获取school_name
	 */
	get_school_information_school_name(){
		this.$get("~/api/school_information/get_list?like=0", { page: 1, size: 10, "school_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_school_information_school_name = json.result.list;
			result_school_information_school_name.map(o => o.title = o['school_name'])
	  			this.result_school_information_school_name = result_school_information_school_name
		 	}
		});
	},
	/**
	 * 获取city
	 */
	get_school_information_city(){
		this.$get("~/api/school_information/get_list?like=0", { page: 1, size: 10, "city": this.query.word }, (json) => {
		  if (json.result) {
			var result_school_information_city = json.result.list;
			result_school_information_city.map(o => o.title = o['city'])
	  			this.result_school_information_city = result_school_information_city
		 	}
		});
	},
	/**
	 * 获取city
	 */
	get_city_classification_city(){
		this.$get("~/api/city_classification/get_list?like=0", { page: 1, size: 10, "city": this.query.word }, (json) => {
		  if (json.result) {
			var result_city_classification_city = json.result.list;
			result_city_classification_city.map(o => o.title = o['city'])
	  			this.result_city_classification_city = result_city_classification_city
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
	this.get_forum();
		this.get_student_user_full_name();
		this.get_student_user_gender();
		this.get_school_information_school_name();
		this.get_school_information_city();
		this.get_city_classification_city();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_forum();
	  this.get_student_user_full_name();
	  this.get_student_user_gender();
	  this.get_school_information_school_name();
	  this.get_school_information_city();
	  this.get_city_classification_city();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
