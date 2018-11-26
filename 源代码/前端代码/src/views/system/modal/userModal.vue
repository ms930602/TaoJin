<template>
	<el-dialog custom-class="jz-modal" :title="userModalType === 'add' ? '新增用户' : '用户编辑'" :visible="modal" :before-close="beforeClose" :close-on-click-modal="false" :width="modalWidth">
            <el-form class="modal-form" v-if="modal" :model="form" ref="form" :inline="true" :rules="rules" label-width="80px">
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
                        <el-form-item label="用户名称" prop="nickName" v-if="userModalType === 'add'">
                            <inputItem :value.sync="form.nickName"></inputItem>
                        </el-form-item>
                        <el-form-item label="用户名称" v-else>
                            <span>{{form.nickName}}</span>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
                        <el-form-item label="登录帐号" prop="loginName" v-if="userModalType === 'add'">
                            <inputItem :value.sync="form.loginName"></inputItem>
                        </el-form-item>
                        <el-form-item label="登录帐号" v-else>
                            <span>{{form.loginName}}</span>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
                        <el-form-item label="密码" prop="password" v-if="userModalType === 'add'">
                            <inputItem :value.sync="form.password" type="password" maxlength=16 minlength=6></inputItem>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
                        <el-form-item label="确认密码" prop="cfmPassword" v-if="userModalType === 'add'">
                            <inputItem :value.sync="form.cfmPassword" type="password" maxlength=16 minlength=6></inputItem>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
                        <el-form-item label="手机号码" prop="mobile">
                            <inputItem :value.sync="form.mobile"></inputItem>
                        </el-form-item>
                    </el-col>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
                        <el-form-item label="身份证" prop="identityCard">
                            <inputItem :value.sync="form.identityCard"></inputItem>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12">
                        <el-form-item label="是否激活" prop="status">
                            <switchItem :value.sync="form.status" :activeValue="1" :inactiveValue="0" :isdisabled="isstatus"></switchItem>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24">
                        <el-form-item label="角色绑定" class="user-role-list">
                            <el-checkbox-group v-model="form.roleList" :disabled="isrole">
                                <el-checkbox :label="el.id" v-for="el in roleList">{{el.name}}</el-checkbox>
                            </el-checkbox-group>
                        </el-form-item>
                    </el-col>
                </el-row>                          
            </el-form>              
            <div slot="footer" class="dialog-footer flex-x-end">
                <elBtn @click="cancel" text="取消">取消</elBtn>
                <elBtn @click="submit" type="primary" text="提交">提交</elBtn>
                 <elBtn v-show="userModalType !== 'add'&&!isuser"   @click="resetpass" type="info" text="重置密码">重置密码</elBtn>
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
            var vm = this
            var ruleCfmPassword = function(rule, value, callback) {
                if(value !== vm.form.password) {
                    callback(new Error('密码不一致'))
                }else {
                    callback();
                }
            }
			return {
                value4: [new Date(2000, 10, 10, 10, 10), new Date(2000, 10, 11, 10, 10)],
				form: {
                    nickName: '',
                    loginName: '',
                    password: '',
                    cfmPassword: '',
                    mobile: '',
                    identityCard: '',
                    roleList: [],
                    status: 1
                },
                roleList: [],
                rules: {
                    nickName: [{required: true, message: '用户名不能为空' }, this._ruleLength(16)],
                    loginName: [
											{required: true, message: '登录名不能为空' }, 
											this._ruleLength(20), 
											this._ruleExist(configs.userAPI+'user/checkLoginName','登录名',this.user)
										],
                    password: [{required: true, message: '密码不能为空' }, this._rulePassword()],
                    cfmPassword: [{required: true, message: '密码不能为空' },{ validator: ruleCfmPassword, trigger: 'blur' }],
                    mobile: [this._ruleMobile()],
                    identityCard: [this._ruleIDCard()]
                },
                thisUser: local.get('userinfo'),
                isstatus:false,
                isrole:false,
                isuser:false
			}
		},
		props: {
			modal: {
				default: false
			},
            userModalType: {
                default: 'add'
            },
            user: {
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
            Object.assign(this.form, this.user)
            this._ajax({url: this.api + '/permission/tsysRole/list', param: {statu: 1}})
            .then((function(d) {
                this.roleList = d.aaData           
            }).bind(this))
            .then((function() {
                if(this.userModalType !== 'add') {
                    this._ajax({url: this.api + '/permission/tsysRoleUser/getRolesByUserId', param: {userId: this.user.userId}})
                    .then((function(d) {
                        if(d.aaData.length > 0) {
                            var arr = []
                            d.aaData.forEach(function(el) {
                                arr.push(el.id)
                            })
                            Object.assign(this.form, {roleList: arr})
                        }
                    }).bind(this))
                }                
            }).bind(this))          


            //判断是否是自己的账号
            if(this.userModalType !== 'add'){
                if(this.form.userId==this.thisUser.userId){
                    this.isstatus=true;
                    this.isrole=true;
                    this.isuser=true;
                }
            }
            
		},
		methods: {
            //校验登陆账号
            checkLoginName(val){
                var vm=this;
                //包含 数字  字母 下划线 中文  不包含：特殊字符，空格
                var reg=/[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
                if(reg.test(val)){
                    this.$message({type: "warning",message: "登陆账号不能包含非法字符" });
                    vm.form.loginName="";
                    return;
                }
                var regs=/\s/;
                if(regs.test(val)){
                    this.$message({type: "warning",message: "登陆账号不能包含空格" });
                    vm.form.loginName="";
                    return;
                }
            },
            resetpass(){//重置密码
               this._comfirm('重置后密码为：【123456】,是否重置？')
        		.then((function() {
                    let o={
                      userId: this.form.userId
                    };
        			return this._ajax({url: this.userAPI + 'user/resetpass', param: o, arr:true})
        		}).bind(this))
        		.then((function(d) {
					if (d.state == 0) {
                  this.$message({ type: "success", message: "密码重置成功" });
                  this.$emit('close');
                } else {
                  this.$message({
                    type: "warning",
                    message: "密码重置失败" // + d.msg
                  });
                  this.$emit('close');
                }
        		}).bind(this))	
        		.catch(this._confirmCancle);
            },
			cancel() {
				this.$emit('close')
			},
			submit() {
                this.$refs['form'].validate((valid) => {
                    if (valid) {
                        let o = {}, method = 'user/update';
                        if(this.userModalType === 'add') {  
                            method = 'user/create' 
                            o = {
                                nickName: this.form.nickName,
                                loginName: this.form.loginName,
                                mobile: this.form.mobile,
                                identityCard: this.form.identityCard,
                                password: this.form.password,
                                status: 1,
                            }
                        }else {
                            o = {
                                mobile: this.form.mobile,
                                identityCard: this.form.identityCard,
                                userId: this.form.userId,
                                status: this.form.status
                            }
                        }
                        this._ajax({url: this.userAPI + method, param: o})
                        .then((function(d) {
                            let userID;
                            if(this.userModalType === 'add') {  
                                userID = d.aaData.userId
                            }else {
                                userID = this.form.userId
                            }
                            if(this.form.roleList.length > 0) {
                                let arr = []
                                for(let i = 0, l = this.form.roleList.length; i < l; i++) {
                                    arr.push(this.form.roleList[i])
                                }
                                this._ajax({url: this.api + '/permission/tsysRoleUser/binDing', param: {roleId: arr, userId: userID}, arr: true})                     
                            }else {
                                this._ajax({url: this.api + '/permission/tsysRoleUser/binDing', param: {userId: userID}, arr: true})
                            }
                        }).bind(this))
                        .then((function(d) { 
                            this.$message({ type: 'success', message: '操作成功' }); 
                            this.$emit('submit')
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