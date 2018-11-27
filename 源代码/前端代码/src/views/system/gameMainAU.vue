<template>
	<div>
		<el-card class="box-card" style="margin-right: 5px; margin-left: 5px;" shadow="hover">
  <div slot="header" class="clearfix" >
    <span>游戏信息</span>
    <!-- <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button> -->
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
  </div>
  <div>
 <elemTable ref="asdas" :dataList="form.cuList" :isShowPage="false">
		    <el-table-column prop="name" label="货币名称">
		    	<template slot-scope="scope">
		    			<span>{{scope.row.name}}</span>
		      	</template>
		    </el-table-column>
		    <el-table-column prop="ratio" label="系数">
		    	<template slot-scope="scope">
		    			<span>{{scope.row.ratio}}</span>
			    </template>
		    </el-table-column>
        <el-table-column prop="group" label="组号">
          <template slot-scope="scope">
              <span>{{scope.row.group}}</span>
          </template>
        </el-table-column>
		     <el-table-column prop="groupName" label="组名">
		    	<template slot-scope="scope">
		    			<span>{{scope.row.groupName}}</span>
			    </template>
		    </el-table-column>
        <el-table-column prop="sort" label="序号">
          <template slot-scope="scope">
              <span>{{scope.row.sort}}</span>
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
		    <el-table-column label="操作" v-if="form.status == 0">
		    	<template slot-scope="scope">
		    		<el-button-group>
					    <elBtn type="danger" text="删除" @click="deleteCU(scope.row)"></elBtn>
		    		</el-button-group>		    		
		    	</template>
		    </el-table-column>
		</elemTable> 
	</div>

</el-card>    
<el-card class="box-card" style="margin-right: 5px; margin-left: 5px; margin-top:10px; " shadow="hover">
  <div slot="header" class="clearfix">
    <span>税收率</span>
  </div>
  <div>
 <elemTable ref="asdas" :dataList="form.rrList" :isShowPage="false">
		    <el-table-column prop="name" label="税收名称">
		    	<template slot-scope="scope">
		    			<span>{{scope.row.name}}</span>
		      </template>
		    </el-table-column>
		    <el-table-column prop="ratio" label="比率">
		    	<template slot-scope="scope">
		    			<span>{{scope.row.ratio}}</span>
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
		    <el-table-column label="操作" v-if="form.status == 0">
		    	<template slot-scope="scope">
		    		<el-button-group>
					    <elBtn type="danger" text="删除" @click="deleteRR(scope.row)"></elBtn>
		    		</el-button-group>		    		
		    	</template>
		    </el-table-column>
		</elemTable> 
				  
	  		<div style="float: right;margin-top: 15px;line-height: 12px; margin-bottom:15px;">
  	  		<el-button @click="cancel()">返  回</el-button>
	  	</div>
	</div>

</el-card>
	  </div>
</template>

<script>
import mixin from "../../mixin/mixin.js";
import local from "../../local.js";
import configs from "../../configs.js";
export default {
  mixins: [mixin],
  data() {
    return {
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
		}else
		{
			this.form.id = null;
		}
  },
  methods: {
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