<template>
	<el-dialog custom-class="jz-modal" :title="baseModalType === 'add' ? '新增物品' : '编辑物品'" :visible="modal" :before-close="beforeClose" :close-on-click-modal="false" width="40%">
            <el-form class="modal-form" v-if="modal" :model="form" ref="form" :inline="true" :rules="rules" label-width="80px">
                <el-row>
					<el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
						<el-form-item label="首字母" prop="firstCode" >
							<inputItem :value.sync="form.firstCode" :maxLength="1"></inputItem>
						</el-form-item>
					</el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
                        <el-form-item label="物品名称" prop="name" >
                            <inputItem :value.sync="form.name"></inputItem>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
					<el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
						<el-form-item label="所属游戏" prop="gameId">
							<selectInput :value.sync="form.gameId" filterable>
								<el-option
										v-for="item in gameOption"
										:key="item.id"
										:label="item.firstCode+ ' ' + item.name"
										:value="item.id">
									</el-option>
							</selectInput>
						</el-form-item>
					</el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
                    	<el-form-item label="类型" prop="type">
                    		<selectInput :value.sync="form.type" filterable>
                    			<el-option
                    					v-for="item in typeOption"
                    					:key="item.key"
                    					:label="item.value"
                    					:value="item.key">
                    				</el-option>
                    		</selectInput>
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
				form: {
					id:null,
					gameId:null,
                    name: '',
                    type: '0',
                    firstCode: ''
                },
				gameOption:[],
                rules: {
                    gameId: [{required: true, message: '所属游戏不能为空' }],
                    name: [{required: true, message: '名称不能为空' }, this._ruleLength(100)],
					type: [{required: true, message: '类型不能为空' }],
					firstCode: [{required: true, message: '首字母不能为空' }, this._ruleLength(1)]
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
			this._searchDic("ITEM_TYPE").then(
				function(d) {
					this.typeOption = this._dicKey(d);
				}.bind(this)
			);
			this.loadGame();
		},
		methods: {
			loadGame(){
				this._ajax({url: this.rootAPI, name: 'game/list', param: {}}).then((function(d){
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
                        let o = {}, method = 'item/update';
                        o = {
								gameId:this.form.gameId,
								name:this.form.name,
								type:this.form.type,
								firstCode:this.form.firstCode,
                            }
                        if(this.baseModalType === 'add') {
                            method = 'item/create' 
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
