<template>

	<div class="page-login" ref="loginId_id" id='loginId' :style="winSize">
		<!--新的login页面-->
		<div class="new_login">
			<div class="header">
				<img class="header_left" src="../../lib/img/loginImg/logo.png" alt="" />
				<!-- <img class="header_right" src="../../lib/img/loginImg/kh.png" alt="" /> -->
			</div>

			<div class="content">
				<div class="login_form">
					<div class="form_content">
						<el-form id='loginform' label-position="left" label-width="0px" class="loginform demo-ruleForm card-box " :model='data' :rules="rule_data" ref='data'>
							<h1 class="title" style="color:#3B71C8">安全登录</h1>
							<el-form-item prop='username'>
								<el-input class='userImg' type="text" auto-complete="off" placeholder="用户名" v-model='data.username' @keyup.native.enter="login('data')"></el-input>
							</el-form-item>
							<el-form-item prop='password'>
								<el-input class='pwdImg' type="password" auto-complete="off" placeholder="密码" v-model='data.password' @keyup.native.enter="login('data')"></el-input>
							</el-form-item>
							<el-form-item prop='verifiCode'>
								<el-input class="verifiImg" v-model="data.verifiCode" placeholder="请输入验证码" :maxlength="6" @keyup.enter.native="loginlogin('data')"></el-input>
								<div class="yzm">
									<img style="height: 40px;" :src="verifyCodeSRC" @click="refreshVerifyCode">
								</div>
							</el-form-item>
							<el-form-item>
								<el-button class='btn lgbtn'  size='large' @click='login("data")'>登录</el-button>
							</el-form-item>
						</el-form>
					</div>
				</div>
			</div>
		</div>
		<ul class="biaoqian">
			<li></li>
			<li></li>
			<li></li>
		</ul>
		<div style='width: auto;height:60px;'></div>
		<footer id='footer'>成都九洲电子信息系统股份有限公司提供技术支持   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;版本号：<span v-text="sysVerion"></span></footer>
	</div>
</template>

<script>
	import '../scss/login.scss'
	import local from '../local.js'
	import configs from '../configs.js'
	export default {
		name: 'login',
		data() {
			return {
				sysVerion:configs.sysVerion,
				winSize: {
					width: '',
					height: ''
				},
				verifyCodeSRC: ROOT_API + '/login/auth/code',
				data: {
					username: '',
					password: '',
					verifiCode: ''
				},

				rule_data: {
					username: [{
						required: true,
						message: '用户名不能为空！',
						trigger: 'blur'
					}],
					password: [{
						required: true,
						message: '密码不能为空！',
						trigger: 'blur'
					}],
				}
			}
		},
		computed: {
			company() {
				// return jz.Company_Name;
			}
		},
		methods: {
			register() {
				this.$router.push({ path: '/customerRegist' });
			},
			login(ref) {
				var vm = this
				this.$refs[ref].validate((valid) => {
					if(valid) {
						$.ajax({
							type: "POST",
							url: configs.loginAPI,
							dataType: 'jsonp',
		                    jsonp: "callback",
		                    jsonpCallback:"flightHandler",
							data: {
								loginName: vm[ref].username,
								password: vm[ref].password,
								systemId: configs.sysID,
								flag: 1,
								authCode: vm[ref].verifiCode,
							},
							success: function(d) {
		                        if(d.state === 0) {
									local.set('userinfo', d.loginUser)
									local.set('token', d.token)
									local.set('navlist', d.menuItemLlist)
									local.set('sessionUser', d.sessionUser)
									vm.$router.push({name: 'index'})
								}else {
									vm.$message({type: 'error', message: d.msg});
								}
		                    }
						})
// 						$.post(configs.loginAPI, {
// 							loginName: vm[ref].username,
// 							password: vm[ref].password,
// 							systemId: 4,
// 						})
// 						.then(function(d) {
// 							if(d.state === 0) {
// //									console.log(d)
// 								var menuItemLlist = d.menuItemLlist || []
// 								var buttonItemMap = d.buttonItemMap || {}
// 								var loginUser = d.loginUser || {}
// 								local.set('navlist', menuItemLlist)
// 								local.set('btnMap', buttonItemMap)
// 								local.set('userinfo', loginUser)
// 								local.set('token', d.token)
// 								vm.$router.push({ name: index });

// 							} else {

// 								vm.$message({ type: 'error', message: d.msg });  
// 							}
// 						})
// 						.fail(function() {
// 							vm.$message({ type: 'error', message: '接口异常' });  
// 						})
					}
				});
			},			
			refreshVerifyCode() {
				this.verifyCodeSRC = this.verifyCodeSRC + '?s=' + new Date().getTime()
			}
		},
		created() {
			
		},
		mounted() {
			this.refreshVerifyCode()
		},
		
	}
</script>