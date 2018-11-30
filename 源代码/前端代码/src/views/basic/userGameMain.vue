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
					<iconBtn iconClass="el-icon-minus" content="删除" @click="dele">删除</iconBtn>
					<iconBtn iconClass="el-icon-search" content="查询" @click="searchTable">查询</iconBtn>
					<iconBtn iconClass="el-icon-refresh" content="重置" @click="reset">重置</iconBtn>
				</el-button-group>
			</template>
			<template slot="right">
				<selectInput :value.sync="temp.addGameId" filterable :clearable="true">
					<el-option
							v-for="item in gameOption"
							:key="item.id"
							:label="item.firstCode+ ' ' + item.name"
							:value="item.id">
						</el-option>
				</selectInput>
				<el-button type="primary" size="small" round @click="add()" :loading="isSubmit" style="width: 100px;">添加游戏</el-button>
			</template>
		</optionItems>
		<!-- 表格 -->
		<elemTable :dataList="dataList" :currentPage='pageNum' :pageSize="pageSize" :pageTotal="pageTotal" :loading="dataLoading" @sizeChange="handleSizeChange" @currentChange="handleCurrentChange" @selectionChange="selectionChange">
			<el-table-column type="selection" width="55"></el-table-column>
			<el-table-column prop="firstCode" label="首字母" width="60">
				<template slot-scope="scope">
					<span>{{scope.row.firstCode}}</span>
				</template>
			</el-table-column>
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
		    <el-table-column label="操作" width="200">
		    	<template slot-scope="scope">
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
				isSubmit:false,
				gameTypeOption:[],
				searchForm: {
					name: ''
				},
				temp:{
					addGameId:'',
				},
				typeOption:[],
				dataList: [],
				modalType:'add',
				modalObject:{},
				modalShow:false
			}
		},
		mounted() {
			this.loadGame();
			this._searchDic("GAME_TYPE").then(
				function(d) {
					this.gameTypeOption = this._dicKey(d);
					this.searchTable()
				}.bind(this)
			);
		},
		methods: {
			add(){
				if(!this.temp.addGameId || this.temp.addGameId==''){
					this.$message({type: 'warning', message: "请选择一款游戏。"});
					return ;
				}
				this.isSubmit = true;
				this._ajax({url: this.rootAPI + 'userGame/create', param: {gameId:this.temp.addGameId}})
				.then((function(d){
					if(d.state==0){
						this.temp.addGameId = '';
						this.searchTable();
						this.$message({type: 'success', message: d.msg});
					}else{
						this.$message({type: 'danger', message: d.msg});
					}
					this.isSubmit = false;
				}).bind(this))
			},
			loadGame(){
				this._ajax({url: this.rootAPI, name: 'game/list', param: {}}).then((function(d){
					if(d.state==0){
						this.gameOption = d.aaData;
					}else{
						this.$message({type: 'danger', message: d.msg});
					}
				}).bind(this))
			},
			searchTable() {
				Object.assign(this.searchForm, {
					pageNum: this.pageNum, 
					pageSize: this.pageSize
				})
				return this._ajax({url: this.rootAPI, name: 'userGame/list', param: this.searchForm, loading: 'dataLoading'}).then(this.renderTable)
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
        			return this._ajax({url: this.rootAPI + 'userGame/delete', param: o, arr:true})
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