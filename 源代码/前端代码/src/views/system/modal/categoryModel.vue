<template>
	<el-dialog custom-class="jz-modal" :title="categoryModalType === 'add' ? '新增分类' : '分类编辑'" :visible="modal" :before-close="beforeClose" :close-on-click-modal="false" :width="modalWidth">
            <el-form class="modal-form" v-if="modal" :model="form" ref="form" :inline="true" :rules="rules" label-width="80px">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
                        <el-form-item label="分类编码" prop="claCode" v-if="categoryModalType === 'add'">
                            <inputItem :value.sync="form.claCode" @blur="checkClaCode"></inputItem>
                        </el-form-item>
                        <el-form-item label="分类编码" v-else>
                            <span>{{form.claCode}}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
                        <el-form-item label="分类名称" prop="claName">
                            <inputItem :value.sync="form.claName"></inputItem>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
                        <el-form-item label="排序" prop="order">
                            <inputItem :value.sync="form.order" type="number" maxlength="9" @blur="checkOrder"></inputItem>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
                        <el-form-item label="备注" prop="remark">
                            <inputItem type="textarea" :value.sync="form.remark"></inputItem>
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
import local from '../../../local.js'
	export default {
        mixins: [mixin],
		data() {
            var vm = this
            return {
                value4: [new Date(2000, 10, 10, 10, 10), new Date(2000, 10, 11, 10, 10)],
				form: {
                    claCode: '',
                    claName: '',
                    order: '',
                    remark: ''
                },
                roleList: [],
                rules: {
                    //claCode: [{required: true, message: '分类编码不能为空' }, this._ruleLength(16)],
                    claCode: [{required: true, message: '分类编码不能为空' },  this._ruleclaCode()],
                    claName: [{required: true, message: '分类名称不能为空' }, this._ruleLength(20)],
                    order: [{required: true, message: '排序不能为空' }, this._ruleLength(9)],
                    remark: [this._ruleLength(100)]
                },
                thisUser: local.get('userinfo')
			}
		},
		props: {
			modal: {
				default: false
			},
            categoryModalType: {
                default: 'add'
            },
            classificate: {
                default: function() {
                    return {}
                }
            }
		},
        computed: {
            userID() {
                console.log(this.userId)
                return this.userId
            }
        },
		mounted() {
            Object.assign(this.form, this.classificate)    
            console.log(this.thisUser);
		},
		methods: {
            checkOrder(val){
                var vm=this;
                //检查排序是否是正整数
                var re = /^[0-9]+$/ ;
                if(val){
                    if(!re.test(val)){
                            this.$message({ type: 'warning', message: '排序不能为小数' });
                            vm.form.order="";
                            return;
                        }
                }
            },
			cancel() {
				this.$emit('close')
			},
			submit() {
                this.$refs['form'].validate((valid) => {
                    if (valid) {
                        let o = {}, method = 'classificate/update';
                        if(this.categoryModalType === 'add') {  
                            method = 'classificate/create' 
                            o = {
                                claCode: this.form.claCode,
                                claName: this.form.claName,
                                order: this.form.order,
                                remark: this.form.remark,
                                createdPersonId: this.thisUser.userId,
                                createdPerson: this.thisUser.nickName
                            }
                        }else {
                            o = {
                                claCode: this.form.claCode,
                                claName: this.form.claName,
                                order: this.form.order,
                                remark: this.form.remark,
                                id: this.form.id,
                                updatedPersonId: this.thisUser.userId,
                                updatedPerson: this.thisUser.nickName
                            }
                        }
                        this._ajax({url: this.rootAPI + method, param: o})
                        .then((function(d) {
                            if(d.state === 0) {
                                this.$message({ type: 'success', message: '操作成功' }); 
                                this.$emit('submit')
                            }else if(d.state === 2){
                                this.$message({ type: 'warning', message: '没有要修改的记录！' });
                            }else if(d.state === 1){
                                this.$message({ type: 'warning', message: '分类编码重复！' });
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
            checkClaCode(val){
                var vm = this;
                return this._ajax({
                    url: this.rootAPI, 
                    name: 'classificate/list', 
                    param: {'claCode':val}, 
                    loading: 'dataLoading'
                }).then(function(d){
                    if(d.dataCount!=0&&val!=''){
                        vm.$message.error('分类编码重复' );
                        vm.form.claCode=null;
                        return;
                    }
                })
            }
		}
	}
</script>