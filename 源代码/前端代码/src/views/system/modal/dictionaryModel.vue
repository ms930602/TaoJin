<template>
	<el-dialog custom-class="jz-modal" :title="dictionaryModalType === 'add' ? '新增字典' : '字典编辑'" :visible="modal" :before-close="beforeClose" :close-on-click-modal="false" :width="modalWidth">
            <el-form class="modal-form" v-if="modal" :model="form" ref="form" :inline="true" :rules="rules" label-width="80px">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
                        <el-form-item label="数据分类" prop="claCode" v-if="dictionaryModalType === 'add'">
                            <selectInput :value.sync="form.claCode" filterable @selectChange="claSelectChange" :clearable="true">
                                <el-option
                                v-for="item in claList"
                                :key="item.id"
                                :label="item.claName"
                                :value="item.claCode"
                                >
                                </el-option>
                            </selectInput>
                        </el-form-item>
                        <el-form-item label="数据分类" v-else>
                            <span>{{form.claName}}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
                        <el-form-item label="字典编码" prop="dicCode">
                            <inputItem :value.sync="form.dicCode" @blur="checkDicCode"></inputItem>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
                        <el-form-item label="字典名称" prop="dicName">
                            <inputItem :value.sync="form.dicName"></inputItem>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
                        <el-form-item label="排序" prop="order">
                            <inputItem :value.sync="form.order" maxlenth="10"></inputItem>
                        </el-form-item>
                    </el-col>
                </el-row>
                <!-- <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
                        <el-form-item label="是否默认" prop="isDefault">
                            <switchItem :value.sync="form.isDefault" :activeValue="1" :inactiveValue="0"></switchItem>
                        </el-form-item>
                    </el-col>
                </el-row> -->
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
                    dicCode: '',
                    dicName: '',
                    order: '',
                    isDefault: 0
                },
                dictionaryList: [],
                rules: {
                    claCode: [{required: true, message: '数据分类不能为空' }],
                    //dicCode: [{required: true, message: '字典编码不能为空' }, this._ruleLength(16)],
                    dicCode: [{required: true, message: '字典编码不能为空' }, this._ruleclaCode()],
                    dicName: [{required: true, message: '字典名称不能为空' }, this._ruleLength(20)],
                    order: [{required: true, message: '排序不能为空' },this._ruleLength(10)]
                },
                selectCla:{},
                claList:[],
                thisUser: local.get('userinfo')
			}
		},
		props: {
			modal: {
				default: false
			},
            dictionaryModalType: {
                default: 'add'
            },
            dictionary: {
                default: function() {
                    return {}
                }
            }
		},
        computed: {
           
        },
		mounted() {
            Object.assign(this.form, this.dictionary)    
            let method = 'classificate/list';
            this._ajax({url: this.rootAPI + method, param: {}})
            .then((function(d) {
				this.claList = d.aaData;
                // console.log(d.aaData)
			}).bind(this))
		},
		methods: {
            //校验字典编码重复
            checkDicCode(val){
                if(val && this.form.claCode){
                  
                var vm=this;
                return this._ajax({
                url: this.rootAPI,
                name: "dictionary/list",
                param: {'dicCode':val, 'claCode' : this.form.claCode},
                loading: "dataLoading"
            }).then((function(d){
                if(d.dataCount!=0){
                     this.$message({ type: 'warning', message: '字典编码不能重复' }); 
                     vm.form.dicCode="";
                     return;
                }
            }).bind(this));
                }

            },
            claSelectChange(ob){
                var obj = this.claList.find((item)=>{//这里的userList就是上面遍历的数据源
                    return item.claCode === ob;//筛选出匹配数据
                });
                // console.log(obj);
                this.form.claCode = obj.claCode;
                this.form.claName = obj.claName;
                // console.log(this.form.claCode);
            },
			cancel() {
				this.$emit('close')
			},
			submit() {
                this.$refs['form'].validate((valid) => {
                    if (valid) {
                        let o = {}, method = 'dictionary/update';
                        if(this.dictionaryModalType === 'add') {  
                            method = 'dictionary/create' 
                            o = {
                                claCode: this.form.claCode,
                                claName: this.form.claName,
                                dicCode: this.form.dicCode,
                                dicName: this.form.dicName,
                                order: this.form.order,
                                isDefault: 0,
                                createdPersonId: this.thisUser.userId,
                                createdPerson: this.thisUser.nickName
                            }
                        }else {
                            o = {
                                claCode: this.form.claCode,
                                claName: this.form.claName,
                                dicCode: this.form.dicCode,
                                dicName: this.form.dicName,
                                order: this.form.order,
                                isDefault: 0,
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
                                this.$message({ type: 'warning', message: '字典编码重复！' });
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