<template>
	<div>
		<el-card class="box-card" style="margin-right: 5px; margin-left: 5px;" shadow="hover">
  <div slot="header" class="clearfix" >
    <span>游戏信息</span>
  </div>
	<div>
    <el-form class="modal-form" :inline="true" :model="form" :rules="rules" ref="form" label-width="120px">
      <el-row >
				<el-col :span="6">
	          <el-form-item label="游戏名称"  prop='name'>
								<inputItem style="width: 200px;" :maxlength="100" :value.sync="form.name" ></inputItem>
		      	</el-form-item>
				</el-col>
        <el-col :span="6">
              <el-form-item label="游戏类型"  prop='type'>
                  <selectInput :value.sync="form.type" placeHolder="请选择类型" style="width: 220px;">
                    <el-option 
                      v-for="item in gameTypeOption"
                      :key="item.key"
                      :label="item.value"
                      :value="item.key">
                    </el-option>
                  </selectInput>
              </el-form-item>
        </el-col>
				<el-col :span="6">
					<el-button type="primary" @click="saveGame()" :loading="!gameSubmitBtn">
						{{form.id?'修改':'新增'}}
					</el-button>
				</el-col>
			</el-row>
	  </el-form>
		</div>
</el-card>
<el-card class="box-card" style="margin-right: 5px; margin-left: 5px; margin-top:10px; " shadow="hover">
  <div slot="header" class="clearfix">
    <span>货币单位</span>
		<span v-if="form.id">
		<el-button type="text" @click="modalCUAdd()" style="margin-left: 10px;"><i class="el-icon-circle-plus"></i>添加</el-button>
		<el-button type="text" @click="modalCUSubmit()" style="margin-left: 10px;"><i class="el-icon-refresh"></i>刷新</el-button>
		</span>
  </div>
  <div>

	<elemTable :dataList="dataList" :currentPage='pageNum' :pageSize="pageSize" :pageTotal="pageTotal" :loading="dataLoading" @sizeChange="handleSizeChange" @currentChange="handleCurrentChange" @selectionChange="selectionChange">
		<el-table-column prop="name" label="货币名称">
			<template slot-scope="scope">
					<span>{{scope.row.name}}</span>
				</template>
		</el-table-column>
		<el-table-column prop="groupName" label="组名">
			<template slot-scope="scope">
					<span>{{scope.row.groupName}}</span>
			</template>
		</el-table-column>
		<el-table-column prop="group" label="组号">
			<template slot-scope="scope">
					<tagItem type="danger" :text="scope.row.group"></tagItem>
			</template>
		</el-table-column>
		<el-table-column prop="ratio" label="系数">
			<template slot-scope="scope">
					<tagItem :text="scope.row.ratio"></tagItem>
			</template>
		</el-table-column>
		<el-table-column prop="sort" label="序号">
			<template slot-scope="scope">
					<tagItem :text="scope.row.sort"></tagItem>
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
		<el-table-column prop="lastModifyPersonName" label="修改人">
			<template slot-scope="scope">
					<span>{{scope.row.lastModifyPersonName}}</span>
			</template>
		</el-table-column>
		<el-table-column label="操作" width="180">
			<template slot-scope="scope">
				<el-button type="text" @click="modalCUEdit(scope.row)"><i class="el-icon-edit"></i>编辑</el-button>
				<el-button type="text" @click="delCU(scope.row)" style="color:#a90909"><i class="el-icon-delete"></i>删除</el-button>
			</template>
		</el-table-column>
	</elemTable>
	</div>

</el-card>    
<el-card class="box-card" style="margin-right: 5px; margin-left: 5px; margin-top:10px; " shadow="hover">
  <div slot="header" class="clearfix">
    <span>税收类&nbsp;&nbsp;&nbsp;&nbsp;</span>
		<span v-if="form.id">
		<el-button type="text" @click="modalRRAdd()" style="margin-left: 10px;"><i class="el-icon-circle-plus"></i>添加</el-button>
		<el-button type="text" @click="modalRRSubmit()" style="margin-left: 10px;"><i class="el-icon-refresh"></i>刷新</el-button>
		</span>
	</div>
  <div>
