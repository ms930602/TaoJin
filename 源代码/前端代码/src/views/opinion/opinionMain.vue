<template>
	<div class="page-category">
    <!-- 查询条件 -->
		<searchInputItems>
			<searchInputItem name="标题">
				<inputItem :value.sync="searchForm.title" @enter="searchTable"></inputItem>
			</searchInputItem>
		</searchInputItems>
<!-- 操作按钮 -->
		<optionItems>
			<template slot="left">
				<el-button-group>
					<iconBtn iconClass="el-icon-plus" content="新增" @click="modalAdd">新增</iconBtn>
					<iconBtn iconClass="el-icon-minus" content="删除" @click="dele">删除</iconBtn>
					<iconBtn iconClass="el-icon-search" content="查询" @click="searchTable">查询</iconBtn>
					<iconBtn iconClass="el-icon-refresh" content="重置" @click="reset">重置</iconBtn>
				</el-button-group>
			</template>
		</optionItems>
		<!-- 表格 -->
		<elemTable :dataList="dataList" :currentPage='pageNum' :pageSize="pageSize" :pageTotal="pageTotal" :loading="dataLoading" @sizeChange="handleSizeChange" @currentChange="handleCurrentChange" @selectionChange="selectionChange">
			<el-table-column type="selection" width="55"></el-table-column>
		    <el-table-column prop="title" label="标题">
		    	<template slot-scope="scope">
		    		<span>{{scope.row.title}}</span>
		      	</template>
		    </el-table-column>
			<el-table-column prop="remark" label="内容">
		    	<template slot-scope="scope">
		    		<span>{{scope.row.remark}}</span>
		      	</template>
		    </el-table-column>
			<el-table-column prop="handleType" label="反馈状态">
				<template slot-scope="scope">
					<tagItem :type="scope.row.handleType=='2' ? 'success' : 'danger'" :text="_dicValue(scope.row.handleType, handleTypeOption)"></tagItem>
				</template>
			</el-table-column>
			<el-table-column prop="reText" label="反馈内容">
				<template slot-scope="scope">
					<span>{{scope.row.reText}}</span>
				</template>
			</el-table-column>
			
			<el-table-column prop="createtime" label="创建时间">
				<template slot-scope="scope">
					<span>{{scope.row.createtime}}</span>
				</template>
			</el-table-column>
		    <el-table-column label="操作">
		    	<template slot-scope="scope">
		    		<el-button-group>
						<elBtn type="primary" text="编辑" @click="modalEdit(scope.row)"></elBtn>    
						<elBtn type="danger" text="删除"  @click="delRow(scope.row)"></elBtn>
		    		</el-button-group>


		    	</template>
		    </el-table-column>
		</elemTable>
		<opinionModal v-if="modalShow" :modal="modalShow" :baseModalType="modalType" @close="modalClose" :obj="modalObject" @submit="modalSubmit"></opinionModal>
    </div>
</template>

<script>
    import mixin from '../../mixin/mixin.js'
	import opinionModal from './modal/opinionModal.vue'
	import local from '../../local.js'
	export default {
		mixins: [mixin],
		components: {opinionModal},
		data() {
			return {
				searchForm: {
					title: ''
				},
				handleTypeOption:[{key:'1',value:'处理中'},{key:'2',value:'已处理'}],
				dataList: [],
				modalType:'add',
				modalObject:{},
				modalShow:false,
				rowOBJ: {}
			}
		},
		mounted() {
			this.searchTable()
		},
		methods: {
			modalAdd(){
				this.modalType = 'add';
				this.modalObject = {};
				this.modalShow = true;
			},
			modalEdit(row){
				this.modalType = 'edit';
				this.modalObject = row;
				this.modalShow = true;
			},
			searchTable() {
				Object.assign(this.searchForm, {
					pageNum: this.pageNum, 
					pageSize: this.pageSize
				})
				return this._ajax({url: this.rootAPI, name: 'opinion/list', param: this.searchForm, loading: 'dataLoading'}).then(this.renderTable)
			},
			reset() {
				Object.assign(this.searchForm, {
					title: ''
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
						arr.push(el.id)
					})	
					o.id = arr
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
        			return this._ajax({url: this.rootAPI + 'opinion/delete', param: o, arr:true})
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
			}
		}
	}
</script>