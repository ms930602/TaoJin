<template>
	<div class="page-category">
    <!-- 查询条件 -->
		<searchInputItems>
			<searchInputItem name="分类编码">
				<inputItem :value.sync="searchForm.claCode" @enter="searchTable"></inputItem>
			</searchInputItem>
			<searchInputItem name="分类名称">
				<inputItem :value.sync="searchForm.claName" @enter="searchTable"></inputItem>
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
		    <!-- <el-table-column prop="id" label="分类ID">
		    	<template slot-scope="scope">
		    		<toolTip :content="scope.row.id">
		    			<span>{{scope.row.id}}</span>
		    		</toolTip>
		      	</template>
		    </el-table-column> -->
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
		    <el-table-column prop="order" label="排序">
		    	<template slot-scope="scope">
		    		<toolTip :content="scope.row.order">
		    			<span>{{scope.row.order}}</span>
		    		</toolTip>
		      	</template>
		    </el-table-column>
			<el-table-column prop="remark" label="备注">
		    	<template slot-scope="scope">
		    		<toolTip :content="scope.row.remark">
		    			<span>{{scope.row.remark}}</span>
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
		<categoryModel v-if="modalShow" :modal="modalShow" :categoryModalType="modalType" @close="modalClose" :classificate="modalObj" @submit="modalSubmit"></categoryModel>
    </div>
</template>

<script>
  import mixin from '../../mixin/mixin.js'
	import categoryModel from './modal/categoryModel.vue'
	import local from '../../local.js'
	export default {
		mixins: [mixin],
		components: {categoryModel},
		data() {
			return {
				searchForm: {
					claCode: '',
					claName: '',
				},
				dataList: [],				
				rowOBJ: {}
			}
		},
		mounted() {
			console.log(this.rootAPI)
			this._searchDic('USER_STATE')
			.then((function(d) {
				this.dicUserState = this._dicKey(d)
			}).bind(this))
			this.searchTable()
		},
		methods: {
			searchTable() {
				Object.assign(this.searchForm, {
					pageNum: this.pageNum, 
					pageSize: this.pageSize
				})
				return this._ajax({url: this.rootAPI, name: 'classificate/list', param: this.searchForm, loading: 'dataLoading'}).then(this.renderTable)
			},
			reset() {
				Object.assign(this.searchForm, {
					claCode: '',
					claName: ''
				})
				this.handleCurrentChange(1)
			},			
			dele() {			
	        	if(this.delSelection.length === 0) {
	        		this.$message({type: 'info', message: '请选择行'});
	        	}else {
	        		var selection = this.delSelection
	        		var arr = [], o = {}
					selection.forEach(function(el) {
						arr.push(el.userId)
					})	
					o.userId = arr
	        		this.delSubmit(o)		                        		
	        	}	
			},
			delRow(row) {
				var o = {
    				id: [row.id]
    			}
    			this.delSubmit(o)
			},
			delSubmit(o) {
				this._comfirm('确定删除？')
        		.then((function() {        			
        			return this._ajax({url: this.rootAPI + 'classificate/delete', param: o, arr:true})
        		}).bind(this))
        		.then((function(d) {
					if(d.state === 0)
					{
						this.$message({type: 'success', message: '删除成功'});
					}
					else{
						this.$message({type: 'warning', message: '删除失败'+ d.msg});
					}
					this.handleCurrentChange(1);
				}).bind(this))
        		.catch(this._confirmCancle);
			},
			changeMobile(row) {
				// if(!this.regFloat.test(row.mobile)) {
				// 	console.log(this.regFloat.test(row.mobile))
				// 	row.mobile = 0
				// }
			},
			// exportExcel() {
			// 	var fields = ['claName'],
			// 		filedsName = ['用户名称'],
			// 		fileName = '用户';
			// 	this.searchAll({url: this.rootAPI, name: 'classificate/list', param: this.searchForm})
			// 	.then((function(d) {
			// 		if(d.aaData.length > 0) {
			// 			this._csvExport(d.aaData, fields, filedsName, fileName)
			// 		}else {
			// 			this.$message({ type: 'info', message: '无导出数据' });
			// 		}
			// 	}).bind(this))		
			// },
			// exportExcelBySelect() {
			// 	var fields = ['claName'],
			// 		filedsName = ['用户名称'],
			// 		fileName = '用户';
			// 	this.$exportExcelBySelect(fields, filedsName, fileName)
			// },
		}
	}
</script>