<elemTable :dataList="dataRRList" :currentPage='pageRRNum' :pageSize="pageRRSize" :pageTotal="pageRRTotal" :loading="dataRRLoading" @sizeChange="handleRRSizeChange" @currentChange="handleRRCurrentChange" >
		    <el-table-column prop="name" label="税收名称">
		    	<template slot-scope="scope">
		    			<span>{{scope.row.name}}</span>
		      </template>
		    </el-table-column>
		    <el-table-column prop="ratio" label="比率">
		    	<template slot-scope="scope">
							<tagItem type="danger" :text="scope.row.ratio+' %'"></tagItem>
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
        <el-table-column prop="lastModifyPersonName" label="修改人">
          <template slot-scope="scope">
              <span>{{scope.row.lastModifyPersonName}}</span>
          </template>
        </el-table-column>
		    <el-table-column label="操作" width="180">
		    	<template slot-scope="scope">
						<el-button type="text" @click="modalRREdit(scope.row)"><i class="el-icon-edit"></i>编辑</el-button>
						<el-button type="text" @click="delRR(scope.row)" style="color:#a90909"><i class="el-icon-delete"></i>删除</el-button>    		
		    	</template>
		    </el-table-column>
		</elemTable> 
				  
	  		<div style="float: right;margin-top: 15px;line-height: 12px; margin-bottom:15px;">
  	  		<el-button @click="cancel()" type="warning">返  回</el-button>
	  	</div>
	</div>

</el-card>
<currencyUnitModal v-if="modalCUShow" :modal="modalCUShow" :baseModalType="modalCUType" @close="modalCUClose" 
:gameObj="form"
:obj="modalCUObject" @submit="modalCUSubmit"></currencyUnitModal>
<revenueRatioModal v-if="modalRRShow" :modal="modalRRShow" :baseModalType="modalRRType" @close="modalRRClose" 
:gameObj="form"
:obj="modalRRObject" @submit="modalRRSubmit"></revenueRatioModal>
	  </div>
</template>

