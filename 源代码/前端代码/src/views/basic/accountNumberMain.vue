<template>
	<div class="page-category">
    <!-- 查询条件 -->
		<searchInputItems>
			<searchInputItem name="账号名称">
				<inputItem :value.sync="searchForm.loginName" @enter="searchTable"></inputItem>
			</searchInputItem>
			<searchInputItem name="是否封印">
				<selectInput :value.sync="searchForm.status" @selectChange="searchTable" filterable :clearable="true">
					<el-option
							v-for="item in statusOption"
							:key="item.key"
							:label="item.value"
							:value="item.key">
						</el-option>
				</selectInput>
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
			<el-table-column prop="gameId" label="所属游戏">
				<template slot-scope="scope">
					<span>{{_dicGameValue(scope.row.gameId,gameOption)}}</span>
				</template>
			</el-table-column>
		    <el-table-column prop="loginName" label="账号名称">
		    	<template slot-scope="scope">
		    		<span>{{scope.row.loginName}}</span>
		      	</template>
		    </el-table-column>
			<el-table-column prop="password" label="密码(点击显示)">
				<template slot-scope="scope">
					<a href="javascript:void(0)" @click="scope.row.show=!scope.row.show">{{scope.row.show?scope.row.password:'********'}}</a>
				</template>
			</el-table-column>
			<el-table-column prop="status" label="是否封印">
				<template slot-scope="scope">
					<span>{{_dicValue(scope.row.status,statusOption)}}</span>
				</template>
			</el-table-column>
			<el-table-column prop="useTime" label="解封时间">
				<template slot-scope="scope">
					<span>{{scope.row.useTime}}</span>
				</template>
			</el-table-column>
			<el-table-column prop="useTime" label="备注">
				<template slot-scope="scope">
					<span>{{scope.row.remark}}</span>
				</template>
			</el-table-column>
		    <el-table-column label="操作">
		    	<template slot-scope="scope">
		    		<el-button type="text" @click="modalEdit(scope.row)"><i class="el-icon-edit"></i>编辑</el-button>
					<el-button type="text" @click="delRow(scope.row)" style="color:#a90909"><i class="el-icon-delete"></i>删除</el-button>
		    	</template>
		    </el-table-column>
		</elemTable>
		<accountNumberModal v-if="modalShow" :modal="modalShow" :baseModalType="modalType" @close="modalClose" :obj="modalObject" @submit="modalSubmit"></accountNumberModal>
    </div>
</template>

<script>
    import mixin from '../../mixin/mixin.js'
	import accountNumberModal from './modal/accountNumberModal.vue'
	import local from '../../local.js'
	export default {
		mixins: [mixin],
		components: {accountNumberModal},
		data() {
			return {
				searchForm: {
					loginName: '',
					status:'',
				},
				gameOption:[],
				statusOption:[
					{key:'0',value:'未封印'},
					{key:'1',value:'已被封印'}
				],
				typeOption:[],
				dataList: [],
				modalType:'add',
				modalObject:{},
				modalShow:false
			}
		},
		mounted() {
			this.searchTable();
			this.loadGame();
		},
		methods: {
			loadGame(){
				this._ajax({url: this.rootAPI, name: 'userGame/list', param: {}}).then((function(d){
					if(d.state==0){
						this.gameOption = d.aaData;
					}else{
						this.$message({type: 'danger', message: d.msg});
					}
				}).bind(this))
			},
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
				return this._ajax({url: this.rootAPI, name: 'userAccountNumber/list', param: this.searchForm, loading: 'dataLoading'}).then(this.renderTable)
			},
			reset() {
				Object.assign(this.searchForm, {
					loginName: '',
					status:''
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
        			return this._ajax({url: this.rootAPI + 'userAccountNumber/delete', param: o, arr:true})
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
