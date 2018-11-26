<template>
	<div class="page-dataDictionary">
    <!-- 查询条件 -->
		<searchInputItems>
			      <searchInputItem name="字典分类">
									<el-select
								    v-model="searchForm.claCode"
								    filterable
								    remote
								    reserve-keyword
								    placeholder="请输入字典分类"
								    @change = "dataCategoryChange"
								    :remote-method="getDataCategory"
								    :loading="loading">
								    <el-option
								      v-for="item in dataCategory"
								      :key="item.value"
								      :label='item.value==""?item.label:(item.label+"  ("+item.value+")")'
								      :value="item.value">
								    </el-option>
								  </el-select>
			      </searchInputItem>
			<!-- <searchInputItem name="字典分类">
				<selectInput :value.sync="searchForm.claCode" @selectChange="searchTable" :clearable="true">
					<el-option
				      v-for="item in dataCategory"
				      :key="item.key"
				      :label="item.value"
				      :value="item.key">
				    </el-option>
				</selectInput>
			</searchInputItem> -->
			<searchInputItem name="字典编码">
				<inputItem :value.sync="searchForm.dicCode" @enter="searchTable"></inputItem>
			</searchInputItem>
			<searchInputItem name="字典名称">
				<inputItem :value.sync="searchForm.dicName" @enter="searchTable"></inputItem>
			</searchInputItem>
		</searchInputItems>
      <!-- 操作按钮 -->
		<optionItems>
			<template slot="left">
				<el-button-group>
					<iconBtn iconClass="el-icon-plus" content="新增" @click="modalAdd">新增</iconBtn>
					<!--<iconBtn iconClass="el-icon-minus" content="删除" @click="dele">删除</iconBtn>-->
					<iconBtn iconClass="el-icon-search" content="查询" @click="searchTable">查询</iconBtn>
					<iconBtn iconClass="el-icon-refresh" content="重置" @click="reset">重置</iconBtn>
				</el-button-group>				
			</template>
		</optionItems>
		<!-- 表格 -->
		<elemTable :dataList="dataList" :currentPage='pageNum' :pageSize="pageSize" :pageTotal="pageTotal" :loading="dataLoading" @sizeChange="handleSizeChange" @currentChange="handleCurrentChange" @selectionChange="selectionChange">
			<el-table-column type="selection" width="55"></el-table-column>
		    <el-table-column prop="claCode" label="分类编码">
		    	<template slot-scope="scope">
		    		<toolTip :content="scope.row.claCode">
		    			<span>{{scope.row.claCode}}</span>
		    		</toolTip>
		      	</template>
		    </el-table-column>
		    <el-table-column prop="claName" label="分类名称">
		    	<template slot-scope="scope">
					<toolTip :content="scope.row.claName">
		    			<span>{{scope.row.claName}}</span>
		    		</toolTip>
			    </template>
		    </el-table-column>
			<el-table-column prop="dicCode" label="字典编码">
		    	<template slot-scope="scope">
		    		<toolTip :content="scope.row.dicCode">
		    			<span>{{scope.row.dicCode}}</span>
		    		</toolTip>
		      	</template>
		    </el-table-column>
		    <el-table-column prop="dicName" label="字典名称">
		    	<template slot-scope="scope">
					<toolTip :content="scope.row.dicName">
		    			<span>{{scope.row.dicName}}</span>
		    		</toolTip>
			    </template>
		    </el-table-column>
		    <el-table-column prop="order" label="排序">
		    	<template slot-scope="scope">
		    		<toolTip :content="scope.row.order">
		    			<span>{{scope.row.order}}</span>
		    		</toolTip>
		      	</template>
		    </el-table-column>
		    <el-table-column label="操作">
		    	<template slot-scope="scope">
		    		<el-button-group>
		    			<!-- <iconBtn iconClass="el-icon-edit" content="编辑" @click="rowEdit(scope.row)" v-if="!scope.row.rowEditable"></iconBtn>	 -->
		    			<elBtn type="primary" text="编辑" @click="modalEdit(scope.row)"></elBtn>    
					    <iconBtn iconClass="el-icon-check" type="success" content="保存" @click="submitEdit(scope.row)" v-if="scope.row.rowEditable"></iconBtn>
					    <iconBtn iconClass="el-icon-close" type="info" content="取消" @click="cancelEdit(scope.row)" v-if="scope.row.rowEditable"></iconBtn>
					   <!-- <iconBtn iconClass="el-icon-minus" content="删除" @click="delRow(scope.row)"> -->
		    		</el-button-group>		    		
		    	</template>
		    </el-table-column>
		</elemTable>
		<dictionaryModel v-if="modalShow" :modal="modalShow" :dictionaryModalType="modalType" @close="modalClose" :dictionary="modalObj" @submit="modalSubmit"></dictionaryModel>
    </div>