<script>
import mixin from "../../mixin/mixin.js";
import local from "../../local.js";
import configs from "../../configs.js";
import currencyUnitModal from './modal/currencyUnitModal.vue'
import revenueRatioModal from './modal/revenueRatioModal.vue'
export default {
  mixins: [mixin],
	components: {currencyUnitModal,revenueRatioModal},
  data() {
    return {
			//货币单位
			dataList: [],
			modalCUType:'add',
			modalCUObject:{},
			modalCUShow:false,
			//税率
			dataRRList: [],
			modalRRType:'add',
			modalRRObject:{},
			modalRRShow:false,
			dataRRLoading:false,
			pageRRNum: 1,
			pageRRSize: 10,
			pageRRTotal:0,
			
			gameTypeOption:[],
			gameSubmitBtn:true,
      form:{
      	id:null,
      	name:'',
      	type:null
      },
      rules: {
        name: [this._ruleRequired("游戏名称")],
				type: [this._ruleRequired("游戏类型")],
      }
    };
  },
  mounted() {
		this._searchDic("GAME_TYPE").then(
			function(d) {
				this.gameTypeOption = this._dicKey(d);
			}.bind(this)
		);
		var id = this.$route.query.id;
		if(id){
			this.form.id = id;
			this.searchObject(id);
			this.searchCUTable();
			this.searchRRTable();
		}else
		{
			this.form.id = null;
		}
  },
  methods: {
		modalCUAdd(){
			if(!this.form.id){
				this.$message({type: 'warning', message: '请先添加游戏！'});
				return;
			}
			this.modalCUType = 'add';
			this.modalCUObject = {};
			this.modalCUShow = true;
		},
		modalCUEdit(row){
			this.modalCUType = 'edit';
			this.modalCUObject = row;
			this.modalCUShow = true;
		},
		modalCUClose(){
			this.modalCUShow = false;
		},
		modalCUSubmit(){
			this.pageNum = 1;
			this.searchCUTable()
			this.modalCUClose();
		},
		delCU(row){
			var o = {
				id: [row.id]
			}
			this._comfirm('确定删除？')
			.then((function() {
				return this._ajax({url: this.rootAPI + 'currencyUnit/delete', param: o, arr:true})
			}).bind(this))
			.then((function(d) {
				if(d.state === 0)
				{
					this.$message({type: 'success', message: '删除成功'});
					this.searchCUTable();
				}
				else{
					this.$message({type: 'warning', message: '删除失败'+ d.msg});
				}
			}).bind(this))
			.catch(this._confirmCancle);
		},
		searchCUTable() {
			if(!this.form.id)return;
			return this._ajax({url: this.rootAPI, name: 'currencyUnit/list', param: {
				gameId:this.form.id,
				pageNum: this.pageNum,
				pageSize: this.pageSize
			}, loading: 'dataLoading'}).then(this.renderTable)
		},
		//税率 start
		modalRRAdd(){
			if(!this.form.id){
				this.$message({type: 'warning', message: '请先添加游戏！'});
				return;
			}
			this.modalRRType = 'add';
			this.modalRRObject = {};
			this.modalRRShow = true;
		},
		modalRREdit(row){
			this.modalRRType = 'edit';
			this.modalRRObject = row;
			this.modalRRShow = true;
		},
		modalRRClose(){
			this.modalRRShow = false;
		},
		modalRRSubmit(){
			this.pageRRNum = 1;
			this.searchRRTable()
			this.modalRRClose();
		},
		delRR(row){
			var o = {
				id: [row.id]
			}
			this._comfirm('确定删除？')
			.then((function() {
				return this._ajax({url: this.rootAPI + 'revenueRatio/delete', param: o, arr:true})
			}).bind(this))
			.then((function(d) {
				if(d.state === 0)
				{
					this.$message({type: 'success', message: '删除成功'});
					this.searchRRTable();
				}
				else{
					this.$message({type: 'warning', message: '删除失败'+ d.msg});
				}
			}).bind(this))
			.catch(this._confirmCancle);
		},
		searchRRTable() {
			if(!this.form.id)return;
			var _this = this;
			return this._ajax({url: this.rootAPI, name: 'revenueRatio/list', param: {
				gameId:this.form.id,
				pageNum: this.pageRRNum,
				pageSize: this.pageRRSize
			}, loading: 'dataRRLoading'}).then(function(d){
					let dataList = d.aaData
					if (Array.isArray(dataList)) {
						_this.dataRRList = _this.handleDataList(d)
						if (dataList.length > 0) {
							_this.pageRRTotal = d.dataCount
						} else {
							_this.pageRRTotal = 0
						}
					} else {
						_this.handleDataList(d)
					}
			})
		},
		handleRRSizeChange(val){
			this.pageRRNum = 1;
			this.pageRRSize = val;
			this.searchRRTable()
		},
		handleRRCurrentChange(index){
			this.pageRRNum = index;
			this.searchRRTable()
		},
		//税率 end
		searchObject(id){
			this._ajax({url: this.rootAPI + 'game/queryById', param: {id:id}})
        .then((function(d) {
            if(d.state === 0) {
              Object.assign(this.form,d.aaData)
            }else
            {
              this.$message({ type: "error", message: "服务器错误!" });
            }
        }).bind(this))
		},
		cancel(){
			this.$router.push({path:"/gameMain"});
		},
    saveGame(){
				
				this.$refs['form'].validate((valid) => {
						if (valid) {
							  this.gameSubmitBtn = false;
								let o = {}, method = 'game/update';
								o = {
									name:this.form.name,
									type:this.form.type
								};
								if(this.form.id == null) {
										method = 'game/create'
								}else {
										o.id = this.form.id
								}
								this._ajax({url: this.rootAPI + method, param: o})
								.then((function(d) { 
										if(d.state === 0)
										{
											if(!this.form.id || this.form.id == ''){
												if(d.aaData.id){
													this.form.id = d.aaData.id;
												}
											}
											this.$message({type: 'success', message: '添加成功!'});
										}
										else{
											this.$message({type: 'warning', message: '添加失败:'+ d.msg});
										}
										this.gameSubmitBtn = true;
								}).bind(this))
						}
				})
		},
		deleteCU(row){
			
		},
		deleteRR(row){
			
		}
  }
};
</script>

<style>


</style>