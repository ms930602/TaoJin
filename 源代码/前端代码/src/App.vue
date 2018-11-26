<template>
  <div class="page-app">
    <router-view></router-view>  
  </div>
</template>
<script>
export default {
	mounted() {		
//		var vm = this
//		$(window).resize(function() {
//			if(vm.$route.name === 'login') {
//				var winWidth = window.innerWidth;
//				document.documentElement.style.fontSize = (winWidth / 1920) * 100 + 'px';
//			}
//		})
	},
	watch:{
	    $route(to,from){
	        if (!to.matched.length) {
	            this.$router.push('/404');
	        }
	    }
    }
}
</script>
<style lang="sass">
	.page-app{
		height: 100%;
	}
</style>
