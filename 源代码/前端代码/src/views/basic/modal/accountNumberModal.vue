<template>
	<el-dialog custom-class="jz-modal" :title="baseModalType === 'add' ? '新增账号' : '编辑账号'" :visible="modal" :before-close="beforeClose" :close-on-click-modal="false" width="40%">
            <el-form class="modal-form" v-if="modal" :model="form" ref="form" :inline="true" :rules="rules" label-width="80px">
                <el-row style="font-size: 10px;color: red;">
					<p>提示：</p>
					<p>账号信息为保密信息，密码可保存为密码提示，无需存入真实密码。</p>
					<br>
				</el-row>
				<el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
                    	<el-form-item label="账号名称" prop="loginName" >
                    		<inputItem :value.sync="form.loginName"></inputItem>
                    	</el-form-item>
                    </el-col>
					<el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
						<el-form-item label="账号密码" prop="password" >
							<inputItem :value.sync="form.password"></inputItem>
						</el-form-item>
					</el-col>
                </el-row>
                <el-row>
					<el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
						<el-form-item label="是否封印" prop="status">
							<selectInput :value.sync="form.status">
								<el-option
										v-for="item in statusOption"
										:key="item.key"
										:label="item.value"
										:value="item.key">
									</el-option>
							</selectInput>
						</el-form-item>
					</el-col>
					<el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
						<el-form-item label="解封时间" prop="useTime" >
							<el-date-picker style="width:220px;" v-model="form.useTime" value-format="yyyy-MM-dd" type="date" ></el-date-picker>
						</el-form-item>
					</el-col>
                </el-row>
				<el-row>
					<el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
						<el-form-item label="所属游戏" prop="gameId">
							<selectInput :value.sync="form.gameId" filterable>
								<el-option
										v-for="item in gameOption"
										:key="item.gameId"
										:label="item.firstCode+ ' ' + item.name"
										:value="item.gameId">
									</el-option>
							</selectInput>
						</el-form-item>
					</el-col>
					<el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
						<el-form-item label="备注" prop="remark">
							<el-input type="textarea" v-model="form.remark" 
							:rows="5"
							placeholder="请输入内容"></el-input>
						</el-form-item>
					</el-col>
					
				</el-row>
            </el-form>              
            <div slot="footer" class="dialog-footer flex-x-end">
                <elBtn type="primary" @click="submit" :loading="isSubmit" text="提交"></elBtn>
                <elBtn @click="cancel" text="取消">取消</elBtn>
            </div>
        </el-dialog>
</template>
<script>
import mixin from '../../../mixin/mixin.js'
import configs from '../../../configs.js'
import local from '../../../local.js'
	export default {
        mixins: [mixin],
		data() {
			return {
                isSubmit:false,
				gameOption:[],
				statusOption:[
					{key:'0',value:'未封印'},
					{key:'1',value:'已被封印'}
				],
				form: {
					id:null,
					gameId:null,
					loginName:'',
					password:'',
					status:'0',
					useTime:null,
					remark:'',
                },
                rules: {
                    gameId: [{required: true, message: '所属游戏不能为空' }],
                    loginName: [{required: true, message: '账号不能为空' }, this._ruleLength(300)],
					status: [{required: true, message: '封印状态不能为空' }]
                }
			}
		},
		props: {
			modal: {
				default: false
			},
            baseModalType: {
                default: 'add'
            },
            obj: {
                default: function() {
                    return {}
                }
            }
		},
		mounted() {
            Object.assign(this.form, this.obj)
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
			cancel() {
				this.$emit('close')
			},
			submit() {
                this.$refs['form'].validate((valid) => {
                    if (valid) {
                        this.isSubmit = true;
                        let o = {}, method = 'userAccountNumber/update';
                        o = {
                                gameId:this.form.gameId,
                                loginName:this.form.loginName,
                                password:this.form.password,
                                status:this.form.status,
                                useTime:this.form.useTime,
                                remark:this.form.remark
                            }
                        if(this.baseModalType === 'add') {
                            method = 'userAccountNumber/create' 
                        }else {
                            o.id = this.form.id;
                        }
                        this._ajax({url: this.rootAPI + method, param: o})
                        .then((function(d) {
                            if(d.state === 0) {
                            		this.$message({ type: 'success', message: '操作成功' }); 
                            		this.$emit('submit')
                            }
                            else{
                            		this.$message({ type: 'warning', message: '操作失败' });
                            }
                            this.isSubmit = false;
                        }).bind(this))
                    }
                })
			},
            beforeClose(done) {
                this.cancel()
                done()
            },
		}
	}
</script>
