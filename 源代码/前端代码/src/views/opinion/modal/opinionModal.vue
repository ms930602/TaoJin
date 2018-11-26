<template>
	<el-dialog custom-class="jz-modal" :title="baseModalType === 'add' ? '新增意见反馈' : '编辑意见反馈'" :visible="modal" :before-close="beforeClose" :close-on-click-modal="false" width="40%">
            <el-form class="modal-form" v-if="modal" :model="form" ref="form" :inline="true" :rules="rules" label-width="80px">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
                        <el-form-item label="标题" prop="title">
                            <inputItem :value.sync="form.title"></inputItem>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
                        <el-form-item label="类型" prop="type" >
                            <el-radio v-model="form.type" label="1">意见</el-radio>
                            <el-radio v-model="form.type" label="2">BUG</el-radio>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
                        <el-form-item label="反馈内容" prop="remark">
							<el-input type="textarea" v-model="form.remark" 
							:rows="5"
							placeholder="请输入内容"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>                        
            </el-form>              
            <div slot="footer" class="dialog-footer flex-x-end">
                <elBtn @click="cancel" text="取消">取消</elBtn>
                <elBtn @click="submit" type="primary" text="提交">提交</elBtn>
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
				form: {
					id:null,
                    title: '',
                    type: '1',
                    remark: ''
                },
                rules: {
                    title: [{required: true, message: '标题不能为空' }, this._ruleLength(200)],
                    remark: [{required: true, message: '密码不能为空' }, this._ruleLength(300)]
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
		},
		methods: {
			cancel() {
				this.$emit('close')
			},
			submit() {
                this.$refs['form'].validate((valid) => {
                    if (valid) {
                        let o = {}, method = 'opinion/update';
                        if(this.baseModalType === 'add') {
                            method = 'opinion/create' 
                            o = {
                                title: this.form.title,
                                type: this.form.type,
                                remark: this.form.remark
                            }
                        }else {
                            o = {
								id: this.form.id,
                                title: this.form.title,
                                type: this.form.type,
                                remark: this.form.remark
                            }
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