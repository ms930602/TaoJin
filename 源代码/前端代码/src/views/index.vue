<template>
<div :class="[isCollapse ? 'mini-sidebar' : '', 'app-index']">
  <!-- 左侧导航栏 -->
	<div class="left-nav-bar">
		
    <ul :class="[isCollapse ? 'menu-collapse' : '', 'metismenu']" id="menu">
      <li v-for="frt in routers">
        <el-tooltip effect="dark" :content="frt.name" placement="right" :disabled="isCollapse ? false : true">
          <a class="has-arrow btn-waves" href="javascript:void(0);" v-if="frt.hasChildren">
            <i :class="['iconfont', frt.iconCls]"></i>
            <span class="nav-title">{{frt.name}}</span>         
          </a>
          <a class="btn-waves" href="javascript:void(0);" v-else @click="router(frt.caption)" :data-link="frt.caption">
            <i :class="['iconfont', frt.iconCls]"></i>
            <span class="nav-title">{{frt.name}}</span>         
          </a>
        </el-tooltip> 
        <ul aria-expanded="true" v-if="frt.hasChildren">
          <li v-for="sec in frt.children">
            <el-tooltip effect="dark" :content="sec.name" placement="right" :disabled="isCollapse ? false : true">
              <a href="javascript:void(0);" class="btn-waves" @click="router(sec.caption)" v-if="!sec.hasChildren" :data-link="sec.caption">
                <i :class="['iconfont', sec.iconCls]"></i>
                <span class="nav-title">{{sec.name}}</span>
              </a>
              <a href="javascript:void(0);" class="has-arrow btn-waves" v-else>
                <i :class="['iconfont', sec.iconCls]"></i>
                <span class="nav-title">{{sec.name}}</span>
              </a>
            </el-tooltip>  
            <ul aria-expanded="true" v-if="sec.hasChildren">
              <li v-for="thd in sec.children">
                <el-tooltip effect="dark" :content="thd.name" placement="right" :disabled="isCollapse ? false : true">
                  <a href="javascript:void(0);" class="btn-waves" @click="router(thd.caption)" :data-link="thd.caption">
                    <i :class="['iconfont', thd.iconCls]"></i>
                    <span class="nav-title">{{thd.name}}</span>
                  </a>
                </el-tooltip> 
              </li>
            </ul>
          </li>
        </ul> 
      </li>
    </ul>
	</div>
  <!-- 顶部 -->
	<nav class="navbar navbar-expand-lg navbar-dark top-nav-bar justify-content-between"> 
    <div class="navbar-left flex-x-start">
      <div class="navbar-header">
        <img src="lib/img/home_ing_logo.png">
        <span class="h1 navbar-brand mb-0"><img style="	padding-left: 10px;	margin-top: 4px;" src="lib/img/img_word.png"></span>
        <!-- <span class="h1 navbar-brand mb-0">{{ logoTXT}}</span> -->
      </div>
      <div class="flex-y-center nav-btn btn-waves" @click="toggNav">
        <i class="iconfont icon-daohang"></i>
      </div>
    </div>    
    <div style="height: 100%;color: #ffffff" class="flex-y-center">
      <div class="user-name flex-y-center">{{userInfo.nickName}}</div>
      <div class="flex-y-center flex-x-center user-edit">
        <el-dropdown class="flex-y-center flex-x-center" trigger="click" @command="handleCommand">
            <span class="el-dropdown-link">
              <i class="el-icon-caret-bottom el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="a">修改密码</el-dropdown-item>
              <!-- <el-dropdown-item command="b" v-if="isSys">登录错误设置</el-dropdown-item> -->
            </el-dropdown-menu>
          </el-dropdown>
      </div>
      <div class="flex-y-center nav-btn btn-waves" @click="logout">
        退出
      </div>
    </div> 	
	</nav>
  <!-- 内容 -->
	<div class="main-container">
		<div class="bread-box flex-y-center flex-x-between">  
        <div>
          <div class="page-title">{{currentPage}}</div>
          <div class="flex-y-center">
            <span class="c-page">{{currentModel}}</span>      
            <span class="xiegang">/</span>
            <span class="c-model">{{currentPage}}</span>
            <span v-if='isThrName' class="xiegang">/</span>
            <span  class="c-model">{{currentPageThr}}</span> 
          </div> 
        </div>     
        
    </div>
		<div class="page-container">
			<transition name="custom-classes-transition"
		      enter-active-class="animated fadeIn"
		      leave-active-class="animated fadeOut" mode="out-in">        
		      <router-view></router-view>
		    </transition>
		</div>	               
	</div>
  <el-dialog custom-class="jz-modal user-role" title="修改密码" :visible.sync="modalPwd" :before-close="handleClosePwd" size="tiny" :close-on-click-modal="false">
        <el-form class="modal-form" v-if="modalPwd" :model="modelFormPwd" ref="modelFormPwd" :inline="true" :rules="modalRulesPwd" label-width="80px">
          <div class="flex-x-center">
            <el-form-item label="旧密码" prop="oldPwd">
              <el-input v-model="modelFormPwd.oldPwd" type="password" size="mini"></el-input>
            </el-form-item>
          </div>
          <div class="flex-x-center">
            <el-form-item label="新密码" prop="newPwd">
                <el-input v-model="modelFormPwd.newPwd" type="password" size="mini"></el-input>
            </el-form-item>
          </div>
          <div class="flex-x-center">
            <el-form-item label="确认密码" prop="confirmPwd">
                <el-input v-model="modelFormPwd.confirmPwd" type="password" size="mini"></el-input>
            </el-form-item>
          </div>       
        </el-form>          
        <span slot="footer" class="dialog-footer" v-if="!abled">
          <elBtn @click="cancelPwd" text="取消"></elBtn>
          <elBtn @click="submitPwd" text="提交" type="primary"></elBtn>
        </span>
    </el-dialog>
    <el-dialog custom-class="jz-modal user-role" title="登录错误次数设置" :visible.sync="modalErrorCount" :before-close="handleCloseErrorCount" size="tiny" :close-on-click-modal="false">
      <el-form class="modal-form" v-if="modalErrorCount" :inline="true" label-width="120px">
        <div class="flex-x-center">
          <el-form-item label="最大次数">
            <el-input-number v-model="errorCount" :min="1" :max="5"></el-input-number>
          </el-form-item>          
        </div>
        <div class="flex-x-center">
          <el-form-item label="锁定时间(分钟)">
            <el-input-number v-model="errorMin" :min="1" :max="30"></el-input-number>
          </el-form-item> 
        </div>             
      </el-form>          
      <span slot="footer" class="dialog-footer" v-if="!abled">
        <span class="jz-btn jz-btn-gray" @click="cancelErrorCount">取消</span>
        <span class="jz-btn jz-btn-prime" @click="submitErrorCount">提交</span>
      </span>
  </el-dialog>
