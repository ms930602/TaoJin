<template>
	<div class="page-category">
    <!-- 查询条件 -->
		<searchInputItems>
			<searchInputItem name="游戏名称">
				<inputItem :value.sync="searchForm.name" @enter="searchTable"></inputItem>
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
		    <el-table-column prop="name" label="名称">
		    	<template slot-scope="scope">
		    		<span>{{scope.row.name}}</span>
		      	</template>
		    </el-table-column>
			<el-table-column prop="type" label="类型">
		    	<template slot-scope="scope">
		    		<span>{{_dicValue(scope.row.type,gameTypeOption)}}</span>
		      	</template>
		    </el-table-column>
			<el-table-column prop="createtime" label="创建时间">
				<template slot-scope="scope">
					<span>{{scope.row.createtime}}</span>
				</template>
			</el-table-column>
			<el-table-column prop="createUserName" label="创建人">
				<template slot-scope="scope">
					<span>{{scope.row.createUserName}}</span>
				</template>
			</el-table-column>
			
			<el-table-column prop="lastModifyPersonName" label="最后修改人">
				<template slot-scope="scope">
					<span>{{scope.row.lastModifyPersonName}}</span>
				</template>
			</el-table-column>
		    <el-table-column label="操作">
		    	<template slot-scope="scope">
					<el-button type="text" @click="modalEdit(scope.row)"><i class="el-icon-edit"></i>编辑</el-button>
					<el-button type="text" @click="delRow(scope.row)" style="color:#a90909"><i class="el-icon-delete"></i>删除</el-button>
		    	</template>
		    </el-table-column>
		</elemTable>
    </div>
</template>

<script>
    import mixin from '../../mixin/mixin.js'
	import local from '../../local.js'
	export default {
		mixins: [mixin],
		data() {
			return {
				gameTypeOption:[],
				searchForm: {
					name: ''
				},
				typeOption:[],
				dataList: [],
				modalType:'add',
				modalObject:{},
				modalShow:false
			}
		},
		mounted() {
			this._searchDic("GAME_TYPE").then(
				function(d) {
					this.gameTypeOption = this._dicKey(d);
					this.searchTable()
				}.bind(this)
			);
		},
		methods: {
			modalAdd(){
				this.$router.push({path:"/gameMain/a"});
			},
			modalEdit(row){
				this.$router.push({path:"/gameMain/u",query:{id:row.id}});
			},
			searchTable() {
				Object.assign(this.searchForm, {
					pageNum: this.pageNum, 
					pageSize: this.pageSize
				})
				return this._ajax({url: this.rootAPI, name: 'game/list', param: this.searchForm, loading: 'dataLoading'}).then(this.renderTable)
			},
			reset() {
				Object.assign(this.searchForm, {
					name: ''
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
        			return this._ajax({url: this.rootAPI + 'game/delete', param: o, arr:true})
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