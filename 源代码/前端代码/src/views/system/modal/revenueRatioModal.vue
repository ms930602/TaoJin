<template>
	<el-dialog custom-class="jz-modal" :title="baseModalType === 'add' ? '新增税收类' : '税收类'" :visible="modal" :before-close="beforeClose" :close-on-click-modal="false" width="40%">
            <el-form class="modal-form" v-if="modal" :model="form" ref="form" :inline="true" :rules="rules" label-width="80px">
                <el-row>
                	<el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
                		<el-form-item label="游戏名称" >
                			<tagItem :text="gameObj.name"></tagItem>
                		</el-form-item>
                	</el-col>
                </el-row>
				<el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
                        <el-form-item label="税收名称" prop="name">
                            <inputItem :value.sync="form.name" :maxlength="120" ></inputItem>
                        </el-form-item>
                    </el-col>
					<el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
						<el-form-item label="比率(%)" prop="ratio" >
							<inputItem :value.sync="form.ratio"  :maxlength="120"></inputItem>
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
                    name:'',
					gameId:null,
                    ratio:1
                },
                rules: {
                    name: [{required: true, message: '名称不能为空' }, this._ruleLength(120)],
                    ratio: [{required: true, message: '比率不能为空' }],
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
			gameObj:{
				default: function() {
					return {}
				}
			},
            obj: {
                default: function() {
                    return {}
                }
            }
		},
		mounted() {
            Object.assign(this.form, this.obj)
		},
		methods: {
			cancel() {
				this.$emit('close')
			},
			submit() {
                this.$refs['form'].validate((valid) => {
                    if (valid) {
						this.isSubmit = true;
                        let o = {}, method = 'revenueRatio/update';
						o = {
							gameId: this.gameObj.id,
							name: this.form.name,
							ratio: this.form.ratio
						}
                        if(this.baseModalType === 'add') {
                            method = 'revenueRatio/create'
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