</div>
</template>
<script>
import mixin from '../mixin/mixin.js'
import local from '../local.js' 
import configs from '../configs.js' 
export default {
    mixins: [mixin],
    data() {
      var vm = this
      var vNewPwd = function(rule, value, callback) {
        var val = $.trim(value),
            pPattern = vm.regPassword
        if(val.length === 0) {
          callback(new Error('请输入密码'))
        }else if(!pPattern.test(val)){
          callback(new Error('密码应为6到16位字符加数字'))
        }else {
          callback();
        }
      }
      var vConfirmPwd = function(rule, value, callback) {
        var val = $.trim(value)
        if(val !== $.trim(vm.modelFormPwd.newPwd)) {
          callback(new Error('密码不一致'))
        }else {
          callback();
        }
      }
      var sysFlag = false
      var menu = local.get('navlist')
      menu.forEach(function(el) {
        if(el.caption === 'sys') {
          sysFlag = true
        }
      })
      return {
      	isThrName:true,
        logoTXT: CITY + '中国烟草',
        bottomTXT: '版权所有：' + CITY + '商务局',
        isCollapse: false,
        navActive: '',
        navOpens: [],
        scrollTop: 0,
        routers: menu,
        userInfo: local.get('userinfo'),
        routerName: '',
        fatherpath:'',
        modalPwd: false,
        modelFormPwd: {
          oldPwd: '',
          newPwd: '',
          confirmPwd: ''
        },
        modalRulesPwd: {
          newPwd: [{validator: vNewPwd, trigger: 'blur'}],
          confirmPwd: [{ validator: vConfirmPwd, trigger: 'blur' }],
        },
        modalErrorCount: false,
        errorCount: 1,
        errorMin: 1,
        errorCountID: null,
        errorMinID: null,
        isSys: sysFlag
      };
    },
    mounted() {
      var vm = this
    	$(window).scroll((function() {
    		this.scrollTop = $(document).scrollTop()
		  }).bind(this))
      $("#menu").metisMenu({});
      $('.metismenu').slimScroll({
          position: 'left',
          height: 'auto',
          width: 'none',
      });
      Waves.attach('.btn-waves');
      Waves.init();
      $('.page-container').css('min-height', ($('.bottom-nav-bar').offset().top - 146)) 
      this.routerName = this.$route.name
      $('.btn-waves').removeClass('active')
      $('.btn-waves').each(function(index, element) {
        var link = $(element).data('link')
        if(link === vm.routerName) {
          $(element).addClass('active')
          $(element).parent().parent().addClass('in')
          $(element).parent().parent().parent().addClass('active')
        }
      })
      $('.router-link').removeClass('active')
      $('.router-link').each(function(index, element) {
        var link = $(element).data('link')
        if(link === vm.routerName) {
          $(element).addClass('active')
        }
      })
      if(!this.userInfo.lastLoginTime) {
        this.showMsg('初始密码存在风险，是否现在去修改密码？', (function() {             
          this.modalPwd = true
        }).bind(this))  
      }
    },
    computed: {
      currentModel() {
        return this.$route.meta.father
      },
      currentPage() {
        return this.$route.meta.name
      },
      currentPageThr(){
      	if(!this.$route.meta.thrName){
      		this.isThrName=false;
      	}else{
      		this.isThrName=true;
      	}
      	return this.$route.meta.thrName
      },
    	showBackTop() {
    		return this.scrollTop > 0 ? true : false
    	}

    },
    methods: {
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      back2Top() {
        $('html, body').animate({scrollTop: 0}, 500);
      },
      toggNav() {
        this.navOpens = []
        this.isCollapse = !this.isCollapse
      },
      router(name) {        
        if(name === 'datavpeisong') {
          window.open(DATAV_PSZX)
        }else if(name === 'datavtuancai') {
          window.open(DATAV_TCDW)
        }else if(name === 'datavqiye') {
          window.open(DATAV_QY)
        }else if(name === 'datavcangchu') {
          window.open(DATAV_CANGCHU)
        }else {
          this.$router.push({name: name})
        }
      },
      logout() {  
        this._comfirm('退出登录？')
        .then((function() {
          local.removeRedis()
          local.remove('userinfo')
          local.remove('token')
          local.remove('navlist')
          local.remove('sessionUser')
          this.$router.push({name: 'login'})
        }).bind(this))
        .catch(this._confirmCancle);
      },
      handleCommand(cmd) {
        if(cmd === 'a') {
          this.modalPwd = true
        }else if(cmd === 'b') {
          $ajax.call(this, {url: configs.api + '/user/dictionaries/list', param: {type: 'login_conf'}})
          .then((function(d) {
            if(d.state === 0) {
              this.errorCount = d.aaData[1].value
              this.errorCountID = d.aaData[1].id
              this.errorMin = d.aaData[0].value
              this.errorMinID = d.aaData[0].id
              this.modalErrorCount = true
            }
          }).bind(this))          
        }
      },
      handleClosePwd(done) {
        this.modelFormPwd = {
          oldPwd: '',
          newPwd: '',
          confirmPwd: ''
        }
        done()
      },
      cancelPwd() {
        this.modalPwd = false
        this.modelFormPwd = {
          oldPwd: '',
          newPwd: '',
          confirmPwd: ''
        }
      },
      submitPwd() {
        this.$refs['modelFormPwd'].validate((valid) => {
          if (valid) {
            this._ajax({url: configs.userAPI + 'user/updateMyPassword', param: {
                  oldPassword: $.trim(this.modelFormPwd.oldPwd),
                  newPassword: $.trim(this.modelFormPwd.newPwd)
                }})
                .then((function(d) {
                  if(d.state === 0) {
                    this.$message({ type: 'success', message: '修改成功，请重新登录' });
                    local.remove('userinfo')
                    local.remove('token')
                    local.remove('navlist')
                    local.remove('sessionUser')
                    this.$router.push({name: 'login'})
                  }
                }).bind(this))
          }
        })
      },
      handleCloseErrorCount(done) {
        this.cancelErrorCount()
        done()
      },
      cancelErrorCount() {
        this.modalErrorCount = false
        $ajax.call(this, {url: configs.api + '/user/dictionaries/list', param: {type: 'login_conf'}})
        .then((function(d) {
          if(d.state === 0) {
            this.errorCount = d.aaData[1].value
            this.errorCountID = d.aaData[1].id
            this.errorMin = d.aaData[0].value
            this.errorMinID = d.aaData[0].id
          }
        }).bind(this))  
      },
      submitErrorCount() {
        $ajax.call(this, {url: configs.api + '/user/dictionaries/update', param: {
          id: this.errorCountID,
          value: this.errorCount,
          type: 'login_conf'
        }})
        .then((function(d) {
          return $ajax.call(this, {url: configs.api + '/user/dictionaries/update', param: {
            id: this.errorMinID,
            value: this.errorMin,
            type: 'login_conf'
          }})
        }).bind(this))
        .then((function(d) {
          this.$message({ type: 'success', message: '操作成功' });
          this.cancelErrorCount()
        }).bind(this))
      }
    },
    watch:{
      $route(to, from){
        var vm = this
        this.routerName = to.name
        //处理进入二级页面时一级页面任然是选中状态
        this.fatherpath=to.meta.fatherpath||'';
        $('.btn-waves').removeClass('active')
        $('.btn-waves').each(function(index, element) {
          var link = $(element).data('link')
          if(link === vm.routerName) {
            $(element).addClass('active')
          }
          if(link===vm.fatherpath){
            $(element).addClass('active')
          }
        })
        $('.router-link').removeClass('active')
        $('.router-link').each(function(index, element) {
          var link = $(element).data('link')
          if(link === vm.routerName) {
            $(element).addClass('active')
          }
        })
      }
    }
  }
</script>
<style>
.iconfont{
  font-size: 24px !important;
}
</style>
