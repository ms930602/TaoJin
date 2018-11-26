<template>
	<div class="page-user">
		<!-- 查询条件 -->
		<searchInputItems>
			<searchInputItem name="角色名称">
				<inputItem :value.sync="searchForm.name" @enter="searchTable"></inputItem>
			</searchInputItem>
			<searchInputItem name="角色状态">
				<selectInput :value.sync="searchForm.statu" @selectChange="searchTable" :clearable="true">
					<el-option
				      v-for="item in dicRoleState"
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
					<iconBtn iconClass="el-icon-search" content="查询" @click="searchTableEnter">查询</iconBtn>
					<iconBtn iconClass="el-icon-refresh" content="重置" @click="reset">重置</iconBtn>
				</el-button-group>				
			</template>
		</optionItems>
		<!-- 表格 -->
		<elemTable :dataList="dataList" :currentPage='pageNum' :pageSize="pageSize" :pageTotal="pageTotal" :loading="dataLoading" @sizeChange="handleSizeChange" @currentChange="handleCurrentChange" @selectionChange="selectionChange">
			<el-table-column type="selection" width="55"></el-table-column>
		    <el-table-column prop="name" label="角色名称">
		    	<template slot-scope="scope">
		    		<toolTip :content="scope.row.name">
		    			<span>{{scope.row.name}}</span>
		    		</toolTip>
		      	</template>
		    </el-table-column>
		    <el-table-column prop="createtime" label="创建日期">
		    	<template slot-scope="scope">
		    		<toolTip :content="scope.row.createtime">
		    			<span>{{scope.row.createtime}}</span>
		    		</toolTip>
		      	</template>
		    </el-table-column>
		    <el-table-column prop="statu" label="角色状态">
		    	<template slot-scope="scope">
		    		<tagItem :type="scope.row.statu ? 'success' : 'danger'" :text="_dicValue(scope.row.statu, dicRoleState)"></tagItem>
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
		<userRoleModal v-if="modalShow" :modal="modalShow" 
		:userRoleModalType="modalType" @close="modalClose" 
		:defaultChecked="defaultChecked"
		:userRole="modalObj" @submit="modalSubmit"></userRoleModal>
	</div>
</template>
<script>
	import mixin from '../../mixin/mixin.js'
	import configs from '../../configs.js'
	import userRoleModal from './modal/userRoleModal.vue'
	export default {
		mixins: [mixin],
		components: {userRoleModal},
		data() {
			return {
				searchForm: {
					name: '',
					statu: null,
				},
				dataList: [],
				defaultChecked: []				
			}
		},
		mounted() {
			this._searchDic('ROLE_STATE')
			.then((function(d) {
				this.dicRoleState = this._dicKey(d)
			}).bind(this))
			.then(this.searchTable)
		},
		methods: {
			searchTable() {

				Object.assign(this.searchForm, {
					pageNum: this.pageNum, 
					pageSize: this.pageSize
				})
				return this._ajax({url: this.api +'/permission/tsysRole/list', param: this.searchForm, loading: 'dataLoading'}).then(this.renderTable)
			},

			searchTableEnter(){
                   this.pageNum=1;
				Object.assign(this.searchForm, {
					pageNum: this.pageNum, 
					pageSize: this.pageSize
				})
				return this._ajax({url: this.api +'/permission/tsysRole/list', param: this.searchForm, loading: 'dataLoading'}).then(this.renderTable)				
			},


			reset() {
				Object.assign(this.searchForm, {
					name: '',
					statu: '',
				})
				this.handleCurrentChange(1)
			},			
			dele() {			
	        	if(this.delSelection.length === 0) {
	        		this.$message({type: 'info', message: '请选择行'});
	        	}else {
	        		let selection = this.delSelection
	        		var arr = [], o = {}
					selection.forEach(function(el) {
						arr.push(el.id)
					})	
					o.roleId = arr
					this.delSubmit(o)		                        		
	        	}	
			},
			delRow(row) {
				var o = {
    				roleId: [row.id]
    			}
				this.delSubmit(o)
			},
			modalEdit(o) {
				this._ajax({url: configs.api + '/permission/tsysRoleModlePermission/initMenusTreeService', param: {
                    roleId: o.id, 
                    sysId: configs.sysID,
                    flag: 1
                }})
                .then((function(d) {
                    var data = d.aaData
                    data.forEach((function(el) {                         
	                	if(el.selected) {
		                    el.selectId.forEach((function(em) {
		                    	var sonid = parseInt(em.slice(0, -2))
		                    	this.defaultChecked.push(sonid)
		                    }).bind(this))
		                }                 
	                }).bind(this))
                    this.modalObj = o
					this.modalType = 'edit'
					this.modalShow = true
                }).bind(this))
			},
            modalClose() {
            	this.defaultChecked = []
				this.modalObj = {}
				this.modalShow = false
				this.modalType = 'add'
			},
			delSubmit(o) {
				this._comfirm('确定删除？')
				.then((function() {
					return this._ajax({url: configs.api + '/permission/tsysRole/delete', param: o, arr:true})
				}).bind(this))
				.then((function(d) {
					if(d.state == 0) {
						this.$message({type: 'success', message: '删除成功'});
					}
					this.handleCurrentChange(1)
				}).bind(this))
				.catch(this._confirmCancle);
			}
		}
	}
</script>