</template>

<script>
import mixin from "../../mixin/mixin.js";
import dictionaryModel from "./modal/dictionaryModel.vue";
import local from "../../local.js";
export default {
  mixins: [mixin],
  components: { dictionaryModel },
  data() {
    return {
      dataCategory: [],
      searchForm: {
				claCode: "",
				claNames:"",
        dicCode: "",
        dicName: ""
      },
      dataList: [],
      rowOBJ: {}
    };
  },
  mounted() {
    console.log(this.rootAPI);
		this.searchTable();
		this.getDataCategory();
  },
  methods: {
		//获取字典分类	
  	getDataCategory(query){
  		var vm = this
  		if(query==null){
  			query=''
  		}
		vm.dataCategory = []
		return vm._ajax({
			url: vm.rootAPI, 
			name: 'classificate/queryClassificate', 
			param: {keyword:query}, 
			}).then((d) => {
				if(d.state === 0){
					var aaData = d.aaData
					aaData.forEach(function(el) {
						vm.dataCategory.push({
							value: el.claCode,
  						label: el.claName
						})
					})	
				}
    		}, (d) => {
                this.$message.error('数据获取失败');
            })
  	},
		dataCategoryChange(val){
  		let obj = {};
            obj = this.dataCategory.find((item)=>{
                return item.value === val;
            });
						this.searchForm.claNames = obj.label;
						this.searchTable();
		},
    searchTable() {
      Object.assign(this.searchForm, {
        pageNum: this.pageNum,
        pageSize: this.pageSize
      });
      return this._ajax({
        url: this.rootAPI,
        name: "dictionary/list",
        param: this.searchForm,
        loading: "dataLoading"
      }).then(this.renderTable);
    },
    reset() {
      Object.assign(this.searchForm, {
				claCode: "",
				claNames:"",
        dicCode: "",
        dicName: ""
      });
      this.handleCurrentChange(1);
    },
    dele() {
      if (this.delSelection.length === 0) {
        this.$message({ type: "info", message: "请选择行" });
      } else {
        var selection = this.delSelection;
        var arr = [],
          o = {};
        selection.forEach(function(el) {
          arr.push(el.id);
        });
        o.id = arr;
        this.delSubmit(o);
      }
    },
    delRow(row) {
      var o = {
        id: [row.id]
      };
      this.delSubmit(o);
    },
    delSubmit(o) {
      this._comfirm("确定删除？")
        .then(
          function() {
            return this._ajax({
              url: this.rootAPI + "dictionary/delete",
              param: o,
              arr: true
            });
          }.bind(this)
        )
        .then(
          function(d) {
            if (d.state === 0) {
              this.$message({ type: "success", message: "删除成功" });
            } else {
              this.$message({ type: "warning", message: "删除失败" + d.msg });
            }
            this.handleCurrentChange(1);
          }.bind(this)
        )
        .catch(this._confirmCancle);
    },
    changeMobile(row) {}
  }
};
</script>