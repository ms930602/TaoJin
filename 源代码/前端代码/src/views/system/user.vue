<template>
	<div class="page-user">
		<!-- 查询条件 -->
		<searchInputItems>
			<searchInputItem name="用户名称">
				<inputItem :value.sync="searchForm.nickName" @enter="searchTable"></inputItem>
			</searchInputItem>
			<searchInputItem name="登录帐号">
				<inputItem :value.sync="searchForm.loginName" @enter="searchTable"></inputItem>
			</searchInputItem>
			<searchInputItem name="用户状态">
				<selectInput :value.sync="searchForm.status" @selectChange="searchTable" :clearable="true">
					<el-option
				      v-for="item in dicUserState"
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
		    <el-table-column prop="nickName" label="用户名称">
		    	<template slot-scope="scope">
		    		<toolTip :content="scope.row.nickName">
		    			<span>{{scope.row.nickName}}</span>
		    		</toolTip>
		      	</template>
		    </el-table-column>
		    <el-table-column prop="loginName" label="登录帐号">
		    	<template slot-scope="scope">
		    		<toolTip :content="scope.row.loginName">
		    			<span>{{scope.row.loginName}}</span>
		    		</toolTip>
		      	</template>
		    </el-table-column>
		    <el-table-column prop="status" label="用户状态">
		    	<template slot-scope="scope">
					<tagItem :type="scope.row.status ? 'success' : 'danger'" :text="_dicValue(scope.row.status, dicUserState)"></tagItem>
			    </template>
		    </el-table-column>
		    <el-table-column prop="mobile" label="手机号">
		    	<template slot-scope="scope">
			        <inputItem :value.sync="scope.row.mobile" @blur="changeMobile(scope.row)" v-if="scope.row.rowEditable"></inputItem>
			        <toolTip :content="scope.row.mobile" v-if="!scope.row.rowEditable">
		    			<span>{{scope.row.mobile}}</span>
		    		</toolTip>
			    </template>
		    </el-table-column>
		    <el-table-column prop="identityCard" label="身份证号">
		    	<template slot-scope="scope">
		    		<toolTip :content="scope.row.identityCard">
		    			<span>{{scope.row.identityCard}}</span>
		    		</toolTip>
		      	</template>
		    </el-table-column>
		    <el-table-column label="操作">
		    	<template slot-scope="scope">
		    		<el-button-group>
		    			<!-- <iconBtn iconClass="el-icon-edit" content="编辑" @click="rowEdit(scope.row)" v-if="!scope.row.rowEditable"></iconBtn>	 -->
		    			<elBtn type="primary" text="编辑"  @click="modalEdit(scope.row)" v-if="!scope.row.rowEditable"></elBtn> 
						<!-- <iconBtn    content="编辑" @click="modalEdit(scope.row)" v-if="!scope.row.rowEditable"></iconBtn>     -->
					    <elBtn  type="success" text="保存" @click="submitEdit(scope.row)" v-if="scope.row.rowEditable"></elBtn>
					    <elBtn  type="info" text="取消" @click="cancelEdit(scope.row)" v-if="scope.row.rowEditable"></elBtn>
					    <elBtn   type="danger" text="删除" @click="delRow(scope.row)" v-if="scope.row.userId != thisUser.userId"></elBtn>
		    		</el-button-group>		    		
		    	</template>
		    </el-table-column>
		</elemTable>
		<userModal v-if="modalShow" :modal="modalShow" :userModalType="modalType" @close="modalClose" :user="modalObj" @submit="modalSubmit"></userModal>
	</div>
</template>
<script>
	import mixin from '../../mixin/mixin.js'
	import userModal from './modal/userModal.vue'
	import local from '../../local.js'
	export default {
		mixins: [mixin],
		components: {userModal},
		data() {
			return {
				searchForm: {
					nickName: '',
					loginName: '',
					status: '',
				},
				dataList: [],				
				rowOBJ: {},
				thisUser: local.get('userinfo')
			}
		},
		mounted() {
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
				return this._ajax({url: this.userAPI, name: 'user/list', param: this.searchForm, loading: 'dataLoading'}).then(this.renderTable)
			},
			reset() {
				Object.assign(this.searchForm, {
					nickName: '',
					loginName: '',
					status: '',
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
    				userId: [row.userId]
    			}
    			this.delSubmit(o)
			},
			delSubmit(o) {
				this._comfirm('确定删除？')
        		.then((function() {        			
        			return this._ajax({url: this.userAPI + 'user/delete', param: o, arr:true})
        		}).bind(this))
        		.then((function(d) {
        			this._ajax({url: this.api + '/permission/tsysRoleUser/binDing', param: o, arr: true})
					this.$message({type: 'success', message: '删除成功'});
					this.handleCurrentChange(1)
        		}).bind(this))	
        		.catch(this._confirmCancle);
			},
			changeMobile(row) {
				// if(!this.regFloat.test(row.mobile)) {
				// 	console.log(this.regFloat.test(row.mobile))
				// 	row.mobile = 0
				// }
			},
			exportExcel() {
				var fields = ['nickName'],
					filedsName = ['用户名称'],
					fileName = '用户';
				this.searchAll({url: this.userAPI, name: 'user/list', param: this.searchForm})
				.then((function(d) {
					if(d.aaData.length > 0) {
						this._csvExport(d.aaData, fields, filedsName, fileName)
					}else {
						this.$message({ type: 'info', message: '无导出数据' });
					}
				}).bind(this))		
			},
			exportExcelBySelect() {
				var fields = ['nickName'],
					filedsName = ['用户名称'],
					fileName = '用户';
				this.$exportExcelBySelect(fields, filedsName, fileName)
			},
			// rowEdit(o) {
			// 	if(!this.rowOBJ[o.userId]) {
			// 		this.rowOBJ[o.userId] = {}
			// 	}
			// 	Object.assign(this.rowOBJ[o.userId], o)
			// 	o.rowEditable = true
			// },
			// cancelEdit(o) {
			// 	Object.assign(o, this.rowOBJ[o.userId])
			// },
			// submitEdit(o) {
			// 	if(!this.TelePhone.test(o.mobile)) {
			// 		this.$message({ type: 'error', message: '手机号填写有误' }); 
			// 		o.rowError = true
			// 	}else {
			// 		this._ajax({url: this.userAPI + 'user/update', param: o, loading: 'dataLoading'})
   //                      .then((function(d) { 
   //                      	this.$message({ type: 'success', message: '操作成功' }); 
   //                  		o.rowEditable = false
   //                      }).bind(this))
			// 	}
			// }
		}
	}
</script>